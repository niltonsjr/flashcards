# Flashcards - Tarjetas de aprendizaje :card_index:

### Proyecto final del Ciclo Formativo de Grado Superior de Desarrollo de Aplicaciones Multiplataforma cursado en el [I.E.S. Aguadulce](http://www.iesaguadulce.es/centro/) :mortar_board:

## Visión general del sistema

El objetivo del proyecto es desarrollar una Aplicación WEB donde el usuario podrá crear y utilizar sus propias tarjetas de aprendizaje (flashcards).

Las tarjetas de aprendizaje, también conocidas como flashcards, tarjetas educativas, tarjetas de estudio, tarjetas de memorización o tarjetas mnemotécnicas son tarjetas que contienen palabras, imágenes, símbolos o números en uno o ambos lados y se usan para adquirir conocimientos al memorizar su contenido mediante el repaso espaciado del conjunto de tarjetas.

En un lado de la tarjeta el usuario introduce una pregunta y al otro la respuesta. Puede utilizarse también para entrenar vocabulario al aprender nuevos idiomas, indicando de un lado la palabra en un idioma y al otro el significado o traducción.

En la aplicación a desarrollar, el usuario deberá crear una cuenta en la aplicación, y una vez esté conectado, podrá añadir nuevas tarjetas, crear categorías para clasificarlas y utilizarlas para sus estudios.

La aplicación permitirá filtrar las tarjetas por categorías, mostrando la parte de la pregunta y luego, el usuario dará la vuelta a la tarjeta, para mostrar su respuesta, indicando si la respuesta ha sido correcta o no. Además el sistema deberá almacenar el número de aciertos de cada tarjeta, ofreciendo al usuario las tarjetas según el número de aciertos, siendo que las tarjetas menos acertadas deben aparecer con mayor frecuencia sobre las más acertadas.

El proyecto consta de un back end desarrollado en Java con Spring Boot y un front end desarrollado en ReactJS.

--- 

## Diagrama de clases
<p align="center">
   <img width="80%" height="80%" src="https://github.com/niltonsjr/assets/blob/main/Flashcards_readme/Diagrama_de_clases.jpg?raw=true">
</p>

---

## :hammer: Tecnologias utilizadas:

### - Back end:

    - Java 11
    - Spring Boot
    - Spring Data JPA
    - Spring Security
    - JUnit y Mockito
    - Maven
    - H2 / PostgreSQL
    - Postman

---

### - Front end:

    - HTML / CSS / Bootstrap 
    - Javascript / TypeScript
    - ReactJS
    - React Hooks
    - React Hook Form
    - Axios
    - JEST y Testing Library

---

## :computer: Implantación

- **Back end:** [Heroku](https://www.heroku.com/)
- **Front end:** [Netlify](https://www.netlify.com/)

Enlace a la aplicación: [Flashcards](https://dam-flashcards.netlify.app/)

##### :mag: Usuarios de acceso:

- Perfil usuario   
-- nombre de usuario: mariafs | contraseña: 123456  
- Perfil administrador  
-- nombre de usuario: adminprueba | contraseña: 123456

---

## :camera: Capturas de pantalla

### Acceso como usuario administrador:
<p align="center">
   <img width="80%" height="80%" src="https://github.com/niltonsjr/assets/blob/main/Flashcards_readme/flashcards_inicio_perfil_administrador.gif?raw=true">
</p>

### Formulario de registro
<p align="center">
   <img width="80%" height="80%" src="https://github.com/niltonsjr/assets/blob/main/Flashcards_readme/flashcards_nuevo_registro.gif?raw=true">
</p>

### Pantalla adaptativa
<p align="center">
   <img width="80%" height="80%" src="https://github.com/niltonsjr/assets/blob/main/Flashcards_readme/flashcards_pantalla_adaptativa.gif?raw=true">
</p>


### License

MIT © Nilton da Silva Junior