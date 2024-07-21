
## movie-service

 - pom.xml properties:
```
<awspring.cloud.version>3.1.1</awspring.cloud.version>
```

 - pom.xml dependency:
```
<dependency>
	<groupId>io.awspring.cloud</groupId>
	<artifactId>spring-cloud-aws-starter-secrets-manager</artifactId>
	<version>${awspring.cloud.version}</version>
</dependency>	
```

- Create `application-prod.properties` under `src/main/resources` with the following properties.
```
spring.config.import=aws-secretsmanager:/prod/netflux/db/movie?prefix=db.
spring.datasource.url=jdbc:postgresql://${db.host}:5432/movie
spring.datasource.username=${db.username}
spring.datasource.password=${db.password}
server.port=8080
```

## customer-service:

 - pom.xml properties:
```
<awspring.cloud.version>3.1.1</awspring.cloud.version>
```

 - pom.xml dependency:
```
<dependency>
	<groupId>io.awspring.cloud</groupId>
	<artifactId>spring-cloud-aws-starter-secrets-manager</artifactId>
	<version>${awspring.cloud.version}</version>
</dependency>	
```

- Create `application-prod.properties` under `src/main/resources` with the following properties.
```
spring.config.import=aws-secretsmanager:/prod/netflux/db/customer?prefix=db.
spring.datasource.url=jdbc:postgresql://${db.host}:5432/customer
spring.datasource.username=${db.username}
spring.datasource.password=${db.password}
movie.service.url=http://movie-service:8080
server.port=8080
```	