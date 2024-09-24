package io.github.pfwikis.bots.replacer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.facts.SModel;
import io.github.pfwikis.bots.utils.SimpleCache.CacheId;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class Replacer extends SimpleBot {

	public Replacer() {
		super("replacer", "Bot Manual Bulk Operations");
	}
	
	@Override
	public String getDescription() {
		return "This bot is only started by hand for manual bulk changes to the wiki.";
	}

	@Override
	public void run(RunContext ctx) throws IOException {	
		//replaceWrongCitations();
		
		//replaceCitations();
		
		//removeOldCiteBooks();
		
		/*
		for(int i=2005;i<=2026;i++) {
			var p = run.getServer().getName()+" release calendar/"+i;
			if(run.getWiki().pageExists(p))
				run.getWiki().delete(p, "Remove outdated page");
		}*/
		
		var pages = new ArrayList<>(run.getWiki().getPagesTranscluding("Template:Infobox"));
		int co = 0;
		Collections.shuffle(pages);
		for(var p:pages) {
			
			var txt = run.getWiki().getPageText(p.getTitle());
			
			var ntxt = txt.replaceAll("Infobox *\\| *("
					+Arrays.stream(SModel.CONCEPTS).map(c->c.getName()).collect(Collectors.joining("|"))
					+")", "Infobox");
			if(!ntxt.equals(txt)) {
				run.getWiki().edit(
					p.getTitle(),
					ntxt,
					"Simplify infobox usage"
				);
			}
			log.info("{}/{}",co++,pages.size());
		}

		
		/*for(var p:run.getWiki().getPagesTranscluding("Template:Tlx")) {
			var txt = run.getWiki().getPageText(p.getTitle());
			var nTxt = txt.replaceAll("(?i)\\{\\{ *tlx *\\|", "{{tl|");
			if(!txt.equals(nTxt)) {
				run.getWiki().edit(p.getTitle(), nTxt, "Replace tlx with tl template");
			}
		}
		/*
		for(var p:run.getWiki().getPagesTranscluding("Template:Infobox/Book")) {
			if(p.getTitle().contains(":")) continue;
			var txt = run.getWiki().getPageText(p.getTitle());
			var nTxt = txt.replace("Infobox/Book", "Infobox|Book");
			if(!txt.equals(nTxt)) {
				run.getWiki().edit(p.getTitle(), nTxt, "Change style of calling semantic infobox");
			}
		}*/

		/*if(run.isStarfinder())
			return;*/
		/*var pages = run.getWiki().getPagesInNamespace("Property");
		for(var p:pages) {
			var text = run.getWiki().getPageText(p.getTitle());
			if(text.contains("{{Property")) continue;
			var newText = text.replaceAll(
					"\\* *\\[\\[(.*?)::(.*?)\\]\\]",
					"|$1=$2"
				).replaceFirst("^\\|", "{{Property\n|")
				.replaceAll("\\[\\[Category:", "}}\n[[Category:");
			if(!newText.equals(text)) {
				run.getWiki().edit(p.getTitle(), newText, "Use new Property template");
			}
			else {
				log.warn("No luck on {}", p.getTitle());
			}
		}*/
		
		/*
		var todo = Maps.newHashMap(
			"Positive Energy Plane", "Creation's Forge",
			"Positive Energy Plane/Inhabitants", "Creation's Forge/Inhabitants",
			"Positive Energy Plane/Locations", "Creation's Forge/Locations",
			"Positive Energy Plane/Nations", "Creation's Forge/Nations",
			"Positive Energy Plane/Settlements", "Creation's Forge/Settlements",
			"Negative Energy Plane", "Void",
			"Negative Energy Plane/Inhabitants", "Void/Inhabitants",
			"Negative Energy Plane/Locations", "Void/Locations",
			"Negative Energy Plane/Settlements", "Void/Settlements",
			"Shadow Plane", "Netherworld",
			"Shadow Plane/Inhabitants", "Netherworld/Inhabitants",
			"Shadow Plane/Locations", "Netherworld/Locations",
			"Shadow Plane/Nations", "Netherworld/Nations",
			"Shadow Plane/Organizations", "Netherworld/Organizations",
			"Shadow Plane/Settlements", "Netherworld/Settlements",
			"Material Plane", "Universe"
		);

		//rename categories
		for(var e : todo.entrySet()) {
			for(var p:run.getWiki().getPagesInCategory("Category:"+e.getKey())) {
				var text = run.getWiki().getPageText(p.getTitle());
				var newText = text.replaceAll("\\[\\[Category: *"+e.getKey()+" *((\\|[^\\]]*)?\\]\\])", "[[Category:"+e.getValue()+"$1");
				if(!newText.equals(text)) {
					run.getWiki().edit(p.getTitle(), newText, "Renamed category "+e.getKey()+" to "+e.getValue());
				}
				else {
					log.warn("No luck on {}", p.getTitle());
				}
			}
		}
		for(var e : todo.entrySet()) {
			if(run.getWiki().pageExists("Category:"+e.getKey()))
				run.getWiki().rename("Category:"+e.getKey(), "Category:"+e.getValue(), false, "Renamed category "+e.getKey()+" to "+e.getValue());
		}
		
		//search and replace
		for(var e : todo.entrySet()) {
			for(var p:run.getWiki().getPagesInCategory("Category:"+e.getKey())) {
				if(!p.getTitle().startsWith("File:")) continue;
				
				var text = run.getWiki().getPageText(p.getTitle());
				var newText = text.replaceAll("(\\| *keyword\\d+ *= *)(half-elves)(\\s*[\\|\n])", "$1aiuvarins$3")
						          .replaceAll("(\\| *keyword\\d+ *= *)(half-orcs)(\\s*[\\|\n])", "$1dromaars$3");
				if(!newText.equals(text)) {
					run.getWiki().edit(p.getTitle(), newText, "Renamed category "+e.getKey()+" to "+e.getValue());
				}
				else {
					log.warn("No luck on {}", p.getTitle());
				}
			}
		}*/
	}

	private void removeOldCiteBooks() {
		var subPages = run.getWiki().getAllSubPages("Template", "Cite book");
		for(var p:subPages) {
			if(run.getWiki().getPagesTranscluding(p).isEmpty()) {
				run.getWiki().delete(p, "No longer needed, superseded by Cite template");
			}
		}
	}

	private void replaceCitations() {
		var pages = run.getWiki().getPagesTranscluding("Template:Cite book");
		for(var p:pages) {
			if(p.getTitle().contains(":") && !p.getTitle().startsWith("Talk:")) continue;
			String title = p.getTitle();
			replaceCitations(title);
		}
	}

	
	private void replaceCitations(String title) {
		var txt = run.getWiki().getPageText(title);
		var nTxt = replaceNamed(txt);
		nTxt = replaceSimpleCases(nTxt);
		
		if(!nTxt.equals(txt)) {
			run.getWiki().edit(
				title,
				nTxt,
				"Replace references"
			);
		}
	}

	private String replaceSimpleCases(String txt) {
		var nTxt = replace(txt, "<ref *> *\\{\\{ *[Cc]ite book/(?<template>[^\\|\\}]+?) *\\|? *\\}\\} *</ref>");
		nTxt = replace(nTxt, "<ref *> *\\{\\{ *[Cc]ite book/(?<template>[^\\|\\}]+?) *\\| *(?<l1>\\d+) *\\}\\} *</ref>");
		nTxt = replace(nTxt, "<ref *> *\\{\\{ *[Cc]ite book/(?<template>[^\\|\\}]+?) *\\| *(?<l1>inside back cover) *\\}\\} *</ref>");
		nTxt = replace(nTxt, "<ref *> *\\{\\{ *[Cc]ite book/(?<template>[^\\|\\}]+?) *\\| *(?<showas>(?<l1>\\\\d+)'?'?ff\\.?'?'?)\\}\\} *</ref>");
		nTxt = replace(nTxt, "<ref *> *\\{\\{ *[Cc]ite book/(?<template>[^\\|\\}]+?) *\\| *(?<l1>\\d+) *\\| *(?<showas>\\d+'?'?ff\\.?'?'?)\\}\\} *</ref>");
		nTxt = replace(nTxt, "<ref *> *\\{\\{ *[Cc]ite book/(?<template>[^\\|\\}]+?) *\\| *(?<l1>\\d+)(\\-|&ndash;)(?<l2>\\d+) *\\}\\} *</ref>");
		nTxt = replace(nTxt, "<ref *> *\\{\\{ *[Cc]ite book/(?<template>[^\\|\\}]+?) *\\| *(\\d+) *\\| *(?<l1>\\d+)(\\-|&ndash;|\\–)(?<l2>\\d+) *\\}\\} *</ref>");
		nTxt = replaceInsideCover(nTxt, "(?i)<ref *> *\\{\\{ *Cite book/(?<template>[^\\|\\}]+?) *\\| *(?<l>inside +(front|back) +cover|rear +inside +cover|inside +cover|front +inside +cover|ifc) *\\}\\} *</ref>");
		nTxt = replaceFollowing(nTxt, "<ref *> *\\{\\{ *[Cc]ite book/(?<template>[^\\|\\}]+?) *\\| *(?<l1>\\d+)'?'?f'?'?\\}\\} *</ref>");
		nTxt = replaceFollowing2(nTxt, "<ref *> *\\{\\{ *[Cc]ite book/(?<template>[^\\|\\}]+?) *\\| *(?<l1>\\d+) *\\| *(?<l2>\\d+)'?'?f'?'?\\}\\} *</ref>");
		nTxt = replaceMultiples(nTxt, "<ref *> *\\{\\{ *[Cc]ite book/(?<template>[^\\|\\}]+?) *\\| *(\\d+) *\\| *(?<l>\\d+((,|and) *\\d+)+)\\}\\} *</ref>");
		nTxt = replaceMultiples(nTxt, "<ref *> *\\{\\{ *[Cc]ite book/(?<template>[^\\|\\}]+?) *\\| *(?<l>\\d+((,|and) *\\d+)+)\\}\\} *</ref>");
		nTxt = replaceNote(nTxt, "<ref *> *(?<cite> *\\{\\{ *[Cc]ite book/[^\\}]+\\}\\}) *(?<note>[^<]+?) *</ref>");
		return nTxt;
	}


	private static Pattern ID_REF = Pattern.compile("<ref +name *= *(\"(?<id>[^\"]+)\"|(?<id2>[^ >]+?)) *(/>|> *(?<value>\\{\\{[Cc]ite book/.*?) *</ref>)");
	private String replaceNamed(String txt) {
		Map<String, String> refs = new HashMap<>();
		ID_REF.matcher(txt).results().forEach(mr-> {
			var id = Optional.ofNullable(mr.group("id")).orElse(mr.group("id2")).trim();
			var value = Optional.ofNullable(mr.group("value")).orElse("").trim();
			refs.merge(id, value, (a,b)->a.length()>b.length()?a:b);
		});
		refs.entrySet().removeIf(e->e.getValue().isEmpty());
		
		return ID_REF.matcher(txt).replaceAll(mr-> {
			var id = Optional.ofNullable(mr.group("id")).orElse(mr.group("id2")).trim();
			if(!refs.containsKey(id))
				return mr.group();
			
			var clean = "<ref>"+refs.get(id)+"</ref>";
			var replaced = replaceSimpleCases(clean);
			if(!replaced.equals(clean)) {
				return replaced;
			}
			else return mr.group();
		});
	}

	private String replaceInsideCover(String txt, String pattern) {
		var m = Pattern.compile(pattern).matcher(txt);
		return m.replaceAll(mr -> {
			var template = mr.group("template");
			var l = mr.group("l").trim().replaceAll(" +", " ").toLowerCase();
			l = switch(l) {
				case "inside cover", "ifc", "front inside cover"->"inside front cover"; 
				case "rear inside cover"->"inside back cover";
				case "inside back cover", "inside front cover" -> l;
				default -> throw new IllegalStateException("Unhandled inside '"+l+"'");
			};
			var clean = "<ref>{{Cite book/"+template+"}}</ref>";
			var replaced = replaceSimpleCases(clean);
			if(!replaced.equals(clean)) {
				return replaced.replace("}}", "|"+l+"}}");
			}
			else return mr.group();
		});
	}
	
	private String replaceFollowing(String txt, String pattern) {
		var m = Pattern.compile(pattern).matcher(txt);
		return m.replaceAll(mr -> {
			var template = mr.group("template");
			var l1 = mr.group("l1").trim();
			var clean = "<ref>{{Cite book/"+template+"|"+l1+"-"+Integer.toString(Integer.parseInt(l1)+1)+"}}</ref>";
			var replaced = replaceSimpleCases(clean);
			if(!replaced.equals(clean)) {
				return replaced;
			}
			else return mr.group();
		});
	}
	
	private String replaceFollowing2(String txt, String pattern) {
		var m = Pattern.compile(pattern).matcher(txt);
		return m.replaceAll(mr -> {
			var template = mr.group("template");
			var l1 = mr.group("l1").trim();
			var l2 = mr.group("l2").trim();
			if(!l1.equals(l2))
				return mr.group();
			var clean = "<ref>{{Cite book/"+template+"|"+l1+"-"+Integer.toString(Integer.parseInt(l1)+1)+"}}</ref>";
			var replaced = replaceSimpleCases(clean);
			if(!replaced.equals(clean)) {
				return replaced;
			}
			else return mr.group();
		});
	}
	
	private String replaceMultiples(String txt, String pattern) {
		var m = Pattern.compile(pattern).matcher(txt);
		return m.replaceAll(mr -> {
			var template = mr.group("template");
			var l = Arrays.stream(mr.group("l").trim().split("\\D+")).map(Integer::parseInt).toList();
			var clean = "<ref>{{Cite book/"+template+"}}</ref>";
			var replaced = replaceSimpleCases(clean);
			if(!replaced.equals(clean)) {
				return l.stream()
					.map(v->replaced.replace("}}", "|"+v+"}}"))
					.collect(Collectors.joining());
			}
			else return mr.group();
		});
	}
	
	private String replaceNote(String txt, String pattern) {
		var m = Pattern.compile(pattern).matcher(txt);
		return m.replaceAll(mr -> {
			var cite = mr.group("cite").trim();
			var note = mr.group("note").trim();
			var clean = "<ref>"+cite+"</ref>";
			var replaced = replaceSimpleCases(clean);
			if(!replaced.equals(clean)) {
				note = StringUtils.removeStart(note, ".").trim();
				return replaced.replace("}}", "|note="+note+"}}");
			}
			else return mr.group();
		});
	}

	private String replace(String txt, String pattern) {
		var m = Pattern.compile(pattern).matcher(txt);
		return m.replaceAll(mr -> {
			var template = mr.group("template");
			var l1 = mr.namedGroups().containsKey("l1")?mr.group("l1"):null;
			var l2 = mr.namedGroups().containsKey("l2")?mr.group("l2"):null;
			var showAs = mr.namedGroups().containsKey("showas")?mr.group("showas"):null;
			
			var tTemplate = run.cache(CacheId.REPLACER_RESOLVE, template, ()->this.resolve(template));
			if(tTemplate == null) {
				log.error("Can't resolve template '{}'", template);
				return m.group();
			}
			
			var sb = new StringBuilder("{{Ref|").append(tTemplate);
			if(l1!=null) sb.append("|").append(l1);
			if(l2!=null) sb.append("|").append(l2);
			if(showAs!=null) sb.append("|show as=").append(showAs);
			sb.append("}}");
			return sb.toString();
		});
	}

	private String resolve(String template) {
		if(run.getWiki().pageExists("Template:Cite/"+template)) {
			return template;
		}
		//test after resolving redirect
		template = StringUtils.removeStart(run.getWiki().resolveRedirects("Template:Cite book/"+template), "Template:Cite book/");
		if(run.getWiki().pageExists("Template:Cite/"+template)) {
			return template;
		}
		
		//get real name from cite template
		var txt = run.getWiki().getPageText("Template:Cite book/"+template);
		var m=Pattern.compile("\\| *title *= *\\[\\[ *([^\\|\\]]+)").matcher(txt);
		if(m.find()) {
			var realName = m.group(1);
			realName = run.getWiki().resolveRedirects(realName);
			if(run.getWiki().pageExists("Template:Cite/"+realName)) {
				return realName;
			}
		}
		
		return null;
	}

	private void replaceWrongCitations() {
		for(var wanted : run.getWiki().getWantedTemplates()) {
			if(!wanted.startsWith("Template:Cite/")) continue;
			
			var template = wanted.substring(14);
			var txt = run.getWiki().getPageText("Template:Cite book/"+template);
			var m=Pattern.compile("\\| *title *= *\\[\\[ *([^\\|\\]]+)")
				.matcher(txt);
			if(m.find()) {
				var realName = m.group(1);
				realName = run.getWiki().resolveRedirects(realName);
				if(realName.equals(template))
					continue;
				
				var pages = run.getWiki().getPagesTranscluding(wanted);
				for(var p:pages) {
					var articleTxt = run.getWiki().getPageText(p.getTitle());
					var nTxt = articleTxt
						.replaceAll(Pattern.quote("{{Ref|"+template)+"(\\||\\})", "{{Ref|"+realName+"$1");
					
					if(!nTxt.equals(articleTxt)) {
						run.getWiki().edit(
							p.getTitle(),
							nTxt,
							"Replace references with correct name.");
					}
					else {
						log.error("What?");
					}
				}
			}
			else {
				log.error("No title in {} txt:\n{}", wanted, txt);
			}
		}
	}
	
	

}
