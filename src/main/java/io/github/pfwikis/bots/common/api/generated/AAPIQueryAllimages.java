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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryAllimagesSort;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryAllimagesDir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryAllimagesProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryAllimagesFilterbots;

import io.github.pfwikis.bots.common.api.generated.params.AAPIImagerotateGenerator.AAPIImagerotateGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIPurgeGenerator.AAPIPurgeGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryGenerator.AAPIQueryGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPISetnotificationtimestampGenerator.AAPISetnotificationtimestampGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPITemplatedataGenerator.AAPITemplatedataGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIWatchGenerator.AAPIWatchGeneratorModule;

/** Enumerate all images sequentially.
 */
public class AAPIQueryAllimages
		implements AAPIModule,
				AAPIImagerotateGeneratorModule,
				AAPIPurgeGeneratorModule,
				AAPIQueryListModule,
				AAPIQueryGeneratorModule,
				AAPISetnotificationtimestampGeneratorModule,
				AAPITemplatedataGeneratorModule,
				AAPIWatchGeneratorModule {

	public static AAPIQueryAllimages create() {

		AAPIQueryAllimages v = new AAPIQueryAllimages();

		return v;
	}

	private AAPIQueryAllimages() {}

	private AAPIQueryAllimagesSort sort;

	private AAPIQueryAllimagesDir dir;

	private String from;

	private String to;

	private java.time.Instant start;

	private java.time.Instant end;

	private List<AAPIQueryAllimagesProp> prop;

	private String prefix;

	private Long minsize;

	private Long maxsize;

	private String sha1;

	private String sha1base36;

	private String user;

	private AAPIQueryAllimagesFilterbots filterbots;

	private List<String> mime;

	private Integer limit;

	/**Property to sort by.
	 */
	public AAPIQueryAllimages sort(AAPIQueryAllimagesSort sort) {
		this.sort = sort;

		return this;
	}

	/**Property to sort by.
	 */
	public AAPIQueryAllimagesSort getSort() {
		return this.sort;
	}

	/**The direction in which to list.
	 */
	public AAPIQueryAllimages dir(AAPIQueryAllimagesDir dir) {
		this.dir = dir;

		return this;
	}

	/**The direction in which to list.
	 */
	public AAPIQueryAllimagesDir getDir() {
		return this.dir;
	}

	/**The image title to start enumerating from. Can only be used with aisort=name.
	 */
	public AAPIQueryAllimages from(String from) {
		this.from = from;

		return this;
	}

	/**The image title to start enumerating from. Can only be used with aisort=name.
	 */
	public String getFrom() {
		return this.from;
	}

	/**The image title to stop enumerating at. Can only be used with aisort=name.
	 */
	public AAPIQueryAllimages to(String to) {
		this.to = to;

		return this;
	}

	/**The image title to stop enumerating at. Can only be used with aisort=name.
	 */
	public String getTo() {
		return this.to;
	}

	/**The timestamp to start enumerating from. Can only be used with aisort=timestamp.
	 */
	public AAPIQueryAllimages start(java.time.Instant start) {
		this.start = start;

		return this;
	}

	/**The timestamp to start enumerating from. Can only be used with aisort=timestamp.
	 */
	public java.time.Instant getStart() {
		return this.start;
	}

	/**The timestamp to end enumerating. Can only be used with aisort=timestamp.
	 */
	public AAPIQueryAllimages end(java.time.Instant end) {
		this.end = end;

		return this;
	}

	/**The timestamp to end enumerating. Can only be used with aisort=timestamp.
	 */
	public java.time.Instant getEnd() {
		return this.end;
	}

	/**<p>Which file information to get:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryAllimages prop(AAPIQueryAllimagesProp prop) {
		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which file information to get:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryAllimages prop(AAPIQueryAllimagesProp... prop) {
		this.prop = List.of(prop);
		return this;
	}

	/**<p>Which file information to get:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryAllimagesProp> getProp() {
		return this.prop;
	}

	/**Search for all image titles that begin with this value. Can only be used with aisort=name.
	 */
	public AAPIQueryAllimages prefix(String prefix) {
		this.prefix = prefix;

		return this;
	}

	/**Search for all image titles that begin with this value. Can only be used with aisort=name.
	 */
	public String getPrefix() {
		return this.prefix;
	}

	/**Limit to images with at least this many bytes.
	 */
	public AAPIQueryAllimages minsize(Long minsize) {
		this.minsize = minsize;

		return this;
	}

	/**Limit to images with at least this many bytes.
	 */
	public Long getMinsize() {
		return this.minsize;
	}

	/**Limit to images with at most this many bytes.
	 */
	public AAPIQueryAllimages maxsize(Long maxsize) {
		this.maxsize = maxsize;

		return this;
	}

	/**Limit to images with at most this many bytes.
	 */
	public Long getMaxsize() {
		return this.maxsize;
	}

	/**SHA1 hash of image. Overrides aisha1base36.
	 */
	public AAPIQueryAllimages sha1(String sha1) {
		this.sha1 = sha1;

		return this;
	}

	/**SHA1 hash of image. Overrides aisha1base36.
	 */
	public String getSha1() {
		return this.sha1;
	}

	/**SHA1 hash of image in base 36 (used in MediaWiki).
	 */
	public AAPIQueryAllimages sha1base36(String sha1base36) {
		this.sha1base36 = sha1base36;

		return this;
	}

	/**SHA1 hash of image in base 36 (used in MediaWiki).
	 */
	public String getSha1base36() {
		return this.sha1base36;
	}

	/**Only return files where the last version was uploaded by this user. Can only be used with aisort=timestamp. Cannot be used together with aifilterbots.
	 */
	public AAPIQueryAllimages user(String user) {
		this.user = user;

		return this;
	}

	/**Only return files where the last version was uploaded by this user. Can only be used with aisort=timestamp. Cannot be used together with aifilterbots.
	 */
	public String getUser() {
		return this.user;
	}

	/**How to filter files uploaded by bots. Can only be used with aisort=timestamp. Cannot be used together with aiuser.
	 */
	public AAPIQueryAllimages filterbots(AAPIQueryAllimagesFilterbots filterbots) {
		this.filterbots = filterbots;

		return this;
	}

	/**How to filter files uploaded by bots. Can only be used with aisort=timestamp. Cannot be used together with aiuser.
	 */
	public AAPIQueryAllimagesFilterbots getFilterbots() {
		return this.filterbots;
	}

	/**What MIME types to search for, e.g. <kbd>image/jpeg</kbd>.
	 */
	public AAPIQueryAllimages mime(String mime) {
		this.mime = List.of(mime);

		return this;
	}

	/**What MIME types to search for, e.g. <kbd>image/jpeg</kbd>.
	 */
	public AAPIQueryAllimages mime(String... mime) {
		this.mime = List.of(mime);
		return this;
	}

	/**What MIME types to search for, e.g. <kbd>image/jpeg</kbd>.
	 */
	public List<String> getMime() {
		return this.mime;
	}

	/**How many images in total to return.
	 */
	public AAPIQueryAllimages limit(Integer limit) {
		this.limit = limit;

		return this;
	}

	/**How many images in total to return.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryAllimages(");

		if (sort != null) {

			sb.append("aisort").append("=").append(sort.getJsonValue());

			sb.append(", ");
		}

		if (dir != null) {

			sb.append("aidir").append("=").append(dir.getJsonValue());

			sb.append(", ");
		}

		if (from != null) {

			sb.append("aifrom").append("=").append(from);

			sb.append(", ");
		}

		if (to != null) {

			sb.append("aito").append("=").append(to);

			sb.append(", ");
		}

		if (start != null) {

			sb.append("aistart")
					.append("=")
					.append(start.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());

			sb.append(", ");
		}

		if (end != null) {

			sb.append("aiend")
					.append("=")
					.append(end.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());

			sb.append(", ");
		}

		if (prop != null) {

			sb.append("aiprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (prefix != null) {

			sb.append("aiprefix").append("=").append(prefix);

			sb.append(", ");
		}

		if (minsize != null) {

			sb.append("aiminsize").append("=").append(minsize.toString());

			sb.append(", ");
		}

		if (maxsize != null) {

			sb.append("aimaxsize").append("=").append(maxsize.toString());

			sb.append(", ");
		}

		if (sha1 != null) {

			sb.append("aisha1").append("=").append(sha1);

			sb.append(", ");
		}

		if (sha1base36 != null) {

			sb.append("aisha1base36").append("=").append(sha1base36);

			sb.append(", ");
		}

		if (user != null) {

			sb.append("aiuser").append("=").append(user);

			sb.append(", ");
		}

		if (filterbots != null) {

			sb.append("aifilterbots").append("=").append(filterbots.getJsonValue());

			sb.append(", ");
		}

		if (mime != null) {

			sb.append("aimime")
					.append("=")
					.append(mime.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("ailimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (sort != null) {

			req.addParameter(paramPrefix + "aisort", sort.getJsonValue());
		}

		if (dir != null) {

			req.addParameter(paramPrefix + "aidir", dir.getJsonValue());
		}

		if (from != null) {

			req.addParameter(paramPrefix + "aifrom", from);
		}

		if (to != null) {

			req.addParameter(paramPrefix + "aito", to);
		}

		if (start != null) {

			req.addParameter(
					paramPrefix + "aistart",
					start.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());
		}

		if (end != null) {

			req.addParameter(
					paramPrefix + "aiend",
					end.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());
		}

		if (prop != null) {

			req.addParameter(
					paramPrefix + "aiprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (prefix != null) {

			req.addParameter(paramPrefix + "aiprefix", prefix);
		}

		if (minsize != null) {

			req.addParameter(paramPrefix + "aiminsize", minsize.toString());
		}

		if (maxsize != null) {

			req.addParameter(paramPrefix + "aimaxsize", maxsize.toString());
		}

		if (sha1 != null) {

			req.addParameter(paramPrefix + "aisha1", sha1);
		}

		if (sha1base36 != null) {

			req.addParameter(paramPrefix + "aisha1base36", sha1base36);
		}

		if (user != null) {

			req.addParameter(paramPrefix + "aiuser", user);
		}

		if (filterbots != null) {

			req.addParameter(paramPrefix + "aifilterbots", filterbots.getJsonValue());
		}

		if (mime != null) {

			req.addParameter(
					paramPrefix + "aimime",
					mime.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "ailimit", limit.toString());

		} else {
			req.addParameter(paramPrefix + "ailimit", "5000");
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
			c.accept(AAPIQueryAllimages.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryAllimages.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return false;
		}

		@Override
		protected boolean internalRequiresPagination() {
			return limit == null;
		}
	}
}
