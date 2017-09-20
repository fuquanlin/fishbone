#add eureka to service

* add annotation

@EnableEurekaClient

* add dependenies

        compile 'org.springframework.cloud:spring-cloud-starter-eureka:1.2.3.RELEASE'

* add config

    eureka:
      client:
        serviceUrl:
          defaultZone: http://localhost:9080/eureka/
