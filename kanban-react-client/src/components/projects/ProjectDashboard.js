/*	
 * 	ProjectDashboard component displays the projects of the current user
 */

import React from "react";
import {Link} from "react-router-dom";
import {connect} from "react-redux";

import ProjectsList from "./ProjectsList";
import {getProjects} from "../../actions";

class ProjectDashbord extends React.Component{

	componentDidMount(){
		this.props.getProjects();
	}

	render(){
		if(!this.props.projects){
			return <div> LOADING </div>;
		}

		return(
			<div>
				<div className="jumbotron" style={{padding: "2%", backgroundColor: "#F7F7F7"}}>
					<div className="ui grid">
  						<div className="thirteen wide column">
  							<h1 className="display-4">Projects Dashboard</h1>
  							<p className="lead">This is where you can view all of your projects.</p>
  						</div>
  						<div className="three wide column">
  							{this.renderCreateButton()}
  						</div>
					</div>
				</div>
				<ProjectsList projects={this.props.projects}/>
			</div>
		);
	}

	renderCreateButton = () => {
		return(
			<Link to="/projects/new" className="ui right floated green basic button" style={{marginTop: "30%"}}>Create Project</Link>
		);
	}
}

const mapStateToProps = (state) => {
	return{
		projects: state.projects
	};
}

export default connect(mapStateToProps, {getProjects})(ProjectDashbord);