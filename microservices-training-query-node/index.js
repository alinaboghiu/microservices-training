var express = require('express'),
    request = require('request'),
    app = express();

var users = [];

app.get('/users', function(req, res) {
  res.send(users);
});

app.get('/users/:id', function(req, res) {
  var id = req.params.id;
  if(id < users.length) {
    res.send('User #' + id + ' ' + users[id]);
  } else {
    res.send('User #' + id + ' does not exist');
  }
});

app.listen(3000, function () {
  console.log('Example app listening on port 3000!');
});

setInterval(function() {
  request('http://localhost:8086/requests', function (error, response, body) {
    if (!error && response.statusCode == 200) {
      var requests = JSON.parse(body).results;
      console.log(requests) // Show the HTML for the Google homepage.
    }
  })
}, 1000);
