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

import io.github.pfwikis.bots.common.api.generated.params.AAPIUploadWatchlist;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** <p>Upload a file, or get the status of pending uploads.
 * </p>
 * <p>Several methods are available:
 * </p>
 * <ul><li>Upload file contents directly, using the <var>file</var> parameter.</li>
 * <li>Upload the file in pieces, using the <var>filesize</var>, <var>chunk</var>, and <var>offset</var> parameters.</li>
 * <li>Have the MediaWiki server fetch a file from a URL, using the <var>url</var> parameter.</li>
 * <li>Complete an earlier upload that failed due to warnings, using the <var>filekey</var> parameter.</li></ul>
 * <p>Note that the HTTP POST must be done as a file upload (i.e. using <code>multipart/form-data</code>) when sending the <var>file</var>.
 * </p>
 */
public class AAPIUpload implements AAPIModule, AAPITokenModule, AAPIMainActionModule {

	public static AAPIUpload create() {

		AAPIUpload v = new AAPIUpload();

		return v;
	}

	private AAPIUpload() {}

	private String filename;

	private String comment;

	private List<String> tags;

	private String text;

	private AAPIUploadWatchlist watchlist;

	private Boolean ignorewarnings;

	private byte[] file;

	private String url;

	private String filekey;

	private Boolean stash;

	private Long filesize;

	private Long offset;

	private byte[] chunk;

	private Boolean async;

	private Boolean checkstatus;

	private String token;

	/**Target filename.
	 */
	public AAPIUpload filename(String filename) {

		this.filename = filename;

		return this;
	}

	/**Target filename.
	 */
	public String getFilename() {
		return this.filename;
	}

	/**Upload comment. Also used as the initial page text for new files if <var>text</var> is not specified.
	 */
	public AAPIUpload comment(String comment) {

		this.comment = comment;

		return this;
	}

	/**Upload comment. Also used as the initial page text for new files if <var>text</var> is not specified.
	 */
	public String getComment() {
		return this.comment;
	}

	/**Change tags to apply to the upload log entry and file page revision.
	 */
	public AAPIUpload tags(String... tags) {

		this.tags = List.of(tags);

		return this;
	}

	/**Change tags to apply to the upload log entry and file page revision.
	 */
	public List<String> getTags() {
		return this.tags;
	}

	/**Initial page text for new files.
	 */
	public AAPIUpload text(String text) {

		this.text = text;

		return this;
	}

	/**Initial page text for new files.
	 */
	public String getText() {
		return this.text;
	}

	/**Unconditionally add or remove the page from the current user's watchlist, use preferences (ignored for bot users) or do not change watch.
	 */
	public AAPIUpload watchlist(AAPIUploadWatchlist watchlist) {

		this.watchlist = watchlist;

		return this;
	}

	/**Unconditionally add or remove the page from the current user's watchlist, use preferences (ignored for bot users) or do not change watch.
	 */
	public AAPIUploadWatchlist getWatchlist() {
		return this.watchlist;
	}

	/**Ignore any warnings.
	 */
	public AAPIUpload ignorewarnings(Boolean ignorewarnings) {

		this.ignorewarnings = ignorewarnings;

		return this;
	}

	/**Ignore any warnings.
	 */
	public Boolean getIgnorewarnings() {
		return this.ignorewarnings;
	}

	/**File contents.
	 */
	public AAPIUpload file(byte[] file) {

		this.file = file;

		return this;
	}

	/**File contents.
	 */
	public byte[] getFile() {
		return this.file;
	}

	/**URL to fetch the file from.
	 */
	public AAPIUpload url(String url) {

		this.url = url;

		return this;
	}

	/**URL to fetch the file from.
	 */
	public String getUrl() {
		return this.url;
	}

	/**Key that identifies a previous upload that was stashed temporarily.
	 */
	public AAPIUpload filekey(String filekey) {

		this.filekey = filekey;

		return this;
	}

	/**Key that identifies a previous upload that was stashed temporarily.
	 */
	public String getFilekey() {
		return this.filekey;
	}

	/**If set, the server will stash the file temporarily instead of adding it to the repository.
	 */
	public AAPIUpload stash(Boolean stash) {

		this.stash = stash;

		return this;
	}

	/**If set, the server will stash the file temporarily instead of adding it to the repository.
	 */
	public Boolean getStash() {
		return this.stash;
	}

	/**Filesize of entire upload.
	 */
	public AAPIUpload filesize(Long filesize) {

		this.filesize = filesize;

		return this;
	}

	/**Filesize of entire upload.
	 */
	public Long getFilesize() {
		return this.filesize;
	}

	/**Offset of chunk in bytes.
	 */
	public AAPIUpload offset(Long offset) {

		this.offset = offset;

		return this;
	}

	/**Offset of chunk in bytes.
	 */
	public Long getOffset() {
		return this.offset;
	}

	/**Chunk contents.
	 */
	public AAPIUpload chunk(byte[] chunk) {

		this.chunk = chunk;

		return this;
	}

	/**Chunk contents.
	 */
	public byte[] getChunk() {
		return this.chunk;
	}

	/**Make potentially large file operations asynchronous when possible.
	 */
	public AAPIUpload async(Boolean async) {

		this.async = async;

		return this;
	}

	/**Make potentially large file operations asynchronous when possible.
	 */
	public Boolean getAsync() {
		return this.async;
	}

	/**Only fetch the upload status for the given file key.
	 */
	public AAPIUpload checkstatus(Boolean checkstatus) {

		this.checkstatus = checkstatus;

		return this;
	}

	/**Only fetch the upload status for the given file key.
	 */
	public Boolean getCheckstatus() {
		return this.checkstatus;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public AAPIUpload token(String token) {

		this.token = token;

		return this;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public String getToken() {
		return this.token;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIUpload(");

		if (filename != null) {

			sb.append("filename").append("=").append(filename);

			sb.append(", ");
		}

		if (comment != null) {

			sb.append("comment").append("=").append(comment);

			sb.append(", ");
		}

		if (tags != null) {

			sb.append("tags")
					.append("=")
					.append(tags.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (text != null) {

			sb.append("text").append("=").append(text);

			sb.append(", ");
		}

		if (watchlist != null) {

			sb.append("watchlist").append("=").append(watchlist.getJsonValue());

			sb.append(", ");
		}

		if (ignorewarnings != null) {

			sb.append("ignorewarnings").append("=").append(ignorewarnings.toString());

			sb.append(", ");
		}

		if (file != null) {

			sb.append("file").append("=").append(file.toString());

			sb.append(", ");
		}

		if (url != null) {

			sb.append("url").append("=").append(url);

			sb.append(", ");
		}

		if (filekey != null) {

			sb.append("filekey").append("=").append(filekey);

			sb.append(", ");
		}

		if (stash != null) {

			sb.append("stash").append("=").append(stash.toString());

			sb.append(", ");
		}

		if (filesize != null) {

			sb.append("filesize").append("=").append(filesize.toString());

			sb.append(", ");
		}

		if (offset != null) {

			sb.append("offset").append("=").append(offset.toString());

			sb.append(", ");
		}

		if (chunk != null) {

			sb.append("chunk").append("=").append(chunk.toString());

			sb.append(", ");
		}

		if (async != null) {

			sb.append("async").append("=").append(async.toString());

			sb.append(", ");
		}

		if (checkstatus != null) {

			sb.append("checkstatus").append("=").append(checkstatus.toString());

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

		if (filename != null) {

			req.addParameter(paramPrefix + "filename", filename);
		}

		if (comment != null) {

			req.addParameter(paramPrefix + "comment", comment);
		}

		if (tags != null) {

			req.addParameter(
					paramPrefix + "tags",
					tags.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (text != null) {

			req.addParameter(paramPrefix + "text", text);
		}

		if (watchlist != null) {

			req.addParameter(paramPrefix + "watchlist", watchlist.getJsonValue());
		}

		if (ignorewarnings != null) {

			req.addParameter(paramPrefix + "ignorewarnings", ignorewarnings.toString());
		}

		if (file != null) {

			req.addParameter(paramPrefix + "file", file.toString());
		}

		if (url != null) {

			req.addParameter(paramPrefix + "url", url);
		}

		if (filekey != null) {

			req.addParameter(paramPrefix + "filekey", filekey);
		}

		if (stash != null) {

			req.addParameter(paramPrefix + "stash", stash.toString());
		}

		if (filesize != null) {

			req.addParameter(paramPrefix + "filesize", filesize.toString());
		}

		if (offset != null) {

			req.addParameter(paramPrefix + "offset", offset.toString());
		}

		if (chunk != null) {

			req.addParameter(paramPrefix + "chunk", chunk.toString());
		}

		if (async != null) {

			req.addParameter(paramPrefix + "async", async.toString());
		}

		if (checkstatus != null) {

			req.addParameter(paramPrefix + "checkstatus", checkstatus.toString());
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
			c.accept(AAPIUpload.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIUpload.this);

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
