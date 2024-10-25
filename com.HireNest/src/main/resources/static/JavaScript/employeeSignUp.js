const regex = /^[A-Za-z0-9]+@[a-z]+\.[a-z]{3}$/;
const passdigit = /[0-9]/;
const passlower = /[a-z]/;
const passupper = /[A-Z]/;

employeeRegister = (event)=>{
	event.preventDefault();
	
	
	const companyName = document.getElementById("companyName").value;
	
	const email = document.getElementById("email").value;

	const password = document.getElementById("password").value;
     
	let status = false;
	// company name validation
	if(companyName.length > 1)
		{
			
			const errmsg = document.getElementById("companyerror");
			errmsg.innerHTML = "";
			status = true;
			
		}
		else
		{
			
			const errmsg = document.getElementById("companyerror");
			errmsg.innerHTML = "Enter Company Name";
			status = false;
			
		}
		
		// email validation
		if(email.length > 1)
			{
				
				if(regex.test(email) ==  true)
					{
					
						const emailerrmsg = document.getElementById("emailerror");
						emailerrmsg.innerHTML = "";
						status = true;
					}
					else
					{
						
						const emailerrmsg = document.getElementById("emailerror");
						emailerrmsg.innerHTML = "Invalid email";
						status = false;
					}
			}
			else
			{
				
				const emailerrmsg = document.getElementById("emailerror");
			    emailerrmsg.innerHTML = "Enter Email";
				status = false;
			}
			
			// password validation
		if(password.length > 1)
			{
				if(passupper.test(password) == false || passlower.test(password) == false || passdigit.test(password) == false)
					{
						
						const pass = document.getElementById("passerr");
						pass.innerHTML = "Password should Atleast contain 1 Capital,Small char and 1 Number";	
						status = false;
					}
					else{
						
						const pass = document.getElementById("passerr");
						pass.innerHTML = "";
						status = true;
					}
			}
			else
			{
				
				const pass = document.getElementById("passerr");
				pass.innerHTML = "Enter Password";
				status = false;
			}
			
			if(status === true)
							{
								data = {
									companyName : companyName,
									email : email,
									password : password
								};
								employeeSignUp(data);
							}
			
}

async function employeeSignUp(data){
	
	const response = await axios.post("http://localhost:8080/employee/register",data);
	if(response.data === "employeeLogin")
		{
			alert("Account Created Successfully");
			window.location.assign("/HTML/employeeLogin.html");
		}
		else
		{
			alert("Email already Exists, Try With Different Email");
			
		}
}
