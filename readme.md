# Main

Extract data from Oracle (large tables) and upload to GCP Storage

Create Spring Boot project with maven, using dependencies:

- Oracle Driver
- JDBC API
- Lombok

Implements **CommandLineRunner** and add **run** method

```java
public class DemoApplication implements CommandLineRunner {

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






//189865,40011504,MANZANA ISRAEL X KG,1,D2,FRESCOS,A06,FRUTAS Y VERDURAS,S12,FRUTAS,L057,FRUTAS,F0201,POMACEA,SF00602,MANZANA,40011504       ,MANZANA ISRAEL X KG,MKO0038,ARO,2060203777,CONSTRUCTORA E INVERSIONES N Y R S.,2,Activo,,,PE,NACIONAL,F,189865,791,7,26,110,422,1449,189865,2200400115047,F,099,Propio,,,PE,,,,F,,,1,,,,,212851,40011504-1               ,417783,1660470,42176,0001,,503015,KG,Kilogramos,KG,Kilogramos,KG,Kilogramos,,,,,,0000,Toda Temporada,,ARO MANZAN,2021-03-22 00:00:00.0,,,,,,"MANZANA ISRAEL X 

