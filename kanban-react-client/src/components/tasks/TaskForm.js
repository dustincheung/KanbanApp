import React from "react";
import {Field, reduxForm} from 'redux-form'; //import Field component and reduxForm function

class TaskForm extends React.Component {
	render(){
		return(
			<form className="ui form error" onSubmit={this.props.handleSubmit(this.onSubmit)} style={{width: "50%", margin: "auto", paddingTop: "5%"}}>
				<div className="form-row">
					<div className="form-group col-md-8">
						<Field name="taskTitle" label="Task Title: " component={this.renderFieldText}/>
					</div>
					<div className="form-group col-md-4">
						<Field name="dueDate" label="Due Date:  " component={this.renderFieldDate}/>
					</div>
				</div>
				<Field name="taskDescription" label="Description: " component={this.renderFieldText}/>
				<Field name="acceptCriteria" label="Acceptance Criteria: " component={this.renderFieldText}/>
				 <div className="form-row">
    				<div className="col">
    				  <Field name="priority" label="Priority:  " component={this.renderPriorityDropDown}/>
   				 	</div>
    				<div className="col">
     				 <Field name="status" label="Status:  " component={this.renderStatusDropDown}/>
    				</div>
  				</div>				
				<div className="text-center">
					<button className="ui button primary" style={{marginTop: "4%", width: "100%"}}> Submit </button>
				</div>
			</form>
		);
	}

	onSubmit = (formValues) =>{
		this.props.onSubmit(formValues); //onSubmit that is passed down
	}

	//functional component that renders text input, and also accepts redux formProps
	//formProps gives us acess to label, input, meta
	renderFieldText = (formProps) => {						
		return(
			<div className="form-group">					
				<label>{formProps.label}</label>
				<input {...formProps.input}/>			
				{this.renderError(formProps.meta)}
			</div>
		); 
	}

	renderFieldDate = (formProps) => {
		return(
			<div className="form-group">
				<label>{formProps.label}</label>
				<input
                      type="date"
                      className="form-control form-control-lg"
                      value={formProps.input.value}
                      onChange={formProps.input.onChange}
                />
                {this.renderError(formProps.meta)}
			</div>
		);
	}

	renderPriorityDropDown = (formProps) => {
		return(
			<div className="form-group">
				<label>{formProps.label}</label>
					<select className="ui search dropdown" value={formProps.input.value} onChange={formProps.input.onChange}>
      					<option value={3}>Low</option>
      					<option value={2}>Medium</option>
      					<option value={1}>High</option>
      				</select>	
                {this.renderError(formProps.meta)}
			</div>
		);
	}

	renderStatusDropDown = (formProps) => {
		return(
			<div className="form-group">
				<label>{formProps.label}</label>
					<select className="ui search dropdown" value={formProps.input.value} onChange={formProps.input.onChange}>
      					<option value="Todo">Todo</option>
      					<option value="In Progress">In Progress</option>
      					<option value="Done">Done</option>
      				</select>	
                {this.renderError(formProps.meta)}
			</div>
		);
	}

	//functional component that display errors after input is touched
	renderError = (meta) => {
		if(meta.touched && meta.error){
			return(
				<div className="ui error message">
					<div className="header">
						{meta.error}
					</div>
				</div>
			);
		}
	}
}

//returns empty errors object if no errors, if there are it will add a key value pair to errors obj and return 
//if a key in the error obj has a key that matches a field name it is auto passed in an argument named formProps.meta
//that is called in renderInput
const validate = (formValues) => {
	const errors ={};

	if(!formValues.taskTitle){
		errors.taskTitle = "Please enter a title";
	}

	if(!formValues.dueDate){
		errors.dueDate = "Please enter a due date";
	}

	if(!formValues.taskDescription){
		errors.taskDescription = "Please enter a task description";
	}

	return errors;
};

export default reduxForm({form: "taskForm", validate: validate})(TaskForm);