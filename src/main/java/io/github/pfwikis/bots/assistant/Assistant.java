package io.github.pfwikis.bots.assistant;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Objects;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.common.model.Page;
import io.github.pfwikis.bots.utils.Jackson;

@Parameters
public class Assistant extends SimpleBot {

	public Assistant() {
		super("assistant", "Bot Assistant");
	}
	
	@Override
	protected String getDescription() {
		return """
		This bot executes the tasks given to it via the [https://github.com/pf-wikis/bots/issues/new/choose tasks page].
		It is meant to start automized tasks with manually given parameters.
		The tasks it understands are:
		
		===replaceImage===
		This task is used to replace images with better version, that have a different extension.
		This job uploads a new image with the same name as the given old image, but a new extension. It copies the description
		from the original file. It then replaces every usage of the original file with the new file and then deletes the original
		image.
		""";
	}

	@Override
	public void run() throws Exception {
		//check if task is in environment variable
		String env = System.getenv("ISSUE_TEMPLATE");
		if(!StringUtils.isBlank(env)) {
			var task = Jackson.JSON.readValue(env, Task.class);
			if((run.isStarfinder()?"Starfinderwiki":"Pathfinderwiki").equals(task.getWiki()))
				execute(task);
		}
	}

	private void execute(Task task) throws IOException {
		if(task.getTask() == TaskType.replaceImage) {
			Objects.requireNonNull(task.getImage());
			Objects.requireNonNull(task.getReplaceWith());
			String oldExt = FilenameUtils.getExtension(task.getImage());
			String newExt = FilenameUtils.getExtension(task.getReplaceWith());
			var oldFile = StringUtils.prependIfMissing(task.getImage(), "File:");
			if(oldExt.equals(newExt) || !run.getWiki().pageExists(oldFile)) {
				throw new IllegalStateException("Invalid inputs");
			}
			
			
			var description = run.getWiki().getPageText(oldFile);
			var name = oldFile.substring(0, oldFile.lastIndexOf('.'))+"."+newExt;
			
			if(!run.getWiki().pageExists(name)) {
				var tmp = File.createTempFile("upload_", "."+newExt);
				FileUtils.copyInputStreamToFile(URI.create(task.getReplaceWith()).toURL().openStream(), tmp);
				if(!run.getWiki().upload(
					tmp.toPath(),
					name,
					description,
					"Higher resolution version of "+task.getImage()
				)) {
					throw new IllegalStateException();
				}
				tmp.delete();
			}
			
			for(Page p:run.getWiki().getImageUsage(oldFile)) {
				var oldTxt = run.getWiki().getPageText(p.getTitle());
				var nameMatcher = StringUtils.removeStart(oldFile.replaceAll("[ _]", "[ _]").replace(".", "\\."), "File:");
				var txt = oldTxt.replaceAll(nameMatcher, StringUtils.removeStart(name.replace('_', ' '), "File:"));
				if(!oldTxt.equals(txt)) {
					run.getWiki().edit(p.getTitle(), txt, "Replace image with new version");
				}
			}
			run.getWiki().delete(oldFile, "Replaced with "+name);
			discord.report("I replaced "+discord.wikiLink(oldFile, "/wiki/"+oldFile)+" with "+discord.wikiLink(name, "/wiki/"+name));
		}
	}
}
