# Product Service

Este es un servicio web simple para gestionar productos, construido con Spring Boot y Docker. Permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre productos y está configurado para usar una base de datos en memoria H2.

## Tecnologías Utilizadas

- Spring Boot: Framework para construir aplicaciones basadas en Java.
- H2 Database: Base de datos en memoria utilizada para almacenamiento.
- Docker: Plataforma para crear y gestionar contenedores.

## Funcionalidades

- CRUD de Productos: Crear, leer, actualizar y eliminar productos.
- Dockerización: Contenerización de la aplicación y la base de datos.
- API RESTful: Interfaz para interactuar con el servicio de productos.

## Uso
### Ejecutar la Aplicación con Docker
Para construir y ejecutar los contenedores, usa el siguiente comando:

```bash
docker-compose up --build
```
La aplicación estará disponible en http://localhost:8080 o http://0.0.0.0:8080/.

## API Endpoints
- GET /products: Obtiene todos los productos.
- GET /products/{id}: Obtiene un producto por ID.
- POST /products/create: Crea un nuevo producto.
- PUT /products/update/{id}: Actualiza un producto existente.
- DELETE /products/remove/{id}: Elimina un producto por ID.

Alternativamente en la carpeta "coleccion postman", se encontrará una colección con los anteriores endpoints, los cuales podrán ser ejecutados despues de haber desplegado la app como se menciona anteriormente.
