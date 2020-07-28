import React from "react";
import {connect} from "react-redux";
import {Link} from "react-router-dom";

import TaskList from "./TaskList";
import {getTasks, clearTask} from "../../actions";

class TaskBoard extends React.Component{
	
	componentDidMount(){
		const projTag = this.props.match.params.projTag;
		this.props.getTasks(projTag);
		this.props.clearTask(); //clears Task state when update is cancelled
	}

	render(){
		if(!this.props.tasks){
			return <div> LOADING </div>;
		}

		return(
			<div>
				<div className="ui grid">
  					<div className="thirteen wide column">
  						<h1 className="display-4" style={{fontFamily: "Roboto", fontWeight: "400"}}>Kanban Board</h1>
  						<p className="lead" style={{fontFamily: "Roboto"}}>This is where you can view and orgainize all project tasks.</p>
  					</div>
  					<div className="three wide column">
  						{this.renderCreateButton()}
  					</div>
				</div>
				<div className="ui divider"></div>
				{this.renderSwimLanes()}
			</div>

		);
	}

	renderCreateButton = () => {
		const taskCreatePath = "/projects/" + this.props.match.params.projTag + "/tasks/new";

		return(
			<Link to={taskCreatePath} className="ui right floated green button" style={{marginTop: "30%"}}>Create Task</Link>
		);
	}

	renderSwimLanes(){
		return(
			<div className="ui internally celled grid">
  				<div className="three column row">
    				<div className="column">
    					<h3 className="ui block header"> Todo </h3>
    					<TaskList tasks={this.props.tasksTodo} status="TODO"/>
    				</div>
    				<div className="column">
    					<h3 className="ui block header"> In Progress </h3>
    					<TaskList tasks={this.props.tasksInProgress} status="IN_PROGRESS"/>
    				</div>
    				<div className="column">
    					<h3 className="ui block header"> Done </h3>
    					<TaskList tasks={this.props.tasksDone} status="DONE"/>
    				</div>
    			</div>
  			</div>
		)
	}
}

const mapStateToProps = (state) => {
	return { 
		tasks: state.tasks,
		tasksTodo: filterTasks(state.tasks, "Todo"),
		tasksInProgress: filterTasks(state.tasks, "In Progress"),
		tasksDone: filterTasks(state.tasks, "Done")
	}
}

const filterTasks = (tasks, status) => {
	return tasks.filter(task => task.status === status);
}

export default connect(mapStateToProps, {getTasks, clearTask})(TaskBoard);
