(function() {

	var packageSelector, opList;

	window.addEventListener("load", () => {
		opList = new OptProdList();
		packageSelector = new PackageSelector();
		//fire fake event to get first list of products 
		let e = document.createEvent("HTMLEvents");
		e.initEvent("change", false, true);
		packageSelector.element.dispatchEvent(e);
	})


	function OptProdList() {
		this.element = document.getElementById("opList");
		//function to hide list of optional products
		this.hide = function() {
			this.element.style.display = "none";
		}
		//function to show list of optional products (if more than a single product available)
		this.show = function() {
			if (this.element.children.length == 0) this.hide();
			else this.element.removeAttribute("style");
		}
		//function to remove all products from list
		this.clear = function() {
			let children = this.element.children;
			while (children.length > 0) {
				this.element.removeChild(children[0]);
			}
		}
		//function to update list (clears and inserts new values)
		this.update = function(req) {
			this.clear();
			let products = JSON.parse(req.response);
			let i;
			for (i = 0; i < products.length; i++) {
				let newOptProd = document.createElement("div");
				newOptProd.setAttribute("class", "checkbox");
				let newInput = document.createElement("input");
				newInput.setAttribute("type", "checkbox");
				newInput.setAttribute("value", products[i]);
				newInput.setAttribute("name", "optProd" + i);
				let newLabel = document.createElement("label");
				newLabel.innerText = products[i];
				newOptProd.appendChild(newInput);
				newOptProd.appendChild(newLabel);
				this.element.appendChild(newOptProd);
			}
		}

		this.updateAndShow = function(req) {
			if (req.readyState === 4 && req.status === 200) {
				opList.update(req);
				opList.show();
			}
		}

		this.hide();
		this.clear();
	}

	function PackageSelector() {
		this.element = document.querySelector("select[name='package']")
		this.options = this.element.children;

		this.element.addEventListener("change", () => {
			let selectedIndex = this.element.selectedIndex;
			let id = this.element.children[selectedIndex].value;
			makeCall("GET", "JSONProvider?package=" + id, opList.updateAndShow)
		})
	}

}())