import React from "react";

import TaskCard from "./TaskCard";

class TaskList extends React.Component{
	render(){
		let tasks = this.props.tasks;

		return(
			<div>
				{tasks.map((task) => 
					<TaskCard task={task} status={this.props.status} key={task.taskTag}/>
				)}
			</div>
		);
	}
}

export default TaskList;