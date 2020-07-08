/*  
 *  ProjectsList component renders the list of ProjectCard components
 */

import React from "react";

import ProjectCard from "./ProjectCard";

class ProjectsList extends React.Component{
	render(){
		let projects = this.props.projects;

		return(
			<div>
				{projects.map((project) => 
					<ProjectCard project={project} key={project.projTag}/>
				)}
			</div>
		);
	}
}

export default ProjectsList;