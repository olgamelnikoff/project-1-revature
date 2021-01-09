/**
 * Getting a session user
 */

let submitButton = document.getElementById("credentialsSubmit");
var form = document.getElementById("submit-request-form");

submitButton.addEventListener("click", function() {
		let xhttp = new XMLHttpRequest();
	
		xhttp.onreadystatechange = function(){
			console.log(xhttp.status);
		
		if(xhttp.readyState == 4 && xhttp.status == 200){
			form.action = '/employees/login';
		}
		else if (xhttp.readyState == 4 && xhttp.status == 404){
			alert ("Username does not exist.");
		}
	}
	
	xhttp.open("POST", "http://localhost:7001/employees/login");
	
	xhttp.send();
		
		
		
		
		

		
	});


/*let emplObj;
let emplId;

function getUser(callback){
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function(){
		
		if(xhttp.readyState == 4 && xhttp.status == 200){
			emplObj = JSON.parse(xhttp.responseText);
			emplId = emplObj.id;
			
			console.log(emplObj);
			console.log(emplObj.id);
			callback(emplId);
			

		}
		else {
			alert ("Username does not exist.");
		}
	}
	
	xhttp.open("GET", "http://localhost:7001/employees/login");
	
	xhttp.send();
}*/


