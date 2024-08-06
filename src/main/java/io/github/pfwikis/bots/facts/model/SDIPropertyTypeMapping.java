package io.github.pfwikis.bots.facts.model;

import io.github.pfwikis.bots.common.model.SemanticSubject.PageRef;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SDIPropertyTypeMapping<TYPE> {

	public static SDIPropertyTypeMapping<String> FULL_TITLE = prop(String.class, "Full_title");
	public static SDIPropertyTypeMapping<String> NAME = prop(String.class, "Name");
	public static SDIPropertyTypeMapping<String> WEBSITE = prop(String.class, "Website");
	public static SDIPropertyTypeMapping<String> PUBCODE = prop(String.class, "Pubcode");
	public static SDIPropertyTypeMapping<PageRef> IMAGE = prop(PageRef.class, "Image");
	public static SDIPropertyTypeMapping<PageRef> FACT_TYPE = prop(PageRef.class, "Fact_type");
	public static SDIPropertyTypeMapping<PageRef> GALLERY_PAGE = prop(PageRef.class, "Gallery_page");
			
			
	private final Class<TYPE> type;
	private final String property;
	
	public static <T> SDIPropertyTypeMapping<T> prop(Class<T> type, String property) {
		return new SDIPropertyTypeMapping<>(type, property);
	}
}
