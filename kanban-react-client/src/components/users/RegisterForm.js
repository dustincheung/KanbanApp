import React from "react";
import {connect} from "react-redux";
import {Field, reduxForm} from 'redux-form'; //import Field component and reduxForm function

import ErrorAlert from "../errors/ErrorAlert";
import {createUser} from "../../actions";

class RegisterForm extends React.Component {
	render(){
		return(
			<div>
			<div className="ui raised segment" style={{width: "40%", margin: "auto"}}>
				<form className="ui form error" onSubmit={this.props.handleSubmit(this.onSubmit)}>
				<h2 className="ui dividing header">Sign Up!</h2>
					<div className="form-row">
						<div className="col">
							<Field name="name" label="Name: " component={this.renderFieldText}/>
						</div>
					</div>
				 	<div className="form-row">
				 		<div className="col">
				 			<Field name="username" label="Email: " component={this.renderFieldText}/>
				 		</div>
  					</div>
  					<div className="form-row">
  						<div className="col">
  							<Field name="password" label="Password: " component={this.renderFieldText}/>
  						</div>
  					</div>		
  					<div className="form-row">
  						<div className="col">
  							<Field name="confirmPassword" label="Confirm Password: " component={this.renderFieldText}/>
  						</div>
  					</div>						
					<div className="text-center">
						<button className="ui green basic button" style={{marginTop: "4%", width: "100%"}}> Submit </button>
					</div>
				</form>
			</div>
			{this.renderErrorAlert()}
			</div>
		);
	}

	onSubmit = (formValues) =>{
		this.props.createUser(formValues);
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

	renderErrorAlert = () => {
		if(this.props.errors != null){
			return(
				<ErrorAlert errors={this.props.errors}/>
			);
		}
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

	if(!formValues.name){
		errors.name = "Please enter a name";
	}

	if(!formValues.username){
		errors.username = "Please enter a username";
	}

	if(!formValues.password){
		errors.password = "Please enter a password";
	}

	if(!formValues.confirmPassword){
		errors.confirmPassword = "Please enter a confirmPassword";
	}

	return errors;
};

const mapStateToProps = (state) => {
	return{
		errors: state.errors
	};
}

export default connect(mapStateToProps, {createUser})(reduxForm({form: "registerForm", validate: validate})(RegisterForm));