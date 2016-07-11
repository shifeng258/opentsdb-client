package org.opentsdb.client.builder;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import org.junit.Test;
import org.opentsdb.client.builder.MetricBuilder;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class MetricBuilderTest {
	@Test
	public void test() throws IOException {
		String json = Resources.toString(
				Resources.getResource("multiple_metrics.json"), Charsets.UTF_8);

		MetricBuilder builder = MetricBuilder.getInstance();

		builder.addMetric("metric1").setDataPoint(2, 30L)
				.addTag("tag1", "tab1value").addTag("tag2", "tab2value");

		builder.addMetric("metric2").setDataPoint(2, 232.34)
				.addTag("tag3", "tab3value");

		//assertThat(builder.build(), equalTo(json));
	}

	@Test(expected = IllegalStateException.class)
	public void test_metricContainsTags() throws IOException {
		MetricBuilder builder = MetricBuilder.getInstance();
		builder.addMetric("metric1");
		builder.addMetric("metric2").addTag("tag", "value");

		builder.build();
	}

	public static class MetricTest {
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
}