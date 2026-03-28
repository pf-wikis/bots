package io.github.pfwikis.bots.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fizzed.rocker.model.PlainText;
import com.fizzed.rocker.model.PostProcessorException;
import com.fizzed.rocker.model.TemplateModel;
import com.fizzed.rocker.model.TemplateModelPostProcessor;
import com.fizzed.rocker.model.TemplateUnit;

public class RockerPostProcessor implements TemplateModelPostProcessor {

	final static Logger log = LoggerFactory.getLogger(RockerPostProcessor.class);
	
	@Override
	public TemplateModel process(TemplateModel templateModel, int ppIndex) throws PostProcessorException {
		templateModel.getUnits().replaceAll(this::handle);
		templateModel.getUnits().removeIf(tu -> tu instanceof PlainText pt && pt.getText().isEmpty());
		
		return templateModel;
	}

	private TemplateUnit handle(TemplateUnit tu) {
		if (tu instanceof PlainText pt) {
			var txt = pt.getText();
			txt = txt.replaceAll("(?m)^\t+", "")
				.replace("\n", "")
				.replaceAll("  +", " ")
				.replace("§§§n§§§", "\n");
			return new PlainText(pt.getSourceRef(), txt);
		}
		return tu;
	}
}
