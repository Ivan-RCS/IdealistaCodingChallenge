Idealista Coding Challenge.

En relación a la posición de Idealista, una pequeña aplicación web en Java ha sido desarrollada para resolver el challenge de Idealista.

El entorno de desarrollo utilizado ha sido Eclipe.
Java 8.
Maven, herramienta de compresión y de gestión de proyecto ha sido utiliza. Esta permite compilar y ejecutar los test facilmente utilizando comandos a través de la consola.
Librerías utilizadas:
	- JUnit : para test unitarios
	- gson : para gestionar los objetos JSON
	- Spark-core : para montar la API HTTP, micro framework para crear aplicaciones web de manera rápida con Java 8
	- slf4j : para logging


Usar esto comandos con la herramienta Maven:
	- mvn eclipse:eclipse //para descargar y colocar las liberias en el projecto de eclipse
	- mvn clean install //para generar el jar y ejecutar los test
	- mvn exec:java //para ejecutar la api

Este solución intenta demostrar las habilidades del desarrillador a la hora de modelar diferentes clases, programación orientada a objetos, patrones de diseño, etc. Además
de las últimas carcterísticas de Java como expresiones Lambda y Optional.

El challenge está relacionado con hacer una API HTTP. Se ha implementado para la estructura del servidor web el típico patrón MVC.
Se ha decidio hacer un gran uso de expresiones lambda para facilitar la resolución de los 3 servicios que se solicitan en la prueba, llevando a cabo una programación declarativa. 
Además recalcar una duda en el requisito de la petición de los anuncios para el encargado y desde que fecha son irrelevantes, sin tener grabado dicho dato en el data set facilitado, se ha
puesto una fecha simbólica en el acceso al servicio.

Para leer los datos, se acceden mediante fichero cada vez que estos quieren ser leidos y mediante librerias de gson estos son traducidos a los objetos de Java. Finalmente, salvo en el endpoint
de la media de ratio de calidad, los otros 2 devuelven una respuesta JSON muy similar a la facilitada, dónde cambia el array de pictures, que contienen los objetos JSON completos, el ratio de calidad, y la fecha.

Otros comentarios:
	- Unit Tests han sido incluidos, mirar los paquetes de Test.
	- Javadoc
	
En la carpeta target se encuenta el IdealistaCodingChallenge-1.0.jar
Para ejecutar la aplicación ejecutar el siguiente comando:
    - java -jar IdealistaCodingChallenge-1.0.jar

Puerto:4567
EndPoints:
	- http://localhost:4567/idealista/admin/ads/rate/average
	- http://localhost:4567/idealista/admin/ads/irrelevant
	- http://localhost:4567/idealista/ads
