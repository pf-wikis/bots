package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Exclude users having the given rights. Does not include rights granted by implicit or auto-promoted groups like *, user, or autoconfirmed.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryContributorsExcluderights {
	APIHIGHLIMITS("apihighlimits"),

	APPLYCHANGETAGS("applychangetags"),

	AUTOCONFIRMED("autoconfirmed"),

	AUTOCREATEACCOUNT("autocreateaccount"),

	AUTOPATROL("autopatrol"),

	BIGDELETE("bigdelete"),

	BLOCK("block"),

	BLOCKEMAIL("blockemail"),

	BOT("bot"),

	BROWSEARCHIVE("browsearchive"),

	CHANGETAGS("changetags"),

	CONFIRMACCOUNT("confirmaccount"),

	CONFIRMACCOUNT_NOTIFY("confirmaccount-notify"),

	CREATEACCOUNT("createaccount"),

	CREATECLASS("createclass"),

	CREATEPAGE("createpage"),

	CREATETALK("createtalk"),

	DELETE("delete"),

	DELETE_REDIRECT("delete-redirect"),

	DELETEBATCH("deletebatch"),

	DELETEBATCH_SPOOF("deletebatch-spoof"),

	DELETECHANGETAGS("deletechangetags"),

	DELETEDHISTORY("deletedhistory"),

	DELETEDTEXT("deletedtext"),

	DELETELOGENTRY("deletelogentry"),

	DELETEREVISION("deleterevision"),

	ECHO_CREATE("echo-create"),

	EDIT("edit"),

	EDITCONTENTMODEL("editcontentmodel"),

	EDITINTERFACE("editinterface"),

	EDITMYOPTIONS("editmyoptions"),

	EDITMYPRIVATEINFO("editmyprivateinfo"),

	EDITMYUSERCSS("editmyusercss"),

	EDITMYUSERJS("editmyuserjs"),

	EDITMYUSERJSON("editmyuserjson"),

	EDITMYUSERJSREDIRECT("editmyuserjsredirect"),

	EDITMYWATCHLIST("editmywatchlist"),

	EDITPROTECTED("editprotected"),

	EDITRESTRICTEDFIELDS("editrestrictedfields"),

	EDITSEMIPROTECTED("editsemiprotected"),

	EDITSITECSS("editsitecss"),

	EDITSITEJS("editsitejs"),

	EDITSITEJSON("editsitejson"),

	EDITUSERCSS("editusercss"),

	EDITUSERJS("edituserjs"),

	EDITUSERJSON("edituserjson"),

	EDITWIDGETS("editwidgets"),

	HIDEUSER("hideuser"),

	IMPORT("import"),

	IMPORTUPLOAD("importupload"),

	INTERWIKI("interwiki"),

	IPBLOCK_EXEMPT("ipblock-exempt"),

	LOOKUPCREDENTIALS("lookupcredentials"),

	MANAGE_ALL_PUSH_SUBSCRIPTIONS("manage-all-push-subscriptions"),

	MANAGECHANGETAGS("managechangetags"),

	MARKBOTEDITS("markbotedits"),

	MASSEDITREGEX("masseditregex"),

	MERGEHISTORY("mergehistory"),

	MINOREDIT("minoredit"),

	MOVE("move"),

	MOVE_CATEGORYPAGES("move-categorypages"),

	MOVE_ROOTUSERPAGES("move-rootuserpages"),

	MOVE_SUBPAGES("move-subpages"),

	MOVEFILE("movefile"),

	MULTIPAGEEDIT("multipageedit"),

	NOMINORNEWTALK("nominornewtalk"),

	NORATELIMIT("noratelimit"),

	NUKE("nuke"),

	OVERRIDE_EXPORT_DEPTH("override-export-depth"),

	PAGELANG("pagelang"),

	PATROL("patrol"),

	PATROLMARKS("patrolmarks"),

	PROTECT("protect"),

	READ("read"),

	RENAMEUSER("renameuser"),

	REQUESTIPS("requestips"),

	REUPLOAD("reupload"),

	REUPLOAD_OWN("reupload-own"),

	REUPLOAD_SHARED("reupload-shared"),

	ROLLBACK("rollback"),

	SENDEMAIL("sendemail"),

	SITEADMIN("siteadmin"),

	SKIPCAPTCHA("skipcaptcha"),

	SMW_ADMIN("smw-admin"),

	SMW_PAGEEDIT("smw-pageedit"),

	SMW_PATTERNEDIT("smw-patternedit"),

	SMW_SCHEMAEDIT("smw-schemaedit"),

	SMW_VIEWEDITPAGEINFO("smw-vieweditpageinfo"),

	SMW_VIEWENTITYASSOCIATEDREVISIONMISMATCH("smw-viewentityassociatedrevisionmismatch"),

	SMW_VIEWJOBQUEUEWATCHLIST("smw-viewjobqueuewatchlist"),

	SUPPRESSIONLOG("suppressionlog"),

	SUPPRESSREDIRECT("suppressredirect"),

	SUPPRESSREVISION("suppressrevision"),

	UNBLOCKSELF("unblockself"),

	UNDELETE("undelete"),

	UNWATCHEDPAGES("unwatchedpages"),

	UPLOAD("upload"),

	UPLOAD_BY_URL("upload_by_url"),

	USERMERGE("usermerge"),

	USERRIGHTS("userrights"),

	USERRIGHTS_INTERWIKI("userrights-interwiki"),

	VIEWEDITTAB("viewedittab"),

	VIEWMYPRIVATEINFO("viewmyprivateinfo"),

	VIEWMYWATCHLIST("viewmywatchlist"),

	VIEWSUPPRESSED("viewsuppressed");

	private final String jsonValue;
}
