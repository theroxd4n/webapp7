package com.group7.voluntaweb.Models;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "volunteerings")
public class Volunteering {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@OneToMany(mappedBy = "volunteering")
	private Set<UsersVolunteerings> joined_users;

	@NotEmpty
	private String name;

	@NotEmpty
	private long category_id;
	@NotEmpty

	private Date startdate;
	@NotEmpty

	private Date enddate;
	@NotEmpty
	@Lob
	private String description;
	@NotEmpty
	private String image;
	@NotEmpty
	private String city;
	@NotEmpty
	private String email;

	public Volunteering() {
	}

	public Volunteering(Set<UsersVolunteerings> joined_users, @NotEmpty String name, @NotEmpty long category_id,
			@NotEmpty Date startdate, @NotEmpty Date enddate, @NotEmpty String description, @NotEmpty String image,
			@NotEmpty String city, @NotEmpty String email) {
		super();
		this.joined_users = joined_users;
		this.name = name;
		this.category_id = category_id;
		this.startdate = startdate;
		this.enddate = enddate;
		this.description = description;
		this.image = image;
		this.city = city;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCategory() {
		return category_id;
	}

	public void setCategory(long category) {
		this.category_id = category;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Set<UsersVolunteerings> getJoined_users() {
		return joined_users;
	}

	public void setJoined_users(Set<UsersVolunteerings> joined_users) {
		this.joined_users = joined_users;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}