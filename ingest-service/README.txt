ingest-service currently using
kibana-4.0.2-linux-x64
and
elasticsearch-1.5.2

may work with later versions of ES or Kibana, but does not work with current ones

setup for ES:
For this project's InjestServiceApplicationTests.java to run out of the box, the following property must be changed in elasticsearch.yml in elasticsearch's config directory:
cluster.name: elasticsearch_roger-dev

to use your own cluster.name, you will have to change where it refers to "elasticsearch_roger-dev" in this project's elasticsearch-test-connection.xml

run elasticsearch

create the index you will be using:
curl -XPUT 'http://localhost:9200/metadata_v1/'

then use the following command in the directory /ingest-service/src/main/java/piazza/services/ingest/model to upload the JSON mapping:
curl -XPUT 'http://localhost:9200/metadata_v1/metadata/_mapping' -a @metaTemplate.json
