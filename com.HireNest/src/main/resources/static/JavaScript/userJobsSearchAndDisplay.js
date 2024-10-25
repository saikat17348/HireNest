window.onload = function()
{
	const jobsData = JSON.parse(localStorage.getItem('jobs'));

	const jobListElement = document.getElementById("job-list");
	
	const jobs = jobsData.jobs || jobsData; 
	// check if jobs are available
		
	if(jobs && jobs.length > 0)//Array.isArray(jobs))
		{
			jobListElement.innerHTML = "";
		
		jobs.forEach(job =>{
			const jobItem = document.createElement("li");
			jobItem.classList.add("job-list");
			
			jobItem.innerHTML = `
			<div class="job-details">
			        <h3>${job.title}</h3>
			        <p><strong>Company:</strong> <span>${job.company}</span></p>
			        <p><strong>Location:</strong> <span>${job.location}</span></p>
			        <p><strong>Experience Level:</strong> <span>${job.experienceLevel}</span></p>
			        <p><strong>Description:</strong> <span>${job.description}</span></p>
					<button onClick="fun(${job.id})" class="view-details-button">View Details</button>
					<button onClick="applyjob(${job.id})" class="apply-button">Apply </button>
			</div>
			<hr></hr>`;
			
			jobListElement.appendChild(jobItem);
		});
		}
		else{
			jobListElement.innerHTML = "<li>No Jobs available </li>"
		}
}


// function to call the api and redirect the webpage when the application is submitted
async function applyjob(id)
{
	try{
		localStorage.setItem("jobId",id);
		const response = await axios.get(`http://localhost:8080/applyforjob?id=${id}`)
		if(response.data == "applyJobs")
			{
				
				window.location.assign("/HTML/applyForJobs.html");
				
			}
			else
			{
				window.location.assign("/HTML/userJobsSearchAndDisplay.html");
			}
	}
	catch(error){
		alert(error);
	}
	
}

// job search api block

const jobsearch = async(event)=>{
	
	event.preventDefault();
	
	const jobtitle = document.getElementById("title").value;
	const location = document.getElementById("location").value;
	const explevel = document.getElementById("experienceLevel").value;
	const company = document.getElementById("company").value;
	
	const formData = new FormData();
	
	formData.append("title",jobtitle);
	formData.append("location", location);
	formData.append("experienceLevel", explevel);
	formData.append("company", company);
	
	try{
		const response = await axios.post("http://localhost:8080/job/search",formData);

		
		 if(response.data.length >= 1)
			{
				localStorage.setItem('searchedjobs',JSON.stringify(response.data));
				
				window.location.assign("/HTML/displaySearchedJobs.html");
			}
			else
			{
				window.location.assign("/HTML/displaySearchedJobs.html");
			}
	}
	catch(error)
	{
		alert(error);
	}
		
}

logout = async(event)=>{
	
	event.preventDefault();
	const response = await axios.get("http://localhost:8080/user/logout");
	if(response.data == "loggedout");
	{
		window.location.assign("/HTML/userLogin.html")
	}
}