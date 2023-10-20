# Main

Extract data from Oracle (large tables) and upload to GCP Storage

Create Spring Boot project with maven, using dependencies:

- Oracle Driver
- JDBC API
- Lombok

Implements **CommandLineRunner** and add **run** method

```java
public class DemooraclegcpApplication implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
```

Add dependencies **opencsv** and  **google-cloud-storage**

```xml
		<!-- https://mvnrepository.com/artifact/com.opencsv/opencsv -->
		<dependency>
			<groupId>com.opencsv</groupId>
			<artifactId>opencsv</artifactId>
			<version>5.8</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/com.google.cloud/google-cloud-storage -->
		<dependency>
			<groupId>com.google.cloud</groupId>
			<artifactId>google-cloud-storage</artifactId>
			<version>2.28.0</version>
		</dependency>
```

## Set path of key GCP

```cmd
export GOOGLE_APPLICATION_CREDENTIALS=/Users/maxzavaleta/Downloads/my-gcp-4b96b-468f767d8c9b.json
```

# Set environment variables

In pat **src/main/resources** create application.yml
```yml

spring:
  datasource:
    url: jdbc:oracle:thin:@hostname:port:sid
    username: user
    password: ******
filepath: /.../demo/data/
gcp:
  project_id: ******
  bucket_name: ******
```





