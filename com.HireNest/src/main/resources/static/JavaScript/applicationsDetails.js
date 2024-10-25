window.onload = function() {

    
	// getting the list of applications and converting to string
	const applications = JSON.parse(localStorage.getItem("listofapplications"));
     
	// selected the span tag by id
	const heading = document.getElementById("jobtitle");
	
	// getting the table body element where the rows will be added
	  const tablebody = document.getElementById("app-list");
	  
	  // clearing the table body
	  tablebody.innerHTML = "";

	if (applications && applications.length > 0) {
		
		
		// looping through each applications and cretaing a new table row for each applications.
		for (const app of applications)
		  {
			
			heading.innerHTML = app.jobTitle
			
			const row = document.createElement('tr');
			
			   row.innerHTML = `<td>${app.name}</td>
			                    <td>${app.email}</td>
			                    <td>${app.phone}</td>
			                    <td>${app.experienceLevel}</td>
			                    <td>
			               
			                    <button type="submit" name="Id" onclick=(viewresume(${app.id}))>View Resume</button>
			               
			                   </td> `
							   
			
							   tablebody.append(row);
		}
	}
	else {
		
		 const title = localStorage.getItem("jobtitle");
		 
		 heading.innerHTML = title;
		
		 const belowbodyheading = document.createElement('h3');
		 belowbodyheading.innerHTML = "No Applications Available";
		 
		 tablebody.append(belowbodyheading);
          // const h1 = document.getElementById("h1");
		  // h1.innerHTML = "No Applications Availabale";
		 // heading.innerHTML = app.jobTitle
	}

}


viewresume = async (id) => {
	try {
		const res = await axios.get(`http://localhost:8080/application/view/resume?id=${id}`,{responseType:'blob'});
		
		//creating a url for the blob res
		const url = window.URL.createObjectURL(new Blob([res.data]));
		
		//window.location.href = `viewResume.html?url=${encodeURIComponent(url)}`;
		
		//window.open = `viewResume.html?url=${encodeURIComponent(url)}`, '_blank');
		
		window.open(`viewResume.html?url=${encodeURIComponent(url)}`, '_blank');
		
		// Open the blob URL in a new window or tab
		 //  window.open("viewResume.html", '_blank'); // '_blank' opens the URL in a new tab
	}
	catch (error) {
		alert(error);
	}
}
