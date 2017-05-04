function onMessage(jsonData) {
  var chatContainer = $("#chatContainer");
  chatContainer.append("<div>" + jsonData.user + ": " + jsonData.message + "(" + jsonData.time + ")</div>")
  $("#chatText").val("");
}

var user = {};

$(document).ready(function () {
  var chatText = $("#chatText");
  var btn = $("#sendText");
  user.username = $("#username").val();
  console.log(user);
  btn.click(function () {
    var username = user.username || "guest";
    $.post("/send", {
      user: username,
      message: chatText.val()
    });
  });


});


var SOCKET_URL = "/chat";

var service = {},
  socket = {
    client: null,
    stomp: null,
    listeners: {},
    initialized: false
  },
  subscriptions = {};

service.RECONNECT_TIMEOUT = 30000;


socket.client = new SockJS(SOCKET_URL);
socket.stomp = Stomp.over(socket.client);


function subscribe(topic_id) {
  if (subscriptions[topic_id])
    return;

  subscriptions[topic_id] = socket.stomp.subscribe(topic_id, onStompMessage);

  function onStompMessage(data) {
    console.log(data);
    var jsonData = data.body.trim();
    if (jsonData.indexOf('{') == 0 || jsonData.indexOf('[') == 0) {
      jsonData = JSON.parse(data.body);
    }
    onMessage(jsonData);
  }
}

var initialize = function () {
  // socket.stomp.debug = null;
  socket.stomp.connect({}, function (frame) {

    socket.reconnect = true;
    socket.initialized = true;

  });

  socket.client.onopen = function (data) {
    console.log(data);
    subscribe("/room/emt");
    socket.stomp.subscribe("/room/notification", function (data) {
      console.log(data);
      var notification = data.body.trim();
      alert(notification);
    });
  };

  socket.client.onclose = function () {
    socket.initialized = false;
    if (socket.reconnect) {
      setTimeout(function () {
        initialize();
      }, service.RECONNECT_TIMEOUT);
    }
  };
};

initialize();


