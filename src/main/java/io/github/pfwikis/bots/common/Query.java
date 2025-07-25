package io.github.pfwikis.bots.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import io.github.pfwikis.bots.common.model.AllpagesQuery;
import io.github.pfwikis.bots.common.model.AllusersQuery;
import io.github.pfwikis.bots.common.model.ImageUsageQuery;
import io.github.pfwikis.bots.common.model.LogEventsQuery;
import io.github.pfwikis.bots.common.model.PageInfoQuery;
import io.github.pfwikis.bots.common.model.PageQuery;
import io.github.pfwikis.bots.common.model.QueryListUsers;
import io.github.pfwikis.bots.common.model.QueryPageQuery;
import io.github.pfwikis.bots.common.model.QueryResponse;
import io.github.pfwikis.bots.common.model.QueryTokens;
import io.github.pfwikis.bots.common.model.RecentChanges;
import io.github.pfwikis.bots.common.model.RecentChanges.RecentChange;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Query<T> {

	public static final Query<RecentChanges> LIST_RECENT_CHANGES = new Query.WithContinue<>(
		RecentChanges.class,
		"list",
		"recentchanges",
		"rccontinue"
	) {
		public RecentChanges mergeResults(RecentChanges a, RecentChanges b) {
			var l = new ArrayList<RecentChange>();
			l.addAll(List.of(a.recentchanges()));
			l.addAll(List.of(b.recentchanges()));
			return new RecentChanges(l.toArray(RecentChange[]::new));
		};
	};

	public static final Query<AllpagesQuery> LIST_ALL_PAGES = new Query.WithContinue<>(
		AllpagesQuery.class,
		"list",
		"allpages",
		"apcontinue"
	) {
		public AllpagesQuery mergeResults(AllpagesQuery a, AllpagesQuery b) {
			a.getAllpages().addAll(b.getAllpages());
			return a;
		};
	};

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
	
	public static final Query<PageQuery> PROP_TRANSCLUDED_IN = new Query.WithContinue<>(
		PageQuery.class,
		"prop",
		"transcludedin",
		"ticontinue"
	) {
		public PageQuery mergeResults(PageQuery a, PageQuery b) {
			a.getPages().addAll(b.getPages());
			return a;
		};
	};
	
	public static final Query<PageQuery> PROP_CATEGORIES = new Query.WithContinue<>(
		PageQuery.class,
		"prop",
		"categories",
		"clcontinue"
	) {
		@Override
		public PageQuery mergeResults(PageQuery a, PageQuery b) {
			a.getPages().addAll(b.getPages());
			return a;
		}
	};
			
	public static final Query<QueryPageQuery> LIST_QUERY_PAGE = new Query.WithContinue<>(
		QueryPageQuery.class, 
		"list",
		"querypage",
		"qpoffset"
	) {
		@Override
		public QueryPageQuery mergeResults(QueryPageQuery a, QueryPageQuery b) {
			a.getQuerypage().getResults().addAll(b.getQuerypage().getResults());
			return a;
		}
	};
	
	public static final Query<LogEventsQuery> LIST_LOG_EVENTS = new Query.WithContinue<>(
		LogEventsQuery.class, 
		"list",
		"logevents",
		"lecontinue"
	) {
		@Override
		public LogEventsQuery mergeResults(LogEventsQuery a, LogEventsQuery b) {
			a.getLogevents().addAll(b.getLogevents());
			return a;
		}
	};
	
	public static final Query<ImageUsageQuery> LIST_IMAGE_USAGE = new Query.WithContinue<>(
		ImageUsageQuery.class, 
		"list",
		"imageusage",
		"iucontinue"
	) {
		@Override
		public ImageUsageQuery mergeResults(ImageUsageQuery a, ImageUsageQuery b) {
			a.getImageusage().addAll(b.getImageusage());
			return a;
		}
	};
	
	public static final Query<PageQuery> LIST_PAGES_LINKING_TO = new Query.WithContinue<>(
		PageQuery.class, 
		"prop",
		"linkshere",
		"lhcontinue"
	) {
		@Override
		public PageQuery mergeResults(PageQuery a, PageQuery b) {
			a.getPages().addAll(b.getPages());
			return a;
		}
	};
	
	public static final Query<PageInfoQuery> PAGE_INFO = new Query<>(
		PageInfoQuery.class, 
		"prop",
		"info"
	);
	
	public static final Query<AllusersQuery> LIST_ALL_USERS = new Query.WithContinue<>(
			AllusersQuery.class, 
			"list",
			"allusers",
			"aufrom"
	) {
		@Override
		public AllusersQuery mergeResults(AllusersQuery a, AllusersQuery b) {
			a.getAllusers().addAll(b.getAllusers());
			return a;
		}
	};
	
	private final Class<T> responseType;
	private final String what;
	private final String which;
	
	@Override
	public String toString() {
		return "["+what+" "+which+"]";
	}

	public String[] nextPageParams(String[] params, QueryResponse<T> resp) {
		throw new IllegalStateException("continue not supported for "+toString()+", but required");
	}
	
	public T mergeResults(T query, T remainingPages) {
		throw new IllegalStateException("continue not supported for "+toString()+", but required");
	}

	private static abstract class WithContinue<T> extends Query<T> {
		
		private final String continueValue;

		public WithContinue(Class<T> responseType, String what, String which, String continueValue) {
			super(responseType, what, which);
			this.continueValue = continueValue;
		}
		
		@Override
		public String[] nextPageParams(String[] params, QueryResponse<T> resp) {
			var i = ArrayUtils.indexOf(params, continueValue);
			if(i == -1) {
				return ArrayUtils.addAll(
					params,
					new String[]{continueValue, resp.getContinueInfo().getGenericValue(continueValue)}
				);
			}
			var result = Arrays.copyOf(params, params.length);
			result[i+1] = resp.getContinueInfo().getGenericValue(continueValue);
			return result;
			
		}
		
		public abstract T mergeResults(T a, T b);
	}
}
