package kanbanapp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Backlog {
	
	//primary key, id will use database server side strategy to dictate value (auto-increment)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private Integer taskSequence = 0;
	
	//api uses projTag as main key of identification, this field provides us easy access to associated projTag
	private String projTag;
	
	//OneToOne relationship with project
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id", nullable = false)
	@JsonIgnore
	private Project project;
	
	//Foreign Key: OneToMany relationship with tasks
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "backlog")
	private List<Task> tasks = new ArrayList<>();
	
	//no-arg constructor
	public Backlog() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getTaskSequence() {
		return taskSequence;
	}

	public void setTaskSequence(Integer taskSequence) {
		this.taskSequence = taskSequence;
	}

	public String getProjTag() {
		return projTag;
	}

	public void setProjTag(String projTag) {
		this.projTag = projTag;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
}	
