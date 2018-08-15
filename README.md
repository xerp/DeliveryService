# Delivery Service
A Rest API that manage delivery routes

## Dependencies
* [Java 10](http://www.oracle.com/technetwork/java/javase/downloads/jdk10-downloads-4416644.html)
* [Gradle](https://gradle.org/)

## Api methods
### Routes
```java
// Welcome method
GET /routes/index
// Get all routes
GET /routes/
// Get route from origin_point to destination_point
GET /routes/{origin_point}/to/{destination_point}
// Insert sample data
POST /routes/
// Add route from origin_point to destination_point
POST /routes/{origin_point}/to/{destination_point}
// Update route from origin_point to destination_point
PUT /routes/{origin_point}/to/{destination_point}
// Delete route from origin_point to destination_point
DELETE /routes/{origin_point}/to/{destination_point}
```
### Points
```java
// Welcome method
GET /points/index
// Get all points
GET /points/
// Get point with name
GET /points/{name}
// Add point with name
POST /point/{name}
// Update point with name
PUT /points/{name}
// Delete point with name
DELETE /points/{name}
```
### Sample JSON
```
GET /routes/a/to/b
```
```json
{
    "origin": {
        "id": 1,
        "name": "A"
    },
    "destination": {
        "id": 2,
        "name": "B"
    },
    "paths": {
        "paths": [
            {
                "origin": {
                    "id": 1,
                    "name": "A"
                },
                "destination": {
                    "id": 3,
                    "name": "C"
                },
                "time": 900,
                "cost": 20
            },
            {
                "origin": {
                    "id": 3,
                    "name": "C"
                },
                "destination": {
                    "id": 2,
                    "name": "B"
                },
                "time": 900,
                "cost": 12
            },
            {
                "origin": {
                    "id": 1,
                    "name": "A"
                },
                "destination": {
                    "id": 5,
                    "name": "E"
                },
                "time": 30,
                "cost": 5
            },
            {
                "origin": {
                    "id": 1,
                    "name": "A"
                },
                "destination": {
                    "id": 4,
                    "name": "H"
                },
                "time": 10,
                "cost": 1
            },
            {
                "origin": {
                    "id": 4,
                    "name": "H"
                },
                "destination": {
                    "id": 5,
                    "name": "E"
                },
                "time": 30,
                "cost": 1
            },
            {
                "origin": {
                    "id": 5,
                    "name": "E"
                },
                "destination": {
                    "id": 6,
                    "name": "D"
                },
                "time": 3,
                "cost": 5
            },
            {
                "origin": {
                    "id": 6,
                    "name": "D"
                },
                "destination": {
                    "id": 7,
                    "name": "F"
                },
                "time": 4,
                "cost": 50
            },
            {
                "origin": {
                    "id": 7,
                    "name": "F"
                },
                "destination": {
                    "id": 8,
                    "name": "I"
                },
                "time": 45,
                "cost": 50
            },
            {
                "origin": {
                    "id": 7,
                    "name": "F"
                },
                "destination": {
                    "id": 9,
                    "name": "G"
                },
                "time": 40,
                "cost": 50
            },
            {
                "origin": {
                    "id": 8,
                    "name": "I"
                },
                "destination": {
                    "id": 2,
                    "name": "B"
                },
                "time": 65,
                "cost": 5
            },
            {
                "origin": {
                    "id": 9,
                    "name": "G"
                },
                "destination": {
                    "id": 2,
                    "name": "B"
                },
                "time": 64,
                "cost": 73
            }
        ],
        "pathCount": 11
    },
    "shortestPath": "A-E-D-F-G-B"
}
```
### Authentication
For authentication i used the basic method, the user and password were hardcoded(for simplication) and are:
* User: admin
* Password: qwerty

**Authorization header**:
```
Authorization: Basic YWRtaW46cXdlcnR5
```

## Persistence
For persistence i used [H2](http://www.h2database.com/html/main.html) database(in memory mode), so no ddl or sql script is needed for deployment.

## Unit testing
After the dependencies were met, for test the solution, you need follow these steps:
1. Go to project folder
2. Run *gradle test* (**NOTE**: There are ignored tests)

## Testing the solution
After the dependencies were met, for run the solution, you need follow these steps:
1. Go to project folder
2. Run *gradle bootRun*
3. Open a rest client(like [postman](https://www.getpostman.com/)) and perform a request to *http://localhost:9000/routes/index* or *http://localhost:9000/points/index*

## Compile the solution
After the dependencies were met, for compile the solution, you need follow these steps:
1. Go to project folder
2. Run *gradle bootJar*
3. Go to build/libs to take the jar created recently

## Runing solution

```bash
$> java -jar deliveryservice-1.0.jar # Default port is 9000
```
or
```bash
$> java -jar -Dserver.port=SERVER_PORT deliveryservice-1.0.jar
```

  
