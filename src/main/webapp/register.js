
function validateUser() {
	event.preventDefault();
	const name = document.querySelector("#name").value;
	const email = document.querySelector("#email").value;
	const number = document.querySelector("#mobile").value;
	const password = document.querySelector("#password").value;
	const city = document.querySelector("#city").value;

	//let user={name:name,email:email,number:number,password:password};
	try {
		if (name == null || name.trim() == "") {
			throw new Error("Name cannot be null or empty");
		}
		else if (!email.includes("@") || !email.includes(".")) {
			throw new Error("Enter valid email");
		}
		else if (number.length != 10) {
			throw new Error("Enter valid mobile number")
		}
		else if (password.length < 8 || password.length > 16) {
			throw new Error("Password should be minimum 8 characters and maximum 16 characters");
		}
		else if (city == null || city.trim() == "") {
			throw new Error("City name cannot be null");
		}
		else{
			count = 1;
		//	document.getElementById("message").innerHTML="Successful";
		//	alert("success");
		}
	} catch (Error) {
		count = 0;
//		document.getElementById("message").innerHTML=Error.message;
		setTimeout(function(){toastr.error(Error.message);},3000);	
	} 
	console.log(count);
	if(count == 1){
	    const url = "http://localhost:8080/ChocoWebDemo/RegisterServlet?name=" + name + "&email=" + email + "&mobile=" + number + "&password=" + password + "&city=" + city;	
	    fetch(url).then(res => res.json()).then(res => {
		//const url = "http://localhost:9000/user/register";
		//fetch(url).then(res => res.text()).then(res => {
		let output = res;
		console.log(output);
//		document.getElementById("message").innerHTML = output;
	
		if(output.includes("Welcome")){
			toastr.success(output);
			setTimeout(function(){window.location.replace('Login.html');},3000);
			//window.location.replace('Login.html');
		}
		else{
			toastr.error(output+ " please continue to login");
//			toastr.error(output+" continue to login");
			setTimeout(function(){window.location.replace('Registration.html');},3000);
			//window.location.replace('Registration.html');
		}
		//console.log("Registered Successfully");
		//let content="";
		// for(let user of users){
		// 	content+=`<tr><td>${user.id}</td><td>${user.name}</td></tr>`;
		// }
		//document.querySelector("usersTbl").innerHTML=content;

	});
}
}
function back(){
	event.preventDefault();
	window.location.replace('ShowChocolates.html');
}

//toastr.success("Success");
//toastr.error("Failure");