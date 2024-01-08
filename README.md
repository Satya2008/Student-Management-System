
## Introduction

The Student Management System is a Spring Boot-based application designed to streamline the process of managing students and their courses. The system provides functionalities for administrators and students to perform various operations securely.

## Features

### Admin/Owner Operations:
1. Admit a student with details such as name, date of birth, gender, and a unique student code. Multiple addresses (permanent, correspondence, current) are supported.
2. Upload course details, including course name, description, course type, duration, and topics.
3. Assign courses to students (many-to-many relationship).
4. Search for students by name.
5. Retrieve a list of students assigned to a particular course.

### Student Operations:
1. Update profile details, including email, mobile number, parent's name, and address.
2. Search for topics/courses assigned to the student.
3. Leave a course.

### Authentication:
- Admins authenticate using credentials (Role-Based Authentication).
- Students validate themselves using their unique student code and password.

## Technologies Used

- Spring Boot: Framework for creating standalone, production-grade Spring-based applications.
- Hibernate: Object-relational mapping framework for Java.
- JPA: Java Persistence API for managing relational data in Java applications.
- Swagger: Tool for API documentation.
- Spring Security: Authentication and access control framework.


## Project Structure



![Screenshot (188)](https://github.com/Satya2008/Student-Management-System/assets/119415073/bdfc5ba3-c3d7-4cf9-b3f9-70b2f6c2826a)



The project follows a modular structure with distinct layers:
- **Entity Layer:** Contains the JPA entities representing the data model.
- **Repo Layer:** Includes repositories for database operations.
- **Controller:** Manages incoming HTTP requests, handling the flow of data between the client and the application.
- **Service:** Business logic layer that contains the core functionalities.
- **DTO Layer:** Data Transfer Objects for mapping between DTOs and entities.
