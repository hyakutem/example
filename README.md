# example

## Requirements

* java17

### personal access token
https://docs.github.com/ja/authentication/keeping-your-account-and-data-secure/managing-your-personal-access-tokens

for use ghcr.io container registory

https://docs.github.com/ja/packages/working-with-a-github-packages-registry/working-with-the-container-registry

```
docker login ghcr.io -u ${username}
Password: ${token}
```

## build

```
./mvnw -e clean package
```

```
java -jar ./target/example-0.0.1-SNAPSHOT.jar
```

```
curl http://localhost:8080
```

## build container image and push to registory

```
./mvnw -e compile jib:build -DskipTests \
    -Dimage=ghcr.io/hyakutem/example:0.0.1
```

## build container image without push

```
./mvnw -e compile jib:buildTar -DskipTests \
    -Djib.to.image=ghcr.io/hyakutem/example:0.0.1
```


