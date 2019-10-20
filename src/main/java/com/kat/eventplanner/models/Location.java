package com.kat.eventplanner.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;


@Entity
@Table(name="locations")
public class Location {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Size(min=5, message="location must be at least 5 characters")
    private String location;
 	@Size(min=2, max=2, message="state abriviations are 2 characters")
    private String state;
 	@Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    @OneToMany(mappedBy="location", fetch = FetchType.LAZY)
    private List<User> users;
    @OneToMany(mappedBy="location", fetch = FetchType.LAZY)
    private List<Event> events;
    
    
    
 
    public Location() {
	}
    
    
    
	public Location(Long id, @Size(min = 5, message = "location must be at least 5 characters") String location,
			@Size(min = 2, max = 2, message = "state abriviations are 2 characters") String state, Date createdAt,
			Date updatedAt, List<User> users, List<Event> events) {
		this.id = id;
		this.location = location;
		this.state = state;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.users = users;
		this.events = events;
	}



	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

}
