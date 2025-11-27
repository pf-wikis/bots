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
	@JsonSubTypes.Type(value = LdJson.Offer.class, name = "Offer"),
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
    	private Offer offers;
		
    	public String toShortDescription() {
			if(description == null) return null;
			if(description.contains("\n")) return description.substring(0, description.indexOf('\n')).replace("|", "{{!}}");
			return description.replace("|", "{{!}}");
		}
    	
    	public String toLongDescription() {
			if(description == null) return null;
			return description.substring(description.indexOf('\n')+1, description.length()).replace("\n", "\n\n").replace("|", "{{!}}");
		}
    }
    
    @Getter @Setter
    public static class Offer extends LdJson {
    	private String itemCondition;
    	private String price;
    }
}
