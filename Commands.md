# Commands

```
curl -L -X POST 'localhost:8080/persons' \
-H 'Content-Type: application/json' \
--data-raw '{
    "id": null,
    "name": "john"
}'
```


```
curl -L -X GET 'localhost:8080/persons/{}' \
-H 'Content-Type: application/json' \
--data-raw '{
    "id": null,
    "name": "john"
}'
```
