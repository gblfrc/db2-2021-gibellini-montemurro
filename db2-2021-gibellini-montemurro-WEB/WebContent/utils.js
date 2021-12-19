function makeCall(method, url, callback) {
	var req = new XMLHttpRequest();
	req.onreadystatechange = function(e) {
		e.preventDefault();
		callback(req)
	};
	req.open(method, url);
	req.send();
}