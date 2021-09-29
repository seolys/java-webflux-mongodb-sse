const queryString = getQueryStringObject();

let authToken = queryString["authToken"];
while(!authToken) {
  authToken = prompt("토큰을 입력하세요.");
  document.querySelector("title").innerHTML = `sse(authToken = ${authToken})`;
}

const eventSource = new EventSource(`http://localhost:8080/connect/${authToken}`);
appendDiv("new EventSource");

eventSource.onopen = () => {
  appendDiv("Connection to server opened.");
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

  appendDiv(data);
  scrollDown();

  if(isLogout(data.eventType)) {
    eventSource.close();
  }
}

eventSource.onerror = function() {
  console.log("EventSource failed.");
  // eventSource.close();
};

function appendDiv(data) {
  const div = document.createElement("div");
  div.innerHTML = JSON.stringify(data);
  document.querySelector("body").append(div);
}

function scrollDown() {
  document.body.scrollTop = document.body.scrollHeight;
}

function getQueryStringObject() {
  var a = window.location.search.substr(1).split('&');
  if (a == "") {
    return {};
  }
  var b = {};
  for (var i = 0; i < a.length; ++i) {
    var p = a[i].split('=', 2);
    if (p.length == 1) {
      b[p[0]] = "";
    } else {
      b[p[0]] = decodeURIComponent(p[1].replace(/\+/g, " "));
    }
  }
  return b;
}
