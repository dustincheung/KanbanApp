import React from "react";
import {Link} from "react-router-dom";

class TaskCard extends React.Component{
	render(){
		const task = this.props.task;

		return(
			<div className="ui card" style={{backgroundColor: "#FFCAC2"}}>
  				<div className="content">
    				<h4 className="ui right floated sub header">{task.taskTag}</h4>
    				<div className="header"> {task.taskTitle} </div>
    				<div className="description">
      					<p>{task.taskDescription}</p>
    				</div>
  				</div>
  				<div className="extra content">
    				<Link class="left floated tiny circular ui icon button">
      					<i className="expand icon"></i>
      				</Link>
    				<Link class="right floated tiny circular ui icon button">
      					<i className="trash alternate outline icon"></i>
      				</Link>
  				</div>
			</div>				
		);
	}
}

export default TaskCard;