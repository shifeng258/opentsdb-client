package org.opentsdb.client;

import java.io.IOException;

import org.opentsdb.client.builder.MetricBuilder;
import org.opentsdb.client.response.Response;

public interface Client {

	public final static String POST_API = "/api/put";

	/**
	 * Sends metrics from the builder to the KairosDB server.
	 *
	 * @param builder
	 *            metrics builder
	 * @return response from the server
	 * @throws IOException
	 *             problem occurred sending to the server
	 */
	Response pushMetrics(MetricBuilder builder) throws IOException;

}