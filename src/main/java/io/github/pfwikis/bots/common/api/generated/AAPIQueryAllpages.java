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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryAllpagesFilterredir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryAllpagesFilterlanglinks;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryAllpagesPrtype;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryAllpagesPrlevel;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryAllpagesPrfiltercascade;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryAllpagesPrexpiry;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryAllpagesDir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIImagerotateGenerator.AAPIImagerotateGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIPurgeGenerator.AAPIPurgeGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryGenerator.AAPIQueryGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPISetnotificationtimestampGenerator.AAPISetnotificationtimestampGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPITemplatedataGenerator.AAPITemplatedataGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIWatchGenerator.AAPIWatchGeneratorModule;

/** Enumerate all pages sequentially in a given namespace.
 */
public class AAPIQueryAllpages
		implements AAPIModule,
				AAPIImagerotateGeneratorModule,
				AAPIPurgeGeneratorModule,
				AAPIQueryListModule,
				AAPIQueryGeneratorModule,
				AAPISetnotificationtimestampGeneratorModule,
				AAPITemplatedataGeneratorModule,
				AAPIWatchGeneratorModule {

	public static AAPIQueryAllpages create() {

		AAPIQueryAllpages v = new AAPIQueryAllpages();

		return v;
	}

	private AAPIQueryAllpages() {}

	private String from;

	private String to;

	private String prefix;

	private NS namespace;

	private AAPIQueryAllpagesFilterredir filterredir;

	private AAPIQueryAllpagesFilterlanglinks filterlanglinks;

	private Integer minsize;

	private Integer maxsize;

	private List<AAPIQueryAllpagesPrtype> prtype;

	private List<AAPIQueryAllpagesPrlevel> prlevel;

	private AAPIQueryAllpagesPrfiltercascade prfiltercascade;

	private AAPIQueryAllpagesPrexpiry prexpiry;

	private Integer limit = 5000;

	private AAPIQueryAllpagesDir dir;

	/**The page title to start enumerating from.
	 */
	public AAPIQueryAllpages from(String from) {

		this.from = from;

		return this;
	}

	/**The page title to start enumerating from.
	 */
	public String getFrom() {
		return this.from;
	}

	/**The page title to stop enumerating at.
	 */
	public AAPIQueryAllpages to(String to) {

		this.to = to;

		return this;
	}

	/**The page title to stop enumerating at.
	 */
	public String getTo() {
		return this.to;
	}

	/**Search for all page titles that begin with this value.
	 */
	public AAPIQueryAllpages prefix(String prefix) {

		this.prefix = prefix;

		return this;
	}

	/**Search for all page titles that begin with this value.
	 */
	public String getPrefix() {
		return this.prefix;
	}

	/**The namespace to enumerate.
	 */
	public AAPIQueryAllpages namespace(NS namespace) {

		this.namespace = namespace;

		return this;
	}

	/**The namespace to enumerate.
	 */
	public NS getNamespace() {
		return this.namespace;
	}

	/**Which pages to list.
	 */
	public AAPIQueryAllpages filterredir(AAPIQueryAllpagesFilterredir filterredir) {

		this.filterredir = filterredir;

		return this;
	}

	/**Which pages to list.
	 */
	public AAPIQueryAllpagesFilterredir getFilterredir() {
		return this.filterredir;
	}

	/**Filter based on whether a page has langlinks. Note that this may not consider langlinks added by extensions.
	 */
	public AAPIQueryAllpages filterlanglinks(AAPIQueryAllpagesFilterlanglinks filterlanglinks) {

		this.filterlanglinks = filterlanglinks;

		return this;
	}

	/**Filter based on whether a page has langlinks. Note that this may not consider langlinks added by extensions.
	 */
	public AAPIQueryAllpagesFilterlanglinks getFilterlanglinks() {
		return this.filterlanglinks;
	}

	/**Limit to pages with at least this many bytes.
	 */
	public AAPIQueryAllpages minsize(Integer minsize) {

		this.minsize = minsize;

		return this;
	}

	/**Limit to pages with at least this many bytes.
	 */
	public Integer getMinsize() {
		return this.minsize;
	}

	/**Limit to pages with at most this many bytes.
	 */
	public AAPIQueryAllpages maxsize(Integer maxsize) {

		this.maxsize = maxsize;

		return this;
	}

	/**Limit to pages with at most this many bytes.
	 */
	public Integer getMaxsize() {
		return this.maxsize;
	}

	/**Limit to protected pages only.
	 */
	public AAPIQueryAllpages prtype(AAPIQueryAllpagesPrtype... prtype) {

		this.prtype = List.of(prtype);

		return this;
	}

	/**Limit to protected pages only.
	 */
	public List<AAPIQueryAllpagesPrtype> getPrtype() {
		return this.prtype;
	}

	/**Filter protections based on protection level (must be used with apprtype= parameter).
	 */
	public AAPIQueryAllpages prlevel(AAPIQueryAllpagesPrlevel... prlevel) {

		this.prlevel = List.of(prlevel);

		return this;
	}

	/**Filter protections based on protection level (must be used with apprtype= parameter).
	 */
	public List<AAPIQueryAllpagesPrlevel> getPrlevel() {
		return this.prlevel;
	}

	/**Filter protections based on cascadingness (ignored when apprtype isn't set).
	 */
	public AAPIQueryAllpages prfiltercascade(AAPIQueryAllpagesPrfiltercascade prfiltercascade) {

		this.prfiltercascade = prfiltercascade;

		return this;
	}

	/**Filter protections based on cascadingness (ignored when apprtype isn't set).
	 */
	public AAPIQueryAllpagesPrfiltercascade getPrfiltercascade() {
		return this.prfiltercascade;
	}

	/**<p>Which protection expiry to filter the page on:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryAllpages prexpiry(AAPIQueryAllpagesPrexpiry prexpiry) {

		this.prexpiry = prexpiry;

		return this;
	}

	/**<p>Which protection expiry to filter the page on:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryAllpagesPrexpiry getPrexpiry() {
		return this.prexpiry;
	}

	/**How many total pages to return.
	 */
	public AAPIQueryAllpages limit(Integer limit) {

		this.limit = limit;

		return this;
	}

	/**How many total pages to return.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	/**The direction in which to list.
	 */
	public AAPIQueryAllpages dir(AAPIQueryAllpagesDir dir) {

		this.dir = dir;

		return this;
	}

	/**The direction in which to list.
	 */
	public AAPIQueryAllpagesDir getDir() {
		return this.dir;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryAllpages(");

		if (from != null) {

			sb.append("apfrom").append("=").append(from);

			sb.append(", ");
		}

		if (to != null) {

			sb.append("apto").append("=").append(to);

			sb.append(", ");
		}

		if (prefix != null) {

			sb.append("apprefix").append("=").append(prefix);

			sb.append(", ");
		}

		if (namespace != null) {

			sb.append("apnamespace").append("=").append(Integer.toString(namespace.getId()));

			sb.append(", ");
		}

		if (filterredir != null) {

			sb.append("apfilterredir").append("=").append(filterredir.getJsonValue());

			sb.append(", ");
		}

		if (filterlanglinks != null) {

			sb.append("apfilterlanglinks").append("=").append(filterlanglinks.getJsonValue());

			sb.append(", ");
		}

		if (minsize != null) {

			sb.append("apminsize").append("=").append(minsize.toString());

			sb.append(", ");
		}

		if (maxsize != null) {

			sb.append("apmaxsize").append("=").append(maxsize.toString());

			sb.append(", ");
		}

		if (prtype != null) {

			sb.append("apprtype")
					.append("=")
					.append(
							prtype.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (prlevel != null) {

			sb.append("apprlevel")
					.append("=")
					.append(
							prlevel.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (prfiltercascade != null) {

			sb.append("apprfiltercascade").append("=").append(prfiltercascade.getJsonValue());

			sb.append(", ");
		}

		if (prexpiry != null) {

			sb.append("apprexpiry").append("=").append(prexpiry.getJsonValue());

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("aplimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (dir != null) {

			sb.append("apdir").append("=").append(dir.getJsonValue());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (from != null) {

			req.addParameter(paramPrefix + "apfrom", from);
		}

		if (to != null) {

			req.addParameter(paramPrefix + "apto", to);
		}

		if (prefix != null) {

			req.addParameter(paramPrefix + "apprefix", prefix);
		}

		if (namespace != null) {

			req.addParameter(paramPrefix + "apnamespace", Integer.toString(namespace.getId()));
		}

		if (filterredir != null) {

			req.addParameter(paramPrefix + "apfilterredir", filterredir.getJsonValue());
		}

		if (filterlanglinks != null) {

			req.addParameter(paramPrefix + "apfilterlanglinks", filterlanglinks.getJsonValue());
		}

		if (minsize != null) {

			req.addParameter(paramPrefix + "apminsize", minsize.toString());
		}

		if (maxsize != null) {

			req.addParameter(paramPrefix + "apmaxsize", maxsize.toString());
		}

		if (prtype != null) {

			req.addParameter(
					paramPrefix + "apprtype",
					prtype.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (prlevel != null) {

			req.addParameter(
					paramPrefix + "apprlevel",
					prlevel.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (prfiltercascade != null) {

			req.addParameter(paramPrefix + "apprfiltercascade", prfiltercascade.getJsonValue());
		}

		if (prexpiry != null) {

			req.addParameter(paramPrefix + "apprexpiry", prexpiry.getJsonValue());
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "aplimit", limit.toString());
		}

		if (dir != null) {

			req.addParameter(paramPrefix + "apdir", dir.getJsonValue());
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
			c.accept(AAPIQueryAllpages.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryAllpages.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return false;
		}
	}
}
