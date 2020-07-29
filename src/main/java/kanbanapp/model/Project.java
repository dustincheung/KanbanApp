/*
 *  Project entity model
 */

package kanbanapp.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

@Entity
public class Project {
		
		//primary key, id will use database server side strategy to dictate value (auto-increment)
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		//API uses projTag as main key of identification, backlog also contains projTag
		@NotBlank(message = "Project Tag is a required field")
		@Column(unique = true, updatable = false)
		@Size(min=4, max=5, message = "Project Tag must be 4 to 5 characters")
		private String projTag;
		
		@NotBlank(message = "Project Title is a required field")
		private String projTitle;
		
		@NotBlank(message = "Project Description is a require field")
		private String description;
		
		@JsonFormat(pattern = "yyyy-MM-dd")
		private Date startDate;
		
		@JsonFormat(pattern = "yyyy-MM-dd")
		private Date endDate;
		
		@Column(updatable = false) 
		@JsonFormat(pattern = "yyyy-MM-dd")
		private Date createdDate;
		
		@JsonFormat(pattern = "yyyy-MM-dd")
		private Date updatedDate;
		
		//fetch type: eager means fetch load associated data immediately w/ the rest of the fields
		//cascade type: specifies the Project is the "owning" side of the relationship (if proj is deleted, all children objs are deleted)
		//mappedBy: specifies exact name of project field in Backlog obj
		@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "project")
		@JsonIgnore
		private Backlog backlog;
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JsonIgnore
		private User user;
		
		private String projOwner;
		
		private int todoCount;
		
		private int inProgCount;
		
		private int doneCount;
		
		private int totalTaskCount;
		
		//no arg constructor
		public Project() {
			
		}
		
		@PrePersist
		//stores the date of creation when a Project object is created
		protected void onCreate() {
			this.createdDate = new Date();
		}
		
		@PreUpdate
		//stores the date of update when a Project object is updated
		protected void onUpdate(){
			this.updatedDate = new Date();
		}
		
		//getters/setters for all class fields
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getProjTag() {
			return projTag;
		}

		public void setProjTag(String projTag) {
			this.projTag = projTag;
		}

		public String getProjTitle() {
			return projTitle;
		}

		public void setProjTitle(String projTitle) {
			this.projTitle = projTitle;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Date getStartDate() {
			return startDate;
		}

		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}

		public Date getEndDate() {
			return endDate;
		}

		public void setEndDate(Date endDate) {
			this.endDate = endDate;
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

		public Backlog getBacklog() {
			return backlog;
		}

		public void setBacklog(Backlog backlog) {
			this.backlog = backlog;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public String getProjOwner() {
			return projOwner;
		}

		public void setProjOwner(String projOwner) {
			this.projOwner = projOwner;
		}

		public int getTodoCount() {
			return todoCount;
		}

		public void setTodoCount(int todoCount) {
			this.todoCount = todoCount;
		}

		public int getInProgCount() {
			return inProgCount;
		}

		public void setInProgCount(int inProgCount) {
			this.inProgCount = inProgCount;
		}

		public int getDoneCount() {
			return doneCount;
		}

		public void setDoneCount(int doneCount) {
			this.doneCount = doneCount;
		}

		public int getTotalTaskCount() {
			return totalTaskCount;
		}

		public void setTotalTaskCount(int totalTaskCount) {
			this.totalTaskCount = totalTaskCount;
		}	
}
