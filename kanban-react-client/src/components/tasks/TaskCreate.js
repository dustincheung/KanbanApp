import React from "react";
import {connect} from "react-redux";

import ErrorAlert from "../errors/ErrorAlert";
import TaskForm from "./TaskForm";
import {createTask} from "../../actions";

class TaskCreate extends React.Component{
	render(){
		return(
			<div style={{margin: "5%"}}>
				<TaskForm onSubmit={this.onSubmit}/>
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
		const projTag = this.props.match.params.projTag;
		this.props.createTask(formValues, projTag);
	}
}

const mapStateToProps = (state) => {
	return{
		errors: state.errors
	};
}

export default connect(mapStateToProps, {createTask})(TaskCreate);