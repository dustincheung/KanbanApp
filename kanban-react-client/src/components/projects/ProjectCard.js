/*  
 *  ProjectCard component renders the summary card view for each project
 */

import React from "react";
import {Link} from "react-router-dom";
import {connect} from "react-redux";

import {deleteProject} from "../../actions";

class ProjectCard extends React.Component{
	
	render(){
		if(!this.props.project){
			return(
				<div> LOADING </div>
			)
		}

		return(
			<div className="card" style={{marginTop: "2%"}}>
  				{this.renderTag()}
  				<div className="card-body">
    				<div className="ui grid">
    					<div className="four wide column">
  							{this.renderStats()}
  						</div>
  						<div className="nine wide column">
  							{this.renderProjectInfo()}
  						</div>
  						<div className="three wide column">
  							{this.renderButtons()}
  						</div>
					</div>
  				</div>
			</div>				
		);
	}

	renderStats = () => {
		return(
			<div className="ui small statistics">
  				<div className="red statistic">
   						<div className="value"> 20 </div>
    				<div className="label"> Todo </div>
  				</div>
  				<div className="yellow statistic">
    				<div className="value"> 10</div>
    				<div className="label"> In Progress </div>
  				</div>
  				<div className="green statistic">
    				<div className="value"> 6 </div>
    				<div className="label"> Done </div>
  				</div>
			</div>
		);
	}

	renderTag = () => {
		return(
			<div className="card-header">
    			{this.props.project.projTag}
  			</div>
		);
	}

	renderProjectInfo = () => {
		return(
			<div style={{float: "left"}}>
  				<h2 className="card-title">{this.props.project.projTitle}</h2>
    			<h5 className="card-text">{this.props.project.description}</h5>
  			</div>	
		);
	}

	renderButtons = () => {
		
		const projectEditPath	= "/projects/" + this.props.project.projTag + "/edit";
    const backlogPath = "/projects/" + this.props.project.projTag + "/tasks";

		return(
			<div className="ui vertical labeled icon buttons" style={{float: "right", width: "100%"}}>
  				<Link className="ui button" to={backlogPath}>
    				<i className="columns icon"></i>
    				Kanban
  				</Link>
  				<Link className="ui button" to={projectEditPath}>
    				<i className="edit icon"></i>
   					Update
  				</Link>
  				<button className="ui button" onClick={(event) => this.onDeleteClick(event)}>
    				<i className="trash icon"></i>
    				Delete
  				</button>
			</div>
		);
	}

  onDeleteClick = (event) => {
      event.stopPropagation();
      this.props.deleteProject(this.props.project.projTag);
    }
}

export default connect(null, {deleteProject})(ProjectCard);