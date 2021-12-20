(function() {
	var buttons, salesPerPackage, salesPerPackageAndValidity, amountWithoutOptional, amountWithOptional,
		averageNumberOfOptional, insolventClients, suspendedOrders, alerts, bestSeller, 
		dataPerPackage;

	window.addEventListener("load", () => {
		buttons = new Button();
		salesPerPackage = new SalesPerPackage();
		salesPerPackageAndValidity = new SalesPerPackageAndValidity();
		amountWithoutOptional = new AmountWithoutOptional();
		amountWithOptional = new AmountWithOptional();
		averageNumberOfOptional = new AverageNumberOfOptional();
		insolventClients = new InsolventClients();
		suspendedOrders = new SuspendedOrders();
		alerts = new Alerts();
		bestSeller = new BestSeller();
	});

	function SalesPerPackage() {
		this.element = document.querySelector("div[class='purchasePerPackage']");
		this.salesPerPackage = document.querySelector("div[class='purchasePerPackage']>table>tbody");
		
		this.hide = function hide() {
			this.element.style.display = "none";
		}

		this.clear = function clear() {
			while (this.salesPerPackage.children.length > 0) {
				this.salesPerPackage.removeChild(this.salesPerPackage.children[0]);
			}
		}

		this.show = function show() {
			let i = 0;
			console.log(dataPerPackage);
			for (i = 0; i < dataPerPackage.length; i++) {
				let newRow = document.createElement("tr");
				let packageCell = document.createElement("td");
				packageCell.textContent = dataPerPackage[i].property0;
				newRow.appendChild(packageCell);
				let totPurchaseCell = document.createElement("td");
				totPurchaseCell.setAttribute("class", "number");
				totPurchaseCell.textContent = dataPerPackage[i].property1;
				newRow.appendChild(totPurchaseCell);
				salesPerPackage.salesPerPackage.appendChild(newRow);
			}
			this.element.removeAttribute("style");
		}

		this.hide();
		this.clear();
	}

	function SalesPerPackageAndValidity() {
		this.element = document.querySelector("div[class='purchasePerPackageAndValidity']");
		this.salesPerPackageAndValidity = document.querySelector("div[class='purchasePerPackageAndValidity']>table>tbody");
		
		this.hide = function hide() {
			this.element.style.display = "none";
		}

		this.clear = function clear() {
			while (this.salesPerPackageAndValidity.children.length > 0) {
				this.salesPerPackageAndValidity.removeChild(this.salesPerPackageAndValidity.children[0]);
			}
		}

		this.show = function show() {
			let i = 0;
			for (i = 0; i < dataPerPackage.length; i++) {
				let newRow = document.createElement("tr");
				let packageCell = document.createElement("td");
				packageCell.textContent = dataPerPackage[i].property0;
				newRow.appendChild(packageCell);
				let validityCell = document.createElement("td");
				validityCell.setAttribute("class", "number");
				validityCell.textContent = dataPerPackage[i].property1;
				newRow.appendChild(validityCell);
				let totPurchaseCell = document.createElement("td");
				totPurchaseCell.setAttribute("class", "number");
				totPurchaseCell.textContent = dataPerPackage[i].property2;
				newRow.appendChild(totPurchaseCell);
				salesPerPackageAndValidity.salesPerPackageAndValidity.appendChild(newRow);
			}
			this.element.removeAttribute("style");
		}
		
		this.hide();
		this.clear();
	}

	function AmountWithoutOptional() {
		this.element = document.querySelector("div[class='amountWithoutOptionalProduct']");
		this.amountWithoutOptional = document.querySelector("div[class='amountWithoutOptionalProduct']>table>tbody");
	
		this.hide = function hide() {
			this.element.style.display = "none";
		}

		this.clear = function clear() {
			while (this.amountWithoutOptional.children.length > 0) {
				this.amountWithoutOptional.removeChild(this.amountWithoutOptional.children[0]);
			}
		}
		
		this.show = function show() {
			let i = 0;
			for (i = 0; i < dataPerPackage.length; i++) {
				let newRow = document.createElement("tr");
				let packageCell = document.createElement("td");
				packageCell.textContent = dataPerPackage[i].property0;
				newRow.appendChild(packageCell);
				let totAmountCell = document.createElement("td");
				totAmountCell.setAttribute("class", "number");
				totAmountCell.textContent = dataPerPackage[i].property1 + '\u20ac';
				newRow.appendChild(totAmountCell);
				amountWithoutOptional.amountWithoutOptional.appendChild(newRow);
			}
			this.element.removeAttribute("style");
		}
		
		this.hide();
		this.clear();
	}

	function AmountWithOptional() {
		this.element = document.querySelector("div[class='amountWithOptionalProduct']");
		this.amountWithOptional = document.querySelector("div[class='amountWithOptionalProduct']>table>tbody");
		
		this.hide = function hide() {
			this.element.style.display = "none";
		}

		this.clear = function clear() {
			while (this.amountWithOptional.children.length > 0) {
				this.amountWithOptional.removeChild(this.amountWithOptional.children[0]);
			}
		}
		
		this.show = function show() {
			let i = 0;
			for (i = 0; i < dataPerPackage.length; i++) {
				let newRow = document.createElement("tr");
				let packageCell = document.createElement("td");
				packageCell.textContent = dataPerPackage[i].property0;
				newRow.appendChild(packageCell);
				let totAmountCell = document.createElement("td");
				totAmountCell.setAttribute("class", "number");
				totAmountCell.textContent = dataPerPackage[i].property1 + '\u20ac';
				newRow.appendChild(totAmountCell);
				amountWithOptional.amountWithOptional.appendChild(newRow);
			}
			this.element.removeAttribute("style");
		}
		this.hide();
		this.clear();
	}

	function AverageNumberOfOptional() {
		this.element = document.querySelector("div[class='averageNumberOfOptionalProduct']");
		this.averageNumberOfOptional = document.querySelector("div[class='averageNumberOfOptionalProduct']>table>tbody");
		
		this.hide = function hide() {
			this.element.style.display = "none";
		}

		this.clear = function clear() {
			while (this.averageNumberOfOptional.children.length > 0) {
				this.averageNumberOfOptional.removeChild(this.averageNumberOfOptional.children[0]);
			}
		}
		
		this.show = function show() {
			let i = 0;
			for (i = 0; i < dataPerPackage.length; i++) {
				let newRow = document.createElement("tr");
				let packageCell = document.createElement("td");
				packageCell.textContent = dataPerPackage[i].property0;
				newRow.appendChild(packageCell);
				let avgCell = document.createElement("td");
				avgCell.setAttribute("class", "number");
				if(dataPerPackage[i].property1!=0){
					avgCell.textContent = dataPerPackage[i].property2/dataPerPackage[i].property1;
				}else{
					avgCell.textContent ="0";
				}
				newRow.appendChild(avgCell);
				averageNumberOfOptional.averageNumberOfOptional.appendChild(newRow);
			}
			this.element.removeAttribute("style");
		}
		this.hide();
		this.clear();
	}

	function InsolventClients() {
		this.element = document.querySelector("div[class='insolventClients']");
		this.insolventClients = document.querySelector("div[class='insolventClients']>table>tbody");
		
		this.hide = function hide() {
			this.element.style.display = "none";
		}

		this.clear = function clear() {
			while (this.insolventClients.children.length > 0) {
				this.insolventClients.removeChild(this.insolventClients.children[0]);
			}
		}
		
		this.show = function show() {
			let i = 0;
			for (i = 0; i < dataPerPackage.length; i++) {
				let newRow = document.createElement("tr");
				let usernameCell = document.createElement("td");
				usernameCell.textContent = dataPerPackage[i].username;
				newRow.appendChild(usernameCell);
				let emailCell = document.createElement("td");
				emailCell.textContent = dataPerPackage[i].email;
				newRow.appendChild(emailCell);
				insolventClients.insolventClients.appendChild(newRow);
			}
			this.element.removeAttribute("style");
		}
		
		this.hide();
		this.clear();
	}

	function SuspendedOrders() {
		this.element = document.querySelector("div[class='suspendedOrders']");
		this.suspendedOrders = document.querySelector("div[class='suspendedOrders']>table>tbody");
		
		this.hide = function hide() {
			this.element.style.display = "none";
		}

		this.clear = function clear() {
			while (this.suspendedOrders.children.length > 0) {
				this.suspendedOrders.removeChild(this.suspendedOrders.children[0]);
			}
		}
		
		this.show = function show() {
			let i = 0;
			for (i = 0; i < dataPerPackage.length; i++) {
				let newRow = document.createElement("tr");
				let orderCell = document.createElement("td");
				orderCell.textContent = dataPerPackage[i].property0;
				newRow.appendChild(orderCell);
				let dateCell = document.createElement("td");
				dateCell.setAttribute("class", "number");
				dateCell.textContent = dataPerPackage[i].property1;
				newRow.appendChild(dateCell);
				let timeCell = document.createElement("td");
				timeCell.setAttribute("class", "number");
				timeCell.textContent = dataPerPackage[i].property2;
				newRow.appendChild(timeCell);
				suspendedOrders.suspendedOrders.appendChild(newRow);
			}
			this.element.removeAttribute("style");
		}
		
		this.hide();
		this.clear();
	}

	function Alerts() {
		this.element = document.querySelector("div[class='alerts']");
		this.alerts = document.querySelector("div[class='alerts']>table>tbody");
		
		this.hide = function hide() {
			this.element.style.display = "none";
		}

		this.clear = function clear() {
			while (this.alerts.children.length > 0) {
				this.alerts.removeChild(this.alerts.children[0]);
			}
		}
		
		this.show = function show() {
			let i = 0;
			for (i = 0; i < dataPerPackage.length; i++) {
				let newRow = document.createElement("tr");
				let orderCell = document.createElement("td");
				orderCell.textContent = dataPerPackage[i].property0;
				newRow.appendChild(orderCell);
				let usernameCell = document.createElement("td");
				usernameCell.textContent = dataPerPackage[i].property1;
				newRow.appendChild(usernameCell);
				let emailCell = document.createElement("td");
				emailCell.textContent = dataPerPackage[i].property2;
				newRow.appendChild(emailCell);
				let amountCell = document.createElement("td");
				amountCell.setAttribute("class", "number");
				amountCell.textContent = dataPerPackage[i].property3 + '\u20ac';
				newRow.appendChild(amountCell);
				let dateCell = document.createElement("td");
				dateCell.setAttribute("class", "number");
				dateCell.textContent = dataPerPackage[i].property4;
				newRow.appendChild(dateCell);
				let timeCell = document.createElement("td");
				timeCell.setAttribute("class", "number");
				timeCell.textContent = dataPerPackage[i].property5;
				newRow.appendChild(timeCell);
				alerts.alerts.appendChild(newRow);
			}
			this.element.removeAttribute("style");
		}
		
		this.hide();
		this.clear();
	}

	function BestSeller() {
		this.element = document.querySelector("div[class='bestSeller']");
		this.bestSeller = document.querySelector("div[class='bestSeller']>table>tbody");
		
		this.hide = function hide() {
			this.element.style.display = "none";
		}

		this.clear = function clear() {
			while (this.bestSeller.children.length > 0) {
				this.bestSeller.removeChild(this.bestSeller.children[0]);
			}
		}
		
		this.show = function show() {
			let i = 0;
			for (i = 0; i < dataPerPackage.length; i++) {
				let newRow = document.createElement("tr");
				let optionalProductCell = document.createElement("td");
				optionalProductCell.textContent = dataPerPackage[i].id_optprod;
				newRow.appendChild(optionalProductCell);
				let revenueCell = document.createElement("td");
				revenueCell.setAttribute("class", "number");
				revenueCell.textContent = dataPerPackage[i].tot_revenue + '\u20ac';
				newRow.appendChild(revenueCell);
				bestSeller.bestSeller.appendChild(newRow);
			}
			this.element.removeAttribute("style");
		}
		this.hide();
		this.clear();
	}

	function Button() {
		this.element = document.querySelector("div[class='Buttons']");
		this.purchasePerPackageButton = document.getElementById("purchasePerPackageButton");
		this.purchasePerPackageAndValidityPeriodButton = document.getElementById("purchasePerPackageAndValidityPeriodButton");
		this.amountWithoutOptionalButton = document.getElementById("amountWithoutOptionalButton");
		this.amountWithOptionalButton = document.getElementById("amountWithOptionalButton");
		this.averageNumberButton = document.getElementById("averageNumberButton");
		this.insolventClientsButton = document.getElementById("insolventClientsButton");
		this.suspendedOrdersButton = document.getElementById("suspendedOrdersButton");
		this.alertsButton = document.getElementById("alertsButton");
		this.bestSellerButton = document.getElementById("bestSellerButton");
		let choice;
		
		this.purchasePerPackageButton.addEventListener('click', (e) => {
				e.preventDefault();
				this.clear();
				choice=salesPerPackage;
				makeCall("GET", "JSONProvider?table=perPackage", this.update);
		})

		this.purchasePerPackageAndValidityPeriodButton.addEventListener('click', (e) => {
				e.preventDefault();
				this.clear();
				choice=salesPerPackageAndValidity;
				makeCall("GET", "JSONProvider?table=perValidity", this.update);
		})

		this.amountWithoutOptionalButton.addEventListener('click', (e) => {
				e.preventDefault();
				this.clear();
				choice=amountWithoutOptional;
				makeCall("GET", "JSONProvider?table=withoutOpt", this.update);
		})
		
		this.amountWithOptionalButton.addEventListener('click', (e) => {
				e.preventDefault();
				this.clear();
				choice=amountWithOptional;
				makeCall("GET", "JSONProvider?table=withOpt", this.update);
		})
		
		this.averageNumberButton.addEventListener('click', (e) => {
				e.preventDefault();
				this.clear();
				choice=averageNumberOfOptional;
				makeCall("GET", "JSONProvider?table=average", this.update);
		})
		
		this.insolventClientsButton.addEventListener('click', (e) => {
				e.preventDefault();
				this.clear();
				choice=insolventClients;
				makeCall("GET", "JSONProvider?table=insolvent",this.update);
		}) 
		
		this.suspendedOrdersButton.addEventListener('click', (e) => {
				e.preventDefault();
				this.clear();
				choice=suspendedOrders;
				makeCall("GET", "JSONProvider?table=suspended", this.update);
		}) 
		
		this.alertsButton.addEventListener('click', (e) => {
			e.preventDefault();
			this.clear();
			choice=alerts;
			makeCall("GET", "JSONProvider?table=alerts", this.update);
		}) 
		
		this.bestSellerButton.addEventListener('click', (e) => {
			e.preventDefault();
			this.clear();
			choice=bestSeller;
			makeCall("GET", "JSONProvider?table=best", this.update);
		}) 
		
		this.clear = function clear() {
			salesPerPackage.hide();
			salesPerPackage.clear();
			salesPerPackageAndValidity.hide();
			salesPerPackageAndValidity.clear();
			amountWithoutOptional.hide();
			amountWithoutOptional.clear();
			amountWithOptional.hide();
			amountWithOptional.clear();
			averageNumberOfOptional.hide();
			averageNumberOfOptional.clear();
			insolventClients.hide();
			insolventClients.clear();
			suspendedOrders.hide();
			suspendedOrders.clear();
			alerts.hide();
			alerts.clear();
			bestSeller.hide();
			bestSeller.clear();
		}
		
		this.update = function update(req) {
			if (req.readyState === 4) {
				if (req.status === 200) {			
					dataPerPackage = JSON.parse(req.responseText);
					console.log(dataPerPackage);
					choice.show();
				}
			}
		}
	}
}())