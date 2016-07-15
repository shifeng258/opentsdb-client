package org.opentsdb.client.request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shifeng on 2016/5/19.
 * MyProject
 */
public class SubQueries {
    private String aggregator;
    private String metric;
    private Boolean rate = false;
    private Map<String, String> rateOptions;
    private String downsample;
    private Map<String, String> tags = new HashMap<String, String>();
    private List<Filter> filters = new ArrayList<Filter>();

    public SubQueries addAggregator(String aggregator) {
        this.aggregator = aggregator;
        return this;
    }

    public SubQueries addMetric(String metric) {
        this.metric = metric;
        return this;
    }

    public SubQueries addDownsample(String downsample) {
        this.downsample = downsample;
        return this;
    }

    public SubQueries addTag(Map<String, String> tag) {
        this.tags.putAll(tag);
        return this;
    }

    public SubQueries addTag(String tag, String value) {
        this.tags.put(tag, value);
        return this;
    }

    public String getAggregator() {
        return aggregator;
    }

    public void setAggregator(String aggregator) {
        this.aggregator = aggregator;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public Boolean getRate() {
        return rate;
    }

    public void setRate(Boolean rate) {
        this.rate = rate;
    }

    public Map<String, String> getRateOptions() {
        return rateOptions;
    }

    public void setRateOptions(Map<String, String> rateOptions) {
        this.rateOptions = rateOptions;
    }

    public String getDownsample() {
        return downsample;
    }

    public void setDownsample(String downsample) {
        this.downsample = downsample;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }
}
