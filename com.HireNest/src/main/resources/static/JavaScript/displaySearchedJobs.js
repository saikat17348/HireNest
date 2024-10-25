window.onload = function() {
	const searchedjobsresult = JSON.parse(localStorage.getItem('searchedjobs'));
	
	const joblistdiv = document.getElementById("job-list");

	if (!searchedjobsresult) {

		joblistdiv.innerHTML = "";
		const msg = document.getElementById("head");
		msg.innerHTML = "No Available Jobs";
	}
	else {

		const jobs = searchedjobsresult.data || searchedjobsresult;

		if (jobs && Array.isArray(jobs)) {

			joblistdiv.innerHTML = "";

			jobs.forEach(job => {
				const jobItem = document.createElement("li");
				jobItem.classList.add("job-list");

				jobItem.innerHTML = `
				
				<div class="job-details">
							        <h3>${job.title}</h3>
							        <p><strong>Company:</strong> <span>${job.company}</span></p>
							        <p><strong>Location:</strong> <span>${job.location}</span></p>
							        <p><strong>Experience Level:</strong> <span>${job.experienceLevel}</span></p>
							        <p><strong>Description:</strong> <span>${job.description}</span></p>
									
									<button onClick="applyjob(${job.id})" class="apply-button">Apply </button>
							</div>
							<hr></hr>`

				joblistdiv.append(jobItem);
			});
		}

	}
	localStorage.removeItem('searchedjobs');
}

// apply job function
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