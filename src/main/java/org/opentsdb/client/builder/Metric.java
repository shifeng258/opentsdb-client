/*
 * Copyright 2013 Proofpoint Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.opentsdb.client.builder;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.opentsdb.client.util.Preconditions.checkNotNullOrEmpty;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

/**
 * A metric contains measurements or data points. Each data point has a time
 * stamp of when the measurement occurred and a value that is either a long or
 * double and optionally contains tags. Tags are labels that can be added to
 * better identify the metric. For example, if the measurement was done on
 * server1 then you might add a tag named "host" with a value of "server1". Note
 * that a metric must have at least one tag.
 */
public class Metric {

	@SerializedName("metric")
	private String name;

	private long timestamp;

	private Object value;

	private Map<String, String> tags = new HashMap<String, String>();

	protected Metric(String name) {
		this.name = checkNotNullOrEmpty(name);
	}

	/**
	 * Adds a tag to the data point.
	 *
	 * @param name
	 *            tag identifier
	 * @param value
	 *            tag value
	 * @return the metric the tag was added to
	 */
	public Metric addTag(String name, String value) {
		checkNotNullOrEmpty(name);
		checkNotNullOrEmpty(value);
		tags.put(name, value);

		return this;
	}

	/**
	 * Adds tags to the data point.
	 * 
	 * @param tags
	 *            map of tags
	 * @return the metric the tags were added to
	 */
	public Metric addTags(Map<String, String> tags) {
		checkNotNull(tags);
		this.tags.putAll(tags);

		return this;
	}

	/**
	 * set the data point for the metric.
	 *
	 * @param timestamp
	 *            when the measurement occurred
	 * @param value
	 *            the measurement value
	 * @return the metric
	 */
	protected Metric innerAddDataPoint(long timestamp, Object value) {
		checkArgument(timestamp > 0);
		this.timestamp = timestamp;
		this.value = checkNotNull(value);

		return this;
	}

	/**
	 * Adds the data point to the metric with a timestamp of now.
	 *
	 * @param value
	 *            the measurement value
	 * @return the metric
	 */
	public Metric setDataPoint(long value) {
		return innerAddDataPoint(System.currentTimeMillis(), value);
	}

	public Metric setDataPoint(long timestamp, long value) {
		return innerAddDataPoint(timestamp, value);
	}

	/**
	 * Adds the data point to the metric.
	 *
	 * @param timestamp
	 *            when the measurement occurred
	 * @param value
	 *            the measurement value
	 * @return the metric
	 */
	public Metric setDataPoint(long timestamp, double value) {
		return innerAddDataPoint(timestamp, value);
	}

	/**
	 * Adds the data point to the metric with a timestamp of now.
	 *
	 * @param value
	 *            the measurement value
	 * @return the metric
	 */
	public Metric setDataPoint(double value) {
		return innerAddDataPoint(System.currentTimeMillis(), value);
	}

	/**
	 * Time when the data point was measured.
	 *
	 * @return time when the data point was measured
	 */
	public long getTimestamp() {
		return timestamp;
	}

	public Object getValue() {
		return value;
	}

	public String stringValue() throws DataFormatException {
		return value.toString();
	}

	public long longValue() throws DataFormatException {
		try {
			return ((Number) value).longValue();
		} catch (Exception e) {
			throw new DataFormatException("Value is not a long");
		}
	}

	public double doubleValue() throws DataFormatException {
		try {
			return ((Number) value).doubleValue();
		} catch (Exception e) {
			throw new DataFormatException("Value is not a double");
		}
	}

	public boolean isDoubleValue() {
		return !(((Number) value).doubleValue() == Math.floor(((Number) value)
				.doubleValue()));
	}

	public boolean isIntegerValue() {
		return ((Number) value).doubleValue() == Math.floor(((Number) value)
				.doubleValue());
	}

	/**
	 * Returns the metric name.
	 *
	 * @return metric name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the tags associated with the data point.
	 *
	 * @return tag for the data point
	 */
	public Map<String, String> getTags() {
		return Collections.unmodifiableMap(tags);
	}

}
