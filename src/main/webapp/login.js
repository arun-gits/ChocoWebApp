function checkUser(){
     event.preventDefault();
     var count = 0;
     const data = document.querySelector("#data").value;
     const password = document.querySelector("#password").value;
     for(let c of data){
         count=checkNumber(c)+count;
     }
    // console.log(count);
     try{
       if(data.includes("@") && !data.includes(".") || data.includes(".") && !data.includes("@")){
         throw new Error("Enter valid email");
       }
        else if(count!=data.length && !data.includes("@") && !data.includes(".")){
            
            throw new Error("Enter valid email");
        }
        else if(count==data.length && data.length!=10){
            
            throw new Error ("Enter valid mobile number");
        }
        else if(password.length<8 || password.length>16){
          throw new Error("Password should be minimum 8 character and maximum 16 characters");
        }
      // alert("Success");
       console.log("Success");
       temp = 1;
     }
     catch(Error){
       console.log(Error.message);
       toastr.error(Error.message);
       temp = 0;
     }
 	// console.log(temp);
 	 if(temp == 1){
		const url = "http://localhost:8080/ChocoWebDemo/LoginServlet?data="+data+"&password="+password;
		fetch(url).then(res=>res.text()).then(res=>
		{
			let message = res;
			console.log(message);
//			document.getElementById("message").innerHTML=message;
			if(message.includes("Hello")){
				toastr.success(message);
				localStorage.setItem("sessionEmail",data);
				setTimeout(function(){window.location.replace('UserHome.html');},3000);
			}
			else if(!message.includes("Hello")){
				toastr.error(message);
				setTimeout(function(){window.location.replace('Login.html');},3000);
			}
		});
 }
   }
 
function checkNumber(data){
    if(data.includes("1") || data.includes("2") || data.includes("3") || data.includes("4") || data.includes("5") || data.includes("6") || data.includes("7") || data.includes("8") || data.includes("9") || data.includes("0")){
      return 1;
  }
  else{
      return 0;
  }
}
function back(){
	event.preventDefault();
	window.location.replace('ShowChocolates.html');
}