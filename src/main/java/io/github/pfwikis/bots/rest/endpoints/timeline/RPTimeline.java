package io.github.pfwikis.bots.rest.endpoints.timeline;

import java.time.temporal.ChronoField;
import java.util.Optional;
import java.util.UUID;

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
		var events = bot.getRun().getWiki().semanticAsk(Printouts.class, "[[Event date::+]][[Fact type::Template:Event]][[Event keyword::"+param.getKeyword()+"]]"
				+"""
				|?Name=name
				|?Event date#ISO=date
				|?Event end date#ISO=endDate
				|?Event description=description
				|?Event source=source
				""", 500);
			
		if(events.isEmpty()) return error(null, "No events with the given keyword.");
		
		var tlEvents = events.stream()
			.map(e->new TimelineEvent(
				TimelineDate.from(e.getPrintouts().date()),
				TimelineDate.from(e.getPrintouts().endDate()),
				new TimelineText(
					e.getPrintouts().name(),
					"""
					<div class="timeline-text">
						<h4 class="timeline-header">[[%s|%s]]</h4>
						<div class="timeline-dates">%s</div>
						<div class="timeline-description">%s</div>
					</div>
					""".formatted(
						e.toPage(),
						e.getPrintouts().name(),
						SFactTypes.DATE.toInfoboxDisplay(null, e.getPrintouts().date().getRaw())
							+ (e.getPrintouts().endDate()!=null
								?("–"+SFactTypes.DATE.toInfoboxDisplay(null, e.getPrintouts().endDate().getRaw()))
								:""
							),
						Optional.ofNullable(e.getPrintouts().description()).orElse("")
					)
				)
			))
			.toList();
		

		var json = Jackson.JSON.writeValueAsString(tlEvents);
		var id = "timeline-"+UUID.randomUUID().toString();
		var script = """
		<script type="text/javascript">
			if (!window.hasOwnProperty('modernTimeline')) {window.modernTimeline = {};}
			window.modernTimeline["%s"] = {
				events: %s,
				options: {
					duration: 0
				}
			};
		</script>
		""".formatted(id, json);
		
		return RPResult.builder()
			.block(new RPBlock(RPBlockType.HTML, "<div id=\"%s\" class=\"timeline\" style=\"height:500px\"></div>".formatted(id)))
			.dependsOn(events.stream().map(e->e.toPage()).distinct().toList())
			.headItem(script)
			.build();
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
