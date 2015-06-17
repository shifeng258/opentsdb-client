package org.opentsdb.client;

import java.io.IOException;

import org.opentsdb.client.builder.MetricBuilder;
import org.opentsdb.client.response.Response;

public interface HttpClient extends Client {

	public Response pushMetrics(MetricBuilder builder,
			ExpectResponse exceptResponse) throws IOException;
}