let tab = document.getElementsByTagName("tbody")[0];

window.onload = function() {
	fullTable();
}

function fullTable() {
	getSession(function(data) {
		let resId = data;
		let reimbId;
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

					if (currentStatus == 1) {
						reimbId = singleArrayNew[0];

						let newRowNew = tab.insertRow(tab.rows.length - 1);
						for (let j = 0; j < singleArrayNew.length; j++) {
							let cellNew = newRowNew.insertCell(j);
							let thisValueNew = singleArrayNew[j]
							if (j == 2) {
								var date = new Date(thisValueNew).toLocaleDateString("en-US");
								cellNew.innerText = date;
							}
							else if (j == 3) {
								cellNew.innerText = "Not resolved yet";
							}
							else if (j == 6) {
								authorID = thisValueNew;
								getNames(function(name) {
									cellNew.innerText = name;
								}, authorID);
							}

							else if (j == 7) {
								cellNew.innerText = "Not resolved yet";
							}

							else if (j == 8) {
								cellNew.innerText = "Pending";
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
						let approveCell = newRowNew.insertCell(newRowNew.cells.length);
						let approveButton = document.createElement("button");
						approveButton.innerHTML = "Approve";
						approveButton.setAttribute("id", `${reimbId}`);
						approveButton.setAttribute("class", "approval");
						approveButton.setAttribute("style", "background-color: khaki");
						approveCell.appendChild(approveButton);

						let rejectCell = newRowNew.insertCell(newRowNew.cells.length);
						let rejectButton = document.createElement("button");
						rejectButton.innerHTML = "Reject";
						rejectButton.setAttribute("id", `${reimbId}`);
						rejectButton.setAttribute("style", "background-color: khaki");
						rejectCell.appendChild(rejectButton);

						approveButton.addEventListener("click", function() {
							let reimbursementId = this.id;
							let xhttp = new XMLHttpRequest();

							xhttp.onreadystatechange = function() {

								if (xhttp.readyState == 4 && xhttp.status == 200) {
									if (authorID == resId) {
										alert("You cannot approve your own request.");
									}
									else {
										alert("The request has been approved.");
									}

								}
							}

							xhttp.open("POST", `http://localhost:7001/employees/${reimbursementId}/${resId}/approve`);

							xhttp.send();


						});

						rejectButton.addEventListener("click", function() {
							let reimbursementId = this.id;
							let xhttp = new XMLHttpRequest();

							xhttp.onreadystatechange = function() {

								if (xhttp.readyState == 4 && xhttp.status == 200) {
									alert("The request has been rejected");
								}
							}

							xhttp.open("POST", `http://localhost:7001/employees/${reimbursementId}/${resId}/reject`);

							xhttp.send();


						});
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
			emplFirstName = emplObj.firstName;
			emplLastName = emplObj.lastName;

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