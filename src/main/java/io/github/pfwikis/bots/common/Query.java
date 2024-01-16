package io.github.pfwikis.bots.common;

import io.github.pfwikis.bots.common.model.AllpagesQuery;
import io.github.pfwikis.bots.common.model.AllusersQuery;
import io.github.pfwikis.bots.common.model.ImageUsageQuery;
import io.github.pfwikis.bots.common.model.LogEventsQuery;
import io.github.pfwikis.bots.common.model.PageQuery;
import io.github.pfwikis.bots.common.model.QueryListUsers;
import io.github.pfwikis.bots.common.model.QueryTokens;
import io.github.pfwikis.bots.common.model.RecentChanges;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Query<T> {

	public static final Query<RecentChanges> LIST_RECENT_CHANGES = new Query<>(
		RecentChanges.class,
		"list",
		"recentchanges"
	);

	public static final Query<AllpagesQuery> LIST_ALL_PAGES = new Query<>(
		AllpagesQuery.class,
		"list",
		"allpages"
	);

	public static final Query<QueryListUsers> LIST_USERS = new Query<>(
		QueryListUsers.class,
		"list",
		"users"
	);

	public static final Query<QueryTokens> META_TOKENS = new Query<>(
		QueryTokens.class,
		"meta",
		"tokens"
	);

	public static final Query<PageQuery> GENERATOR_CATEGORY_MEMBERS = new Query<>(
		PageQuery.class,
		"generator",
		"categorymembers"
	);
	
	public static final Query<PageQuery> PROP_TRANSCLUDED_IN = new Query<>(
		PageQuery.class,
		"prop",
		"transcludedin"
	);
	
	public static final Query<PageQuery> PROP_CATEGORIES = new Query<>(
		PageQuery.class,
		"prop",
		"categories"
	);
	
	public static final Query<LogEventsQuery> LIST_LOG_EVENTS = new Query<>(
		LogEventsQuery.class, 
		"list",
		"logevents"
	);
	
	public static final Query<ImageUsageQuery> LIST_IMAGE_USAGE = new Query<>(
		ImageUsageQuery.class, 
		"list",
		"imageusage"
	);
	
	public static final Query<AllusersQuery> LIST_ALL_USERS = new Query<>(
		AllusersQuery.class, 
		"list",
		"allusers"
	);
	
	
	
	
	
	private final Class<T> responseType;
	private final String what;
	private final String which;
	
	@Override
	public String toString() {
		return "["+what+" "+which+"]";
	}
}