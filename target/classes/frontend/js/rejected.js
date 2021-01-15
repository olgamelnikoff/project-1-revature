let tab = document.getElementsByTagName("tbody")[0];

window.onload = function() {
	fullTable();
}

function fullTable() {
	getSession(function(data) {
		let id = data;
		let xhttp = new XMLHttpRequest();

		xhttp.onreadystatechange = function() {

			if (xhttp.readyState == 4 && xhttp.status == 200) {
				let allReimbursements = JSON.parse(xhttp.responseText);


				for (let i = 0; i < allReimbursements.length; i++) {

					let singleObjectNew = allReimbursements[i];
					let singleArrayNew = Object.values(singleObjectNew);
					let currentStatus = singleArrayNew[8];
					let authorID;
					let resolverID;

					if (currentStatus == 3) {

						let newRowNew = tab.insertRow(tab.rows.length - 1);
						for (let j = 0; j < singleArrayNew.length; j++) {
							let cellNew = newRowNew.insertCell(j);
							let thisValueNew = singleArrayNew[j]
							if (j == 2 || j == 3) {
								var date = new Date(thisValueNew).toLocaleDateString("en-US");
								cellNew.innerText = date;
							}
							else if (j == 6) {
								authorID = thisValueNew;
								getNames(function(name) {
									cellNew.innerText = name;
								}, authorID);
							}

							else if (j == 7) {
								resolverID = thisValueNew;
								getNames(function(name) {
									cellNew.innerText = name;
								}, resolverID);
							}

							else if (j == 8) {
								cellNew.innerText = "Rejected";
							}

							else if (j == 9) {
								if (thisValueNew == 1) {
									cellNew.innerText = "Lodging";
								}
								else if (thisValueNew == 2) {
									cellNew.innerText = "Travel";
								}

								else if (thisValueNew == 3) {
									cellNew.innerText = "Food";
								}

								else if (thisValueNew == 4) {
									cellNew.innerText = "Other";
								}

							}
							else {
								cellNew.innerText = thisValueNew;

							}
						}
					}
				}

			}
		}

		xhttp.open("GET", `http://localhost:7001/employees/view-requests`);

		xhttp.send();

	});
}

let emplObj;
let emplId;

function getSession(callback) {
	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {

		if (xhttp.readyState == 4 && xhttp.status == 200) {
			emplObj = JSON.parse(xhttp.responseText);
			emplId = emplObj.id;

			callback(emplId);
		}
	}

	xhttp.open("GET", "http://localhost:7001/employees/session");

	xhttp.send();
}

function getNames(callback, authorID) {

	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {

		if (xhttp.readyState == 4 && xhttp.status == 200) {
			emplObj = JSON.parse(xhttp.responseText);
			emplFirstName = emplObj.firstName;
			emplLastName = emplObj.lastName;
			let stringOfNames = emplFirstName + " " + emplLastName;
			callback(stringOfNames);

		}
	}

	xhttp.open("GET", `http://localhost:7001/employees/${authorID}/get-first-and-last-name`);

	xhttp.send();
};