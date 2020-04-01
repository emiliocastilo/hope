Para arrancar se necesita el postgresql, los datos de conexión están en el application.properties,
hay que crear el esquema!!

mvn clean package  -- te creará la estructura de base de datos con una carga inicial de datos

arrancar la aplicación con el run de springboot con java 1.8

solo se permite el acceso a los endpoints de actuator, del swagger y el post de /login para realizar la autentificación
jwt. 

Hay unos ejemplos en la carpeta de postman

