/*	
 * 	TaskEdit Component renders TaskForm for editing a task, initialize form with current task in state
 */

import React from "react";
import {connect} from "react-redux";

import ErrorAlert from "../errors/ErrorAlert";
import TaskForm from "./TaskForm";
import {getTask, updateTask} from "../../actions";

class TaskEdit extends React.Component{

	componentDidMount(){
		const projTag = this.props.match.params.projTag;
		const taskTag = this.props.match.params.taskTag;
		this.props.getTask(projTag, taskTag);
	}

	render(){
		const projTag = this.props.match.params.projTag;

		if(!this.props.task){
			return(
				<div> LOADING </div>
			)
		}

		return(
			<div style={{margin: "5%"}}>
				<TaskForm onSubmit={this.onSubmit} projTag={projTag} initialValues={
					{
						taskTitle: this.props.task.taskTitle, 
						dueDate: this.props.task.dueDate, 
						taskDescription: this.props.task.taskDescription, 
						acceptCriteria: this.props.task.acceptCriteria,
						priority: this.props.task.priority,
						status: this.props.task.status
					}}/>
				{this.renderErrorAlert()}
			</div>
		);
	}

	onSubmit = (formValues) => {
		this.props.updateTask(this.props.task.projTag, this.props.task.taskTag, formValues);
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
		task: state.task,
		errors: state.errors
	};
}

export default connect(mapStateToProps, {getTask, updateTask})(TaskEdit);