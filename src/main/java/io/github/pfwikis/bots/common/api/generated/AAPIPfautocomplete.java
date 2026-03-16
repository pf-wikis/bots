package io.github.pfwikis.bots.common.api.generated;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.NonNull;

import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;

import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import io.github.pfwikis.bots.common.api.model.AAPITokenModule;
import io.github.pfwikis.bots.common.api.model.ContainsPageRef;
import io.github.pfwikis.bots.common.api.AAPI;
import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryTokensType;
import io.github.pfwikis.bots.common.api.generated.params.NS;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** Autocompletion used by the Page Forms extension.
 */
public class AAPIPfautocomplete implements AAPIModule, AAPIMainActionModule {

	public static AAPIPfautocomplete create() {

		AAPIPfautocomplete v = new AAPIPfautocomplete();

		return v;
	}

	private AAPIPfautocomplete() {}

	private Integer limit;

	private String substr;

	private String property;

	private String category;

	private String concept;

	private String wikidata;

	private String semantic_query;

	private String cargo_table;

	private String cargo_field;

	private String cargo_where;

	private String namespace;

	private String external_url;

	private String baseprop;

	private String base_cargo_table;

	private String base_cargo_field;

	private String basevalue;

	/**A limit on the number of results returned
	 */
	public AAPIPfautocomplete limit(Integer limit) {

		this.limit = limit;

		return this;
	}

	/**A limit on the number of results returned
	 */
	public Integer getLimit() {
		return this.limit;
	}

	/**The substring to autocomplete on
	 */
	public AAPIPfautocomplete substr(String substr) {

		this.substr = substr;

		return this;
	}

	/**The substring to autocomplete on
	 */
	public String getSubstr() {
		return this.substr;
	}

	/**A Semantic MediaWiki property whose values will be autocompleted on
	 */
	public AAPIPfautocomplete property(String property) {

		this.property = property;

		return this;
	}

	/**A Semantic MediaWiki property whose values will be autocompleted on
	 */
	public String getProperty() {
		return this.property;
	}

	/**A category whose pages will be autocompleted on
	 */
	public AAPIPfautocomplete category(String category) {

		this.category = category;

		return this;
	}

	/**A category whose pages will be autocompleted on
	 */
	public String getCategory() {
		return this.category;
	}

	/**A Semantic MediaWiki "concept" whose pages will be autocompleted on
	 */
	public AAPIPfautocomplete concept(String concept) {

		this.concept = concept;

		return this;
	}

	/**A Semantic MediaWiki "concept" whose pages will be autocompleted on
	 */
	public String getConcept() {
		return this.concept;
	}

	/**Search string for getting values from Wikidata
	 */
	public AAPIPfautocomplete wikidata(String wikidata) {

		this.wikidata = wikidata;

		return this;
	}

	/**Search string for getting values from Wikidata
	 */
	public String getWikidata() {
		return this.wikidata;
	}

	/**A Semantic MediaWiki query whose results will be autocompleted on
	 */
	public AAPIPfautocomplete semantic_query(String semantic_query) {

		this.semantic_query = semantic_query;

		return this;
	}

	/**A Semantic MediaWiki query whose results will be autocompleted on
	 */
	public String getSemantic_query() {
		return this.semantic_query;
	}

	/**A database table, defined by the Cargo extension, whose values will be autocompleted on
	 */
	public AAPIPfautocomplete cargo_table(String cargo_table) {

		this.cargo_table = cargo_table;

		return this;
	}

	/**A database table, defined by the Cargo extension, whose values will be autocompleted on
	 */
	public String getCargo_table() {
		return this.cargo_table;
	}

	/**The field of the Cargo table whose values will be autocompleted on
	 */
	public AAPIPfautocomplete cargo_field(String cargo_field) {

		this.cargo_field = cargo_field;

		return this;
	}

	/**The field of the Cargo table whose values will be autocompleted on
	 */
	public String getCargo_field() {
		return this.cargo_field;
	}

	/**A filter to apply, if the cargo_table and cargo_field parameters are set
	 */
	public AAPIPfautocomplete cargo_where(String cargo_where) {

		this.cargo_where = cargo_where;

		return this;
	}

	/**A filter to apply, if the cargo_table and cargo_field parameters are set
	 */
	public String getCargo_where() {
		return this.cargo_where;
	}

	/**A namespace whose pages will be autocompleted on
	 */
	public AAPIPfautocomplete namespace(String namespace) {

		this.namespace = namespace;

		return this;
	}

	/**A namespace whose pages will be autocompleted on
	 */
	public String getNamespace() {
		return this.namespace;
	}

	/**An alias for an external URL from which to get values
	 */
	public AAPIPfautocomplete external_url(String external_url) {

		this.external_url = external_url;

		return this;
	}

	/**An alias for an external URL from which to get values
	 */
	public String getExternal_url() {
		return this.external_url;
	}

	/**A previous Semantic MediaWiki property in the form to check against
	 */
	public AAPIPfautocomplete baseprop(String baseprop) {

		this.baseprop = baseprop;

		return this;
	}

	/**A previous Semantic MediaWiki property in the form to check against
	 */
	public String getBaseprop() {
		return this.baseprop;
	}

	/**The Cargo table for a previous field in the form to check against
	 */
	public AAPIPfautocomplete base_cargo_table(String base_cargo_table) {

		this.base_cargo_table = base_cargo_table;

		return this;
	}

	/**The Cargo table for a previous field in the form to check against
	 */
	public String getBase_cargo_table() {
		return this.base_cargo_table;
	}

	/**The Cargo field for a previous field in the form to check against
	 */
	public AAPIPfautocomplete base_cargo_field(String base_cargo_field) {

		this.base_cargo_field = base_cargo_field;

		return this;
	}

	/**The Cargo field for a previous field in the form to check against
	 */
	public String getBase_cargo_field() {
		return this.base_cargo_field;
	}

	/**The value to check for the previous property or field
	 */
	public AAPIPfautocomplete basevalue(String basevalue) {

		this.basevalue = basevalue;

		return this;
	}

	/**The value to check for the previous property or field
	 */
	public String getBasevalue() {
		return this.basevalue;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIPfautocomplete(");

		if (limit != null) {

			sb.append("limit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (substr != null) {

			sb.append("substr").append("=").append(substr);

			sb.append(", ");
		}

		if (property != null) {

			sb.append("property").append("=").append(property);

			sb.append(", ");
		}

		if (category != null) {

			sb.append("category").append("=").append(category);

			sb.append(", ");
		}

		if (concept != null) {

			sb.append("concept").append("=").append(concept);

			sb.append(", ");
		}

		if (wikidata != null) {

			sb.append("wikidata").append("=").append(wikidata);

			sb.append(", ");
		}

		if (semantic_query != null) {

			sb.append("semantic_query").append("=").append(semantic_query);

			sb.append(", ");
		}

		if (cargo_table != null) {

			sb.append("cargo_table").append("=").append(cargo_table);

			sb.append(", ");
		}

		if (cargo_field != null) {

			sb.append("cargo_field").append("=").append(cargo_field);

			sb.append(", ");
		}

		if (cargo_where != null) {

			sb.append("cargo_where").append("=").append(cargo_where);

			sb.append(", ");
		}

		if (namespace != null) {

			sb.append("namespace").append("=").append(namespace);

			sb.append(", ");
		}

		if (external_url != null) {

			sb.append("external_url").append("=").append(external_url);

			sb.append(", ");
		}

		if (baseprop != null) {

			sb.append("baseprop").append("=").append(baseprop);

			sb.append(", ");
		}

		if (base_cargo_table != null) {

			sb.append("base_cargo_table").append("=").append(base_cargo_table);

			sb.append(", ");
		}

		if (base_cargo_field != null) {

			sb.append("base_cargo_field").append("=").append(base_cargo_field);

			sb.append(", ");
		}

		if (basevalue != null) {

			sb.append("basevalue").append("=").append(basevalue);

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (limit != null) {

			req.addParameter(paramPrefix + "limit", limit.toString());
		}

		if (substr != null) {

			req.addParameter(paramPrefix + "substr", substr);
		}

		if (property != null) {

			req.addParameter(paramPrefix + "property", property);
		}

		if (category != null) {

			req.addParameter(paramPrefix + "category", category);
		}

		if (concept != null) {

			req.addParameter(paramPrefix + "concept", concept);
		}

		if (wikidata != null) {

			req.addParameter(paramPrefix + "wikidata", wikidata);
		}

		if (semantic_query != null) {

			req.addParameter(paramPrefix + "semantic_query", semantic_query);
		}

		if (cargo_table != null) {

			req.addParameter(paramPrefix + "cargo_table", cargo_table);
		}

		if (cargo_field != null) {

			req.addParameter(paramPrefix + "cargo_field", cargo_field);
		}

		if (cargo_where != null) {

			req.addParameter(paramPrefix + "cargo_where", cargo_where);
		}

		if (namespace != null) {

			req.addParameter(paramPrefix + "namespace", namespace);
		}

		if (external_url != null) {

			req.addParameter(paramPrefix + "external_url", external_url);
		}

		if (baseprop != null) {

			req.addParameter(paramPrefix + "baseprop", baseprop);
		}

		if (base_cargo_table != null) {

			req.addParameter(paramPrefix + "base_cargo_table", base_cargo_table);
		}

		if (base_cargo_field != null) {

			req.addParameter(paramPrefix + "base_cargo_field", base_cargo_field);
		}

		if (basevalue != null) {

			req.addParameter(paramPrefix + "basevalue", basevalue);
		}
	}

	private final Builder builder = new Builder();

	@Override
	public Builder builder() {
		return builder;
	}

	private class Builder extends AAPIModule.Builder {
		@Override
		public void forEachModule(Consumer<AAPIModule> c) {
			c.accept(AAPIPfautocomplete.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIPfautocomplete.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return false;
		}
	}
}
