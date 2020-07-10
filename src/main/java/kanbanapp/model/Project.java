package kanbanapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

@Entity
public class Project {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
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
		
		// no arg constructor
		public Project() {
			
		}
		
		// getters/setters for all class fields
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
		
}
