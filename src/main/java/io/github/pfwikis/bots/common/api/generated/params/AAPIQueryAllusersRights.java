package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Only include users with the given rights. Does not include rights granted by implicit or auto-promoted groups like *, user, or autoconfirmed.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryAllusersRights {
	APIHIGHLIMITS("apihighlimits"),

	APPLYCHANGETAGS("applychangetags"),

	AUTOCONFIRMED("autoconfirmed"),

	AUTOCREATEACCOUNT("autocreateaccount"),

	AUTOPATROL("autopatrol"),

	BADCAPTCHA("badcaptcha"),

	BIGDELETE("bigdelete"),

	BLOCK("block"),

	BLOCKEMAIL("blockemail"),

	BOT("bot"),

	BROWSEARCHIVE("browsearchive"),

	CHANGEEMAIL("changeemail"),

	CHANGETAGS("changetags"),

	CONFIRMACCOUNT("confirmaccount"),

	CONFIRMACCOUNT_NOTIFY("confirmaccount-notify"),

	CONFIRMEMAIL("confirmemail"),

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

	LINKPURGE("linkpurge"),

	LOOKUPCREDENTIALS("lookupcredentials"),

	MAILPASSWORD("mailpassword"),

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

	PURGE("purge"),

	READ("read"),

	RENAMEUSER("renameuser"),

	RENDERFILE("renderfile"),

	RENDERFILE_NONSTANDARD("renderfile-nonstandard"),

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

	STASHBASEHTML("stashbasehtml"),

	STASHEDIT("stashedit"),

	SUPPRESSIONLOG("suppressionlog"),

	SUPPRESSREDIRECT("suppressredirect"),

	SUPPRESSREVISION("suppressrevision"),

	THANKS_NOTIFICATION("thanks-notification"),

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

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryAllusersRights> set =
				EnumSet.noneOf(AAPIQueryAllusersRights.class);

		public AAPIQueryAllusersRights[] build() {
			return set.toArray(AAPIQueryAllusersRights[]::new);
		}

		public Builder APIHIGHLIMITS() {
			set.add(APIHIGHLIMITS);
			return this;
		}

		public Builder APPLYCHANGETAGS() {
			set.add(APPLYCHANGETAGS);
			return this;
		}

		public Builder AUTOCONFIRMED() {
			set.add(AUTOCONFIRMED);
			return this;
		}

		public Builder AUTOCREATEACCOUNT() {
			set.add(AUTOCREATEACCOUNT);
			return this;
		}

		public Builder AUTOPATROL() {
			set.add(AUTOPATROL);
			return this;
		}

		public Builder BADCAPTCHA() {
			set.add(BADCAPTCHA);
			return this;
		}

		public Builder BIGDELETE() {
			set.add(BIGDELETE);
			return this;
		}

		public Builder BLOCK() {
			set.add(BLOCK);
			return this;
		}

		public Builder BLOCKEMAIL() {
			set.add(BLOCKEMAIL);
			return this;
		}

		public Builder BOT() {
			set.add(BOT);
			return this;
		}

		public Builder BROWSEARCHIVE() {
			set.add(BROWSEARCHIVE);
			return this;
		}

		public Builder CHANGEEMAIL() {
			set.add(CHANGEEMAIL);
			return this;
		}

		public Builder CHANGETAGS() {
			set.add(CHANGETAGS);
			return this;
		}

		public Builder CONFIRMACCOUNT() {
			set.add(CONFIRMACCOUNT);
			return this;
		}

		public Builder CONFIRMACCOUNT_NOTIFY() {
			set.add(CONFIRMACCOUNT_NOTIFY);
			return this;
		}

		public Builder CONFIRMEMAIL() {
			set.add(CONFIRMEMAIL);
			return this;
		}

		public Builder CREATEACCOUNT() {
			set.add(CREATEACCOUNT);
			return this;
		}

		public Builder CREATECLASS() {
			set.add(CREATECLASS);
			return this;
		}

		public Builder CREATEPAGE() {
			set.add(CREATEPAGE);
			return this;
		}

		public Builder CREATETALK() {
			set.add(CREATETALK);
			return this;
		}

		public Builder DELETE() {
			set.add(DELETE);
			return this;
		}

		public Builder DELETE_REDIRECT() {
			set.add(DELETE_REDIRECT);
			return this;
		}

		public Builder DELETEBATCH() {
			set.add(DELETEBATCH);
			return this;
		}

		public Builder DELETEBATCH_SPOOF() {
			set.add(DELETEBATCH_SPOOF);
			return this;
		}

		public Builder DELETECHANGETAGS() {
			set.add(DELETECHANGETAGS);
			return this;
		}

		public Builder DELETEDHISTORY() {
			set.add(DELETEDHISTORY);
			return this;
		}

		public Builder DELETEDTEXT() {
			set.add(DELETEDTEXT);
			return this;
		}

		public Builder DELETELOGENTRY() {
			set.add(DELETELOGENTRY);
			return this;
		}

		public Builder DELETEREVISION() {
			set.add(DELETEREVISION);
			return this;
		}

		public Builder ECHO_CREATE() {
			set.add(ECHO_CREATE);
			return this;
		}

		public Builder EDIT() {
			set.add(EDIT);
			return this;
		}

		public Builder EDITCONTENTMODEL() {
			set.add(EDITCONTENTMODEL);
			return this;
		}

		public Builder EDITINTERFACE() {
			set.add(EDITINTERFACE);
			return this;
		}

		public Builder EDITMYOPTIONS() {
			set.add(EDITMYOPTIONS);
			return this;
		}

		public Builder EDITMYPRIVATEINFO() {
			set.add(EDITMYPRIVATEINFO);
			return this;
		}

		public Builder EDITMYUSERCSS() {
			set.add(EDITMYUSERCSS);
			return this;
		}

		public Builder EDITMYUSERJS() {
			set.add(EDITMYUSERJS);
			return this;
		}

		public Builder EDITMYUSERJSON() {
			set.add(EDITMYUSERJSON);
			return this;
		}

		public Builder EDITMYUSERJSREDIRECT() {
			set.add(EDITMYUSERJSREDIRECT);
			return this;
		}

		public Builder EDITMYWATCHLIST() {
			set.add(EDITMYWATCHLIST);
			return this;
		}

		public Builder EDITPROTECTED() {
			set.add(EDITPROTECTED);
			return this;
		}

		public Builder EDITRESTRICTEDFIELDS() {
			set.add(EDITRESTRICTEDFIELDS);
			return this;
		}

		public Builder EDITSEMIPROTECTED() {
			set.add(EDITSEMIPROTECTED);
			return this;
		}

		public Builder EDITSITECSS() {
			set.add(EDITSITECSS);
			return this;
		}

		public Builder EDITSITEJS() {
			set.add(EDITSITEJS);
			return this;
		}

		public Builder EDITSITEJSON() {
			set.add(EDITSITEJSON);
			return this;
		}

		public Builder EDITUSERCSS() {
			set.add(EDITUSERCSS);
			return this;
		}

		public Builder EDITUSERJS() {
			set.add(EDITUSERJS);
			return this;
		}

		public Builder EDITUSERJSON() {
			set.add(EDITUSERJSON);
			return this;
		}

		public Builder EDITWIDGETS() {
			set.add(EDITWIDGETS);
			return this;
		}

		public Builder HIDEUSER() {
			set.add(HIDEUSER);
			return this;
		}

		public Builder IMPORT() {
			set.add(IMPORT);
			return this;
		}

		public Builder IMPORTUPLOAD() {
			set.add(IMPORTUPLOAD);
			return this;
		}

		public Builder INTERWIKI() {
			set.add(INTERWIKI);
			return this;
		}

		public Builder IPBLOCK_EXEMPT() {
			set.add(IPBLOCK_EXEMPT);
			return this;
		}

		public Builder LINKPURGE() {
			set.add(LINKPURGE);
			return this;
		}

		public Builder LOOKUPCREDENTIALS() {
			set.add(LOOKUPCREDENTIALS);
			return this;
		}

		public Builder MAILPASSWORD() {
			set.add(MAILPASSWORD);
			return this;
		}

		public Builder MANAGE_ALL_PUSH_SUBSCRIPTIONS() {
			set.add(MANAGE_ALL_PUSH_SUBSCRIPTIONS);
			return this;
		}

		public Builder MANAGECHANGETAGS() {
			set.add(MANAGECHANGETAGS);
			return this;
		}

		public Builder MARKBOTEDITS() {
			set.add(MARKBOTEDITS);
			return this;
		}

		public Builder MASSEDITREGEX() {
			set.add(MASSEDITREGEX);
			return this;
		}

		public Builder MERGEHISTORY() {
			set.add(MERGEHISTORY);
			return this;
		}

		public Builder MINOREDIT() {
			set.add(MINOREDIT);
			return this;
		}

		public Builder MOVE() {
			set.add(MOVE);
			return this;
		}

		public Builder MOVE_CATEGORYPAGES() {
			set.add(MOVE_CATEGORYPAGES);
			return this;
		}

		public Builder MOVE_ROOTUSERPAGES() {
			set.add(MOVE_ROOTUSERPAGES);
			return this;
		}

		public Builder MOVE_SUBPAGES() {
			set.add(MOVE_SUBPAGES);
			return this;
		}

		public Builder MOVEFILE() {
			set.add(MOVEFILE);
			return this;
		}

		public Builder MULTIPAGEEDIT() {
			set.add(MULTIPAGEEDIT);
			return this;
		}

		public Builder NOMINORNEWTALK() {
			set.add(NOMINORNEWTALK);
			return this;
		}

		public Builder NORATELIMIT() {
			set.add(NORATELIMIT);
			return this;
		}

		public Builder NUKE() {
			set.add(NUKE);
			return this;
		}

		public Builder OVERRIDE_EXPORT_DEPTH() {
			set.add(OVERRIDE_EXPORT_DEPTH);
			return this;
		}

		public Builder PAGELANG() {
			set.add(PAGELANG);
			return this;
		}

		public Builder PATROL() {
			set.add(PATROL);
			return this;
		}

		public Builder PATROLMARKS() {
			set.add(PATROLMARKS);
			return this;
		}

		public Builder PROTECT() {
			set.add(PROTECT);
			return this;
		}

		public Builder PURGE() {
			set.add(PURGE);
			return this;
		}

		public Builder READ() {
			set.add(READ);
			return this;
		}

		public Builder RENAMEUSER() {
			set.add(RENAMEUSER);
			return this;
		}

		public Builder RENDERFILE() {
			set.add(RENDERFILE);
			return this;
		}

		public Builder RENDERFILE_NONSTANDARD() {
			set.add(RENDERFILE_NONSTANDARD);
			return this;
		}

		public Builder REQUESTIPS() {
			set.add(REQUESTIPS);
			return this;
		}

		public Builder REUPLOAD() {
			set.add(REUPLOAD);
			return this;
		}

		public Builder REUPLOAD_OWN() {
			set.add(REUPLOAD_OWN);
			return this;
		}

		public Builder REUPLOAD_SHARED() {
			set.add(REUPLOAD_SHARED);
			return this;
		}

		public Builder ROLLBACK() {
			set.add(ROLLBACK);
			return this;
		}

		public Builder SENDEMAIL() {
			set.add(SENDEMAIL);
			return this;
		}

		public Builder SITEADMIN() {
			set.add(SITEADMIN);
			return this;
		}

		public Builder SKIPCAPTCHA() {
			set.add(SKIPCAPTCHA);
			return this;
		}

		public Builder SMW_ADMIN() {
			set.add(SMW_ADMIN);
			return this;
		}

		public Builder SMW_PAGEEDIT() {
			set.add(SMW_PAGEEDIT);
			return this;
		}

		public Builder SMW_PATTERNEDIT() {
			set.add(SMW_PATTERNEDIT);
			return this;
		}

		public Builder SMW_SCHEMAEDIT() {
			set.add(SMW_SCHEMAEDIT);
			return this;
		}

		public Builder SMW_VIEWEDITPAGEINFO() {
			set.add(SMW_VIEWEDITPAGEINFO);
			return this;
		}

		public Builder SMW_VIEWENTITYASSOCIATEDREVISIONMISMATCH() {
			set.add(SMW_VIEWENTITYASSOCIATEDREVISIONMISMATCH);
			return this;
		}

		public Builder SMW_VIEWJOBQUEUEWATCHLIST() {
			set.add(SMW_VIEWJOBQUEUEWATCHLIST);
			return this;
		}

		public Builder STASHBASEHTML() {
			set.add(STASHBASEHTML);
			return this;
		}

		public Builder STASHEDIT() {
			set.add(STASHEDIT);
			return this;
		}

		public Builder SUPPRESSIONLOG() {
			set.add(SUPPRESSIONLOG);
			return this;
		}

		public Builder SUPPRESSREDIRECT() {
			set.add(SUPPRESSREDIRECT);
			return this;
		}

		public Builder SUPPRESSREVISION() {
			set.add(SUPPRESSREVISION);
			return this;
		}

		public Builder THANKS_NOTIFICATION() {
			set.add(THANKS_NOTIFICATION);
			return this;
		}

		public Builder UNBLOCKSELF() {
			set.add(UNBLOCKSELF);
			return this;
		}

		public Builder UNDELETE() {
			set.add(UNDELETE);
			return this;
		}

		public Builder UNWATCHEDPAGES() {
			set.add(UNWATCHEDPAGES);
			return this;
		}

		public Builder UPLOAD() {
			set.add(UPLOAD);
			return this;
		}

		public Builder UPLOAD_BY_URL() {
			set.add(UPLOAD_BY_URL);
			return this;
		}

		public Builder USERMERGE() {
			set.add(USERMERGE);
			return this;
		}

		public Builder USERRIGHTS() {
			set.add(USERRIGHTS);
			return this;
		}

		public Builder USERRIGHTS_INTERWIKI() {
			set.add(USERRIGHTS_INTERWIKI);
			return this;
		}

		public Builder VIEWEDITTAB() {
			set.add(VIEWEDITTAB);
			return this;
		}

		public Builder VIEWMYPRIVATEINFO() {
			set.add(VIEWMYPRIVATEINFO);
			return this;
		}

		public Builder VIEWMYWATCHLIST() {
			set.add(VIEWMYWATCHLIST);
			return this;
		}

		public Builder VIEWSUPPRESSED() {
			set.add(VIEWSUPPRESSED);
			return this;
		}
	}
}
