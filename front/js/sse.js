let authToken = null;
while(!authToken) {
  authToken = prompt("토큰을 입력하세요.");
  document.querySelector("title").innerHTML = `sse(authToken = ${authToken})`;
}

const eventSource = new EventSource(`http://localhost:8080/connect/${authToken}`);

function sseDivElement(data) {
  const sseDiv = document.createElement("div");
  // sseDiv.innerHTML = `id: ${data.id}, pushType: ${data.pushType}, createdAt: ${data.createdAt}`;
  sseDiv.innerHTML = JSON.stringify(data);
  return sseDiv;
}

eventSource.onmessage = (event) => {
  const data = JSON.parse(event.data);
  console.log("data:", data);

  const $sse = sseDivElement(data);
  document.querySelector("body").append($sse);
}
