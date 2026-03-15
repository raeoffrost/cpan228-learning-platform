# TrainHub

## What the Application Does
TrainHub is a simple workplace training portal built with Spring Boot. It allows users to browse internal training courses and add new courses through a web form.

The application includes a training catalog where courses can be filtered by category and difficulty, sorted by title or duration, and displayed with pagination. All course data is stored in a database using Spring Data JPA.

Sample training courses are loaded automatically when the application starts so the catalog can be viewed immediately.

---

## How to Run the Application

### Requirements
- Java 17

The project uses the Maven wrapper, so Maven does not need to be installed separately.

### Run the Application

From the project root folder:

**Windows (PowerShell)**

```powershell
.\mvnw.cmd spring-boot:run
````

**Mac / Linux**

```bash
./mvnw spring-boot:run
```

After starting the application, open a browser and go to:

```
http://localhost:8080
```

Optional: H2 database console

```
http://localhost:8080/h2-console
```

---

## Author

Abby Friesen

Student #: n01719981
