Para arrancar se necesita el postgresql, los datos de conexión están en el application.properties

mvn clean package  -- te creará la estructura de base de datos, solo falta lanzar el script import.sql para poder realizar las pruebas con postman

run del main que se encuentra en HopesBackApplication, o el jar del target: java -jar ...

solo se permite el acceso a los endpoints de actuator, del swagger y el post de /login para realizar la autentificación
jwt. 
Hay unos ejemplos en la carpeta de postman

