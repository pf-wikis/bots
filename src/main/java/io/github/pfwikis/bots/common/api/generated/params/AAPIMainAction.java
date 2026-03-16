package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import io.github.pfwikis.bots.common.api.generated.AAPIAcquiretempusername;

import io.github.pfwikis.bots.common.api.generated.AAPIAsk;

import io.github.pfwikis.bots.common.api.generated.AAPIAskargs;

import io.github.pfwikis.bots.common.api.generated.AAPIBlock;

import io.github.pfwikis.bots.common.api.generated.AAPIChangeauthenticationdata;

import io.github.pfwikis.bots.common.api.generated.AAPIChangecontentmodel;

import io.github.pfwikis.bots.common.api.generated.AAPIChecktoken;

import io.github.pfwikis.bots.common.api.generated.AAPICirrusConfigDump;

import io.github.pfwikis.bots.common.api.generated.AAPICirrusMappingDump;

import io.github.pfwikis.bots.common.api.generated.AAPICirrusProfilesDump;

import io.github.pfwikis.bots.common.api.generated.AAPICirrusSettingsDump;

import io.github.pfwikis.bots.common.api.generated.AAPIClearhasmsg;

import io.github.pfwikis.bots.common.api.generated.AAPIClientlogin;

import io.github.pfwikis.bots.common.api.generated.AAPICompare;

import io.github.pfwikis.bots.common.api.generated.AAPICreateaccount;

import io.github.pfwikis.bots.common.api.generated.AAPIDelete;

import io.github.pfwikis.bots.common.api.generated.AAPIDiscussiontoolsedit;

import io.github.pfwikis.bots.common.api.generated.AAPIDiscussiontoolsfindcomment;

import io.github.pfwikis.bots.common.api.generated.AAPIDiscussiontoolsgetsubscriptions;

import io.github.pfwikis.bots.common.api.generated.AAPIDiscussiontoolssubscribe;

import io.github.pfwikis.bots.common.api.generated.AAPIDiscussiontoolsthank;

import io.github.pfwikis.bots.common.api.generated.AAPIEchocreateevent;

import io.github.pfwikis.bots.common.api.generated.AAPIEchomarkread;

import io.github.pfwikis.bots.common.api.generated.AAPIEchomarkseen;

import io.github.pfwikis.bots.common.api.generated.AAPIEchomute;

import io.github.pfwikis.bots.common.api.generated.AAPIEdit;

import io.github.pfwikis.bots.common.api.generated.AAPIEmailuser;

import io.github.pfwikis.bots.common.api.generated.AAPIEmbedvideo;

import io.github.pfwikis.bots.common.api.generated.AAPIExpandtemplates;

import io.github.pfwikis.bots.common.api.generated.AAPIExtSrfDatatablesApi;

import io.github.pfwikis.bots.common.api.generated.AAPIExtSrfSlideshowShow;

import io.github.pfwikis.bots.common.api.generated.AAPIFeedcontributions;

import io.github.pfwikis.bots.common.api.generated.AAPIFeedrecentchanges;

import io.github.pfwikis.bots.common.api.generated.AAPIFeedwatchlist;

import io.github.pfwikis.bots.common.api.generated.AAPIFilerevert;

import io.github.pfwikis.bots.common.api.generated.AAPIHelp;

import io.github.pfwikis.bots.common.api.generated.AAPIImagerotate;

import io.github.pfwikis.bots.common.api.generated.AAPIImport;

import io.github.pfwikis.bots.common.api.generated.AAPILinkaccount;

import io.github.pfwikis.bots.common.api.generated.AAPILogin;

import io.github.pfwikis.bots.common.api.generated.AAPILogout;

import io.github.pfwikis.bots.common.api.generated.AAPIManagetags;

import io.github.pfwikis.bots.common.api.generated.AAPIMergehistory;

import io.github.pfwikis.bots.common.api.generated.AAPIMove;

import io.github.pfwikis.bots.common.api.generated.AAPIOpensearch;

import io.github.pfwikis.bots.common.api.generated.AAPIOptions;

import io.github.pfwikis.bots.common.api.generated.AAPIParaminfo;

import io.github.pfwikis.bots.common.api.generated.AAPIParse;

import io.github.pfwikis.bots.common.api.generated.AAPIPatrol;

import io.github.pfwikis.bots.common.api.generated.AAPIPfautocomplete;

import io.github.pfwikis.bots.common.api.generated.AAPIPfautoedit;

import io.github.pfwikis.bots.common.api.generated.AAPIProtect;

import io.github.pfwikis.bots.common.api.generated.AAPIPurge;

import io.github.pfwikis.bots.common.api.generated.AAPIQuery;

import io.github.pfwikis.bots.common.api.generated.AAPIRemoveauthenticationdata;

import io.github.pfwikis.bots.common.api.generated.AAPIResetpassword;

import io.github.pfwikis.bots.common.api.generated.AAPIRevisiondelete;

import io.github.pfwikis.bots.common.api.generated.AAPIRollback;

import io.github.pfwikis.bots.common.api.generated.AAPIRsd;

import io.github.pfwikis.bots.common.api.generated.AAPISetnotificationtimestamp;

import io.github.pfwikis.bots.common.api.generated.AAPISetpagelanguage;

import io.github.pfwikis.bots.common.api.generated.AAPISmwbrowse;

import io.github.pfwikis.bots.common.api.generated.AAPISmwinfo;

import io.github.pfwikis.bots.common.api.generated.AAPISmwtask;

import io.github.pfwikis.bots.common.api.generated.AAPITag;

import io.github.pfwikis.bots.common.api.generated.AAPITemplatedata;

import io.github.pfwikis.bots.common.api.generated.AAPIThank;

import io.github.pfwikis.bots.common.api.generated.AAPIUnblock;

import io.github.pfwikis.bots.common.api.generated.AAPIUndelete;

import io.github.pfwikis.bots.common.api.generated.AAPIUnlinkaccount;

import io.github.pfwikis.bots.common.api.generated.AAPIUpload;

import io.github.pfwikis.bots.common.api.generated.AAPIUserrights;

import io.github.pfwikis.bots.common.api.generated.AAPIValidatepassword;

import io.github.pfwikis.bots.common.api.generated.AAPIVeforallParsoidUtils;

import io.github.pfwikis.bots.common.api.generated.AAPIWatch;

/**<p>Which action to perform.
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIMainAction {

	/**Acquire a temporary user username and stash it in the current session, if temp account creation is enabled and the current user is logged out. If a name has already been stashed, returns the same name.*/
	ACQUIRETEMPUSERNAME("acquiretempusername", AAPIAcquiretempusername.class),

	/**API module to query Semantic MediaWiki using the ask language.*/
	ASK("ask", AAPIAsk.class),

	/**API module to query Semantic MediaWiki using the ask language as list of conditions, printouts and parameters.*/
	ASKARGS("askargs", AAPIAskargs.class),

	/**Block a user.*/
	BLOCK("block", AAPIBlock.class),

	/**Change authentication data for the current user.*/
	CHANGEAUTHENTICATIONDATA("changeauthenticationdata", AAPIChangeauthenticationdata.class),

	/**Change the content model of a page*/
	CHANGECONTENTMODEL("changecontentmodel", AAPIChangecontentmodel.class),

	/**Check the validity of a token from <kbd><a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a></kbd>.*/
	CHECKTOKEN("checktoken", AAPIChecktoken.class),

	/**Dump of CirrusSearch configuration.*/
	CIRRUS_CONFIG_DUMP("cirrus-config-dump", AAPICirrusConfigDump.class),

	/**Dump of CirrusSearch mapping for this wiki.*/
	CIRRUS_MAPPING_DUMP("cirrus-mapping-dump", AAPICirrusMappingDump.class),

	/**Dump of CirrusSearch profiles for this wiki.*/
	CIRRUS_PROFILES_DUMP("cirrus-profiles-dump", AAPICirrusProfilesDump.class),

	/**Dump of CirrusSearch settings for this wiki.*/
	CIRRUS_SETTINGS_DUMP("cirrus-settings-dump", AAPICirrusSettingsDump.class),

	/**Clears the <code>hasmsg</code> flag for the current user.*/
	CLEARHASMSG("clearhasmsg", AAPIClearhasmsg.class),

	/**Log in to the wiki using the interactive flow.*/
	CLIENTLOGIN("clientlogin", AAPIClientlogin.class),

	/**Get the difference between two pages.*/
	COMPARE("compare", AAPICompare.class),

	/**Create a new user account.*/
	CREATEACCOUNT("createaccount", AAPICreateaccount.class),

	/**Delete a page.*/
	DELETE("delete", AAPIDelete.class),

	/**Post a message on a discussion page.*/
	DISCUSSIONTOOLSEDIT("discussiontoolsedit", AAPIDiscussiontoolsedit.class),

	/**Find a comment by its ID or name.*/
	DISCUSSIONTOOLSFINDCOMMENT("discussiontoolsfindcomment", AAPIDiscussiontoolsfindcomment.class),

	/**Get the subscription statuses of given topics.*/
	DISCUSSIONTOOLSGETSUBSCRIPTIONS(
			"discussiontoolsgetsubscriptions", AAPIDiscussiontoolsgetsubscriptions.class),

	/**Subscribe (or unsubscribe) to receive notifications about a topic.*/
	DISCUSSIONTOOLSSUBSCRIBE("discussiontoolssubscribe", AAPIDiscussiontoolssubscribe.class),

	/**Send a public thank-you notification for a comment.*/
	DISCUSSIONTOOLSTHANK("discussiontoolsthank", AAPIDiscussiontoolsthank.class),

	/**Manually trigger a notification to a user*/
	ECHOCREATEEVENT("echocreateevent", AAPIEchocreateevent.class),

	/**Mark notifications as read for the current user.*/
	ECHOMARKREAD("echomarkread", AAPIEchomarkread.class),

	/**Mark notifications as seen for the current user.*/
	ECHOMARKSEEN("echomarkseen", AAPIEchomarkseen.class),

	/**Mute or unmute notifications from certain users or pages.*/
	ECHOMUTE("echomute", AAPIEchomute.class),

	/**Create and edit pages.*/
	EDIT("edit", AAPIEdit.class),

	/**Email a user.*/
	EMAILUSER("emailuser", AAPIEmailuser.class),

	/**Get generated video embed code for given parameters.*/
	EMBEDVIDEO("embedvideo", AAPIEmbedvideo.class),

	/**Expands all templates within wikitext.*/
	EXPANDTEMPLATES("expandtemplates", AAPIExpandtemplates.class),

	/***/
	EXT_SRF_DATATABLES_API("ext.srf.datatables.api", AAPIExtSrfDatatablesApi.class),

	/***/
	EXT_SRF_SLIDESHOW_SHOW("ext.srf.slideshow.show", AAPIExtSrfSlideshowShow.class),

	/**Returns a user's contributions feed.*/
	FEEDCONTRIBUTIONS("feedcontributions", AAPIFeedcontributions.class),

	/**Returns a recent changes feed.*/
	FEEDRECENTCHANGES("feedrecentchanges", AAPIFeedrecentchanges.class),

	/**Returns a watchlist feed.*/
	FEEDWATCHLIST("feedwatchlist", AAPIFeedwatchlist.class),

	/**Revert a file to an old version.*/
	FILEREVERT("filerevert", AAPIFilerevert.class),

	/**Display help for the specified modules.*/
	HELP("help", AAPIHelp.class),

	/**Rotate one or more images.*/
	IMAGEROTATE("imagerotate", AAPIImagerotate.class),

	/**Import a page from another wiki, or from an XML file.*/
	IMPORT("import", AAPIImport.class),

	/**Link an account from a third-party provider to the current user.*/
	LINKACCOUNT("linkaccount", AAPILinkaccount.class),

	/**Log in and get authentication cookies.*/
	LOGIN("login", AAPILogin.class),

	/**Log out and clear session data.*/
	LOGOUT("logout", AAPILogout.class),

	/**Perform management tasks relating to change tags.*/
	MANAGETAGS("managetags", AAPIManagetags.class),

	/**Merge page histories.*/
	MERGEHISTORY("mergehistory", AAPIMergehistory.class),

	/**Move a page.*/
	MOVE("move", AAPIMove.class),

	/**Search the wiki using the OpenSearch protocol.*/
	OPENSEARCH("opensearch", AAPIOpensearch.class),

	/**Change preferences of the current user.*/
	OPTIONS("options", AAPIOptions.class),

	/**Obtain information about API modules.*/
	PARAMINFO("paraminfo", AAPIParaminfo.class),

	/**Parses content and returns parser output.*/
	PARSE("parse", AAPIParse.class),

	/**Patrol a page or revision.*/
	PATROL("patrol", AAPIPatrol.class),

	/**Autocompletion used by the Page Forms extension.*/
	PFAUTOCOMPLETE("pfautocomplete", AAPIPfautocomplete.class),

	/**Create or edit a page using a form defined by the Page Forms extension.*/
	PFAUTOEDIT("pfautoedit", AAPIPfautoedit.class),

	/**Change the protection level of a page.*/
	PROTECT("protect", AAPIProtect.class),

	/**Purge the cache for the given titles.*/
	PURGE("purge", AAPIPurge.class),

	/**Fetch data from and about MediaWiki.*/
	QUERY("query", AAPIQuery.class),

	/**Remove authentication data for the current user.*/
	REMOVEAUTHENTICATIONDATA("removeauthenticationdata", AAPIRemoveauthenticationdata.class),

	/**Send a password reset email to a user.*/
	RESETPASSWORD("resetpassword", AAPIResetpassword.class),

	/**Delete and undelete revisions.*/
	REVISIONDELETE("revisiondelete", AAPIRevisiondelete.class),

	/**Undo the last edit to the page.*/
	ROLLBACK("rollback", AAPIRollback.class),

	/**Export an RSD (Really Simple Discovery) schema.*/
	RSD("rsd", AAPIRsd.class),

	/**Update the notification timestamp for watched pages.*/
	SETNOTIFICATIONTIMESTAMP("setnotificationtimestamp", AAPISetnotificationtimestamp.class),

	/**Change the language of a page.*/
	SETPAGELANGUAGE("setpagelanguage", AAPISetpagelanguage.class),

	/**API module to support browse activities for different entity types in Semantic MediaWiki.*/
	SMWBROWSE("smwbrowse", AAPISmwbrowse.class),

	/**API module to retrieve information about Semantic MediaWiki statistics and other meta information.*/
	SMWINFO("smwinfo", AAPISmwinfo.class),

	/**API module to execute Semantic MediaWiki related tasks (for internal use only, not for public use).*/
	SMWTASK("smwtask", AAPISmwtask.class),

	/**Add or remove change tags from individual revisions or log entries.*/
	TAG("tag", AAPITag.class),

	/**Fetch data stored by the TemplateData extension.*/
	TEMPLATEDATA("templatedata", AAPITemplatedata.class),

	/**Send a thank-you notification to an editor.*/
	THANK("thank", AAPIThank.class),

	/**Unblock a user.*/
	UNBLOCK("unblock", AAPIUnblock.class),

	/**Undelete revisions of a deleted page.*/
	UNDELETE("undelete", AAPIUndelete.class),

	/**Remove a linked third-party account from the current user.*/
	UNLINKACCOUNT("unlinkaccount", AAPIUnlinkaccount.class),

	/**Upload a file, or get the status of pending uploads.*/
	UPLOAD("upload", AAPIUpload.class),

	/**Change a user's group membership.*/
	USERRIGHTS("userrights", AAPIUserrights.class),

	/**Validate a password against the wiki's password policies.*/
	VALIDATEPASSWORD("validatepassword", AAPIValidatepassword.class),

	/**Convert text back and forth from Wikitext to HTML*/
	VEFORALL_PARSOID_UTILS("veforall-parsoid-utils", AAPIVeforallParsoidUtils.class),

	/**Add or remove pages from the current user's watchlist.*/
	WATCH("watch", AAPIWatch.class);

	private final String jsonValue;

	private final Class<? extends AAPIMainActionModule> type;

	public static interface AAPIMainActionModule extends AAPIModule {}

	public static class AAPIMainActionSubmodule
			extends AAPISubmodule<AAPIMainAction, AAPIMainActionModule> {
		public AAPIMainActionSubmodule(AAPIMainAction k, AAPIMainActionModule v) {
			super(k, v);
		}
	}

	public static AAPIMainActionSubmodule createSubmodule(AAPIMainActionModule module) {
		return new AAPIMainActionSubmodule(
				switch (module) {
					case AAPIAcquiretempusername g -> ACQUIRETEMPUSERNAME;

					case AAPIAsk g -> ASK;

					case AAPIAskargs g -> ASKARGS;

					case AAPIBlock g -> BLOCK;

					case AAPIChangeauthenticationdata g -> CHANGEAUTHENTICATIONDATA;

					case AAPIChangecontentmodel g -> CHANGECONTENTMODEL;

					case AAPIChecktoken g -> CHECKTOKEN;

					case AAPICirrusConfigDump g -> CIRRUS_CONFIG_DUMP;

					case AAPICirrusMappingDump g -> CIRRUS_MAPPING_DUMP;

					case AAPICirrusProfilesDump g -> CIRRUS_PROFILES_DUMP;

					case AAPICirrusSettingsDump g -> CIRRUS_SETTINGS_DUMP;

					case AAPIClearhasmsg g -> CLEARHASMSG;

					case AAPIClientlogin g -> CLIENTLOGIN;

					case AAPICompare g -> COMPARE;

					case AAPICreateaccount g -> CREATEACCOUNT;

					case AAPIDelete g -> DELETE;

					case AAPIDiscussiontoolsedit g -> DISCUSSIONTOOLSEDIT;

					case AAPIDiscussiontoolsfindcomment g -> DISCUSSIONTOOLSFINDCOMMENT;

					case AAPIDiscussiontoolsgetsubscriptions g -> DISCUSSIONTOOLSGETSUBSCRIPTIONS;

					case AAPIDiscussiontoolssubscribe g -> DISCUSSIONTOOLSSUBSCRIBE;

					case AAPIDiscussiontoolsthank g -> DISCUSSIONTOOLSTHANK;

					case AAPIEchocreateevent g -> ECHOCREATEEVENT;

					case AAPIEchomarkread g -> ECHOMARKREAD;

					case AAPIEchomarkseen g -> ECHOMARKSEEN;

					case AAPIEchomute g -> ECHOMUTE;

					case AAPIEdit g -> EDIT;

					case AAPIEmailuser g -> EMAILUSER;

					case AAPIEmbedvideo g -> EMBEDVIDEO;

					case AAPIExpandtemplates g -> EXPANDTEMPLATES;

					case AAPIExtSrfDatatablesApi g -> EXT_SRF_DATATABLES_API;

					case AAPIExtSrfSlideshowShow g -> EXT_SRF_SLIDESHOW_SHOW;

					case AAPIFeedcontributions g -> FEEDCONTRIBUTIONS;

					case AAPIFeedrecentchanges g -> FEEDRECENTCHANGES;

					case AAPIFeedwatchlist g -> FEEDWATCHLIST;

					case AAPIFilerevert g -> FILEREVERT;

					case AAPIHelp g -> HELP;

					case AAPIImagerotate g -> IMAGEROTATE;

					case AAPIImport g -> IMPORT;

					case AAPILinkaccount g -> LINKACCOUNT;

					case AAPILogin g -> LOGIN;

					case AAPILogout g -> LOGOUT;

					case AAPIManagetags g -> MANAGETAGS;

					case AAPIMergehistory g -> MERGEHISTORY;

					case AAPIMove g -> MOVE;

					case AAPIOpensearch g -> OPENSEARCH;

					case AAPIOptions g -> OPTIONS;

					case AAPIParaminfo g -> PARAMINFO;

					case AAPIParse g -> PARSE;

					case AAPIPatrol g -> PATROL;

					case AAPIPfautocomplete g -> PFAUTOCOMPLETE;

					case AAPIPfautoedit g -> PFAUTOEDIT;

					case AAPIProtect g -> PROTECT;

					case AAPIPurge g -> PURGE;

					case AAPIQuery g -> QUERY;

					case AAPIRemoveauthenticationdata g -> REMOVEAUTHENTICATIONDATA;

					case AAPIResetpassword g -> RESETPASSWORD;

					case AAPIRevisiondelete g -> REVISIONDELETE;

					case AAPIRollback g -> ROLLBACK;

					case AAPIRsd g -> RSD;

					case AAPISetnotificationtimestamp g -> SETNOTIFICATIONTIMESTAMP;

					case AAPISetpagelanguage g -> SETPAGELANGUAGE;

					case AAPISmwbrowse g -> SMWBROWSE;

					case AAPISmwinfo g -> SMWINFO;

					case AAPISmwtask g -> SMWTASK;

					case AAPITag g -> TAG;

					case AAPITemplatedata g -> TEMPLATEDATA;

					case AAPIThank g -> THANK;

					case AAPIUnblock g -> UNBLOCK;

					case AAPIUndelete g -> UNDELETE;

					case AAPIUnlinkaccount g -> UNLINKACCOUNT;

					case AAPIUpload g -> UPLOAD;

					case AAPIUserrights g -> USERRIGHTS;

					case AAPIValidatepassword g -> VALIDATEPASSWORD;

					case AAPIVeforallParsoidUtils g -> VEFORALL_PARSOID_UTILS;

					case AAPIWatch g -> WATCH;

					default -> throw new IllegalArgumentException();
				},
				module);
	}

	public static List<AAPIMainActionSubmodule> createSubmodule(AAPIMainActionModule... modules) {
		var result = new ArrayList<AAPIMainActionSubmodule>();
		for (var v : modules) {
			result.add(createSubmodule(v));
		}
		return result;
	}
}
