let authToken = null;
while(!authToken) {
  authToken = prompt("토큰을 입력하세요.");
  document.querySelector("title").innerHTML = `sse(authToken = ${authToken})`;
}

const eventSource = new EventSource(`http://localhost:8080/connect/${authToken}`);
console.log("new EventSource");

eventSource.onopen = () => {
  console.log("Connection to server opened.");
}

function isLogout(eventType) {
  // return eventType === "DUPLICATE_LOGIN"
  //     || eventType === "LOGOUT"
  //     || eventType === "CHANGE_AUTH"
  //     || eventType === "REDIS_TOKEN_EXPIRED";
  return eventType === "LOGOUT";
}

eventSource.onmessage = (event) => {
  const data = JSON.parse(event.data);
  console.log("data:", data);

  const $div = createDiv(data);
  document.querySelector("body").append($div);

  if(isLogout(data.eventType)) {
    eventSource.close();
  }
}

eventSource.onerror = function() {
  console.log("EventSource failed.");
  // eventSource.close();
};

function createDiv(data) {
  const div = document.createElement("div");
  div.innerHTML = JSON.stringify(data);
  return div;
}
