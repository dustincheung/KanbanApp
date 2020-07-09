import React from "react";
import {connect} from "react-redux";

import ErrorAlert from "../errors/ErrorAlert";
import ProjectForm from "./ProjectForm";
import {getProject, updateProject} from "../../actions";

class ProjectEdit extends React.Component{

	componentDidMount(){
		const projTag = this.props.match.params.projTag;
		this.props.getProject(projTag);
	}

	render(){
		if(!this.props.project){
			return(
				<div> LOADING </div>
			)
		}

		return(
			<div style={{margin: "5%"}}>
				<h3 style={{textAlign: "center"}}> Edit a Project </h3>
				<ProjectForm onSubmit={this.onSubmit} initialValues={
					{
						projTitle: this.props.project.projTitle, 
						projTag: this.props.project.projTag, 
						description: this.props.project.description, 
						startDate: this.props.project.startDate,
						endDate: this.props.project.endDate
					}}/>
				{this.renderErrorAlert()}
			</div>
		);
	}

	onSubmit = (formValues) => {
		this.props.updateProject(this.props.project.projTag, formValues);
	}

	renderErrorAlert = () => {
		if(this.props.errors != null){
			return(
				<ErrorAlert errors={this.props.errors}/>
			);
		}
	}
}

const mapStateToProps = (state) => {
	return{
		errors: state.errors,
		project: state.project
	};
}

export default connect(mapStateToProps, {getProject, updateProject})(ProjectEdit);