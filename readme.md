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




