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

/** Get generated video embed code for given parameters.
 */
public class AAPIEmbedvideo implements AAPIModule, AAPIMainActionModule {

	public static AAPIEmbedvideo create(@NonNull String service, @NonNull String id) {

		AAPIEmbedvideo v = new AAPIEmbedvideo();

		v.service = service;

		v.id = id;

		return v;
	}

	private AAPIEmbedvideo() {}

	private String service;

	private String id;

	private String dimensions;

	private String alignment;

	private String description;

	private String container;

	private String urlargs;

	private String autoresize;

	/**Name of the service (youtube, twitch, etc)
	 */
	public String getService() {
		return this.service;
	}

	/**The ID of the video for that service
	 */
	public String getId() {
		return this.id;
	}

	/**Either a numeric width (100) or width by height (100x100)
	 */
	public AAPIEmbedvideo dimensions(String dimensions) {
		this.dimensions = dimensions;

		return this;
	}

	/**Either a numeric width (100) or width by height (100x100)
	 */
	public String getDimensions() {
		return this.dimensions;
	}

	/**Alignment of video
	 */
	public AAPIEmbedvideo alignment(String alignment) {
		this.alignment = alignment;

		return this;
	}

	/**Alignment of video
	 */
	public String getAlignment() {
		return this.alignment;
	}

	/**Description of video
	 */
	public AAPIEmbedvideo description(String description) {
		this.description = description;

		return this;
	}

	/**Description of video
	 */
	public String getDescription() {
		return this.description;
	}

	/**Accepts frame, or leave empty
	 */
	public AAPIEmbedvideo container(String container) {
		this.container = container;

		return this;
	}

	/**Accepts frame, or leave empty
	 */
	public String getContainer() {
		return this.container;
	}

	/**Additional arguments to pass in the video url (for some services)
	 */
	public AAPIEmbedvideo urlargs(String urlargs) {
		this.urlargs = urlargs;

		return this;
	}

	/**Additional arguments to pass in the video url (for some services)
	 */
	public String getUrlargs() {
		return this.urlargs;
	}

	/**Auto resize video? (true or false)
	 */
	public AAPIEmbedvideo autoresize(String autoresize) {
		this.autoresize = autoresize;

		return this;
	}

	/**Auto resize video? (true or false)
	 */
	public String getAutoresize() {
		return this.autoresize;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIEmbedvideo(");

		if (service != null) {

			sb.append("service").append("=").append(service);

			sb.append(", ");
		}

		if (id != null) {

			sb.append("id").append("=").append(id);

			sb.append(", ");
		}

		if (dimensions != null) {

			sb.append("dimensions").append("=").append(dimensions);

			sb.append(", ");
		}

		if (alignment != null) {

			sb.append("alignment").append("=").append(alignment);

			sb.append(", ");
		}

		if (description != null) {

			sb.append("description").append("=").append(description);

			sb.append(", ");
		}

		if (container != null) {

			sb.append("container").append("=").append(container);

			sb.append(", ");
		}

		if (urlargs != null) {

			sb.append("urlargs").append("=").append(urlargs);

			sb.append(", ");
		}

		if (autoresize != null) {

			sb.append("autoresize").append("=").append(autoresize);

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (service != null) {

			req.addParameter(paramPrefix + "service", service);
		}

		if (id != null) {

			req.addParameter(paramPrefix + "id", id);
		}

		if (dimensions != null) {

			req.addParameter(paramPrefix + "dimensions", dimensions);
		}

		if (alignment != null) {

			req.addParameter(paramPrefix + "alignment", alignment);
		}

		if (description != null) {

			req.addParameter(paramPrefix + "description", description);
		}

		if (container != null) {

			req.addParameter(paramPrefix + "container", container);
		}

		if (urlargs != null) {

			req.addParameter(paramPrefix + "urlargs", urlargs);
		}

		if (autoresize != null) {

			req.addParameter(paramPrefix + "autoresize", autoresize);
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
			c.accept(AAPIEmbedvideo.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIEmbedvideo.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return false;
		}

		@Override
		protected boolean internalRequiresPagination() {
			return true;
		}
	}
}
