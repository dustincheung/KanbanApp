package kanbanapp.model;

import java.util.Date;

import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import kanbanapp.util.TaskStatus;

@Entity
public class Task {
	
	//primary key, id will use database server side strategy to dictate value (auto-increment)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	//we will use this as a formatted type "id": PROJ-1 where PROJ is projTag and 1 is taskSeqIncrementor from backlog
	@Column(updatable = false)
	private String taskTag;

	@Column(updatable = false)
	private String projTag;
	
	@NotBlank(message = "Please enter a task title")
	private String taskTitle;
	
	@NotBlank(message = "Please enter a task description")
	private String taskDescription;
	
	private String acceptCriteria;
	
	private Integer priority;
	
	private TaskStatus status;
	
	private Date dueDate;
	
	//ManyToOne relationship with Backlog
	//Refresh: indicates to owner entity (backlog) when a task is deleted it will refresh parent entity
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name="backlog_id", nullable = false, updatable = false)
	@JsonIgnore
	private Backlog backlog;
	
	@Column(updatable = false) 
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createdDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updatedDate;
	
	// no arg constructor
	public Task() {
		
	}
			
	@PrePersist
	// stores the date of creation when a Project object is created
	protected void onCreate() {
		this.createdDate = new Date();
	}
			
	@PreUpdate
	// stores the date of update when a Project object is updated
	protected void onUpdate(){
		this.updatedDate = new Date();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTaskTag() {
		return taskTag;
	}

	public void setTaskTag(String taskTag) {
		this.taskTag = taskTag;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public String getAcceptCriteria() {
		return acceptCriteria;
	}

	public void setAcceptCriteria(String acceptCriteria) {
		this.acceptCriteria = acceptCriteria;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getProjTag() {
		return projTag;
	}

	public void setProjTag(String projTag) {
		this.projTag = projTag;
	}

	public Backlog getBacklog() {
		return backlog;
	}

	public void setBacklog(Backlog backlog) {
		this.backlog = backlog;
	}
}
