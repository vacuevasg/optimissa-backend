# Evaluación Técnica Optimissa Java 

Este proyecto es una API REST desarrollada con Java 11 y Spring Boot que implementa autenticación JWT, catálogos filtrables, consumo de APIs externas, y cifrado AES.

El proyecto se encuentra desplegado en mi infraestructura privada de AWS para su conveniencia. Para probarlo, puede seguir las instrucciones de despliegue o utilizar la siguiente URL:

```bash
https://optimissa-back.proyectosvc.com
```

## Tecnologías utilizadas

- Java 11
- Spring Boot 2.7.18
- Spring Security + JWT
- JPA + H2 Database
- RestTemplate para consumo de API externa
- AES/CBC/PKCS5Padding para cifrado

## Requisitos

- Java 11
- Maven

## Pasos para ejecutar el proyecto

```bash
git clone <URL del repositorio>
cd <directorio>
mvn clean install
mvn spring-boot:run
```

URL aplicación:

```bash
https://optimissa-back.proyectosvc.com
http://localhost:8080
```


Acceso a H2:

https://optimissa-back.proyectosvc.com/h2-console
http://localhost:8080/h2-console

- JDBC URL: jdbc:h2:mem:testdb
- Usuario: sa
- Password: 


## ENDPOINTS (orden recomendado):

Por favor usar la colección de postman incluida en este repositorio.

1. POST http://localhost:8080/auth/login o https://optimissa-back.proyectosvc.com/auth/login
   - Request Body:
     { "email": "admin@example.com", "password": "admin123" }
   - Devuelve el token JWT que se debe usar en todos los demás endpoints.

2. POST http://localhost:8080/catalogo
   - Request Body:
     {
       "nombre": "Vainilla"
     }
   - Retorna el catálogo filtrado o completo según el nombre.

3. GET http://localhost:8080/pokemon/ditto
   - Retorna los datos completos del Pokémon Ditto desde la PokéAPI.

4. GET http://localhost:8080/pokemon/list/limit/150/offset/0
   - Retorna una lista con los nombres de los Pokémon paginados.

5. GET http://localhost:8080/pokemon/:nombre
   - Reemplazar ":nombre" por el nombre de cualquier Pokémon para obtener sus datos desde la PokéAPI.

6. POST http://localhost:8080/cifrado/encrypt
   - Request Body:
     { "texto": "Prueba encriptar AES" }
   - Devuelve el texto cifrado y el vector de inicialización (IV).

7. POST http://localhost:8080/cifrado/decrypt
   - Request Body:
     {
       "cifrado": "dy558nfpsRWJtPPLSarql4u1zWaluGoz8/rx/cYwyaA=",
       "iv": "RiLxO7DnwfxNJoXzOFw48g=="
     }
   - Devuelve el texto original descifrado.
