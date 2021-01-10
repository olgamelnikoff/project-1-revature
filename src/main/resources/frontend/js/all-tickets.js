let tab = document.getElementsByTagName("tbody")[0];

window.onload=function(){
	fullTable();
}

function fullTable() {
	getSession(function(data) {
		let id = data;
		let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function(){
		
		if(xhttp.readyState == 4 && xhttp.status == 200){
			let allReimbursements = JSON.parse(xhttp.responseText);
			for (let i = 0; i < allReimbursements.length; i++) {
				let newRow = tab.insertRow(i);
				let singleObject = allReimbursements[i];
				let singleArray = Object.values(singleObject);
				let authorID;
				
				for (let j = 0; j < singleArray.length; j++) {
					let cell = newRow.insertCell(j);
					let thisValue = singleArray[j]
					if (j == 2 || j == 3) {
						var date = new Date (thisValue).toLocaleDateString("en-US");
						if (date == "12/31/1969") {
							cell.innerText = "Not resolved yet"
						}
						else {
							cell.innerText = date;
						}
					}
					
					else if (j == 6) {
						authorID = thisValue;
						getNames (function(name) {
						cell.innerText = name;
						}, authorID);
					}
					
					else if (j == 7 && thisValue == 0) {
						cell.innerText = "Not resolved yet"
					}
					else {
						cell.innerText = thisValue;
						
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

function getSession(callback){
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function(){
		
		if(xhttp.readyState == 4 && xhttp.status == 200){
			emplObj = JSON.parse(xhttp.responseText);
			emplId = emplObj.id;
			callback(emplId);
		}
	}
	
	xhttp.open("GET", "http://localhost:7001/employees/session");
	
	xhttp.send();
}

function getNames (callback, authorID) {		
									
	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {

	if (xhttp.readyState == 4 && xhttp.status == 200) {
		emplObj = JSON.parse(xhttp.responseText);
		emplFirstName = emplObj.firstName;
		emplLastName = emplObj.lastName;
		let stringOfNames = emplFirstName + " " + emplLastName;
		callback (stringOfNames);
									
		}
	}

	xhttp.open("GET", `http://localhost:7001/employees/${authorID}/get-first-and-last-name`);

	xhttp.send();								
};