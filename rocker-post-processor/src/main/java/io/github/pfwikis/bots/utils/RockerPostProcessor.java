package io.github.pfwikis.bots.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fizzed.rocker.compiler.RockerConfiguration;
import com.fizzed.rocker.compiler.TemplateParser;
import com.fizzed.rocker.model.PlainText;
import com.fizzed.rocker.model.PostProcessorException;
import com.fizzed.rocker.model.TemplateModel;
import com.fizzed.rocker.model.TemplateModelPostProcessor;
import com.fizzed.rocker.model.TemplateUnit;
import com.fizzed.rocker.model.ValueExpression;

public class RockerPostProcessor implements TemplateModelPostProcessor {

	final static Logger log = LoggerFactory.getLogger(RockerPostProcessor.class);
	
	@Override
	public TemplateModel process(TemplateModel templateModel, int ppIndex) throws PostProcessorException {
		templateModel.getUnits().replaceAll(this::handle);
		templateModel.getUnits().removeIf(tu -> tu instanceof PlainText pt && pt.getText().isEmpty());
		new TemplateParser(new RockerConfiguration()).combineAdjacentPlain(templateModel);
		
		return templateModel;
	}

	private TemplateUnit handle(TemplateUnit tu) {
		if (tu instanceof PlainText pt) {
			var txt = pt.getText();
			txt = txt.replaceAll("(?m)^\t+", "")
				.replace("\n", "")
				.replaceAll("  +", " ");
			return new PlainText(pt.getSourceRef(), txt);
		}
		if(tu instanceof ValueExpression ve) {
			if(ve.getExpression().equals("\\n") || ve.getExpression().equals("(\\n)")) {
				return new PlainText(ve.getSourceRef(), "\n");
			}
		}
		return tu;
	}
}
