package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Limit to protected pages only.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryAllpagesPrtype {
	EDIT("edit"),

	MOVE("move"),

	UPLOAD("upload");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryAllpagesPrtype> set =
				EnumSet.noneOf(AAPIQueryAllpagesPrtype.class);

		public AAPIQueryAllpagesPrtype[] build() {
			return set.toArray(AAPIQueryAllpagesPrtype[]::new);
		}

		public Builder EDIT() {
			set.add(EDIT);
			return this;
		}

		public Builder MOVE() {
			set.add(MOVE);
			return this;
		}

		public Builder UPLOAD() {
			set.add(UPLOAD);
			return this;
		}
	}
}
