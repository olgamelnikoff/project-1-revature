/**
 * Getting a session user
 */

let tab = document.getElementsByTagName("tbody")[0];
let filter = document.getElementById("filter");

filter.addEventListener('change', function() {
	let type = this.value;
	$("#tbody").remove();
	let newTBody = document.createElement("tbody");
	let thisTable = document.getElementById("thistable");
	thisTable.appendChild(newTBody);
	//let tabOld = tab;
	if(type == "Full Table") {
		console.log("Was in full table");
		fullTable();
	}
	console.log("Type is" + type);
	//let tabNew = document.createElement('tbody');
	//tabOld.parentNode.replaceChild(tabNew, tabOld);
	let xhttp = new XMLHttpRequest();
		
	xhttp.onreadystatechange = function(){
		//console.log("Type 2 is" + type);
		
		if(xhttp.readyState == 4 && xhttp.status == 200){
			//console.log("Type 3 is" + type);
			
			let allReimbursementsNew = JSON.parse(xhttp.responseText);
			
			//let tabNew = document.getElementsByTagName("tbody")[0];
			
			for (let i = 0; i < allReimbursementsNew.length; i++) {
				
				//console.log("Was in first for");
				let singleObjectNew = allReimbursementsNew[i];
				let singleArrayNew = Object.values(singleObjectNew);
				let currentType = singleArrayNew[9];
				console.log(currentType);
				console.log(type);
				
				if(currentType == type) {
					//console.log("Wash in if");
					let newRowNew = newTBody.insertRow(newTBody.rows.length - 1);
					for (let j = 0; j < singleArrayNew.length; j++) {
							let cellNew = newRowNew.insertCell(j);
							let thisValueNew = singleArrayNew[j]
							if (j == 2 || j ==3) {
								//console.log(thisValue);
								var date = new Date (thisValueNew).toLocaleDateString("en-US");
								cellNew.innerText = date;
							}
							else {
								cellNew.innerText = thisValueNew;
								
							}
							//console.log("Wash in second for");
						}
				}
			}
			
			console.log(allReimbursementsNew);
			
		}
	}
	
	xhttp.open("GET", `http://localhost:7001/employees/view-requests`);
	
	xhttp.send();
	
});

function testFunction() {
	console.log("change has been made");
}
window.onload=function(){
	console.log("js is linked");
	fullTable();
}

function fullTable() {
	getSession(function(data) {
		let id = data;
		let xhttp = new XMLHttpRequest();
		console.log(id);
	
	xhttp.onreadystatechange = function(){
		
		if(xhttp.readyState == 4 && xhttp.status == 200){
			console.log(id);
			let allReimbursements = JSON.parse(xhttp.responseText);
			
			
			for (let i = 0; i < allReimbursements.length; i++) {
				let newRow = tab.insertRow(i);
				//console.log("Was in first for");
				let singleObject = allReimbursements[i];
				let singleArray = Object.values(singleObject);
				
				for (let j = 0; j < singleArray.length; j++) {
					let cell = newRow.insertCell(j);
					let thisValue = singleArray[j]
					if (j == 2 || j ==3) {
						//console.log(thisValue);
						var date = new Date (thisValue).toLocaleDateString("en-US");
						cell.innerText = date;
					}
					else {
						cell.innerText = thisValue;
						
					}
					//console.log("Wash in second for");
				}
			}
			
			console.log(allReimbursements);
			
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



/* */


