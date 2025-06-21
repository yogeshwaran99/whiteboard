const topicChat = () => `/topic/room/${localStorage.getItem("roomId")}/chat`;
const topicDraw = () => `/topic/room/${localStorage.getItem("roomId")}/draw`;
const destinationChat = () => `/app/room/${localStorage.getItem("roomId")}/chat`;
const destinationDraw = () => `/app/room/${localStorage.getItem("roomId")}/draw`;

const client = new StompJs.Client({
  brokerURL: "ws://localhost:8080/whiteboard",
});

let buttonConnect, buttonDisconnect, buttonSend;
let conversationDisplay, chatsTable;
let formInput, messageInput;
let canvas, ctx;
let drawing = false;
let lastX = 0, lastY = 0;

client.onConnect = (frame) => {
  setConnected(true);
  console.log("Connected: " + frame);

  client.subscribe(topicChat(), (messageFrame) => {
    const message = JSON.parse(messageFrame.body).content;
    showChatMessage(message);
  });

  client.subscribe(topicDraw(), (message) => {
    const data = JSON.parse(message.body);
    drawLine(data.x1, data.y1, data.x2, data.y2);
  });
};

client.onWebSocketError = (error) => {
  console.error("WebSocket error:", error);
};

client.onStompError = (frame) => {
  console.error("STOMP error: " + frame.headers["message"]);
  console.error("Details: " + frame.body);
};

function setConnected(connected) {
  buttonConnect.disabled = connected;
  buttonDisconnect.disabled = !connected;
  conversationDisplay.style.display = connected ? "block" : "none";
  chatsTable.innerHTML = "";
}

function connect() {
  const roomId = localStorage.getItem("roomId");
  if (!roomId) {
    alert("Please create or join a room first!");
    return;
  }
  client.activate();
}

function disconnect() {
  client.deactivate();
  setConnected(false);
  console.log("Disconnected");
}

function sendMessage() {
  const text = messageInput.value;
  if (text.trim() !== "") {
    client.publish({
      destination: destinationChat(),
      body: JSON.stringify({ content: text }),
    });
    messageInput.value = "";
  }
}

function showChatMessage(message) {
  const row = document.createElement("tr");
  const cell = document.createElement("td");
  cell.textContent = message;
  row.appendChild(cell);
  chatsTable.appendChild(row);
}

function drawLine(x1, y1, x2, y2) {
  ctx.beginPath();
  ctx.moveTo(x1, y1);
  ctx.lineTo(x2, y2);
  ctx.stroke();
}

document.addEventListener("DOMContentLoaded", () => {
  buttonConnect = document.getElementById("connect");
  buttonDisconnect = document.getElementById("disconnect");
  buttonSend = document.getElementById("send");
  conversationDisplay = document.getElementById("conversation");
  chatsTable = document.getElementById("chats");
  formInput = document.getElementById("form");
  messageInput = document.getElementById("message");

  canvas = document.getElementById("whiteboard");
  ctx = canvas.getContext("2d");

  canvas.addEventListener("mousedown", (e) => {
    drawing = true;
    lastX = e.offsetX;
    lastY = e.offsetY;
  });

  canvas.addEventListener("mousemove", (e) => {
    if (!drawing || !client.connected) return;

    const x = e.offsetX;
    const y = e.offsetY;

    drawLine(lastX, lastY, x, y);

    client.publish({
      destination: destinationDraw(),
      body: JSON.stringify({ x1: lastX, y1: lastY, x2: x, y2: y }),
    });

    lastX = x;
    lastY = y;
  });

  canvas.addEventListener("mouseup", () => drawing = false);
  canvas.addEventListener("mouseout", () => drawing = false);

  buttonConnect.addEventListener("click", (e) => {
    connect();
    e.preventDefault();
  });

  buttonDisconnect.addEventListener("click", (e) => {
    disconnect();
    e.preventDefault();
  });

  buttonSend.addEventListener("click", (e) => {
    sendMessage();
    e.preventDefault();
  });

  formInput.addEventListener("submit", (e) => e.preventDefault());

  setConnected(false);
});

const backendURL = "http://localhost:8080/api/rooms";

function createRoom() {
  const name = document.getElementById("roomName").value;

  fetch(`${backendURL}?name=${encodeURIComponent(name)}`, {
    method: "POST"
  })
    .then(res => res.json())
    .then(data => {
      localStorage.setItem("roomId", data.id);
      document.getElementById("roomCreatedMsg").innerText = `Room Created: ${data.id}`;
      document.getElementById("currentRoomId").innerText = data.name;
    })
    .catch(err => {
      console.error("Create room failed:", err);
    });
}

function joinRoom() {
  const id = document.getElementById("roomId").value;

  fetch(`${backendURL}/${id}`)
    .then(res => {
      if (!res.ok) throw new Error("Room not found");
      return res.json();
    })
    .then(data => {
      localStorage.setItem("roomId", data.id);
      document.getElementById("roomJoinMsg").innerText = `Joined Room: ${data.name}`;
      document.getElementById("currentRoomId").innerText = data.name;
    })
    .catch(err => {
      document.getElementById("roomJoinMsg").innerText = "Invalid Room ID!";
      console.error(err);
    });
}
function deleteRoom() {
  const id = document.getElementById("roomIdToDelete").value;

  fetch(`${backendURL}/${id}`, {
    method: "DELETE"
  })
    .then(res => {
      if (!res.ok) throw new Error("Delete failed");
      document.getElementById("roomDeleteMsg").innerText = "Room deleted successfully!";
    })
    .catch(err => {
      console.error(err);
      document.getElementById("roomDeleteMsg").innerText = "Failed to delete room!";
    });
}
