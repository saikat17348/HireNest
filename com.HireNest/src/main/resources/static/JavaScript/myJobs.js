
window.onload = async function()
{
	//alert("after deleteing loading");
	//const jobs = JSON.parse(localStorage.getItem("jobsalongwithcount"));
	
	const response = await axios.post("http://localhost:8080/myjobs");
//	alert(response.data);
	
	if(response.data != null && typeof response.data === "object")
   {
	const jobs = response.data;
	const unordererlist = document.getElementById("li-item");
	
	unordererlist.innerHTML= "";
	
	if(jobs && Array.isArray(jobs) && jobs.length > 0)
		{
			unordererlist.innerHTML = "";
			
	for(const jobss of jobs)
		{
			const jobitem = document.createElement("li");
			jobitem.classList.add("job-details");
			
			jobitem.innerHTML = `
			               <div class="job-details">
			                   <h3>${jobss.title}</h3>
			                   <p><strong>Company:</strong> <span>${jobss.company}</span></p>
			                   <p><strong>Location:</strong> <span>${jobss.location}</span></p>
			                   <p><strong>Experience Level:</strong> <span>${jobss.experienceLevel}</span></p>
			                   <p><strong>Description:</strong> <span>${jobss.description}</span></p>
			                   <div class="job-actions">
			                       
			                           <button type="submit" class="edit-button" onclick="editjob(${jobss.id})" >Edit</button>
			                       
			                           <button type="submit" name="id" class="delete-button" onclick="deletejob(${jobss.id})">Delete</button>
			                      
			                           <p id="noofapp"><strong>No. Of Applicants:</strong> <span>${jobss.applicationcount}</span></p>
			                           <button id="viewapp" type="submit" onclick=viewapplication("${jobss.id}")>View Applications</button>
		
			                   </div>
			               </div>
			           `;
				
					   unordererlist.append(jobitem);	   
					   
		}
		}
		}
		else{
			
			const h1 = document.getElementById("h1");
			h1.innerHTML = "No Jobs Found";
			
			//unordererlist.append(h1);
			
		}	
}

// function is called when edit job button is clicked then api is called and pages are redirected from these function
async function editjob(id)
{
	
	try{
		const res = await axios.post(`http://localhost:8080/employee/jobs/editjobs?id=${id}`);

		if(typeof res.data === "object")
			{
				
				localStorage.setItem("Jobs",JSON.stringify(res.data));
				
				window.location.assign("/HTML/employeeEditJobs.html");
			}
			else
			{
				alert("else block in myjobs js ");
			}
	}catch(error)
	{
		alert(error);
	}
}

// function is called when delete job button is clicked then api is called and pages are redirected from these function

async function deletejob(id) {
	
	try {
		const res = await axios.get(`http://localhost:8080/employee/jobs/deletejob?id=${id}`);

		//alert(res.data);
		if (typeof res.data === "object") 
		{
			
			//alert("if block it should be executed when jobs are available");
			//localStorage.setItem('jobsalongwithcount',JSON.stringify(response.data));
				            
			window.location.assign("/HTML/myJobs.html");
		}
		else if(res.data === "emptyjoblist")
			{
				//alert("to be executed when job list is empty");
				
				//localStorage.setItem('jobsalongwithcount', JSON.stringify([]));
											
				window.location.assign("/HTML/myJobs.html");
			}
		else if(res.data === "employeesignup"){
			
			//alert("Login In again to continue");
			
			window.location.assign("/HTML/employeeLogin.html");
			
		}
		else if(res.data === "jobpost")
		{
			//alert("to be executed whem jobs are not avail by job id in the database return to job post")
										
		    window.location.assign("/HTML/employeeJobPost.html");
		}
		
	}
	catch (error) {
		alert(error);
	}
}

// function for getting the application with the jobid (this function will get the application details for the specific job by its id )

async function viewapplication(id){
	
	try{
		const res = await axios.post(`http://localhost:8080/employee/applications?id=${id}`)
	
		
		if(res.data === "appDTOData" || typeof res.data === "object")
			{
				//alert("if block will execute when applications are avail");
				localStorage.setItem("listofapplications",JSON.stringify(res.data));
				
				window.location.assign("/HTML/applicationsDetails.html");
			}
			else if(typeof res.data === "string")
				{
					localStorage.setItem("listofapplications",JSON.stringify([]));
					
					localStorage.setItem("jobtitle",(res.data));
									
					window.location.assign("/HTML/applicationsDetails.html");
				}
			else
			{
				localStorage.setItem("listofapplications",JSON.stringify([]));
				
				window.location.assign("/HTML/applicationsDetails.html");
			}
	}
	catch(error)
	{
		alert(error);
	}
}
	