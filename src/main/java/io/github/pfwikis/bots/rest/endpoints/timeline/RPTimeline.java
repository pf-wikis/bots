package io.github.pfwikis.bots.rest.endpoints.timeline;

import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import io.github.pfwikis.bots.common.model.SemanticAsk.WikiDate;
import io.github.pfwikis.bots.facts.model.SFactTypes;
import io.github.pfwikis.bots.rest.RPEndpoint;
import io.github.pfwikis.bots.rest.RestProviderBot;
import io.github.pfwikis.bots.utils.Jackson;

public class RPTimeline extends RPEndpoint<RPTimelineParam> {

	public RPTimeline() {
		super(RPTimelineParam.class, "timeline");
	}

	@Override
	public RPResult handle(RestProviderBot bot, RPTimelineParam param) throws Exception {
		if(!param.validate()) {
			return error(null, "Invalid arguments to infobox");
		}
		var query = new StringBuilder("[[Event date::+]][[Fact type::Template:Event]]");
		for(var p:StringUtils.split(param.getKeyword(), ';')) {
			query
				.append("[[Event keyword::")
				.append(p.trim())
				.append("]]");
		}
		query.append("""
			|?Name=name
			|?Event date#ISO=date
			|?Event end date#ISO=endDate
			|?Event description=description
			|?Event source=source
		""");
		
		var events = bot.getRun().getWiki().semanticAsk(Printouts.class, query.toString(), 500);
			
		if(events.isEmpty()) return error(null, "No events with the given keyword.");
		
		var tlEvents = events.stream()
			.map(e->new TimelineEvent(
				TimelineDate.from(e.getPrintouts().date()),
				TimelineDate.from(e.getPrintouts().endDate()),
				new TimelineText(
					e.getPrintouts().name(),
					"""
					<h4 class="timeline-header">[[%s|%s]]<ref>%s</ref></h4>
					<div class="timeline-dates">%s</div>
					<div class="timeline-description">%s</div>
					""".formatted(
						e.toPage(),
						e.getPrintouts().name(),
						e.getPrintouts().source(),
						formatDate(e.getPrintouts().date().getRaw())
							+ (e.getPrintouts().endDate()!=null
								?(" – "+formatDate(e.getPrintouts().endDate().getRaw()))
								:""
							),
						Optional.ofNullable(e.getPrintouts().description()).orElse("")
					).trim()
				)
			))
			.toList();
		
		var id = "timeline-"+UUID.randomUUID().toString();
		return RPResult.builder()
			.block(new RPBlock(RPBlockType.HTML, "<div id=\"%s\" class=\"timeline\"></div>".formatted(id)))
			.dependsOn(events.stream().map(e->e.toPage()).distinct().toList())
			.data(Map.of("id", id, "events", tlEvents))
			.build();
	}
	
	private String formatDate(Temporal t) {
		var sb = new StringBuilder();
		
		
		
		if(t.isSupported(ChronoField.DAY_OF_WEEK)) {
			sb
				.append("[[")
				.append(
					switch(t.get(ChronoField.DAY_OF_WEEK)) {
						case 1 -> "Moonday";
						case 2 -> "Toilday";
						case 3 -> "Wealday";
						case 4 -> "Oathday";
						case 5 -> "Fireday";
						case 6 -> "Starday";
						case 7 -> "Sunday";
						default -> throw new IllegalArgumentException("Day of week "+t.get(ChronoField.DAY_OF_WEEK));
					}
				)
				.append("]], ");
		}
		
		if(t.isSupported(ChronoField.MONTH_OF_YEAR)) {
			sb
			.append("[[")
			.append(
				switch(t.get(ChronoField.MONTH_OF_YEAR)) {
					case  1 -> "Abadius";
					case  2 -> "Calistril";
					case  3 -> "Pharast";
					case  4 -> "Gozran";
					case  5 -> "Desnus";
					case  6 -> "Sarenith";
					case  7 -> "Erastus";
					case  8 -> "Arodus";
					case  9 -> "Rova";
					case 10 -> "Lamashan";
					case 11 -> "Neth";
					case 12 -> "Kuthona";
					default -> throw new IllegalArgumentException("Month of year "+t.get(ChronoField.MONTH_OF_YEAR));
				}
			)
			.append("]]");
		}
		
		if(t.isSupported(ChronoField.DAY_OF_MONTH)) {
			sb
			.append(" ")
			.append("%02d".formatted(t.get(ChronoField.DAY_OF_MONTH)));
		}
		if(!sb.isEmpty())
			sb.append(", ");
		sb
			.append("[[")
			.append(t.get(ChronoField.YEAR))
			.append(" AR]]");

		return "<time datetime=\""+t.toString()+"\">"+sb.toString()+"</time>";
	}

	private static record TimelineDate(
		Integer year,
		Integer month,
		Integer day,
		Integer hour,
		Integer minute
	) {

		public static TimelineDate from(WikiDate date) {
			if(date == null || date.getRaw() == null)
				return null;
			var r = date.getRaw();
			return new TimelineDate(
				r.get(ChronoField.YEAR),
				r.isSupported(ChronoField.MONTH_OF_YEAR)?r.get(ChronoField.MONTH_OF_YEAR):null,
				r.isSupported(ChronoField.DAY_OF_MONTH)?r.get(ChronoField.DAY_OF_MONTH):null,
				r.isSupported(ChronoField.HOUR_OF_DAY)?r.get(ChronoField.HOUR_OF_DAY):null,
				r.isSupported(ChronoField.MINUTE_OF_HOUR)?r.get(ChronoField.MINUTE_OF_HOUR):null
			);
		}
	}
	private static record TimelineText(
		String headline,
		String text
	) {}
	private static record TimelineEvent(
		TimelineDate start_date,
		TimelineDate end_date,
		TimelineText text
	) {}
	
	private static record Printouts(String name, WikiDate date, WikiDate endDate, String description, String source) {}
}
