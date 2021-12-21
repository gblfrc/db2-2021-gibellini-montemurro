function makeCall(method, url, callback) {
	var req = new XMLHttpRequest();
	req.onreadystatechange = function(e) {
		removeError();
		if (req.readyState === 4) {
			if (req.status === 200) {
				e.preventDefault();
				callback(req);
			}else{
				e.preventDefault();
				let error = document.createElement("p");
				error.setAttribute("class", "error");
				error.textContent=req.status + ": " + req.responseText;
				document.querySelector("div.main").appendChild(error);
			}
		}
	};
	req.open(method, url);
	req.send();
}

function removeError(){
	let error = document.querySelector("p.error");
	if (error !== null) error.remove();
}