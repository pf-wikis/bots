package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
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
}
