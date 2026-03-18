package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Defines the task type*/
@Getter
@RequiredArgsConstructor
public enum AAPISmwtaskTask {
	CHECK_QUERY("check-query"),

	DUPLICATE_LOOKUP("duplicate-lookup"),

	INSERT_JOB("insert-job"),

	RUN_ENTITY_EXAMINER("run-entity-examiner"),

	RUN_JOBLIST("run-joblist"),

	TABLE_STATISTICS("table-statistics"),

	UPDATE("update");

	private final String jsonValue;
}
