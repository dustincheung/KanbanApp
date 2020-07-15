import React from "react";
import {connect} from "react-redux";
import {Link} from "react-router-dom";

import TaskList from "./TaskList";
import {getTasks} from "../../actions";

class TaskBoard extends React.Component{
	
	componentDidMount(){
		const projTag = this.props.match.params.projTag;
		this.props.getTasks(projTag);
	}

	render(){
		if(!this.props.tasks){
			return <div> LOADING </div>;
		}

		return(
			<div>
				<div className="jumbotron" style={{padding: "2%", backgroundColor: "#F7F7F7"}}>
					<div className="ui grid">
  						<div className="thirteen wide column">
  							<h1 className="display-4">Kanban Board</h1>
  							<p className="lead">This is where you can view and orgainize all project tasks.</p>
  						</div>
  						<div className="three wide column">
  							{this.renderCreateButton()}
  						</div>
					</div>
				</div>
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
			<div class="ui internally celled grid">
  				<div class="three column row">
    				<div class="column">
    					<h3 class="ui block header"> Todo </h3>
    					<TaskList tasks={this.props.tasks}/>
    				</div>
    				<div class="column">
    					<h3 class="ui block header"> In Progress </h3>
    				</div>
    				<div class="column">
    					<h3 class="ui block header"> Done </h3>
    				</div>
    			</div>
  			</div>
		)
	}
}

const mapStateToProps = (state) => {
	return { 
		tasks: state.tasks
	}
}

export default connect(mapStateToProps, {getTasks})(TaskBoard);
