# hazelcast-spring-boot-demo

This project is a simple demo of using Hazelcast within a Spring Boot application.

- Start `DemoApplication` and `DemoApplication2` to see how to inject Hazelcast data structures, like `IMap` and `IQueue`, to your beans in a Spring Boot application. Beyond that you'll see how Hazelcast works as spring cache manager seemlessly.
- `DemoApplication` uses Hazelcast in embedded mode. To see a sample of Hazelcast-Spring Boot integration using Hazelcast clients, first start `Master.java` to start a Hazelcast instance then start `ClientDemoApplication` to connect to this instance. `ClientDemoApplication` will offer to `IQueue` and items in the queue will be processed by `Master`. You can start as many `Master` instances as you want to drain queue faster.
