import React from "react";
import {Field, reduxForm} from 'redux-form'; //import Field component and reduxForm function

class ProjectForm extends React.Component {
	render(){
		return(
			<form className="ui form error" onSubmit={this.props.handleSubmit(this.onSubmit)} style={{width: "50%", margin: "auto", paddingTop: "5%"}}>
				<div className="form-row">
					<div className="form-group col-md-8">
						<Field name="projTitle" label="Enter Title: " component={this.renderFieldText}/>
					</div>
					<div className="form-group col-md-4">
						<Field name="projTag" label="Enter Tag (5 digits): " component={this.renderFieldText}/>
					</div>
				</div>
				<Field name="description" label="Enter Description: " component={this.renderFieldText}/>
				 <div className="form-row">
    				<div className="col">
    				  <Field name="startDate" label="Start Date:  " component={this.renderFieldDate}/>
   				 	</div>
    				<div className="col">
     				 <Field name="endDate" label="End Date:  " component={this.renderFieldDate}/>
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

	//functional component that renders date input, and also accepts redux formProps
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