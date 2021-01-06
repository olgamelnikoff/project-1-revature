let tab = document.getElementsByTagName("tbody")[0];

window.onload=function(){
	fullTable();
}

function test () {
	
}

function fullTable() {
	getSession(function(data) {
		let id = data;
		let xhttp = new XMLHttpRequest();
		console.log(id);
	
	xhttp.onreadystatechange = function(){
		
		if(xhttp.readyState == 4 && xhttp.status == 200){
			let allReimbursements = JSON.parse(xhttp.responseText);
			for (let i = 0; i < allReimbursements.length; i++) {
				let newRow = tab.insertRow(i);
				let singleObject = allReimbursements[i];
				let singleArray = Object.values(singleObject);
				
				for (let j = 0; j < singleArray.length; j++) {
					let cell = newRow.insertCell(j);
					let thisValue = singleArray[j]
					if (j == 2 || j == 3) {
						var date = new Date (thisValue).toLocaleDateString("en-US");
						cell.innerText = date;
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
			
			console.log(emplObj);
			console.log(emplObj.id);
			
			callback(emplId);
		}
	}
	
	xhttp.open("GET", "http://localhost:7001/employees/session");
	
	xhttp.send();
}