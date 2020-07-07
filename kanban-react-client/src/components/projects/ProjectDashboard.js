import React from "react";

import ProjectCard from "./ProjectCard";

class ProjectDashbord extends React.Component{
	render(){
		return(
			<div className="jumbotron" style={{padding: "2%"}}>
				<div class="ui grid">
  					<div class="fourteen wide column">
  						<h1 className="display-4">Projects</h1>
  						<p className="lead">This is where you can view all of your projects.</p>
  					</div>
  					<div class="two wide column">
  						<button class="ui right floated green button" style={{marginTop: "30%"}}>Create Project</button>
  					</div>
				</div>
			</div>
		);
	}
}

export default ProjectDashbord;