package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Filter contributions that have these tags.*/
@Getter
@RequiredArgsConstructor
public enum AAPIFeedcontributionsTagfilter {
	NUKE("Nuke"),

	DISCUSSIONTOOLS("discussiontools"),

	DISCUSSIONTOOLS_ADDED_COMMENT("discussiontools-added-comment"),

	DISCUSSIONTOOLS_EDIT("discussiontools-edit"),

	DISCUSSIONTOOLS_NEWTOPIC("discussiontools-newtopic"),

	DISCUSSIONTOOLS_REPLY("discussiontools-reply"),

	DISCUSSIONTOOLS_SOURCE("discussiontools-source"),

	DISCUSSIONTOOLS_SOURCE_ENHANCED("discussiontools-source-enhanced"),

	DISCUSSIONTOOLS_VISUAL("discussiontools-visual"),

	EDITCHECK_NEWCONTENT("editcheck-newcontent"),

	EDITCHECK_NEWREFERENCE("editcheck-newreference"),

	EDITCHECK_REFERENCE_DECLINE_COMMON_KNOWLEDGE("editcheck-reference-decline-common-knowledge"),

	EDITCHECK_REFERENCE_DECLINE_IRRELEVANT("editcheck-reference-decline-irrelevant"),

	EDITCHECK_REFERENCE_DECLINE_OTHER("editcheck-reference-decline-other"),

	EDITCHECK_REFERENCE_DECLINE_UNCERTAIN("editcheck-reference-decline-uncertain"),

	EDITCHECK_REFERENCES("editcheck-references"),

	EDITCHECK_REFERENCES_ACTIVATED("editcheck-references-activated"),

	MAPS_VISUAL_EDIT("maps-visual-edit"),

	MW_BLANK("mw-blank"),

	MW_CHANGED_REDIRECT_TARGET("mw-changed-redirect-target"),

	MW_CONTENTMODELCHANGE("mw-contentmodelchange"),

	MW_MANUAL_REVERT("mw-manual-revert"),

	MW_NEW_REDIRECT("mw-new-redirect"),

	MW_REMOVED_REDIRECT("mw-removed-redirect"),

	MW_REPLACE("mw-replace"),

	MW_REVERTED("mw-reverted"),

	MW_ROLLBACK("mw-rollback"),

	MW_SERVER_SIDE_UPLOAD("mw-server-side-upload"),

	MW_UNDO("mw-undo"),

	VISUALEDITOR("visualeditor"),

	VISUALEDITOR_NEEDCHECK("visualeditor-needcheck"),

	VISUALEDITOR_SWITCHED("visualeditor-switched"),

	VISUALEDITOR_WIKITEXT("visualeditor-wikitext"),

	WIKIEDITOR("wikieditor");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIFeedcontributionsTagfilter> set =
				EnumSet.noneOf(AAPIFeedcontributionsTagfilter.class);

		public AAPIFeedcontributionsTagfilter[] build() {
			return set.toArray(AAPIFeedcontributionsTagfilter[]::new);
		}

		public Builder NUKE() {
			set.add(NUKE);
			return this;
		}

		public Builder DISCUSSIONTOOLS() {
			set.add(DISCUSSIONTOOLS);
			return this;
		}

		public Builder DISCUSSIONTOOLS_ADDED_COMMENT() {
			set.add(DISCUSSIONTOOLS_ADDED_COMMENT);
			return this;
		}

		public Builder DISCUSSIONTOOLS_EDIT() {
			set.add(DISCUSSIONTOOLS_EDIT);
			return this;
		}

		public Builder DISCUSSIONTOOLS_NEWTOPIC() {
			set.add(DISCUSSIONTOOLS_NEWTOPIC);
			return this;
		}

		public Builder DISCUSSIONTOOLS_REPLY() {
			set.add(DISCUSSIONTOOLS_REPLY);
			return this;
		}

		public Builder DISCUSSIONTOOLS_SOURCE() {
			set.add(DISCUSSIONTOOLS_SOURCE);
			return this;
		}

		public Builder DISCUSSIONTOOLS_SOURCE_ENHANCED() {
			set.add(DISCUSSIONTOOLS_SOURCE_ENHANCED);
			return this;
		}

		public Builder DISCUSSIONTOOLS_VISUAL() {
			set.add(DISCUSSIONTOOLS_VISUAL);
			return this;
		}

		public Builder EDITCHECK_NEWCONTENT() {
			set.add(EDITCHECK_NEWCONTENT);
			return this;
		}

		public Builder EDITCHECK_NEWREFERENCE() {
			set.add(EDITCHECK_NEWREFERENCE);
			return this;
		}

		public Builder EDITCHECK_REFERENCE_DECLINE_COMMON_KNOWLEDGE() {
			set.add(EDITCHECK_REFERENCE_DECLINE_COMMON_KNOWLEDGE);
			return this;
		}

		public Builder EDITCHECK_REFERENCE_DECLINE_IRRELEVANT() {
			set.add(EDITCHECK_REFERENCE_DECLINE_IRRELEVANT);
			return this;
		}

		public Builder EDITCHECK_REFERENCE_DECLINE_OTHER() {
			set.add(EDITCHECK_REFERENCE_DECLINE_OTHER);
			return this;
		}

		public Builder EDITCHECK_REFERENCE_DECLINE_UNCERTAIN() {
			set.add(EDITCHECK_REFERENCE_DECLINE_UNCERTAIN);
			return this;
		}

		public Builder EDITCHECK_REFERENCES() {
			set.add(EDITCHECK_REFERENCES);
			return this;
		}

		public Builder EDITCHECK_REFERENCES_ACTIVATED() {
			set.add(EDITCHECK_REFERENCES_ACTIVATED);
			return this;
		}

		public Builder MAPS_VISUAL_EDIT() {
			set.add(MAPS_VISUAL_EDIT);
			return this;
		}

		public Builder MW_BLANK() {
			set.add(MW_BLANK);
			return this;
		}

		public Builder MW_CHANGED_REDIRECT_TARGET() {
			set.add(MW_CHANGED_REDIRECT_TARGET);
			return this;
		}

		public Builder MW_CONTENTMODELCHANGE() {
			set.add(MW_CONTENTMODELCHANGE);
			return this;
		}

		public Builder MW_MANUAL_REVERT() {
			set.add(MW_MANUAL_REVERT);
			return this;
		}

		public Builder MW_NEW_REDIRECT() {
			set.add(MW_NEW_REDIRECT);
			return this;
		}

		public Builder MW_REMOVED_REDIRECT() {
			set.add(MW_REMOVED_REDIRECT);
			return this;
		}

		public Builder MW_REPLACE() {
			set.add(MW_REPLACE);
			return this;
		}

		public Builder MW_REVERTED() {
			set.add(MW_REVERTED);
			return this;
		}

		public Builder MW_ROLLBACK() {
			set.add(MW_ROLLBACK);
			return this;
		}

		public Builder MW_SERVER_SIDE_UPLOAD() {
			set.add(MW_SERVER_SIDE_UPLOAD);
			return this;
		}

		public Builder MW_UNDO() {
			set.add(MW_UNDO);
			return this;
		}

		public Builder VISUALEDITOR() {
			set.add(VISUALEDITOR);
			return this;
		}

		public Builder VISUALEDITOR_NEEDCHECK() {
			set.add(VISUALEDITOR_NEEDCHECK);
			return this;
		}

		public Builder VISUALEDITOR_SWITCHED() {
			set.add(VISUALEDITOR_SWITCHED);
			return this;
		}

		public Builder VISUALEDITOR_WIKITEXT() {
			set.add(VISUALEDITOR_WIKITEXT);
			return this;
		}

		public Builder WIKIEDITOR() {
			set.add(WIKIEDITOR);
			return this;
		}
	}
}
