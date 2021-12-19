(function() {

	var form, selector;

	window.addEventListener("load", () => {
		form = new ServiceForm();
		selector = new ServiceSelector();
		//fire fake event to get correct form 
		let e = document.createEvent("HTMLEvents");
		e.initEvent("change", false, true);
		selector.element.dispatchEvent(e);

	})


	function ServiceForm() {
		this.element = document.querySelector("form[action='CreateService']");
		this.igb = document.getElementById("igbp");
		this.egbf = document.getElementById("egbfp");
		this.isms = document.getElementById("ismsp");
		this.esmsf = document.getElementById("esmsfp");
		this.im = document.getElementById("imp");
		this.emf = document.getElementById("emfp");

		//function to show necessary fields for 
		//fixed phone service (and empty other inputs)
		this.fixpho = function() {
			this.igb.style.display = "none";
			this.egbf.style.display = "none";
			this.isms.style.display = "none";
			this.esmsf.style.display = "none";
			this.im.style.display = "none";
			this.emf.style.display = "none";
			//section to empty the input
			this.igb.children[0].value = '';
			this.egbf.children[0].value = '';
			this.isms.children[0].value = '';
			this.esmsf.children[0].value = '';
			this.im.children[0].value = '';
			this.emf.children[0].value = '';
		}
		//function to show necessary fields for
		//mobile phone service (and empty other inputs)
		this.mobpho = function() {
			this.igb.style.display = "none";
			this.egbf.style.display = "none";
			this.isms.removeAttribute("style");
			this.esmsf.removeAttribute("style");
			this.im.removeAttribute("style");
			this.emf.removeAttribute("style");
			//section to empty the input
			this.igb.children[0].value = '';
			this.egbf.children[0].value = '';
		}
		//function to show necessary fields for
		//internet services (and empty other inputs)
		this.int = function() {
			this.igb.removeAttribute("style");
			this.egbf.removeAttribute("style");
			this.isms.style.display = "none";
			this.esmsf.style.display = "none";
			this.im.style.display = "none";
			this.emf.style.display = "none";
			//section to empty the input
			this.isms.children[0].value = '';
			this.esmsf.children[0].value = '';
			this.im.children[0].value = '';
			this.emf.children[0].value = '';
		}
	}

	function ServiceSelector() {
		this.element = document.querySelector("form[action='CreateService'] select");

		this.element.addEventListener("change", () => {
			let selInd = this.element.selectedIndex;
			let value = this.element.children[selInd].value;
			if (value == "Fixed phone") form.fixpho();
			else if (value == "Mobile phone") form.mobpho();
			else form.int();
		})
	}

}())