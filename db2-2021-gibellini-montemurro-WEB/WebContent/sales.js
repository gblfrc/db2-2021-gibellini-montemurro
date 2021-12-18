(function() {
	var buttons, salesPerPackage, salesPerPackageAndValidity, amountWithoutOptional, amountWithOptional,
		averageNumberOfOptional, insolventClients, suspendedOrders, alerts, bestSeller, 
		dataPerPackage, dataPerPackageAndValidity, insolventList, suspendedList, alertList, bestSellerList;

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
		// function to hide appeals table
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
			for (i = 0; i < dataPerPackage.length; i++) {
				let newRow = document.createElement("tr");
				let packageCell = document.createElement("td");
				packageCell.textContent = dataPerPackage[i][0];
				newRow.appendChild(packageCell);
				let totPurchaseCell = document.createElement("td");
				totPurchaseCell.textContent = dataPerPackage[i][1];
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
		// function to hide appeals table
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
			for (i = 0; i < dataPerPackageAndValidity.length; i++) {
				let newRow = document.createElement("tr");
				let packageCell = document.createElement("td");
				packageCell.textContent = dataPerPackageAndValidity[i].id_package;
				newRow.appendChild(packageCell);
				let validityCell = document.createElement("td");
				validityCell.textContent = dataPerPackageAndValidity[i].months;
				newRow.appendChild(validityCell);
				let totPurchaseCell = document.createElement("td");
				totPurchaseCell.textContent = dataPerPackageAndValidity[i].totPurchase;
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
		// function to hide appeals table
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
				packageCell.textContent = dataPerPackage[i][0];
				newRow.appendChild(packageCell);
				let totAmountCell = document.createElement("td");
				totAmountCell.textContent = dataPerPackage[i][2] + '\u20ac';
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
		// function to hide appeals table
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
				packageCell.textContent = dataPerPackage[i][0];
				newRow.appendChild(packageCell);
				let totAmountCell = document.createElement("td");
				totAmountCell.textContent = dataPerPackage[i][3] + '\u20ac';
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
		// function to hide appeals table
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
				packageCell.textContent = dataPerPackage[i][0];
				newRow.appendChild(packageCell);
				let avgCell = document.createElement("td");
				if(dataPerPackage[i][1]!=0){
					avgCell.textContent = dataPerPackage[i][4]/dataPerPackage[i][1];
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
		// function to hide appeals table
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
			for (i = 0; i < insolventList.length; i++) {
				let newRow = document.createElement("tr");
				let usernameCell = document.createElement("td");
				usernameCell.textContent = insolventList[i].username;
				newRow.appendChild(usernameCell);
				let emailCell = document.createElement("td");
				emailCell.textContent = insolventList[i].email;
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
		// function to hide appeals table
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
			for (i = 0; i < suspendedList.length; i++) {
				let newRow = document.createElement("tr");
				let orderCell = document.createElement("td");
				orderCell.textContent = suspendedList[i][0];
				newRow.appendChild(orderCell);
				let dateCell = document.createElement("td");
				dateCell.textContent = suspendedList[i][1];
				newRow.appendChild(dateCell);
				let timeCell = document.createElement("td");
				timeCell.textContent = suspendedList[i][2];
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
		// function to hide appeals table
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
			for (i = 0; i < alertList.length; i++) {
				let newRow = document.createElement("tr");
				let orderCell = document.createElement("td");
				orderCell.textContent = alertList[i][0];
				newRow.appendChild(orderCell);
				let usernameCell = document.createElement("td");
				usernameCell.textContent = alertList[i][1];
				newRow.appendChild(usernameCell);
				let emailCell = document.createElement("td");
				emailCell.textContent = alertList[i][2];
				newRow.appendChild(emailCell);
				let amountCell = document.createElement("td");
				amountCell.textContent = alertList[i][3] + '\u20ac';
				newRow.appendChild(amountCell);
				let dateCell = document.createElement("td");
				dateCell.textContent = alertList[i][4];
				newRow.appendChild(dateCell);
				let timeCell = document.createElement("td");
				timeCell.textContent = alertList[i][5];
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
		// function to hide appeals table
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
			for (i = 0; i < bestSellerList.length; i++) {
				let newRow = document.createElement("tr");
				let optionalProductCell = document.createElement("td");
				optionalProductCell.textContent = bestSellerList[i].id_optprod;
				newRow.appendChild(optionalProductCell);
				let revenueCell = document.createElement("td");
				revenueCell.textContent = bestSellerList[i].tot_revenue + '\u20ac';
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
		let firstCallDone = 0;
		
		this.purchasePerPackageButton.addEventListener('click', (e) => {
			if (firstCallDone === 0) {
				makeCall("GET", "GetSalesPage", null, this.update);
				salesPerPackage.show();
				firstCallDone = 1;
			}
			else {
				this.clear();
				salesPerPackage.show();
			}
		})

		this.purchasePerPackageAndValidityPeriodButton.addEventListener('click', (e) => {
			if (firstCallDone === 0) {
				makeCall("GET", "GetSalesPage", null, this.update);
				salesPerPackageAndValidity.show();
				firstCallDone = 1;
			}
			else {
				this.clear();
				salesPerPackageAndValidity.show();
			}
		})

		this.amountWithoutOptionalButton.addEventListener('click', (e) => {
			if (firstCallDone === 0) {
				makeCall("GET", "GetSalesPage", null, this.update);
				amountWithoutOptional.show();
				firstCallDone = 1;
			}
			else {
				this.clear();
				amountWithoutOptional.show();
			}
		})
		
		this.amountWithOptionalButton.addEventListener('click', (e) => {
			if (firstCallDone === 0) {
				makeCall("GET", "GetSalesPage", null, this.update);
				amountWithOptional.show();
				firstCallDone = 1;
			}
			else {
				this.clear();
				amountWithOptional.show();
			}
		})
		
		this.averageNumberButton.addEventListener('click', (e) => {
			if (firstCallDone === 0) {
				makeCall("GET", "GetSalesPage", null, this.update);
				averageNumberOfOptional.show();
				firstCallDone = 1;
			}
			else {
				this.clear();
				averageNumberOfOptional.show();
			}
		})
		
		this.insolventClientsButton.addEventListener('click', (e) => {
			if (firstCallDone === 0) {
				makeCall("GET", "GetSalesPage", null, this.update);
				insolventClients.show();
				firstCallDone = 1;
			}
			else {
				this.clear();
				insolventClients.show();
			}
		}) 
		
		this.suspendedOrdersButton.addEventListener('click', (e) => {
			if (firstCallDone === 0) {
				makeCall("GET", "GetSalesPage", null, this.update);
				suspendedOrders.show();
				firstCallDone = 1;
			}
			else {
				this.clear();
				suspendedOrders.show();
			}
		}) 
		
		this.alertsButton.addEventListener('click', (e) => {
			if (firstCallDone === 0) {
				makeCall("GET", "GetSalesPage", null, this.update);
				alerts.show();
				firstCallDone = 1;
			}
			else {
				this.clear();
				alerts.show();
			}
		}) 
		
		this.bestSellerButton.addEventListener('click', (e) => {
			if (firstCallDone === 0) {
				makeCall("GET", "GetSalesPage", null, this.update);
				bestSeller.show();
				firstCallDone = 1;
			}
			else {
				this.clear();
				bestSeller.show();
			}
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
			alerts.hide();
			bestSeller.hide();
			bestSeller.hide();
		}
		this.update = function update(req) {
			if (req.readyState === 4) {
				if (req.status === 200) {
					let replaced = req.responseText.replaceAll("}]", "]]");
					let objResponse = replaced.split("]]");

					dataPerPackage = JSON.parse(objResponse[0] + "]]");
					dataPerPackageAndValidity = JSON.parse(objResponse[1] + "}]");
					insolventList= JSON.parse(objResponse[2] + "}]");
					suspendedList = JSON.parse(objResponse[3] + "]]");
					alertList= JSON.parse(objResponse[4] + "]]");
					bestSellerList= JSON.parse(objResponse[5] + "}]");
				}
			}
		}
	}
}())