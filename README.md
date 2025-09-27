# Task Management REST API

A Java Spring Boot application that provides a REST API for managing and executing shell command tasks in Kubernetes pods. Tasks are stored in MongoDB and can be searched, created, deleted, and executed through HTTP endpoints.

---

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Setup and Installation](#setup-and-installation)
- [Running the Application](#running-the-application)
- [Usage Examples](#usage-examples)
- [Screenshots](#screenshots)
- [License](#license)

---

## Overview
This Java application provides a REST API for managing "task" objects. Each task represents a shell command that can be executed in a Kubernetes pod. The application stores tasks and their execution history in a MongoDB database.

---

## Features
- Create, search, and delete tasks
- Execute shell commands as tasks
- Validate commands to prevent unsafe or malicious execution
- Store task execution history with timestamps and output
- Search tasks by name substring
- RESTful API endpoints for easy integration

---

## Technology Stack
- **Language:** Java 11+
- **Framework:** Spring Boot
- **Database:** MongoDB
- **Build Tool:** Maven / Gradle
- **Testing Tools:** Postman / Curl
- **Containerization:** Docker (optional for Kubernetes pods)

---
## Setup and Installation

1.Clone the repository
```
git clone https://github.com/yourusername/your-repo.git
cd your-repo
```

2.Install dependencies
```
mvn clean install
```

or
```
./gradlew build
```

3.Configure MongoDB
```
Set MongoDB connection URI in application.properties or application.yml:

spring.data.mongodb.uri=mongodb://localhost:27017/taskdb
```

4.Run the application
```
mvn spring-boot:run
```
## Running the Application

**The API will be available at: http://localhost:8080**

**Test endpoints using Postman, Curl, or any HTTP client**
## Usage Examples
1.Create a Task
```
curl -X PUT http://localhost:8080/tasks \
-H "Content-Type: application/json" \
-d '{
  "id": "123",
  "name": "Print Hello",
  "owner": "John Smith",
  "command": "echo Hello World!"
}'
```
2.Get All Tasks
```
curl http://localhost:8080/tasks
```
3.Execute Task
```
curl -X PUT http://localhost:8080/tasks/123/execute
```
4.Search Tasks by Name
```
curl http://localhost:8080/tasks/search?name=Hello
```
5.Delete Task
```
curl -X DELETE http://localhost:8080/tasks?id=123
```

## Screenshots
### Postman Screenshots
<!-- CREATE -->
#### CREATE
*Insert a new task successfully.*  
![Put_Insert_New_Task](Screenshots/Postman/Put_Insert_New_Task.png)  

<!-- READ -->
#### READ
*Fetch all tasks from the database.*  
![Get_All_Tasks](Screenshots/Postman/Get_All_Tasks.png)  

*Fetch a single task by its ID.*  
![Get_Task_By_Id](Screenshots/Postman/Get_Task_By_Id.png)  

*Search tasks by a keyword in the task name.*  
![Get_Search_By_Keyword](Screenshots/Postman/Get_Search_By_Keyword.png)  

<!-- UPDATE -->
#### UPDATE
*Update an existing task with new details.*  
![Update_Existing_Task](Screenshots/Postman/Update_Existing_Task.png)  

<!-- DELETE -->
#### DELETE
*Delete an existing task successfully.*  
![Delete_Existing_Task](Screenshots/Postman/Delete_Existing_Task.png)  

*Attempt to delete a task that does not exist.*  
![Delete_Non_Existing_Task](Screenshots/Postman/Delete_Non_Existing_Task.png)  

<!-- EXECUTE -->
#### EXECUTE
*Execute a valid task command once.*  
![Put_Execute_Task](Screenshots/Postman/Put_Execute_Task.png)  

*Execute the same task multiple times and record executions.*  
![Put_Execute_Multiple_Times](Screenshots/Postman/Put_Execute_Multiple_Times.png)  

*Attempt to execute a task that does not exist.*  
![Put_Execute_Non_Existing_Task](Screenshots/Postman/Put_Execute_Non_Existing_Task.png)  

<!-- INVALID/UNSAFE COMMAND -->
#### INVALID/UNSAFE COMMAND
*Attempt to insert a task with an unsafe/malicious command.*  
![Put_Unsafe_Command](Screenshots/Postman/Put_Unsafe_Command.png)  

<!-- DATABASE VIEW -->
#### DATABASE VIEW
*View of the task collection in MongoDB after operations.*  
![MongoDB_Output](Screenshots/Postman/MongoDB_Output.png)  

### Curl Screenshots

<!-- CREATE -->
#### CREATE
*Insert a new task successfully.*  
![Put_Insert_New_Task](Screenshots/Curl/Put_Insert_New_Task.png)  

<!-- READ -->
#### READ
*Fetch all tasks from the database.*  
![Get_All_Tasks](Screenshots/Curl/Get_All_Tasks.png)  

*Fetch a single task by its ID.*  
![Get_Task_By_Id](Screenshots/Curl/Get_Task_By_Id.png)  

*Search tasks by a keyword in the task name.*  
![Get_Search_By_Keyword](Screenshots/Curl/Get_Search_By_Keyword.png)  

<!-- UPDATE -->
#### UPDATE
*Update an existing task with new details.*  
![Update_Existing_Task](Screenshots/Curl/Put_Update_Existing_Task.png)  

<!-- DELETE -->
#### DELETE
*Delete an existing task successfully.*  
![Delete_Existing_Task](Screenshots/Curl/Delete_Existing_Task.png)  

*Attempt to delete a task that does not exist.*  
![Delete_Non_Existing_Task](Screenshots/Curl/Delete_Non_Existing_Task.png)  

<!-- EXECUTE -->
#### EXECUTE
*Execute a valid task command once.*  
![Put_Execute_Task](Screenshots/Curl/Put_Execute_Task.png)  

*Execute the same task multiple times and record executions.*  
![Put_Execute_Multiple_Times](Screenshots/Curl/Put_Execute_Multiple_Times.png)  

*Attempt to execute a task that does not exist.*  
![Put_Execute_Non_Existing_Task](Screenshots/Curl/Put_Execute_Non_Existing_Task.png)  

<!-- INVALID/UNSAFE COMMAND -->
#### INVALID/UNSAFE COMMAND
*Attempt to insert a task with an unsafe/malicious command.*  
![Put_Unsafe_Command](Screenshots/Curl/Put_Unsafe_Command.png)  

<!-- DATABASE VIEW -->
#### DATABASE VIEW
*View of the task collection in MongoDB after operations.*  
![MongoDB_Output](Screenshots/Curl/MongoDB_Output.png)  

### License

**This project is licensed under the Kaiburr LLC License © 2025. All rights reserved.**

