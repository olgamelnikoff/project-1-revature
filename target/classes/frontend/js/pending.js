let tab = document.getElementsByTagName("tbody")[0];

window.onload=function(){
	fullTable();
}

function fullTable() {
	getSession(function(data) {
		let resId = data;
		let reimbId;
		let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function(){
		
		if(xhttp.readyState == 4 && xhttp.status == 200){
			let allReimbursements = JSON.parse(xhttp.responseText);
			
			
			for (let i = 0; i < allReimbursements.length; i++) {
				
				let singleObjectNew = allReimbursements[i];
				let singleArrayNew = Object.values(singleObjectNew);
				let currentStatus = singleArrayNew[8];
				
				if(currentStatus == 1) {
					reimbId = singleArrayNew[0];
					console.log(reimbId);
					
					let newRowNew = tab.insertRow(tab.rows.length - 1);
					for (let j = 0; j < singleArrayNew.length; j++) {
							let cellNew = newRowNew.insertCell(j);
							let thisValueNew = singleArrayNew[j]
							if (j == 2 || j ==3) {
								var date = new Date (thisValueNew).toLocaleDateString("en-US");
								cellNew.innerText = date;
							}
							else {
								cellNew.innerText = thisValueNew;
								
							}
					}
					let approveCell = newRowNew.insertCell(newRowNew.cells.length);
					let approveButton = document.createElement("button");
					approveButton.innerHTML="Approve";
					approveButton.setAttribute("id", `${reimbId}`);
					approveButton.setAttribute("style", "background-color: khaki");
					approveCell.appendChild(approveButton);
					
					let rejectCell = newRowNew.insertCell(newRowNew.cells.length);
					let rejectButton = document.createElement("button");
					rejectButton.innerHTML="Reject";
					rejectButton.setAttribute("id", `${reimbId}`);
					rejectButton.setAttribute("style", "background-color: khaki");
					rejectCell.appendChild(rejectButton);
					
					approveButton.addEventListener("click", function() {
						let reimbursementId = this.id;
						console.log(reimbId);
						console.log(resId);
						let xhttp = new XMLHttpRequest();
	
					xhttp.onreadystatechange = function(){
		
					if(xhttp.readyState == 4 && xhttp.status == 200){
						alert("The request has been approved");
					}
					}
					
					xhttp.open("POST", `http://localhost:7001/employees/${reimbursementId}/${resId}/approve`);
	
					xhttp.send();
	
						
					});
					
					rejectButton.addEventListener("click", function() {
						let reimbursementId = this.id;
						console.log(reimbId);
						console.log(resId);
						let xhttp = new XMLHttpRequest();
	
					xhttp.onreadystatechange = function(){
		
					if(xhttp.readyState == 4 && xhttp.status == 200){
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

function getSession(callback){
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function(){
		
		if(xhttp.readyState == 4 && xhttp.status == 200){
			emplObj = JSON.parse(xhttp.responseText);
			emplId = emplObj.id;
			
/*			console.log(emplObj);
			console.log(emplObj.id);*/
			
			callback(emplId);
		}
	}
	
	xhttp.open("GET", "http://localhost:7001/employees/session");
	
	xhttp.send();
}