/*  
 *   TaskCard Component renders small summary card view of a task
 */

import React from "react";
import {Link} from "react-router-dom";
import {connect} from "react-redux";

import {deleteTask, getProject} from "../../actions";

class TaskCard extends React.Component{
  
  constructor(props){
    super(props);
    this.task = this.props.task; //this context is accessible by lifecyle methods and arrow functions
    
    //determines color of card based on status type
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
    				<h3 className="ui right floated sub header">{this.task.taskTag}</h3>
    				<div className="header"> {this.task.taskTitle} </div>
            <div class="ui right floated meta">{this.task.dueDate}</div>
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
      <div className="ui equal width grid">
        <div className="column">
          <Link className="left floated tiny circular ui icon button" to={taskEditPath}>
            <i className="expand icon"></i>
          </Link>
        </div>
        <div className="column">
          <p style={{textAlign: "center"}}>
            {this.convertPriority(this.task.priority)}
          </p>
        </div>
        <div className="column">
          <button className="right floated tiny circular ui icon button" onClick={(event) => {this.onDeleteClick(event)}}>
            <i className="trash alternate outline icon"></i>
          </button>
        </div>
      </div>
    );
  }

  onDeleteClick = async (event) => {
    event.stopPropagation();
    await this.props.deleteTask(this.props.task.projTag, this.props.task.taskTag);
    this.props.getProject(this.props.task.projTag);
  }

  convertPriority = (priorityNum) => {
    switch(priorityNum){
      case 3:
        return "Low Priority";
      case 2: 
        return "Medium Priority";
      case 1:
        return "High Priority";
      default:
        return "Low Priority";
    }
  }
}

export default connect(null, {deleteTask, getProject})(TaskCard);