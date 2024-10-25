window.onload = function(){
	
	
	const jobs = JSON.parse(localStorage.getItem("Jobs"));

	
	const form = document.getElementById("form");
	
	form.innerHTML = 
	`	<input type="hidden" name="id" id="id" value="${jobs.id}"/>

	           <label for="title">Job Title:</label>
	           <input type="text" name="title" id="title" value="${jobs.title}" required="required"	/>
	           
	           <label for="location">Location:</label>
	           <input type="text" name="location" id="location" value="${jobs.location}" required="required"/>
	           
	           <label for="company">Company:</label>
	           <input type="text" name="company" id="company" value="${jobs.company}" required="required"/>
	           
	           <label for="description">Description:</label>
	           <textarea name="description" id="description" required="required">${jobs.description}</textarea>
	           
	           <label for="requirements">Requirements:</label>
	           <textarea name="requirements" rows="5" id="requirements" required="required" >${jobs.requirements}</textarea><br>
	           
	           <label for="salary">Salary:</label>
	           <input type="text" name="salary" id="salary" value="${jobs.salary}" required="required" /><br>
	           
	           <label for="employmentType">Employment Type:</label>
	           <input type="text" name="employementType" id="employementtype" value="${jobs.employementType}" required="required" /><br>
	           
	          
			   <label for="experienceLevel">Experience Level:</label>
			   <select id="experienceLevel" name="experienceLevel" required="required">
			       <option value="Entry" ${jobs.experienceLevel === 'Entry' ? 'selected' : ''}>Entry Level</option>
			       <option value="Mid" ${jobs.experienceLevel === 'Mid' ? 'selected' : ''}>Mid Level</option>
			       <option value="Senior" ${jobs.experienceLevel === 'Senior' ? 'selected' : ''}>Senior Level</option>
			   </select>
	           
	           <input type="hidden" name="postingDate"  id="postingdate" value="${jobs.postingDate}"/><br>

	           <button type="submit">Update Job</button>
			  
	`
}

async function editjobbb(event) {
	event.preventDefault();

	

	const id = document.getElementById("id").value;
	const title = document.getElementById("title").value;
	const location = document.getElementById("location").value;
	const company = document.getElementById("company").value;
	const description = document.getElementById("description").value;
	const requirements = document.getElementById("requirements").value;
	const salary = document.getElementById("salary").value;
	const employementtype = document.getElementById("employementtype").value;
	const experienceLevel = document.getElementById("experienceLevel").value;
	const postingdate = document.getElementById("postingdate").value;
	
	data = {
		id : id,
		title : title, 
		location : location,
		experienceLevel : experienceLevel,
		company : company,
		description : description,
		requirements : requirements,
		salary : salary,
		employementType : employementtype,
		postingDate : postingdate
	}
	
	try{
		const res = await axios.post("http://localhost:8080/employee/jobs/update",data);
		
		if(res.data === "myJobs")
			{
				window.location.assign("/HTML/myJobs.html");
			}
			else
			{
				alert("job not updated");
			}
		
	}catch(error)
	{
		alert(error);
	}
}