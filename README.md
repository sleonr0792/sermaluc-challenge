# Reto técnico creacion de usuarios

## Swagger: [Documentación](http://localhost:8092/swagger-ui-custom.html)
- Link: http://localhost:8092/swagger-ui-custom.html
## Servicios:

### Registrar Usuario: servicio para registrar servicios
- Servicio: api/v1/users
-  Metodo: POST
- Request:
  ``` json
      {
      "name": string,
      "email": string,
      "password":string",
      "phones":[
          {
          "number":string,
          "citycode":string,
          "contrycode":string
          }
       ]
      }
  ```
- Response:
  ``` json
      {
  "id": string,
  "name": string,
  "email": string,
  "token": string,
  "registered": Date,
  "updated":Date,
  "lastLogin":Date,
  "phones": [
      {
          "number": string,
          "citycode": string,
          "contrycode": string
      }
  ],
  "state": string
   ```



### Listar Usuarios:

- Servicio: api/v1/users/list
- Metodo: GET
-  Authorization: Bearer JWT
- Request:
  ``` json
       
  ```
  - Response
    ``` json
    [ 
        {
            "id": string,
            "name": string,
            "email": string,
            "token": string,
            "registered": Date,
            "updated":Date,
            "lastLogin":Date,
            "phones": [
                {
                    "number": string,
                    "citycode": string,
                    "contrycode": string
                }
            ],
            "state": string
        }
    ]
    ```