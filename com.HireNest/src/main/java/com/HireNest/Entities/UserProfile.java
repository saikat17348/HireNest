package com.HireNest.Entities;

import java.util.Arrays;
import java.util.Base64;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class UserProfile 
{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    private String fullName;
         
	    @Column(unique = true)
	    private String email;

	    private String phone;
	    private String location;
	    private int experience;
	    private String skills;
	    private String bio;

	    @Lob
	    @Basic(fetch = FetchType.EAGER)
	    @Column(columnDefinition = "LONGBLOB")
	    private byte[] photo; // Store the photo as a BLOB in the database

	    @Lob
	    @Basic(fetch = FetchType.EAGER)
	    @Column(columnDefinition = "LONGBLOB")
	    private byte[] resume; // Store the resume as a BLOB in the database

		public UserProfile() {
			super();
			// TODO Auto-generated constructor stub
		}

		public UserProfile(Long id, String fullName, String email, String phone, String location, int experience,
				String skills, String bio, byte[] photo, byte[] resume) {
			super();
			this.id = id;
			this.fullName = fullName;
			this.email = email;
			this.phone = phone;
			this.location = location;
			this.experience = experience;
			this.skills = skills;
			this.bio = bio;
			this.photo = photo;
			this.resume = resume;
		}
		
		public String getPhotoBase64()
		{
			if(photo == null)
			{
				return null;
			}
			return Base64.getEncoder().encodeToString(photo);
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getFullName() {
			return fullName;
		}

		public void setFullName(String fullName) {
			this.fullName = fullName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public int getExperience() {
			return experience;
		}

		public void setExperience(int experience) {
			this.experience = experience;
		}

		public String getSkills() {
			return skills;
		}

		public void setSkills(String skills) {
			this.skills = skills;
		}

		public String getBio() {
			return bio;
		}

		public void setBio(String bio) {
			this.bio = bio;
		}

		public byte[] getPhoto() {
			return photo;
		}

		public void setPhoto(byte[] photo) {
			this.photo = photo;
		}

		public byte[] getResume() {
			return resume;
		}

		public void setResume(byte[] resume) {
			this.resume = resume;
		}

		@Override
		public String toString() {
			return "UserProfile [id=" + id + ", fullName=" + fullName + ", email=" + email + ", phone=" + phone
					+ ", location=" + location + ", experience=" + experience + ", skills=" + skills + ", bio=" + bio
					+ ", photo=" + Arrays.toString(photo) + ", resume=" + Arrays.toString(resume) + "]";
		}

	    

}
