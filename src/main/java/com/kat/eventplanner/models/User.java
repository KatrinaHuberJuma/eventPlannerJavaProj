package com.kat.eventplanner.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
	 	@Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private Long id;
	 	@Column(unique=true)
	 	@Email(message="Email must be valid")
	    private String email;
	 	@Size(min=2, message="first name must be at least 2 characters")
	    private String firstName;
	 	@Size(min=2, message="last name must be at least 2 characters")
	    private String lastName;
	 	@ManyToOne(fetch = FetchType.LAZY)
	 	@JoinColumn(name="location_id")
	 	private Location location;
	 	
	 	@Size(min=5, message="password must be at least 5 characters")
	    private String password;
	    @Transient
	    private String passwordConfirmation;
	    @Column(updatable=false)
	    private Date createdAt;
	    private Date updatedAt;
	    
	    public User() {
	    }

	    





		public Location getLocation() {
			return location;
		}







		public void setLocation(Location location) {
			this.location = location;
		}







		public User(Long id, @Email(message = "Email must be valid") String email,
				@Size(min = 2, message = "first name must be at least 2 characters") String firstName,
				@Size(min = 2, message = "last name must be at least 2 characters") String lastName, Location location,
				@Size(min = 5, message = "password must be at least 5 characters") String password,
				String passwordConfirmation, Date createdAt, Date updatedAt) {
			this.id = id;
			this.email = email;
			this.firstName = firstName;
			this.lastName = lastName;
			this.location = location;
			this.password = password;
			this.passwordConfirmation = passwordConfirmation;
			this.createdAt = createdAt;
			this.updatedAt = updatedAt;
		}







		public String getFirstName() {
			return firstName;
		}







		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}







		public String getLastName() {
			return lastName;
		}







		public void setLastName(String lastName) {
			this.lastName = lastName;
		}



		public Long getId() {
			return id;
		}



		public void setId(Long id) {
			this.id = id;
		}



		public String getEmail() {
			return email;
		}



		public void setEmail(String email) {
			this.email = email;
		}



		public String getPassword() {
			return password;
		}



		public void setPassword(String password) {
			this.password = password;
		}



		public String getPasswordConfirmation() {
			return passwordConfirmation;
		}



		public void setPasswordConfirmation(String passwordConfirmation) {
			this.passwordConfirmation = passwordConfirmation;
		}



		public Date getCreatedAt() {
			return createdAt;
		}



		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}



		public Date getUpdatedAt() {
			return updatedAt;
		}



		public void setUpdatedAt(Date updatedAt) {
			this.updatedAt = updatedAt;
		}



		// other getters and setters removed for brevity
	    @PrePersist
	    protected void onCreate(){
	        this.createdAt = new Date();
	    }
	    @PreUpdate
	    protected void onUpdate(){
	        this.updatedAt = new Date();
	    }
}
