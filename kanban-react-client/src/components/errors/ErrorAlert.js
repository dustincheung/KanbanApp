import React from "react";

const ErrorAlert = (props) => {

	var errorMessage = "";

	for(var attr in props.errors){
		errorMessage += props.errors[attr];
	}

	return(
		<div className="alert alert-danger" style={{width: "50%", margin: "auto", marginTop: "2%"}}>
  			<p> {errorMessage} </p>
		</div>
	);
}

export default ErrorAlert;