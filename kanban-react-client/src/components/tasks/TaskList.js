import React from "react";

import TaskCard from "./TaskCard";

class TaskList extends React.Component{
	render(){
		let tasks = this.props.tasks;
		console.log("TASKLIST ");
		console.log(tasks);

		return(
			<div>
				{tasks.map((task) => 
					<TaskCard task={task} key={task.taskTag}/>
				)}
			</div>
		);
	}
}

export default TaskList;