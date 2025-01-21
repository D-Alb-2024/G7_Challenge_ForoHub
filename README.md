# 🦸‍♂️ ForoHub - API Backend 🦸‍♀️

Bienvenido al proyecto **ForoHub**, una plataforma de discusión basada en una API REST que permite a los usuarios interactuar mediante la creación de tópicos, autenticación segura y gestión de respuestas. Esta API está construida con **Spring Boot**, **JWT (JSON Web Token)** para la autenticación y **MySQL** como base de datos.

## 🚀 Características

- **Autenticación con JWT** 🔑
- **Protección de rutas**: Acceso controlado con seguridad basada en tokens. 
- **Gestión de usuarios**: Registro y autenticación de usuarios.
- **Operaciones CRUD**: Creación, lectura, actualización y eliminación de tópicos.

## 📁 Estructura del Proyecto

- **Modelo de Usuario**: Clase `Usuario` que representa a un usuario en el sistema, con campos como nombre, correo electrónico, contraseña y perfil.
- **Autenticación**: Servicios y filtros de seguridad utilizando JWT.
- **Controlador de Autenticación**: Permite iniciar sesión y recibir un token JWT.

## 🔧 Requisitos

- **Java 17+**
- **Spring Boot 2.5+**
- **MySQL 5.7+**
- **Maven**

## 🛠 Configuración

### 1. **Variables de Entorno**

Este proyecto utiliza la variable de entorno `JWT_SECRET` para la seguridad de los tokens JWT. Asegúrate de definir esta variable en tu entorno de ejecución.

#### Archivo `application.properties`

En el archivo `application.properties`, puedes configurar la conexión a la base de datos y el secreto para los tokens JWT:

```properties
# Base de Datos
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}

# Secreto de la API para JWT
api.security.secret=${JWT_SECRET}  # Utiliza la variable de entorno JWT_SECRET

# Configuración de Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Mostrar stacktrace de errores
server.error.include-stacktrace=never
