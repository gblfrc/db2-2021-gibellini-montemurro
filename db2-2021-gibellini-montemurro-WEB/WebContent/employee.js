(function() { // avoid variables ending up in the global scope

	document.getElementById("seeSalesButton").addEventListener('click', (e) => {
		e.preventDefault();
		window.location.href="sales.html";
	})
})();