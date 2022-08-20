# Commands

```shell
curl -L -X POST 'localhost:8080/persons' \
-H 'Content-Type: application/json' \
--data-raw '{
    "id": null,
    "name": "john"
}'
```


```shell
curl -L -X GET 'localhost:8080/persons/63012f2b693f86555c995b3d' \
-H 'Content-Type: application/json' | jq .
```


```shell
curl -L -X GET 'localhost:8080/persons/_page' \
-H 'Content-Type: application/json' | jq .
```
