# practice1011

A simple Java project demonstrating CRUD operations on an H2 database using JDBC and HikariCP connection pooling.

## Features

- Uses H2 embedded database for persistence
- Demonstrates basic CRUD (Create, Read, Update, Delete) operations on a `TASK` table
- Uses HikariCP for efficient connection pooling
- Includes example code for table creation and manipulation

## Project Structure

- `src/main/java/practice1011/`
  - `Jdbc_todo.java`: Standalone example of JDBC CRUD operations
  - `Task.java`: Model class for tasks
  - `TaskRepo.java`: Repository class for database operations using HikariCP
- `pom.xml`: Maven build file with dependencies for H2 and HikariCP

## Requirements

- Java 8 or higher
- Maven

## How to Build

```sh
mvn clean package
```

## How to Run

To run the main repository example:

```sh
mvn exec:java -Dexec.mainClass="practice1011.TaskRepo"
```

Or run the standalone JDBC example:

```sh
mvn exec:java -Dexec.mainClass="practice1011.Jdbc_todo"
```

## Notes

- The H2 database files (`todo.mv.db`, `todo.trace.db`, `todo.lock.db`) are created in the project root.
- The project uses connection pooling via HikariCP for efficient database access.

