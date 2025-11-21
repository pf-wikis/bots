package io.github.pfwikis.bots.paizoretriever;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.Getter;
import lombok.Setter;

@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.EXISTING_PROPERTY,
		property = "@type",
		visible = true,
		defaultImpl = LdJson.class)
@JsonSubTypes({
	@JsonSubTypes.Type(value = LdJson.Product.class, name = "Product"),
})
@Getter @Setter
public class LdJson {
	
	@JsonProperty("@context")
	private String ldContext;
	
	@JsonProperty("@type")
	private String type;
	
	@Getter(onMethod_=@JsonAnyGetter)
    private Map<String, JsonNode> unknownFields = new HashMap<>();

    @JsonAnySetter
    public void setOtherField(String name, JsonNode value) {
        unknownFields.put(name, value);
    }
    
    @Getter @Setter
    public static class Product extends LdJson {
    	private String name;
    	private String url;
    	private String sku;
    	private String description;
    	private String image;
    	
    }
}
