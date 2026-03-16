package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Which information to get:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQuerySiteinfoProp {

	/**Returns configuration for the automatic creation of temporary user accounts (also known as IP masking).*/
	AUTOCREATETEMPUSER("autocreatetempuser"),

	/**Returns the automatic promotion configuration.*/
	AUTOPROMOTE("autopromote"),

	/**Returns the automatic promotion configuration that are only done once.*/
	AUTOPROMOTEONCE("autopromoteonce"),

	/**Returns client-side libraries installed on the wiki*/
	CLIENTLIBRARIES("clientlibraries"),

	/**Returns database server with the highest replication lag.*/
	DBREPLLAG("dbrepllag"),

	/**Returns the default values for user preferences.*/
	DEFAULTOPTIONS("defaultoptions"),

	/**Returns extensions installed on the wiki.*/
	EXTENSIONS("extensions"),

	/**Returns a list of parser extension tags.*/
	EXTENSIONTAGS("extensiontags"),

	/**Returns list of file extensions (file types) allowed to be uploaded.*/
	FILEEXTENSIONS("fileextensions"),

	/**Returns a list of parser function hooks.*/
	FUNCTIONHOOKS("functionhooks"),

	/**Overall system information.*/
	GENERAL("general"),

	/**Returns interwiki map (optionally filtered, optionally localised by using <var>siinlanguagecode</var>).*/
	INTERWIKIMAP("interwikimap"),

	/**Returns a list of languages MediaWiki supports (optionally localised by using <var>siinlanguagecode</var>).*/
	LANGUAGES("languages"),

	/**Returns a list of language codes for which <a href="/w/index.php?title=Mw:Special:MyLanguage/LanguageConverter&amp;action=edit&amp;redlink=1" class="new" title="Mw:Special:MyLanguage/LanguageConverter (page does not exist)">LanguageConverter</a> is enabled, and the variants supported for each.*/
	LANGUAGEVARIANTS("languagevariants"),

	/**Returns libraries installed on the wiki.*/
	LIBRARIES("libraries"),

	/**List of magic words and their aliases.*/
	MAGICWORDS("magicwords"),

	/**List of registered namespace aliases.*/
	NAMESPACEALIASES("namespacealiases"),

	/**List of registered namespaces and their canonical names.*/
	NAMESPACES("namespaces"),

	/**Returns a list of protocols that are allowed in external links.*/
	PROTOCOLS("protocols"),

	/**Returns information on available restriction (protection) types.*/
	RESTRICTIONS("restrictions"),

	/**Returns wiki rights (license) information if available.*/
	RIGHTSINFO("rightsinfo"),

	/**Returns a list of all subscribed hooks (contents of <var><a href="/w/index.php?title=Mw:Special:MyLanguage/Manual:$wgHooks&amp;action=edit&amp;redlink=1" class="new" title="Mw:Special:MyLanguage/Manual:$wgHooks (page does not exist)">$wgHooks</a></var>).*/
	SHOWHOOKS("showhooks"),

	/**Returns a list of all enabled skins (optionally localised by using <var>siinlanguagecode</var>, otherwise in the content language).*/
	SKINS("skins"),

	/**List of special page aliases.*/
	SPECIALPAGEALIASES("specialpagealiases"),

	/**Returns site statistics.*/
	STATISTICS("statistics"),

	/**Returns the upload dialog configuration.*/
	UPLOADDIALOG("uploaddialog"),

	/**Returns user groups and the associated permissions.*/
	USERGROUPS("usergroups"),

	/**Returns a list of variable IDs.*/
	VARIABLES("variables");

	private final String jsonValue;
}
