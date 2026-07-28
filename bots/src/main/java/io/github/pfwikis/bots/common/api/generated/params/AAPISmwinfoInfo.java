package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<span class="apihelp-empty">(no description)</span>*/
@Getter
@RequiredArgsConstructor
public enum AAPISmwinfoInfo {
	CONCEPTCOUNT("conceptcount"),

	DECLAREDPROPCOUNT("declaredpropcount"),

	DELETECOUNT("deletecount"),

	ERRORCOUNT("errorcount"),

	FORMATCOUNT("formatcount"),

	JOBCOUNT("jobcount"),

	PROPCOUNT("propcount"),

	PROPPAGECOUNT("proppagecount"),

	QUERYCOUNT("querycount"),

	QUERYSIZE("querysize"),

	SUBOBJECTCOUNT("subobjectcount"),

	TOTALPROPCOUNT("totalpropcount"),

	USEDPROPCOUNT("usedpropcount");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPISmwinfoInfo> set = EnumSet.noneOf(AAPISmwinfoInfo.class);

		public AAPISmwinfoInfo[] build() {
			return set.toArray(AAPISmwinfoInfo[]::new);
		}

		public Builder CONCEPTCOUNT() {
			set.add(CONCEPTCOUNT);
			return this;
		}

		public Builder DECLAREDPROPCOUNT() {
			set.add(DECLAREDPROPCOUNT);
			return this;
		}

		public Builder DELETECOUNT() {
			set.add(DELETECOUNT);
			return this;
		}

		public Builder ERRORCOUNT() {
			set.add(ERRORCOUNT);
			return this;
		}

		public Builder FORMATCOUNT() {
			set.add(FORMATCOUNT);
			return this;
		}

		public Builder JOBCOUNT() {
			set.add(JOBCOUNT);
			return this;
		}

		public Builder PROPCOUNT() {
			set.add(PROPCOUNT);
			return this;
		}

		public Builder PROPPAGECOUNT() {
			set.add(PROPPAGECOUNT);
			return this;
		}

		public Builder QUERYCOUNT() {
			set.add(QUERYCOUNT);
			return this;
		}

		public Builder QUERYSIZE() {
			set.add(QUERYSIZE);
			return this;
		}

		public Builder SUBOBJECTCOUNT() {
			set.add(SUBOBJECTCOUNT);
			return this;
		}

		public Builder TOTALPROPCOUNT() {
			set.add(TOTALPROPCOUNT);
			return this;
		}

		public Builder USEDPROPCOUNT() {
			set.add(USEDPROPCOUNT);
			return this;
		}
	}
}
