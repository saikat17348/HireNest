const regex = /^[A-Za-z0-9]+@[a-z]+\.[a-z]{3}$/;

fun=async(event)=>{
	event.preventDefault();
	
	const email = document.getElementById("email").value;
	
	
	if(email.length > 1)
		{
			if(regex.test(email))
				{
					const password = document.getElementById("password").value;
					
					
					if(password.length > 1)
						{
							try{

									const response = await axios.post("http://localhost:8080/employee/login",{email:email, password:password});
									if(response.data === "employeeSignUp")
										{
											const errormsg = document.getElementById("pass-error");
											errormsg.innerHTML = "Enter correct email and password";
										}
										else
										{
											//alert("Successfully logged In")
											window.location.assign("/HTML/employeeJobPost.html")
										}
								}
								catch(error){
									alert(error);
								}
						}
						else
						{
							alert("enter pssword");
						}
				}
		}
		else
		{
			alert("Enter email");
		}

}