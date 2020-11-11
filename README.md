# Quarkus for Platform.sh

Quarkus, the Supersonic Subatomic Java, promises to deliver small artifacts, extremely fast boot time, and lower time-to-first-request. 

## Services

* Java 11

## Customizations

The following files and additions make the framework work.  If using this project as a reference for your own existing project, replicate the changes below to your project.

* [`.platform/routes.yaml`](.platform/routes.yaml): Platform.sh allows you to define the [routes](https://docs.platform.sh/configuration/routes.html).
* [`.platform/services.yaml`](.platform/services.yaml):  Platform.sh allows you to completely define and configure the topology and [services you want to use on your project](https://docs.platform.sh/configuration/services.html).
* [`.platform.app.yaml`](.platform.app.yaml): You control your application and the way it will be built and deployed on Platform.sh [via a single configuration file](https://docs.platform.sh/configuration/app-containers.html).
* An additional library dependency, [`platformsh/config-reader-java`](https://github.com/platformsh/config-reader-java), has been added.  It provides convenience wrappers for accessing the Platform.sh environment variables.

## References

* [Platform.sh post](https://platform.sh/blog/2019/java-hello-world-at-platform.sh/)
* [Maven](https://maven.apache.org/)
* [Quarkus](https://quarkus.io/)
* [Java at Platform.sh](https://docs.platform.sh/languages/java.html)


## Articles

| Article                                                      |                                                        Links |
| ------------------------------------------------------------ | -----------------------------------------------------------: |
| Redis                                                        |                                             [Source](redis/) |
| [Panache MongoDB](https://dzone.com/articles/deploy-quarkus-faster-in-the-cloud-with-platformsh-3) |                                  [Source](mongo-db-panache/) |
| [Command Mode Application](https://dzone.com/articles/deploy-quarkus-faster-in-the-cloud-with-platformsh-2) |                                      [Source](command-mode/) |
| [Hibernate Search With Elasticsearch](https://dzone.com/articles/deploy-quarkus-faster-in-the-cloud-with-platformsh-1) | [Source](https://github.com/platformsh-examples/quarkus/tree/master/elasticsearch) |
| [PostgreSQL With Panache](https://dzone.com/articles/deploy-quarkus-faster-in-the-cloud-with-platformsh) | [Source](https://github.com/platformsh-examples/quarkus/tree/master/panache) |
| [PostgreSQL with JPA](https://dzone.com/articles/quarkus-supersonic-subatomic-java-deploy-faster-in) | [Source](https://github.com/platformsh-examples/quarkus/tree/master/jpa) |
| [Hello World](https://dzone.com/articles/quarkus-supersonic-subatomic-java-goes-faster-in-t) |    [Source](https://github.com/platformsh-templates/quarkus) |



# Deploy Friday: E06 Quarkus Supersonic Subatomic Java



https://www.youtube.com/watch?v=PLsaop5M14M
