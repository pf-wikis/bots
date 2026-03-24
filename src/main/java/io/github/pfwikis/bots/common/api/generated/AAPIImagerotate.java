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

import io.github.pfwikis.bots.common.api.generated.params.AAPIImagerotateRotation;

import io.github.pfwikis.bots.common.api.generated.params.AAPIImagerotateGenerator;

import io.github.pfwikis.bots.common.api.generated.params.AAPIImagerotateGenerator.AAPIImagerotateGeneratorModule;
import io.github.pfwikis.bots.common.api.generated.params.AAPIImagerotateGenerator.AAPIImagerotateGeneratorSubmodule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** Rotate one or more images.
 */
public class AAPIImagerotate implements AAPIModule, AAPITokenModule, AAPIMainActionModule {

	public static AAPIImagerotate create(@NonNull AAPIImagerotateRotation rotation) {

		AAPIImagerotate v = new AAPIImagerotate();

		v.rotation = rotation;

		return v;
	}

	private AAPIImagerotate() {}

	private AAPIImagerotateRotation rotation;

	private List<String> tags;

	private List<String> titles;

	private List<Long> pageids;

	private List<Long> revids;

	private AAPIImagerotateGeneratorSubmodule generator;

	private Boolean redirects;

	private Boolean converttitles;

	private String token;

	/**Degrees to rotate image clockwise.
	 */
	public AAPIImagerotateRotation getRotation() {
		return this.rotation;
	}

	/**Tags to apply to the entry in the upload log.
	 */
	public AAPIImagerotate tags(String tags) {
		this.tags = List.of(tags);

		return this;
	}

	/**Tags to apply to the entry in the upload log.
	 */
	public AAPIImagerotate tags(String... tags) {
		this.tags = List.of(tags);
		return this;
	}

	/**Tags to apply to the entry in the upload log.
	 */
	public List<String> getTags() {
		return this.tags;
	}

	/**A list of titles to work on.
	 */
	public AAPIImagerotate titles(String titles) {
		this.titles = List.of(titles);

		return this;
	}

	/**A list of titles to work on.
	 */
	public AAPIImagerotate titles(String... titles) {
		this.titles = List.of(titles);
		return this;
	}

	/**A list of titles to work on.
	 */
	public List<String> getTitles() {
		return this.titles;
	}

	/**A list of page IDs to work on.
	 */
	public AAPIImagerotate pageids(Long pageids) {
		this.pageids = List.of(pageids);

		return this;
	}

	/**A list of page IDs to work on.
	 */
	public AAPIImagerotate pageids(Long... pageids) {
		this.pageids = List.of(pageids);
		return this;
	}

	/**A list of page IDs to work on.
	 */
	public List<Long> getPageids() {
		return this.pageids;
	}

	/**A list of revision IDs to work on. Note that almost all query modules will convert revision IDs to the corresponding page ID and work on the latest revision instead. Only <kbd>prop=revisions</kbd> uses exact revisions for its response.
	 */
	public AAPIImagerotate revids(Long revids) {
		this.revids = List.of(revids);

		return this;
	}

	/**A list of revision IDs to work on. Note that almost all query modules will convert revision IDs to the corresponding page ID and work on the latest revision instead. Only <kbd>prop=revisions</kbd> uses exact revisions for its response.
	 */
	public AAPIImagerotate revids(Long... revids) {
		this.revids = List.of(revids);
		return this;
	}

	/**A list of revision IDs to work on. Note that almost all query modules will convert revision IDs to the corresponding page ID and work on the latest revision instead. Only <kbd>prop=revisions</kbd> uses exact revisions for its response.
	 */
	public List<Long> getRevids() {
		return this.revids;
	}

	/**<p>Get the list of pages to work on by executing the specified query module.
	 * </p><p><strong>Note:</strong> Generator parameter names must be prefixed with a "g", see examples.
	 * </p>
	 * <dl></dl>
	 */
	public AAPIImagerotate generator(AAPIImagerotateGeneratorModule generator) {
		this.generator = AAPIImagerotateGenerator.createSubmodule(generator);

		return this;
	}

	/**<p>Get the list of pages to work on by executing the specified query module.
	 * </p><p><strong>Note:</strong> Generator parameter names must be prefixed with a "g", see examples.
	 * </p>
	 * <dl></dl>
	 */
	public AAPIImagerotateGeneratorSubmodule getGenerator() {
		return this.generator;
	}

	/**Automatically resolve redirects in <var>titles</var>, <var>pageids</var>, and <var>revids</var>, and in pages returned by <var>generator</var>.
	 */
	public AAPIImagerotate redirects(Boolean redirects) {
		this.redirects = redirects;

		return this;
	}

	/**Automatically resolve redirects in <var>titles</var>, <var>pageids</var>, and <var>revids</var>, and in pages returned by <var>generator</var>.
	 */
	public Boolean getRedirects() {
		return this.redirects;
	}

	/**Convert titles to other variants if necessary. Only works if the wiki's content language supports variant conversion. Languages that support variant conversion include ban, en, crh, gan, iu, ku, mni, sh, shi, sr, tg, tly, uz, wuu, zgh and zh.
	 */
	public AAPIImagerotate converttitles(Boolean converttitles) {
		this.converttitles = converttitles;

		return this;
	}

	/**Convert titles to other variants if necessary. Only works if the wiki's content language supports variant conversion. Languages that support variant conversion include ban, en, crh, gan, iu, ku, mni, sh, shi, sr, tg, tly, uz, wuu, zgh and zh.
	 */
	public Boolean getConverttitles() {
		return this.converttitles;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public AAPIImagerotate token(String token) {
		this.token = token;

		return this;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public String getToken() {
		return this.token;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIImagerotate(");

		if (rotation != null) {

			sb.append("rotation").append("=").append(rotation.getJsonValue());

			sb.append(", ");
		}

		if (tags != null) {

			sb.append("tags")
					.append("=")
					.append(tags.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (titles != null) {

			sb.append("titles")
					.append("=")
					.append(titles.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (pageids != null) {

			sb.append("pageids")
					.append("=")
					.append(
							pageids.stream()
									.map(v -> v.toString())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (revids != null) {

			sb.append("revids")
					.append("=")
					.append(
							revids.stream()
									.map(v -> v.toString())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (generator != null) {

			sb.append("generator").append("=").append(generator.getValue().toString());

			sb.append(", ");
		}

		if (redirects != null) {

			sb.append("redirects").append("=").append(redirects.toString());

			sb.append(", ");
		}

		if (converttitles != null) {

			sb.append("converttitles").append("=").append(converttitles.toString());

			sb.append(", ");
		}

		if (token != null) {

			sb.append("token").append("=").append(token);

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (rotation != null) {

			req.addParameter(paramPrefix + "rotation", rotation.getJsonValue());
		}

		if (tags != null) {

			req.addParameter(
					paramPrefix + "tags",
					tags.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (titles != null) {

			req.addParameter(
					paramPrefix + "titles",
					titles.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (pageids != null) {

			req.addParameter(
					paramPrefix + "pageids",
					pageids.stream().map(v -> v.toString()).collect(Collectors.joining("|")));
		}

		if (revids != null) {

			req.addParameter(
					paramPrefix + "revids",
					revids.stream().map(v -> v.toString()).collect(Collectors.joining("|")));
		}

		if (generator != null) {

			req.addParameter(paramPrefix + "generator", generator.getKey().getJsonValue());

			generator.getValue().buildRequest(api, req, paramPrefix + "g");
		}

		if (redirects != null) {

			req.addParameter(paramPrefix + "redirects", redirects.toString());
		}

		if (converttitles != null) {

			req.addParameter(paramPrefix + "converttitles", converttitles.toString());
		}

		token = api.requestToken(AAPIQueryTokensType.CSRF);

		if (token != null) {

			req.addParameter(paramPrefix + "token", token);
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
			c.accept(AAPIImagerotate.this);

			if (generator != null) {

				generator.getValue().builder().forEachModule(c);
			}
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIImagerotate.this);

			if (generator != null) {

				value = reduce.apply(value, generator.getValue().builder().mapReduce(map, reduce));
			}

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return true;
		}

		@Override
		protected boolean internalRequiresPagination() {
			return true;
		}
	}
}
