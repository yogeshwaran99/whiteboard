# TeamCanvas: Real-Time Collaborative Whiteboard and Chat

A full-stack web application for real-time collaboration through a shared whiteboard and chat system.

## Preview

![preview](https://raw.githubusercontent.com/yogeshwaran99/whiteboard/main/assets/preview.png)

## Features

- Synchronized whiteboard drawing using WebSockets
- Real-time chat within rooms
- Room creation, joining, and deletion (only creators can delete)
- User authentication with session-based access control
- REST API and WebSocket integration

## Technologies Used

### Backend

- Java, Spring Boot
- Spring WebSocket (STOMP protocol)
- Spring Security
- PostgreSQL
- RESTful API

### Frontend

- HTML, CSS, JavaScript
- Canvas API for whiteboard
- STOMP.js for real-time messaging

## Room Management

- Rooms are created by authenticated users
- Only the room creator can delete it
- Users can join rooms using a valid room ID

## Real-Time Communication

- Separate WebSocket topics for chat and drawing
- All connected clients receive updates instantly

## Security

- Session-based login/logout via Spring Security
- Authorization enforced for sensitive actions like room deletion

## Project Structure
```
whiteboard/
├── src/                     # Source code (Java, resources)
│   ├── main/
│   │   ├── java/            # Spring Boot application
│   │   └── resources/
│   │       └── static/      # Frontend: HTML, JS, CSS
├── Dockerfile               # Container image definition
├── docker-compose.yml       # Multi-container orchestration
├── pom.xml                  # Maven project file
```

## How to Run the Application

You can run this application in two ways:


### Method 1: Run with Maven (Spring Boot)

> Useful for local development and debugging.

#### Prerequisites

- **Java 21** (required) 
- Maven  

#### Steps

```bash
# Go into the project directory
cd whiteboard

# Run the Spring Boot application
./mvnw spring-boot:run
```

#### The application will start at:

http://localhost:8080



### Method 2: Run with Docker and Docker Compose

 > Ideal for containerized deployment or consistent environments.

#### Prerequisites

  - Docker
  - Docker Compose

#### Steps

```bash
# Clone the repository if not already
git clone https://github.com/yogeshwaran99/whiteboard.git
cd whiteboard

# Build and run all services
docker-compose up --build
```

#### Once containers are up, open in your browser:

http://localhost:8080

```bash
# Stop the App
docker-compose down
```



## Contact

Maintained by [@yogeshwaran99](https://github.com/yogeshwaran99)
Pull requests and contributions are welcome!