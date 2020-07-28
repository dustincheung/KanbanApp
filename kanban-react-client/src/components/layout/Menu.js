/*	
 * 	Menu Component that is always visible and routes to landing and projectsDashboard component
 */

import React from "react";
import {Link} from "react-router-dom";
import {connect} from "react-redux";

import {clearProject, clearErrors, logoutUser} from "../../actions";

class Menu extends React.Component{
	render(){
		return(
        <div>
          {this.renderUserButtons()}
        </div>											
		);
	}

  renderUserButtons = () => {
    if(!this.props.user){ 
      return(
        <div className="ui menu" style={{marginBottom: "14px"}}>
          <Link to="/" className="item">
            KanbanTool
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
    }else{
      return( 
        <div className="ui menu" style={{marginBottom: "14px"}}>
          <Link to="/" onClick={this.clearProjectAndErrors} className="item">
            KanbanTool
          </Link>
          <Link to="/projects" onClick={this.clearProjectAndErrors} className="item">
            Dashboard
          </Link>  
          <div className="right menu">
            <a className="item">
              <i className="user circle outline icon"></i>
              {this.props.user.name}
            </a>
            <a className="item" onClick={this.props.logoutUser}>
              Log Out
            </a>
          </div>
        </div>
      );
    }
  }

  clearProjectAndErrors = () => {
    this.props.clearProject();
    this.props.clearErrors();
  }
}

const mapStateToProps = (state) => {
  return{
    user: state.user
  };
}

export default connect(mapStateToProps, {clearProject, clearErrors, logoutUser})(Menu);