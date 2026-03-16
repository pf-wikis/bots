package io.github.pfwikis.bots.common.api.generator.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SequencedMap;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fizzed.rocker.RockerContent;
import com.google.common.base.CaseFormat;

import io.github.pfwikis.bots.utils.Jackson;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;

@Data
public class GenAPIParameter {
	@JsonBackReference
	private GenAPIModule module;
	private int index;
	private String name;
	private String description;
	private GenAPIParameterType type;
	private boolean required;
	@JsonProperty("default")
	private JsonNode defaultValue;
	private boolean deprecated;
	private boolean multi;
	private Integer lowlimit;
	private Integer highlimit;
	private Integer limit;
	private Integer min;
	private Integer max;
	private Integer highmax;
	private SequencedMap<String, String> submodules;
	private List<String> internalvalues;
	private List<String> deprecatedvalues;
	private List<String> enumValues;
	private List<String> subtypes;
	private boolean mustExist;
	private boolean sensitive;
	private String allspecifier;
	private GenAPITokenType tokentype;
	private Integer maxchars;
	private Integer maxbytes;
	private String submoduleparamprefix;
	private boolean allowsduplicates;
	private List<JsonNode> info;
	private int[] extranamespaces;
	
	public void setType(JsonNode type) throws JacksonException, IllegalArgumentException {
		if(type.isArray() && !type.isEmpty()) {
			this.type = GenAPIParameterType.ENUM;
			this.enumValues = List.of(Jackson.JSON.treeToValue(type, String[].class));
		}
		else if(type.isArray() && type.isEmpty()) {
			this.type = GenAPIParameterType.STRING;
		}
		else {
			this.type = Jackson.JSON.treeToValue(type, GenAPIParameterType.class);
		}
	}

	
	
	@RequiredArgsConstructor
	public static enum GenAPIParameterType {
		ENUM("ENUM", null) {
			@Override
			public String codeToGetJsonValue(String name) {
				return name+".getJsonValue()";
			}
		},
		INTEGER("integer", "Long") {
			@Override
			public String codeToGetJsonValue(String name) {
				return name+".toString()";
			}
		},
		STRING("string", "String"),
		TEXT("text", "String"),
		USERT("user", "String"),
		BOOLEAN("boolean", "Boolean") {
			@Override
			public String codeToGetJsonValue(String name) {
				return name+".toString()";
			}
		},
		TITLE("title", "String"),
		NAMESPACE("namespace", "NS") {
			@Override
			public String codeToGetJsonValue(String name) {
				return "Integer.toString("+name+".getId())";
			}
		},
		TIMESTAMP("timestamp", "java.time.Instant") {
			@Override
			public String codeToGetJsonValue(String name) {
				return name+".truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString()";
			}
		},
		UPLOAD("upload", "byte[]") {
			@Override
			public String codeToGetJsonValue(String name) {
				return name+".toString()";
			}
		},
		PASSWORD("password", "String"),
		LIMIT("limit", "Integer") {
			@Override
			public String codeToGetJsonValue(String name) {
				return name+".toString()";
			}
		},
		RAW("raw", "String");
		
		@Getter(onMethod_=@JsonValue)
		private final String jsonValue;
		@Getter
		private final String javaType;
		
		public String codeToGetJsonValue(String name) {
			return name;
		}
	}
	
	@RequiredArgsConstructor
	public static enum GenAPITokenType {
		CSRF("csrf"),
		LOGIN("login"),
		CREATEACCOUNT("createaccount"),
		PATROL("patrol"),
		ROLLBACK("rollback"),
		USERRIGHTS("userrights"),
		WATCH("watch");
		
		@Getter(onMethod_=@JsonValue)
		private final String jsonValue;
	}
}