# 🥑 Foro Hub Potaxiano API

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)

Una API RESTful desarrollada como parte del último Challenge Back-End de Alura Latam. Esta aplicación simula el funcionamiento de un foro de discusión (Foro Hub), permitiendo a los usuarios crear, consultar, actualizar y eliminar tópicos de manera segura.

## ✨ Características Principales !!

* **CRUD Completo:** Gestión total de tópicos (Crear, Leer, Actualizar, Eliminar).
* **Paginación y Ordenamiento:** Listado de tópicos optimizado con paginación de Spring Data.
* **Seguridad con JWT:** Autenticación de usuarios sin estado (stateless) utilizando JSON Web Tokens.
* **Validaciones:** Uso de `Jakarta Validation` para asegurar la integridad de los datos entrantes (campos obligatorios, prevención de duplicados).
* **Manejo de Errores Global:** Respuestas HTTP limpias y personalizadas para errores 400 y 404 utilizando `@RestControllerAdvice`.
* **Migraciones de Base de Datos:** Control de versiones del esquema de base de datos automatizado con Flyway.

## 🛠️ Tecnologías Utilizadas

* **Lenguaje:** Java 
* **Framework:** Spring Boot 
* **Persistencia:** Spring Data JPA / Hibernate
* **Seguridad:** Spring Security + Auth0 Java JWT
* **Base de Datos:** MySQL
* **Migraciones:** Flyway
* **Herramientas:** Lombok, Maven, Postman

## 🚀 Instalación y Configuración

Sigue estos pasos para ejecutar el proyecto en tu entorno local:

1. **Clonar el repositorio:**
   ```bash
   git clone [https://github.com/angeltorres06/ForoHub-Potaxiano-ONE.git](https://github.com/angeltorres06/ForoHub-Potaxiano-ONE.git)

2. **Configurar la base de datos:**
    Crea una base de datos en MySQL llamada foro_hub_potaxiano.

----------SQL-----------------------------------------
CREATE DATABASE foro_hub_potaxiano;

3. **Variables de Entorno (application.properties):**
Asegúrate de configurar tu usuario, contraseña y la clave secreta de JWT en tu archivo application.properties o en las variables de entorno de tu IDE:

  ------------Properties------------------------------
  spring.datasource.username=tu_usuario
  spring.datasource.password=tu_contrasena
  api.security.secret=tu_clave_secreta_jwt

4. **Ejecutar la aplicación:**
Ejecuta la clase principal ForoHubPotaxianoApplication.java desde tu IDE (IntelliJ IDEA, Eclipse, etc.). Flyway creará las tablas automáticamente.

TT/TONY TORRES
(salveJiafei)
