## Add Sleuth to micro service

*  add dependencies   

      compile 'org.springframework.cloud:spring-cloud-starter-sleuth:1.1.1.RELEASE'
      compile 'org.springframework.cloud:spring-cloud-sleuth-stream:1.1.1.RELEASE'
      compile 'org.springframework.cloud:spring-cloud-stream-binder-kafka:1.1.1.RELEASE'
      
*  add config

      spring:
            sleuth:
                enabled: false
            cloud:
              stream:
                kafka:
                  binder:
                    brokers: localhost:9092
                    zk-nodes: localhost:2181