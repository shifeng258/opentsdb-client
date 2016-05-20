package org.opentsdb.client;

import java.io.IOException;

import org.opentsdb.client.builder.MetricBuilder;
import org.opentsdb.client.request.QueryBuilder;
import org.opentsdb.client.response.Response;
import org.opentsdb.client.response.SimpleHttpResponse;

public interface HttpClient extends Client {

	public Response pushMetrics(MetricBuilder builder,
			ExpectResponse exceptResponse) throws IOException;

	public SimpleHttpResponse pushQueries(QueryBuilder builder,
                                          ExpectResponse exceptResponse) throws IOException;
}