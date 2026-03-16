package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryAllmessages;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryAuthmanagerinfo;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryFilerepoinfo;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryLanguageinfo;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryLinterstats;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryNotifications;

import io.github.pfwikis.bots.common.api.generated.AAPIQuerySiteinfo;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryTokens;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryUnreadnotificationpages;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryUserinfo;

/**<p>Which metadata to get.
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryMeta {

	/**Return messages from this site.*/
	ALLMESSAGES("allmessages", AAPIQueryAllmessages.class),

	/**Retrieve information about the current authentication status.*/
	AUTHMANAGERINFO("authmanagerinfo", AAPIQueryAuthmanagerinfo.class),

	/**Return meta information about image repositories configured on the wiki.*/
	FILEREPOINFO("filerepoinfo", AAPIQueryFilerepoinfo.class),

	/**Return information about available languages.*/
	LANGUAGEINFO("languageinfo", AAPIQueryLanguageinfo.class),

	/**Get number of lint errors in each category*/
	LINTERSTATS("linterstats", AAPIQueryLinterstats.class),

	/**Get notifications waiting for the current user.*/
	NOTIFICATIONS("notifications", AAPIQueryNotifications.class),

	/**Return general information about the site.*/
	SITEINFO("siteinfo", AAPIQuerySiteinfo.class),

	/**Gets tokens for data-modifying actions.*/
	TOKENS("tokens", AAPIQueryTokens.class),

	/**Get pages for which there are unread notifications for the current user.*/
	UNREADNOTIFICATIONPAGES("unreadnotificationpages", AAPIQueryUnreadnotificationpages.class),

	/**Get information about the current user.*/
	USERINFO("userinfo", AAPIQueryUserinfo.class);

	private final String jsonValue;

	private final Class<? extends AAPIQueryMetaModule> type;

	public static interface AAPIQueryMetaModule extends AAPIModule {}

	public static class AAPIQueryMetaSubmodule
			extends AAPISubmodule<AAPIQueryMeta, AAPIQueryMetaModule> {
		public AAPIQueryMetaSubmodule(AAPIQueryMeta k, AAPIQueryMetaModule v) {
			super(k, v);
		}
	}

	public static AAPIQueryMetaSubmodule createSubmodule(AAPIQueryMetaModule module) {
		return new AAPIQueryMetaSubmodule(
				switch (module) {
					case AAPIQueryAllmessages g -> ALLMESSAGES;

					case AAPIQueryAuthmanagerinfo g -> AUTHMANAGERINFO;

					case AAPIQueryFilerepoinfo g -> FILEREPOINFO;

					case AAPIQueryLanguageinfo g -> LANGUAGEINFO;

					case AAPIQueryLinterstats g -> LINTERSTATS;

					case AAPIQueryNotifications g -> NOTIFICATIONS;

					case AAPIQuerySiteinfo g -> SITEINFO;

					case AAPIQueryTokens g -> TOKENS;

					case AAPIQueryUnreadnotificationpages g -> UNREADNOTIFICATIONPAGES;

					case AAPIQueryUserinfo g -> USERINFO;

					default -> throw new IllegalArgumentException();
				},
				module);
	}

	public static List<AAPIQueryMetaSubmodule> createSubmodule(AAPIQueryMetaModule... modules) {
		var result = new ArrayList<AAPIQueryMetaSubmodule>();
		for (var v : modules) {
			result.add(createSubmodule(v));
		}
		return result;
	}
}
