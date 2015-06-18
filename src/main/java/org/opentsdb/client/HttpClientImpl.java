package org.opentsdb.client;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.opentsdb.client.builder.MetricBuilder;
import org.opentsdb.client.response.ErrorDetail;
import org.opentsdb.client.response.Response;
import org.opentsdb.client.response.SimpleHttpResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * HTTP implementation of a client.
 */
public class HttpClientImpl implements HttpClient {

	private static Logger logger = Logger.getLogger(HttpClientImpl.class);

	private String serviceUrl;

	private Gson mapper;

	PoolingHttpClient httpClient = new PoolingHttpClient();

	public HttpClientImpl(String serviceUrl) {
		this.serviceUrl = serviceUrl;

		GsonBuilder builder = new GsonBuilder();
		mapper = builder.create();
	}

	@Override
	public Response pushMetrics(MetricBuilder builder) throws IOException {
		return pushMetrics(builder, ExpectResponse.STATUS_CODE);

	}

	@Override
	public Response pushMetrics(MetricBuilder builder,
			ExpectResponse expectResponse) throws IOException {
		checkNotNull(builder);

		// TODO 错误处理，比如IOException或者failed>0，写到队列或者文件后续重试。
		SimpleHttpResponse response = httpClient
				.doPost(buildUrl(serviceUrl, POST_API, expectResponse),
						builder.build());

		return getResponse(response);
	}

	private String buildUrl(String serviceUrl, String postApiEndPoint,
			ExpectResponse expectResponse) {
		String url = serviceUrl + postApiEndPoint;

		switch (expectResponse) {
		case SUMMARY:
			url += "?summary";
			break;
		case DETAIL:
			url += "?details";
			break;
		default:
			break;
		}

		return url;
	}

	private Response getResponse(SimpleHttpResponse httpResponse) {
		Response response = new Response(httpResponse.getStatusCode());
		String content = httpResponse.getContent();
		if (StringUtils.isNotEmpty(content)) {
			if (response.isSuccess()) {
				ErrorDetail errorDetail = mapper.fromJson(content,
						ErrorDetail.class);
				response.setErrorDetail(errorDetail);
			} else {
				logger.error("request failed!" + httpResponse);
			}
		}
		return response;
	}
}