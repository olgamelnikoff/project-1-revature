/**
 * Getting a session user
 */
window.onload=function(){
	console.log("js is linked");
	getSessionVillain();
}

function getSessionVillain(){
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function(){
		
		if(xhttp.readyState == 4 && xhttp.status == 200){
			let vill = JSON.parse(xhttp.responseText);
			console.log(vill);
		}
	}
	
	xhttp.open("GET", "http://localhost:7001/employees/session");
	
	xhttp.send();
}