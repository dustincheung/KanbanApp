/*	
 * 	Redux Form component for creating/updating a project
 */

import React from "react";
import {Field, reduxForm} from 'redux-form'; //import Field component and reduxForm function

class ProjectForm extends React.Component {
	render(){
		return(
			<div className="ui raised segment" style={{width: "50%", margin: "auto"}}>
				<form className="ui form error" onSubmit={this.props.handleSubmit(this.onSubmit)}>
				<h2 className="ui dividing header">Project Information</h2>
					<div className="form-row">
						<div className="form-group col-md-8">
							<Field name="projTitle" label="Title: " component={this.renderFieldText}/>
						</div>
						<div className="form-group col-md-4">
							<Field name="projTag" label="Tag (5 Chars): " component={this.renderFieldText}/>
						</div>
					</div>
					<Field name="description" label="Description: " component={this.renderTextArea}/>
				 	<div className="form-row">
    					<div className="col">
    				  	<Field name="startDate" label="Start Date:  " component={this.renderFieldDate}/>
   				 		</div>
    					<div className="col">
     				 	<Field name="endDate" label="End Date:  " component={this.renderFieldDate}/>
    					</div>
  					</div>				
					<div className="text-center">
						<button className="ui green basic button" style={{marginTop: "4%", width: "100%"}}> Submit </button>
					</div>
				</form>
			</div>
		);
	}

	onSubmit = (formValues) =>{
		this.props.onSubmit(formValues); //onSubmit that is passed down
	}

	//renders text input, and also accepts redux formProps
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

	renderTextArea = (formProps) => {						
		return(
			<div className="form-group">					
				<label>{formProps.label}</label>
				<textarea {...formProps.input} placeholder="Content" rows="5" cols="40"></textarea>		
				{this.renderError(formProps.meta)}
			</div>
		); 
	}

	//renders date input, and also accepts redux formProps
	renderFieldDate = (formProps) => {
		return(
			<div className="form-group">
				<label>{formProps.label}</label>
				<input
                      type="date"
                      className="form-control form-control-lg"
                      name="start_date"
                      value={formProps.input.value}
                      onChange={formProps.input.onChange}
                />
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

	if(!formValues.projTitle){
		errors.projTitle = "Please enter a title";
	}

	if(!formValues.description){
		errors.description = "Please enter a description";
	}

	if(!formValues.projTag){
		errors.projTag = "Please enter a tag";
	}

	if(!formValues.startDate){
		errors.startDate = "Please enter a start date";
	}

	if(!formValues.endDate){
		errors.endDate = "Please enter an end date";
	}

	return errors;
};

export default reduxForm({form: "projectForm", validate: validate})(ProjectForm);