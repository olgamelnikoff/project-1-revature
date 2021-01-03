/**
 * Getting a session user
 */
window.onload=function(){
	console.log("js is linked");
	getSession(function(data) {
		let id = data;
		let xhttp = new XMLHttpRequest();
		console.log(id);
	
	xhttp.onreadystatechange = function(){
		
		if(xhttp.readyState == 4 && xhttp.status == 200){
			console.log(id);
			let emplReimbursements = JSON.parse(xhttp.responseText);
			
			let tab = document.getElementsByTagName("tbody")[0];
			
			for (let i = 0; i < emplReimbursements.length; i++) {
				let newRow = tab.insertRow(i);
				//console.log("Was in first for");
				let singleObject = emplReimbursements[i];
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
			
			console.log(emplReimbursements);
			
		}
	}
	
	xhttp.open("GET", `http://localhost:7001/employees/${id}/view-requests`);
	
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


