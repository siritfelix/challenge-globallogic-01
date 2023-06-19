# challenge-globallogic-01

Reto por Felix Antonio Sirit Murillo siritfelix@gmail.com

Tecnologías necesarias

`Java 1.8`, `Gradle`, `Spring-Boot`, `h2`, `JPA`

Instalación del proyecto
* Clonar el proyecto desde el repositorio desde https://github.com/siritfelix/challenge-globallogic-01.git
> git clone https://github.com/siritfelix/challenge-globallogic-01.git

* Importar el proyecto en cualquier ide
* Compilar ejecutando: 
> ./gradlew build

* Levantar con: 
> ./gradlew bootRun
* Si usa docker crear y levantar imagen

> docker build -t  challenge-globallogic-01 .
>
> docker run -p 8080:8080 --name user challenge-globallogic-01
>
* Para correr los test
> ./gradlew jacocoTestReport
* La descripcion de los endpoint se puede observar al levantar el proyecto, mediante el swagger accedemos al recurso: http://localhost:8080/swagger-ui.html
* La covertura de test luego de compilar se encuentra en `build\jacoco\test\html\index.html con 85% de coverage`

* Repositorio : https://github.com/siritfelix/challenge-globallogic-01.git