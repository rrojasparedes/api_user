# API de Creación de Usuarios

Esta API permite la creación de usuarios mediante una solicitud POST a la ruta `/api/user/create`. La solicitud debe incluir un JSON con la siguiente estructura:

# Diagrama

![API-USER drawio](https://github.com/rrojasparedes/api_user/assets/3418191/efa173a9-3941-44ea-9696-00c358fb1d97)


# Json de entrada

```json
{
   "name":"Rafael Rojas",
   "email":"rrojasparedes@gmail.com",
   "password":"aatreqw",
   "phones":[
      {
         "number":"9645678",
         "citycode":"32",
         "contrycode":"56"
      },
      {
         "number":"7862934",
         "citycode":"14",
         "contrycode":"56"
      }
   ]
}
```

# Modo de testeo.

## Curl

```bash
curl -X 'POST' \
  'http://localhost:8080/api/users/create' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
   "name":"Rafael Rojas",
   "email":"rrojasparedes@gmail.com",
   "password":"aatreqw",
   "phones":[
      {
         "number":"9645678",
         "citycode":"32",
         "contrycode":"56"
      },
      {
         "number":"7862934",
         "citycode":"14",
         "contrycode":"56"
      }
   ]
}'
```
## Swagger

```bash
/swagger-ui/index.html
```

# Ejemplo de Respuesta Exitosa:

En caso de éxito, la API devuelve una respuesta con el siguiente formato:

```json
{
    "id": 1,
    "created": "2023-11-24T15:43:32.350+00:00",
    "modified": "2023-11-24T15:43:32.350+00:00",
    "lastLogin": "2023-11-24T15:43:32.350+00:00",
    "token": "0ba2437d-1a9d-44e2-b169-e100a6b9041d",
    "active": false
}
```

# Ejemplo de Respuesta en Caso de Error:

En caso de error, la API devuelve un mensaje en el siguiente formato:

```json
{
    "mensaje": "El correo ya está registrado."
}
```

El mensaje indica el tipo de error que ha ocurrido. Pueden ocurrir distintos errores como "El correo ya está registrado", "Contraseña inválida", entre otros, dependiendo de la validación realizada por la API.

# Métodos Disponibles:

```bash
POST /api/users/create: Crea un nuevo usuario con la información proporcionada en el cuerpo de la solicitud.
```
