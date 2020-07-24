/*	
 * 	Menu Component that is always visible and routes to landing and projectsDashboard component
 */

import React from "react";
import {Link} from "react-router-dom";
import {connect} from "react-redux";

import {clearProject} from "../../actions";

class Menu extends React.Component{
	render(){
		return(
			<div className="ui menu" style={{marginBottom: ".6%"}}>
  				<Link to="/" className="item">
    				KanbanTool
  				</Link>
  				<Link to="/projects" onClick={this.props.clearProject} className="item">
  					Dashboard
  				</Link>
  				<div className="right menu">
  					<Link to="/users/register" className="item">
  						Sign Up
  					</Link>
  					<Link to="/users/login" className="item">
  						Log In
  					</Link>
  				</div>
			</div>												
		);
	}
}

export default connect(null, {clearProject})(Menu);