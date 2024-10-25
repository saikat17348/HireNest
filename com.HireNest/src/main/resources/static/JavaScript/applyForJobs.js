
window.onload = async function() {

	//getting the jobId which is saved while clicked on apply button
	const id = localStorage.getItem("jobId");

	try {
		const response = await axios.post(`http://localhost:8080/fetchjobtitle?id=${id}`)
	
		if (typeof response.data === "string") {
			const form = document.querySelector("form");
			//setting the html content before the form.
			form.insertAdjacentHTML("beforebegin", `<h1>Apply for Job: <span>${response.data}</span></h1>`)
			//div.innerHTML = `<h1>Apply for Job: <span>${response.data}: Job Title</span></h1>`;
		}
	}
	catch (error) {
		alert(error + "in api request in while opening the applyForJobs");
	}
}



// api call for form submission 
const applicationforjob = async (event) => {
	event.preventDefault();

	const formData = new FormData();

	const id = localStorage.getItem("jobId");
	formData.append('id', id);
	const name = document.getElementById("name").value;
	formData.append('name', name);
	const email = document.getElementById("email").value;
	formData.append('email', email);
	const resume = document.getElementById("resume").files[0];

	if (typeof resume === "object") {

		formData.append('resume', resume);
	}
	

	try {
		const response = await axios.post(`http://localhost:8080/jobs/apply`, formData,{
			Headers: {
				'Content-Type': 'multipart/form-data'
			}
		});

		if(response.data == "invalid email")
			{
				const error = document.getElementById("error");
				error.innerHTML = "Invalid Email";	
			}
			else if(response.data == "displaySearchJobs")
				{
					alert("job sucessfully applied");
					window.location.assign("/HTML/userJobsSearchAndDisplay.html");
				}
	}
	catch (error) {
		alert(error + " : while submiting the form in the api error");
	}

}

// back button function 
backfun = ()=>
	{
		window.location.assign("/HTML/userJobsSearchAndDisplay.html");
	}