import React from "react";

class Menu extends React.Component{
	render(){
		return(
			<div className="ui menu">
  				<a className="item">
    				KanbanTool
  				</a>
  				<a className="item">
  					Dashboard
  				</a>
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