var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

router.post('/services/api/register', function(req, res, next) {
  var reqBody = req.body;
  console.log(reqBody.username+", "+reqBody.email);
  res.json({"status":"success"});
});

module.exports = router;
