package io.github.pfwikis.bots.common.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import io.github.pfwikis.bots.common.api.generated.params.NS;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PageRef implements ContainsPageRef {
	
	private final PageTitle title;
	
	public static PageRef of(@NonNull PageTitle title) {
		return new PageRef(title);
	}
	
	public static PageRef of(@NonNull String title) {
		return new PageRef(PageTitle.of(title));
	}
	
	public static PageRef of(int pageid, @NonNull PageTitle title) {
		return new PageRef.WithId(pageid, title);
	}
	
	public static PageRef of(@NonNull NS ns, @NonNull String name) {
		return new PageRef(PageTitle.of(ns, name));
	}
	
	@JsonCreator
	public static PageRef of(int pageid, @NonNull String title) {
		return new PageRef.WithId(pageid, PageTitle.of(title));
	}
	
	public boolean hasId() {
		return false;
	}
	
	public int getId() {
		throw new UnsupportedOperationException("Page ID is not defined for "+title);
	}
	
	public PageRef toPageRef() {
		return this;
	}
	
	public PageTitle getTitle() {
		return title;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof PageRef p)) return false;
		
		if(hasId() && p.hasId()) return getId()==p.getId();
		return title.equals(p.title);
	}
	
	private static class WithId extends PageRef {
		private final int id;
		
		private WithId(int id, PageTitle title) {
			super(title);
			this.id = id;
		}
		
		public boolean hasId() {
			return true;
		}
		
		public int getId() {
			return id;
		}
	}
}
