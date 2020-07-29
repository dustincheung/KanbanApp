/*	
 * 	SecureRouting component is used instead of Route component to ensure the route can only be accessed
 *  if the user is signed in, if not it will redirect to login page
 */

import React from "react";
import PropTypes from "prop-types"; //used to validate props
import {connect} from "react-redux";
import {Route, Redirect} from "react-router-dom";

const SecureRouting = ({component: Component, user, ...otherProps}) => {
	return(
	 	<Route {...otherProps} render={ props =>
	 		//if no user, redirect to login page
      		user === null ? (<Redirect to="/users/login" />) : (<Component {...props} />)}
  		/>
  	);
};

//validates props
SecureRouting.propTypes = {
  user: PropTypes.object
};

const mapStateToProps = state => ({
  user: state.user
});

export default connect(mapStateToProps)(SecureRouting);
