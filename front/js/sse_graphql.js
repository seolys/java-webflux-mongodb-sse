let authToken = null;
while(!authToken) {
  authToken = prompt("토큰을 입력하세요.");
  document.querySelector("title").innerHTML = `sse graphql(authToken = ${authToken})`;
}

// const eventSource = new EventSource(`http://localhost:8080/subscriptions`);
const eventSource = new WebSocket(`ws://localhost:8080/subscriptions`);
console.log("new WebSocket");

eventSource.onopen = function () {
  console.log("eventSource opened");

  var query = `subscription ($authToken: String!) {
      userEvent(authToken: $authToken) {
        eventId
        eventType
        createdAt
      }
    }
  `;
  var graphqlMsg = {
    query: query,
    variables: {authToken: authToken}
  }
  console.log("eventSource send");
  eventSource.send(JSON.stringify(graphqlMsg));
}

eventSource.onmessage = (event) => {
  const data = JSON.parse(event.data);
  console.log("data:", data);

  const $sse = sseDivElement(data);
  document.querySelector("body").append($sse);
}

function sseDivElement(data) {
  const sseDiv = document.createElement("div");
  sseDiv.innerHTML = JSON.stringify(data);
  return sseDiv;
}
