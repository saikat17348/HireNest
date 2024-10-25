const regex = /^[A-Za-z0-9]+@[a-z]+\.[a-z]{3}$/;
const mobile = /^[0-9]+$/;

profilecomplete = (event) => {
	event.preventDefault();


	let allfieldsstatus = false;

	const formData = new FormData();

		const fullName = document.getElementById("fullName").value;
		if (fullName.length < 1) {
			allfieldsstatus = false;
			const error = document.getElementById("error");
			error.innerHTML = "Enter full name";
		}
		else {

			
			formData.append('fullName', fullName);
			const error = document.getElementById("error");
			error.innerHTML = "";
			allfieldsstatus = true;

		}

		// email validation
		const email = document.getElementById("email").value;
		if (email.length > 1) {
             
			if (regex.test(email) == true) {

			
				formData.append('email', email);
				const emailerror = document.getElementById("email-error");
				emailerror.innerHTML = "";
				allfieldsstatus = true;


			}
			else {
				allfieldsstatus = false;
				const emailerror = document.getElementById("email-error");
				emailerror.innerHTML = "invalid email";
			}


		}
		else {
			allfieldsstatus = false;
			const emailerror = document.getElementById("email-error");
			emailerror.innerHTML = "Enter email";
		}

		// phone number validation
		const phone = document.getElementById("phone").value;
		if (phone.length >= 1) {

			if (phone.length != 10) {
				allfieldsstatus = false;
				const phoneerror = document.getElementById("phone-error");
				phoneerror.innerHTML = "Enter 10 digits Phone Number";

			}
			else {

				// validating the phone either it contains only digits or not

				if (mobile.test(phone) == true) {

					
					formData.append('phone', phone);
					const phoneerror = document.getElementById("phone-error");
					phoneerror.innerHTML = "";
					allfieldsstatus = true;
				}
				else {
					allfieldsstatus = false;
					const phoneerror = document.getElementById("phone-error");
					phoneerror.innerHTML = "Phone Number should only contain 10 digits";
				}
			}
		}
		else {
			allfieldsstatus = false;
			const phoneerror = document.getElementById("phone-error");
			phoneerror.innerHTML = "Enter Phone Number";
		}


		// after validating the email number and name sending the request to api in the backend
		const location = document.getElementById("location").value;

		formData.append('location', location);

		// setting experience if null setting to 0

		const experience = document.getElementById("experience").value;


		if (experience.length >= 1) {
			formData.append('experience', experience);
		}
		else {
			formData.append('experience', 0);
		}


		// skills
		const skills = document.getElementById("skills").value;
		formData.append('skills', skills);


		// bio
		const bio = document.getElementById("bio").value;
		formData.append('bio', bio);

		// setting resume to null if not selected 
		const resume = document.getElementById("resume").files[0];

		if (resume && resume instanceof File) {

			formData.append('resume', resume);

		}
		else {

			formData.append('resume', null);
		}

		// setting photo to null if not selected

		const photo = document.getElementById("photo").files[0];

		if (photo && photo instanceof File) {

			formData.append('photo', photo);
		}
		else {

			formData.append('photo', null);
		}
	

		if (allfieldsstatus == true) {
			//profilecreationapi(formData);
			profilecreationapi(formData);

		}
		else {
			alert("invalid details");
	}
}


async function profilecreationapi(formData) {


	try {
		const response = await axios.post(`http://localhost:8080/createprofile`, formData, {
			headers: {
				'Content-Type': 'multipart/form-data'
			}
		});
		
		// handled the navigation accordingly
		if (response.data === 'emailexists') {
			alert(" Email already exists, Log in");
			window.location.assign("/HTML/userLogin.html")
		}
		else if (response.data === "emptylist") {
			localStorage.setItem('jobs', JSON.stringify([]))

			window.location.assign("/HTML/userJobsSearchAndDisplay.html");
		}
		else if (response.data === "newlistofjob" || typeof response.data === "object") {

			localStorage.setItem('jobs', JSON.stringify(response.data))

			window.location.assign("/HTML/userJobsSearchAndDisplay.html");
		}
		else if (response.data === "notcreated") {

			alert("Use the email through which you have looged in");
		}

	}
	catch (error) {
		alert(error + "error block");
	}

}