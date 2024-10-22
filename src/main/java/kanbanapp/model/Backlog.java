/*
 *  Backlog entity model
 */

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
	
	//this incrementer is used in the taskTag, it is appended to the projTag to create a readable taskTag (PROJ-1)
	private Integer taskSeqIncrementor = 1;
	
	//API uses projTag as main key of identification, this field provides us easy access to associated projTag
	private String projTag;
	
	//Foreign Key: OneToOne relationship with project
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id", nullable = false)
	@JsonIgnore
	private Project project;
	
	//Foreign Key: OneToMany relationship with tasks
	//Refresh: indicates to refresh tasks list when it is modified (such as when a task is deleted)
	//OrphanRemoval: when backlog is deleted, we want the orphaned tasks to be also deleted
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH, mappedBy = "backlog", orphanRemoval = true)
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

	public Integer getTaskSeqIncrementor() {
		return taskSeqIncrementor;
	}

	public void setTaskSeqIncrementor(Integer taskSeqIncrementor) {
		this.taskSeqIncrementor = taskSeqIncrementor;
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
