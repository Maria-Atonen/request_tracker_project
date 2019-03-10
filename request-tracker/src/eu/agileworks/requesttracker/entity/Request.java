package eu.agileworks.requesttracker.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="request")
public class Request {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id; 
	
	@Column(name="description")
	@NotNull(message="is required")
	@Size(min=1, message="is required")
	@Size(max=200, message="message should be less than 200 characters")
	private String description; 
	
	@Column(name="created")
	private Date created;
	
	@Column(name="deadline")
	private Date deadline;
	
	@Transient
	private boolean overdue;
	
	public Request () {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public boolean isOverdue() {
		return overdue;
	}

	public void setOverdue(boolean overdue) {
		this.overdue = overdue;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", description=" + description + ", created=" + created + ", deadline=" + deadline
				+ ", overdue=" + overdue + "]";
	}
	
	@PrePersist
	public void prePersist() {
		
		created =  new Date(Calendar.getInstance().getTimeInMillis());
	}	
}
