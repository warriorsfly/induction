let stomp = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    const socket = new SockJS('/notifications?username=' + $("#name").val() + '&role=doctor');
    stomp = Stomp.over(socket);
    stomp.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);

        stomp.subscribe('/queue/test.doctor-fourteen', function (message) {
            const body = JSON.parse(message.body).content;
            $("#greetings").append("<tr><td>" + body + "</td></tr>");
        })
        // stomp.subscribe('/user/queue/test', function (message) {
        //     const body = JSON.parse(message.body).content;
        //     $("#greetings").append("<tr><td>" + body + "</td></tr>");
        // })
    });
}

function disconnect() {
    if (stomp !== null) {
        stomp.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stomp.send("/app/hello", {}, JSON.stringify({ 'name': $("#name").val() }));
}

function sendMessage() {
    stomp.send("/app/message/test", {}, JSON.stringify({ 'sender': 'thirteen', 'receiver': 'fourteen', 'title': $("#name").val(), 'content': 'From fairest creatures we desire increase' }));
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () { connect(); });
    $("#disconnect").click(function () { disconnect(); });
    $("#send").click(function () { sendName(); });
    $("#sendToUser").click(function () { sendMessage(); });
});