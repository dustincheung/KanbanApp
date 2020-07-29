/*	
 * 	Project Create Component renders the ProjectForm and passes it onSubmit function
 */

import React from "react";
import {connect} from "react-redux";

import ErrorAlert from "../errors/ErrorAlert";
import ProjectForm from "./ProjectForm";
import {createProject} from "../../actions";

class ProjectCreate extends React.Component{
	render(){
		return(
			<div style={{margin: "5%"}}>
				<ProjectForm onSubmit={this.onSubmit}/>
				{this.renderErrorAlert()}
			</div>
		);
	}

	renderErrorAlert = () => {
		if(this.props.errors != null){
			return(
				<ErrorAlert errors={this.props.errors}/>
			);
		}
	}

	onSubmit = (formValues) => {
		this.props.createProject(formValues);
	}
}

const mapStateToProps = (state) => {
	return{
		errors: state.errors
	};
}

export default connect(mapStateToProps, {createProject})(ProjectCreate);