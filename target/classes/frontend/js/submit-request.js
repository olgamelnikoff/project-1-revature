

let submitButton = document.getElementById("requestSubmit");
submitButton.addEventListener("click", getSession(function(id) {
		var form = document.getElementById("submit-request-form");
		form.action = '/employees/' + id + '/new-request';
}));


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