var express = require('express');
var http = require('http');


var app = express();
app.use(express.static("../tail/src/main/resources/static"));

app.use(function (req, res, next) {
  console.log('Time:', Date.now());
  next();
});

var port = 80;
app.listen(port, function() {
	console.log("server start on port: " + port);
});

