employeejobpost = (event) => {
	event.preventDefault();

	const jobtitle = document.getElementById("title").value;
	const location = document.getElementById("location").value;
	const company = document.getElementById("company").value;
	const description = document.getElementById("description").value;
	const requirements = document.getElementById("requirements").value;
	const salary = document.getElementById("salary").value;
	const EmployementType = document.getElementById("employementType").value;
	const ExperienceLevel = document.getElementById("experienceLevel").value;
	const PostingDate = document.getElementById("postingDate").value;


	if (jobtitle.length < 1 || location.length < 1 || company.length < 1 || description.length < 1 || requirements.length < 1 || salary.length < 1
		|| EmployementType.length < 1 || ExperienceLevel.length < 1 || PostingDate.length < 1) {
		if (jobtitle.length < 1) {
			const titleerr = document.getElementById("title-error");
			titleerr.innerHTML = "Enter job Title";
		}
		else {
			const titleerr = document.getElementById("title-error");
			titleerr.innerHTML = "";
		}
    
		if (location.length > 1) {
			const locationerror = document.getElementById("location-error");
			locationerror.innerHTML = "Enter Location";
		}
		else {
			const locationerror = document.getElementById("location-error");
			locationerror.innerHTML = "";
		}



		if (company.length < 1) {
			const companyerror = document.getElementById("company-error");
			companyerror.innerHTML = "Enter company";
		}
		else {
			const companyerror = document.getElementById("company-error");
			companyerror.innerHTML = "";
		}



		if (description.length < 1) {
			const descriptionerror = document.getElementById("desc-error");
			descriptionerror.innerHTML = "Enter Description";
		}
		else {
			const descriptionerror = document.getElementById("desc-error");
			descriptionerror.innerHTML = "";
		}



		if (requirements.length < 1) {
			const requirementserror = document.getElementById("requirements-error");
			requirementserror.innerHTML = "Enter Requirements";
		}
		else {
			const requirementserror = document.getElementById("requirements-error");
			requirementserror.innerHTML = "";
		}


		if (salary < 1) {
			const salaryerror = document.getElementById("salary-error");
			salaryerror.innerHTML = "Enter salary";
		}
		else {
			const salaryerror = document.getElementById("salary-error");
			salaryerror.innerHTML = "";
		}


		if (EmployementType < 1) {
			const EmployementTypeerror = document.getElementById("emp-type-error");
			EmployementTypeerror.innerHTML = "Enter Employement Type";
		}
		else {
			const EmployementTypeerror = document.getElementById("emp-type-error");
			EmployementTypeerror.innerHTML = "";
		}


		if (ExperienceLevel.length < 1) {
			const ExperienceLevelerror = document.getElementById("exp-error");
			ExperienceLevelerror.innerHTML = "Enter Experience Level";
		}
		else {
			const ExperienceLevelerror = document.getElementById("exp-error");
			ExperienceLevelerror.innerHTML = "";
		}


		if (PostingDate < 1) {
			const PostingDateerror = document.getElementById("post-error");
			PostingDateerror.innerHTML = "Enter Posting Date";
		}
		else {
			const PostingDateerror = document.getElementById("post-error");
			PostingDateerror.innerHTML = "Enter Posting Date";
		}
	}

	// api function called in else block
	else {
	 	
		apifunction({
		 			title: jobtitle,
		 			location: location,
					company: company,
		 			experienceLevel: ExperienceLevel,
		 			description: description,
		 			requirements: requirements,
		 			salary: salary,
		 			employementType: EmployementType,
		 			postingDate: PostingDate
		 		});
	}

}

// api called for jobpost and page redirected from this function
async function apifunction(data) {

	const response = await axios.post("http://localhost:8080/employee/jobs/post", data);
	if(typeof response.data === "object")
		{
			localStorage.setItem('jobsalongwithcount',JSON.stringify(response.data));
				            
			window.location.assign("/HTML/myJobs.html");
		}
		else
		{
			alert("job not posted successfully, Please Login");
			
			window.location.assign("/HTML/employeeLogin.html");
		}
}

// myjobs option function to get the job details for employer when clicked on my jobs in navbar
employerjobslist =async()=>
	{
		
		const response = await axios.post("http://localhost:8080/myjobs");
        
		    if(response.data != null && typeof response.data === "object")
			{
				//alert("to be executed if jobs are available")
				localStorage.setItem('jobsalongwithcount',JSON.stringify(response.data));
	            
				window.location.assign("/HTML/myJobs.html");
			}
			else if(response.data === "jobsempty")
			{
				//alert("to be executed when jobs not avail");
				localStorage.setItem('jobsalongwithcount', JSON.stringify([]));
				
				window.location.assign("/HTML/myJobs.html");
			}
			else
			{
				window.location.assign("/HTML/employeeLogin.html");
			}
	
}

logout = async() =>{
	
	try{
		 const res = await axios.get("http://localhost:8080/logout");
		 
		 if(res.data === "loggedout")
			{
				window.location.assign("/HTML/employeeLogin.html");
			}
	}
	catch(error)
	{
		alert(error);
	}
}