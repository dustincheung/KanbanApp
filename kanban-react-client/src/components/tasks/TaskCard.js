import React from "react";
import {Link} from "react-router-dom";

class TaskCard extends React.Component{
	render(){
		const task = this.props.task;
    const taskEditPath = "/projects/" + task.projTag + "/tasks/" + task.taskTag + "/edit";

		return(
			<div className="ui card" style={{width: "100%", backgroundColor: "#FFCAC2"}}>
  				<div className="content">
    				<h4 className="ui right floated sub header">{task.taskTag}</h4>
    				<div className="header"> {task.taskTitle} </div>
    				<div className="description">
      					<p>{task.taskDescription}</p>
    				</div>
  				</div>
  				<div className="extra content">
    				<Link className="left floated tiny circular ui icon button" to={taskEditPath}>
      					<i className="expand icon"></i>
      				</Link>
    				<button className="right floated tiny circular ui icon button">
      					<i className="trash alternate outline icon"></i>
      				</button>
  				</div>
			</div>				
		);
	}
}

export default TaskCard;