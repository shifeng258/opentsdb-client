package org.opentsdb.client;

import java.io.IOException;

import org.junit.Test;
import org.opentsdb.client.builder.MetricBuilder;
import org.opentsdb.client.response.Response;

public class HttpClientTest {

	@Test
	public void test_pushMetrics_DefaultRetries() {
		HttpClientImpl client = new HttpClientImpl("http://10.75.201.130:4242");

		MetricBuilder builder = MetricBuilder.getInstance();

		builder.addMetric("metric1").setDataPoint(30L)
				.addTag("tag1", "tab1value").addTag("tag2", "tab2value");

		builder.addMetric("metric2").setDataPoint(232.34)
				.addTag("tag3", "tab3value");

		try {
			Response response = client.pushMetrics(builder,
					ExpectResponse.SUMMARY);
			System.out.println(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}