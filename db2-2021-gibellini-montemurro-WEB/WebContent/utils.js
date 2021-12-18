function makeCall(method, url, object, callback) {
	var req = new XMLHttpRequest();
	req.onreadystatechange = function(e) {
		e.preventDefault();
		callback(req)
	};
	req.open(method, url);
	if (object !== null) req.send(object);
	else req.send();
}