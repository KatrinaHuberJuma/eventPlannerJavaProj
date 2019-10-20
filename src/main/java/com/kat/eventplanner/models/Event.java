package com.kat.eventplanner.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;


@Entity
@Table(name="events")
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
 	@Size(min=2, message="event name must be at least 2 characters")
 	private String name;
 	
 	@ManyToOne(fetch =FetchType.LAZY)
 	@JoinColumn(name="user_id")
 	private User host;
	
	@ManyToOne(fetch = FetchType.LAZY)
 	@JoinColumn(name="location_id")
 	private Location location;
}
