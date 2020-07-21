package kanbanapp.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User implements UserDetails {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@Column(unique = true)
		@Email(message="username should be an email")
		@NotBlank(message="Username is required")
		private String username;
		
		@NotBlank(message="name is required")
		private String name;
		
		@NotBlank(message="password is required")
		private String password;
		
		//This field will not persist, but will be used to confirm password field 
		@Transient
		private String confirmPassword;
		
		//OneToMany relationship with Project
		
		private Date createdDate;
		private Date updatedDate;
		
		public User() {
			
		} 
		
		@PrePersist
		// stores the date of creation when a User object is created
		protected void onCreate() {
			this.createdDate = new Date();
		}
		
		@PreUpdate
		// stores the date of update when a User object is updated
		protected void onUpdate(){
			this.updatedDate = new Date();
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getConfirmPassword() {
			return confirmPassword;
		}

		public void setConfirmPassword(String confirmPassword) {
			this.confirmPassword = confirmPassword;
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
		
		/*
		 *  Methods from UserDetails Interface
		 */
		
		@JsonIgnore
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@JsonIgnore
		@Override
		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return false;
		}
		
		@JsonIgnore
		@Override
		public boolean isAccountNonLocked() {
			// TODO Auto-generated method stub
			return false;
		}
		
		@JsonIgnore
		@Override
		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return false;
		}
		
		@JsonIgnore
		@Override
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return false;
		}
}
