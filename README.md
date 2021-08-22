# prueba-ebusiness
Prueba técnica caso de estudio - ebusiness

El proyecto requiere Java SE 11 (LTS), Node.js 14, Angular 12, PostgreSQL 13 y Maven 3.


Obtener código

    >> git clone https://github.com/maferelo/prueba-ebusiness.git


Modificar <base-path>\backend\src\main\resources\application.properties con las credenciales de la base de datos.

# Nota: No es seguro, implementar variables de entorno.
spring.datasource.url=jdbc:postgresql:<db-info> 
spring.datasource.username=<db-username>
spring.datasource.password=<db-password>


Ejecutar API

    >> cd <base-path>/backend


Ejecutar frontend

    >> cd <base-path>/frontend
    >> ng serve


La interfaz se encuentra en http://localhost:4200/ y la API en http://localhost:8080/

Compilar con 

    >> mvn clean compile assembly:single