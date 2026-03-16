package io.github.pfwikis.bots.common.api.model;

public interface ContainsPageRef {
	public PageRef toPageRef();
	
	public default PageTitle toPageTitle() {
		return toPageRef().getTitle();
	}
}