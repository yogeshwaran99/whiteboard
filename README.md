# TeamCanvas: Real-Time Collaborative Whiteboard and Chat

A full-stack web application for real-time collaboration through a shared whiteboard and chat system.

## preview

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
