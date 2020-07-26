/*	
 * 	Menu Component that is always visible and routes to landing and projectsDashboard component
 */

import React from "react";
import {Link} from "react-router-dom";
import {connect} from "react-redux";

import {clearProject, logoutUser} from "../../actions";

class Menu extends React.Component{
	render(){
		return(
			<div className="ui menu" style={{marginBottom: "1.1%"}}>
  				<Link to="/" className="item">
    				KanbanTool
  				</Link>
  				<Link to="/projects" onClick={this.props.clearProject} className="item">
  					Dashboard
  				</Link>
          {this.renderUserButtons()}
			</div>												
		);
	}

  renderUserButtons = () => {
    if(!this.props.user){ 
      return(
        <div className="right menu"> 
          <Link to="/users/register" className="item">
            Sign Up
          </Link>
          <Link to="/users/login" className="item">
            Log In
          </Link>
        </div>
      );
    }else{
      return( 
        <div className="right menu">
          <a onClick={this.props.logoutUser} className="item">
            Log Out
          </a>
        </div>
      );
    }
  }
}

const mapStateToProps = (state) => {
  return{
    user: state.user
  };
}

export default connect(mapStateToProps, {clearProject, logoutUser})(Menu);