package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Filter log actions to only this action. Overrides <var>letype</var>. In the list of possible values, values with the asterisk wildcard such as <kbd>action/*</kbd> can have different strings after the slash (/).*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryLogeventsAction {
	BLOCK_BLOCK("block/block"),

	BLOCK_REBLOCK("block/reblock"),

	BLOCK_UNBLOCK("block/unblock"),

	CONTENTMODEL_CHANGE("contentmodel/change"),

	CONTENTMODEL_NEW("contentmodel/new"),

	CREATE_CREATE("create/create"),

	DELETE_DELETE("delete/delete"),

	DELETE_DELETE_REDIR("delete/delete_redir"),

	DELETE_DELETE_REDIR2("delete/delete_redir2"),

	DELETE_EVENT("delete/event"),

	DELETE_RESTORE("delete/restore"),

	DELETE_REVISION("delete/revision"),

	IMPORT_INTERWIKI("import/interwiki"),

	IMPORT_UPLOAD("import/upload"),

	INTERWIKI_("interwiki/*"),

	MANAGETAGS_ACTIVATE("managetags/activate"),

	MANAGETAGS_CREATE("managetags/create"),

	MANAGETAGS_DEACTIVATE("managetags/deactivate"),

	MANAGETAGS_DELETE("managetags/delete"),

	MERGE_MERGE("merge/merge"),

	MOVE_MOVE("move/move"),

	MOVE_MOVE_REDIR("move/move_redir"),

	NEWUSERS_AUTOCREATE("newusers/autocreate"),

	NEWUSERS_BYEMAIL("newusers/byemail"),

	NEWUSERS_CREATE("newusers/create"),

	NEWUSERS_CREATE2("newusers/create2"),

	NEWUSERS_NEWUSERS("newusers/newusers"),

	PATROL_AUTOPATROL("patrol/autopatrol"),

	PATROL_PATROL("patrol/patrol"),

	PROTECT_MODIFY("protect/modify"),

	PROTECT_MOVE_PROT("protect/move_prot"),

	PROTECT_PROTECT("protect/protect"),

	PROTECT_UNPROTECT("protect/unprotect"),

	RENAMEUSER_RENAMEUSER("renameuser/renameuser"),

	RIGHTS_AUTOPROMOTE("rights/autopromote"),

	RIGHTS_RIGHTS("rights/rights"),

	SUPPRESS_BLOCK("suppress/block"),

	SUPPRESS_DELETE("suppress/delete"),

	SUPPRESS_EVENT("suppress/event"),

	SUPPRESS_REBLOCK("suppress/reblock"),

	SUPPRESS_REVISION("suppress/revision"),

	TAG_UPDATE("tag/update"),

	THANKS_("thanks/*"),

	UPLOAD_OVERWRITE("upload/overwrite"),

	UPLOAD_REVERT("upload/revert"),

	UPLOAD_UPLOAD("upload/upload"),

	USERMERGE_("usermerge/*");

	private final String jsonValue;
}
