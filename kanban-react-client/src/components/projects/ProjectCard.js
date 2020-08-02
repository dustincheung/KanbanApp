/*  
 *  ProjectCard component renders the summary card view for each project
 */

import React from "react";
import {Link} from "react-router-dom";
import {connect} from "react-redux";
import {Progress} from "semantic-ui-react";

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
  				{this.renderCardHeader()}
  				<div className="card-body">
    				<div className="ui grid">
  						<div className="ten wide column">
  							{this.renderProjectInfo()}
  						</div>
  						<div className="six wide column">
  							{this.renderButtons()}
  						</div>
              <div className="sixteen wide column">
                {this.renderProgressBar()}
              </div>
					</div>
  				</div>
			</div>				
		);
	}

  renderCardHeader = () => {
    return(
      <div className="card-header">
          {this.props.project.projTag}
          {this.renderStats()}
      </div>
    );
  }

	renderStats = () => {
		return(
			<div className="ui mini statistics" style={{marginRight: "1%", float: "right"}}>
  				<div className="ui horizontal red statistic">
   						<div className="value"> {this.props.project.todoCount} </div>
    				<div className="label"> Todo </div>
  				</div>
  				<div className="ui horizontal yellow statistic">
    				<div className="value"> {this.props.project.inProgCount} </div>
    				<div className="label"> In Progress </div>
  				</div>
  				<div className="ui horizontal green statistic">
    				<div className="value"> {this.props.project.doneCount} </div>
    				<div className="label"> Done </div>
  				</div>
			</div>
		);
	}

	renderProjectInfo = () => {
		return(
			<div style={{float: "left"}}>
  				<h2 style={{fontFamily: "Roboto", fontWeight: "300"}}>{this.props.project.projTitle}</h2>
    			<h5 style={{fontFamily: "Roboto", fontWeight: "300"}}>{this.props.project.description}</h5>
  			</div>	
		);
	}

	renderButtons = () => {
		
		const projectEditPath	= "/projects/" + this.props.project.projTag + "/edit";
    const backlogPath = "/projects/" + this.props.project.projTag + "/tasks";

		return(
			<div className="ui vertical basic buttons" style={{float: "right", width: "65%"}}>
  				<Link className="ui button" to={backlogPath}>
    				Kanban
  				</Link>
  				<Link className="ui button" to={projectEditPath}>
   					Update
  				</Link>
  				<button className="ui button" onClick={(event) => this.onDeleteClick(event)}>
    				Delete
  				</button>
			</div>
		);
	}

  onDeleteClick = (event) => {
      event.stopPropagation();
      this.props.deleteProject(this.props.project.projTag);
  }

  renderProgressBar = () => {

    if(this.props.project != null){
      var percentComplete = ((this.props.project.doneCount + (this.props.project.inProgCount/2.0)) / this.props.project.totalTaskCount) * 100;
    }

    return(
      <div style={{marginTop: "3%"}}>
        <Progress style={{margin: "0px 10px",}} size="large" percent={percentComplete} indicating />  
        <p style={{textAlign: "center", fontFamily: "Roboto", fontWeight: "300"}}>
          Task Completion Status (Due: {this.props.project.endDate})
        </p>
      </div>  
    );
  }
}

export default connect(null, {deleteProject})(ProjectCard);