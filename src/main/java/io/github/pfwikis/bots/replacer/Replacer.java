package io.github.pfwikis.bots.replacer;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.api.model.PageRef;
import io.github.pfwikis.bots.common.api.model.PageTitle;
import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class Replacer extends SimpleBot {

	public Replacer() {
		super("replacer", "Manual Bulk Operations");
	}
	
	@Override
	public String getDescription() {
		return "This bot is only started by hand for manual bulk changes to the wiki.";
	}
	
	
	@Override
	public void run(RunContext ctx) throws IOException {
		var prices = run.getWiki().getWikitext(PageRef.of("Template:Paizo store/price"));
		var bookFacts = run.getWiki().semanticAsk(Book.class,
				"[[Fact type::Template:Facts/Book]]|?Website=website|?Pubcode=pubcode");
		//var books = List.of(PageRef.of("Facts:A Bitter Bargain"));
		log.info("Still {} book TODO", bookFacts.size());
		for(var b:bookFacts) {
			log.info("Processing {}", b.getPage());
			
			String code = StringUtils.trimToNull(b.getPrintouts().pubcode);
			String web = StringUtils.trimToNull(b.getPrintouts().website);
			if(b.getPrintouts().pubcode==null && b.getPrintouts().website==null) continue;
			
			var txt = run.getWiki().getWikitext(b.getPage());
			var ntxt =txt.replaceAll("\n( *\\| *(Website|Pubcode) *=.*)", "");
			if(txt.equals(ntxt)) continue;
			
			var relPage = PageTitle.of(b.getPage().getTitle().withoutHash().toString()+"/Releases");
			if(!run.getWiki().exists(relPage)) continue;
			var rtxt = run.getWiki().getWikitext(relPage);
			var ind = rtxt.lastIndexOf("}}");
			String rntxt = rtxt.substring(0, ind)
					+ (code==null?"":"|TODO Pubcode="+code+"\n")
					+ ((web==null && !priceExists(prices, code))?"":"|TODO Website="+web+"\n")
					+"}}"+rtxt.substring(ind+2);
			
			run.getWiki().edit(b.getPage(), ntxt, "Move undecipherable pubcode to releases");
			run.getWiki().edit(relPage, rntxt, "Move undecipherable pubcode to releases");
		}
	}
	
	private boolean priceExists(String prices, String code) {
		return Pattern.compile("\\|"+Pattern.quote(code)+"[\\|=]").matcher(prices).find();
	}
	
	public static record Book(String website, String pubcode) {}
	public static record Release(String price, String pubcode, String releaseType, String note) {}
}
