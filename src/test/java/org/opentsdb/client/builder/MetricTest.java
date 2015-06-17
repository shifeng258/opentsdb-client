package org.opentsdb.client.builder;

import org.junit.Test;
import org.opentsdb.client.builder.MetricBuilder;

public class MetricTest {
	@Test(expected = NullPointerException.class)
	public void test_nullMetricName_invalid() {
		MetricBuilder builder = MetricBuilder.getInstance();

		builder.addMetric(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_emptyMetricName_invalid() {
		MetricBuilder builder = MetricBuilder.getInstance();

		builder.addMetric("");
	}

	@Test(expected = NullPointerException.class)
	public void test_nullTagName_invalid() {
		MetricBuilder builder = MetricBuilder.getInstance();

		builder.addMetric("metric1").addTag(null, "value");
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_emptyTagName_invalid() {
		MetricBuilder builder = MetricBuilder.getInstance();

		builder.addMetric("metric1").addTag("", "value");
	}

	@Test(expected = NullPointerException.class)
	public void test_nullTagValue_invalid() {
		MetricBuilder builder = MetricBuilder.getInstance();

		builder.addMetric("metric1").addTag("tag", null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_emptyTagValue_invalid() {
		MetricBuilder builder = MetricBuilder.getInstance();

		builder.addMetric("metric1").addTag("tag", "");
	}
}