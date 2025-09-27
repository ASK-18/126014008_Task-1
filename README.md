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
- [Sample Responses](#sample-responses)
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

<!-- CREATE -->
![Put_Insert_New_Task](screenshots/Put_Insert_New_Task.png)  

<!-- READ -->
![Get_All_Tasks](screenshots/Get_All_Tasks.png)  
![Get_Task_By_Id](screenshots/Get_Task_By_Id.png)  
![Get_Search_By_Keyword](screenshots/Get_Search_By_Keyword.png)  

<!-- UPDATE -->
![Update_Existing_Task](screenshots/Update_Existing_Task.png)  

<!-- DELETE -->
![Delete_Existing_Task](screenshots/Delete_Existing_Task.png)  
![Delete_Non_Existing_Task](screenshots/Delete_Non_Existing_Task.png)  

<!-- EXECUTE -->
![Put_Execute_Task](screenshots/Put_Execute_Task.png)  
![Put_Execute_Multiple_Times](screenshots/Put_Execute_Multiple_Times.png)  
![Put_Execute_Non_Existing_Task](screenshots/Put_Execute_Non_Existing_Task.png)  

<!-- INVALID/UNSAFE COMMAND -->
![Put_Unsafe_Command](screenshots/Put_Unsafe_Command.png)  

<!-- DATABASE VIEW -->
![MongoDB_Output](screenshots/MongoDB_Output.png)  


