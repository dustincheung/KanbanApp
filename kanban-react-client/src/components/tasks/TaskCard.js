import React from "react";
import {Link} from "react-router-dom";
import {connect} from "react-redux";

import {deleteTask} from "../../actions";

class TaskCard extends React.Component{
  
  constructor(props){
    super(props);
    this.task = this.props.task; //this context is accessible by lifecyle methods and arrow functions
    
    switch(this.props.status){
      case "TODO":
        this.color = "#F8C3B9";
        break;
      case "IN_PROGRESS":
        this.color = "#B6DDF6";
        break;
      case "DONE":
        this.color = "#B5EFCE";
        break;
      default:
        this.color = "#F8C3B9";
        break;
    } 
  }

	render(){
		return(
			<div className="ui card" style={{width: "100%", backgroundColor: this.color}}>
  				<div className="content">
    				<h4 className="ui right floated sub header">{this.task.taskTag}</h4>
    				<div className="header"> {this.task.taskTitle} </div>
    				<div className="description">
      					<p>{this.task.taskDescription}</p>
    				</div>
  				</div>
  				<div className="extra content">
    				{this.renderButtons()}
  				</div>
			</div>				
		);
	}

  renderButtons = () => {
    const taskEditPath = "/projects/" + this.task.projTag + "/tasks/" + this.task.taskTag + "/edit";

    return(
      <div>
        <Link className="left floated tiny circular ui icon button" to={taskEditPath}>
          <i className="expand icon"></i>
        </Link>
        <button className="right floated tiny circular ui icon button" onClick={(event) => {this.onDeleteClick(event)}}>
          <i className="trash alternate outline icon"></i>
        </button>
       </div>
    );
  }

  onDeleteClick = (event) => {
    event.stopPropagation();
    this.props.deleteTask(this.props.task.projTag, this.props.task.taskTag);
  }
}

export default connect(null, {deleteTask})(TaskCard);