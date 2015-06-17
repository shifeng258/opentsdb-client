OpenTSDB Java Client
====================

The OpenTSDB client is a Java library that makes sending metrics and querying the OpenTSDB server simple.
The HttpClientImpl class is used to push metrics or query the OpenTSDB server. The library uses the builder pattern to
simplify the task of creating the JSON that is used by the client. Also it use the PoolingHttpClientConnectionManager to 
manager the http connection.

**NOTE** I only implements the send metrics interface. Please feel free to fork and implements for your needs.

## Sending Metrics

Sending metrics is done by using the MetricBuilder. You simply add a metric, the tags associated with the metric, and
the data points.

	HttpClient client = new HttpClientImpl("http://localhost:8242");

	MetricBuilder builder = MetricBuilder.getInstance();

	builder.addMetric("metric1").setDataPoint(2, 30L)
			.addTag("tag1", "tab1value").addTag("tag2", "tab2value");

	builder.addMetric("metric2").setDataPoint(2, 232.34)
			.addTag("tag3", "tab3value");

	try {
		Response response = client.pushMetrics(builder,
				ExpectResponse.SUMMARY);
		System.out.println(response);
	} catch (IOException e) {
		e.printStackTrace();
	}


## Copyright and License

Copyright 2015 Proofpoint Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

[http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.