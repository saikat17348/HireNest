const regex = /^[A-Za-z0-9]+@[a-z]+\.[a-z]{3}$/;
//const pass = /[A-Z]+[a-z]+[0-9]/;
const mobile = /^[0-9]+$/;
const username = /^[A-Z a-z]+$/;

const passdigit = /[0-9]/;
const passlower = /[a-z]/;
const passupper = /[A-Z]/;

// api calling to the backend and navigating from here.
const apicall = async (fullname, EmailId, password, MobileNumber, workStatus, currentCity, terms) => {

	const data = {
		fullName: fullname,
		email: EmailId,
		password: password,
		mobile: MobileNumber,
		workStatus: workStatus,
		currentCity: currentCity,
		terms: terms
	}


	try {
		const response = await axios.post("http://localhost:8080/register", data)
   
		if(response.data === "hirenestUserLogin")
			{
				alert("Account has been Created, Please Login");
				window.location.assign("/HTML/userLogin.html");
			}
			else if(response.data === "home")
				{
					alert("Account already exists, Please Login")
					//window.location.assign("/index.html");
				}
	}
	catch (error) {
		alert(error);
	}

}

//validation function
function RegisterationValidation(event) {

	event.preventDefault();

	const fullname = document.getElementById("fullName").value;
	const EmailId = document.getElementById("email").value;
	const password = document.getElementById("password").value;
	const MobileNumber = document.getElementById("mobile").value;
	const workStatus = document.getElementById("workStatus").value;
	const currentCity = document.getElementById("city").value;
	const terms = document.querySelector("input[name='terms']:checked") ? true : false;

	const isValid = true;




	// name Validation
	if (fullname != "") {
		if (fullname.length > 2) {
			if (username.test(fullname) == false) {
				const error = document.getElementById("fullname-error");
				error.innerHTML = "Name should only contain Aplhabets";
				isValid = false;
			}
			else {
				const error = document.getElementById("fullname-error");
				error.innerHTML = "";
			}
		}
		else {

			const error = document.getElementById("fullname-error");
			error.innerHTML = "FullName should contain atleast 3 Aplhabets ";
			isValid = false;
		}
	}
	else {
		const error = document.getElementById("fullname-error");
		error.innerHTML = "Enter full name";
		isValid = false;

	}

	// email validation 
	if (EmailId != "") {
		if (regex.test(EmailId) == true) {
			const error = document.getElementById("email-error");
			error.innerHTML = "";
		}
		else {
			const error = document.getElementById("email-error");
			error.innerHTML = "Invalid Email ID";
			isValid = false;
		}
	}
	else {
		isValid = false;
		const error = document.getElementById("email-error");
		error.innerHTML = "Enter Email ID";
		isValid = false;
	}

	// password validation
	if (password != "") {
		if (password.length >= 4) {
			if (passdigit.test(password) == false || passlower.test(password) == false || passupper.test(password) == false) {
				const error = document.getElementById("password-error");
				error.innerHTML = "Password should Atleast contain 1 Capital,Small char and 1 Number";
				isValid = false;
			}
			else {
				const error = document.getElementById("password-error");
				error.innerHTML = ""
			}
		}
		else {
			const error = document.getElementById("password-error");
			error.innerHTML = "Password Length should be more then 3";
			isValid = false;
		}
	}
	else {
		const error = document.getElementById("password-error");
		error.innerHTML = "Enter Password";
		isValid = false;
	}

	// mobile number Validation
	if (MobileNumber != "") {
		if (MobileNumber.length == 10) {
			if (mobile.test(MobileNumber) == false) {
				const error = document.getElementById("mobile-error");
				error.innerHTML = "Mobile Number Should contain only digits";
				isValid = false;
			}
			else {
				const error = document.getElementById("mobile-error");
				error.innerHTML = "";
			}
		}
		else {
			const error = document.getElementById("mobile-error");
			error.innerHTML = "Mobile Should contain atleast 10 digit";
			isValid = false;
		}
	}
	else {
		const error = document.getElementById("mobile-error");
		error.innerHTML = "Enter Mobile Number";
		isValid = false;
	}
	// validating work status select or not
	if (workStatus == "") {
		const error = document.getElementById("work-status-error");
		error.innerHTML = "Select Work Status";
		isValid = false;
	}
	else {
		const error = document.getElementById("work-status-error");
		error.innerHTML = "";
	}
	// validating current city
	if (currentCity != "") {
		if (username.test(currentCity) == false) {
			const error = document.getElementById("current-city-error");
			error.innerHTML = "City Name should contain only Alphabets";
			isValid = false;
		}
		else {
			const error = document.getElementById("current-city-error");
			error.innerHTML = "";

		}
	}
	else {
		const error = document.getElementById("current-city-error");
		error.innerHTML = "Enter Current City";
		isValid = false;
	}

	// terms and condi validation
	if (!terms) {
		const error = document.getElementById("terms-error");
		error.innerHTML = "Check the Terms&Condition";
		isValid = false;
	}
	else {
		const error = document.getElementById("terms-error");
		error.innerHTML = "";
	}

	// api call starts here and navigating
	if (isValid == true) {
		apicall(fullname, EmailId, password, MobileNumber, workStatus, currentCity, terms);

	}
}



