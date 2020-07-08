/*	
 * 	Menu Component that is always visible and routes to landing and projectsDashboard component
 */

import React from "react";
import {Link} from "react-router-dom";

class Menu extends React.Component{
	render(){
		return(
			<div className="ui menu">
  				<Link to="/" className="item">
    				KanbanTool
  				</Link>
  				<Link to="/projects" className="item">
  					Dashboard
  				</Link>
  				<div className="right menu">
  					<a className="item">
  						Sign Up
  					</a>
  					<a className="item">
  						Log In
  					</a>
  				</div>
  				
			</div>												
		);
	}
}

export default Menu;