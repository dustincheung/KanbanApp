import React from "react";
import {Link} from "react-router-dom";

const Landing = () => {
	return(
		<div className="ui grid">
  			{renderHeader()}
  			{renderButtons()}
  			{renderInfo()}
		</div>		
	);
}

const renderHeader = () => {
	return(
		<div className="sixteen wide column" style={{backgroundColor: "#A7DDFE", padding: "7%"}}>
  			<h1 style={{fontFamily: "Roboto", fontSize: "700%", textAlign: "center"}}> KanbanTool </h1>
  			<h3 style={{fontFamily: "Roboto", fontWeight: "300", textAlign: "center"}}> Increase your overall productivity by easily applying kanban methodology to all of your projects. </h3>
  		</div>
	);
}

const renderButtons = () => {
	return(
		<div className="sixteen wide column" style={{backgroundColor: "#92AFC0", textAlign: "center", padding: ".6%"}}>
  			<div style={{margin: "auto"}}>
  				<Link to="" className="ui big green button" style={{marginRight: "1%"}}> Sign Up </Link>
  				<Link to="" className="ui big blue button"> Log In </Link>
  			</div>
  		</div>
	);
}

const renderInfo = () => {
	return(
		<div className="ui container" style={{width: "70%", padding: "1.3%"}}>
  				<div className="ui three column grid">
  					<div className="column">
    					<div className="ui fluid card">
      						<div className="image">
      						</div>
      						<div className="content">
        						<a className="header">Clearly visualize the whole scope of a project</a>
      						</div>
    					</div>
  					</div>	
  					<div className="column">
    					<div className="ui fluid card">
     				<div className="image">
    				</div>
      					<div className="content">
        					<a className="header">Easily track the status and priority of each task item</a>
      					</div>
    				</div>
  				</div>
  				<div className="column">
    				<div className="ui fluid card">
      					<div className="image">
      					</div>
      					<div className="content">
        					<a className="header">See insightful statistics about the progree of your project</a>
      					</div>
    				</div>
  				</div>						
  			</div>
  		</div>
	);
}

export default Landing;