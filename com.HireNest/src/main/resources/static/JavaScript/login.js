
const regex = /^[A-Za-z0-9]+@[a-z]+\.[a-z]{3}$/;

async function login(event) {

	event.preventDefault();
	const email = document.getElementById("email").value;
	const password = document.getElementById("password").value;
	const errormsg = document.getElementById("error-msg");
	const passerror = document.getElementById("pass-error");

	if (email != "") {
		if (regex.test(email) == false) {
			errormsg.innerHTML = "Invalid Email";
		}
		else {
			errormsg.innerHTML = "";
			if (password.length > 1) {
				  
				  passerror.innerHTML = "";
				
				try {
					const response = await axios.post("http://localhost:8080/login", { email: email, password: password });

					if (typeof response.data === 'string') {

						if (response.data === 'profileCreation') {
							alert("Your profile has not created, Create you profile to proceed further.");
							window.location.assign("/HTML/userProfileCreation.html")
						}
						else if (response.data === "hirenestUserSignUp") {
							alert("Invalid Email or Password");
							window.location.assign("/HTML/userLogin.html");
						}
						else if(response.data === "emptylist")
							{
								
								localStorage.setItem('jobs',JSON.stringify([]));
								
								window.location.assign("/HTML/userJobsSearchAndDisplay.html");
							}
					}
					else if (typeof response.data === "object") {

						localStorage.setItem('jobs', JSON.stringify(response.data))

						window.location.assign("/HTML/userJobsSearchAndDisplay.html");

						//displayalljobs(response.data);

					}


				}
				catch (error) {
					alert(error);
				}

			}
			else {
				
				passerror.innerHTML = "Enter Password";
			}
		}
	}
	else {
		errormsg.innerHTML = "Email cannot be null";
	}
}
