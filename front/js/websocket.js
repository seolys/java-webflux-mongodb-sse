var webSocket = new WebSocket("ws://localhost:8080/chat")

webSocket.onopen = function () {
  // webSocket.send("야호!!");
}

webSocket.onmessage = function (event) {
  // var data = JSON.parse(event.data);
  appendDiv(event.data);
}

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

$("#sendBtn").click(function () {
  const message = $("#message").val();
  if (!message) {
    return;
  }
  webSocket.send(message);
  appendDiv(`[클라이언트]: ${message}`);
  $("#message").val("");
})

$("#message").keydown(function (e) {
  if (e.keyCode == 13) {
    $("#sendBtn").click();
  }
})
