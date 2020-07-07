import React from "react";

class ProjectCard extends React.Component{
	render(){
		return(
			<div class="card">
  				<div class="card-header">
    				PROJT
  				</div>
  				<div class="card-body">
    				<div class="ui grid">
    					<div class="four wide column">
  							<div class="ui small statistics">
  								<div class="red statistic">
   								 	<div class="value"> 20 </div>
    								<div class="label"> Todo </div>
  								</div>
  								<div class="yellow statistic">
    								<div class="value"> 10</div>
    								<div class="label"> In Progress </div>
  								</div>
  								<div class="green statistic">
    								<div class="value"> 6 </div>
    								<div class="label"> Done </div>
  								</div>
							</div>
  						</div>
  						<div class="nine wide column">
  							<div style={{float: "left"}}>
  								<h2 class="card-title">Sample Project</h2>
    							<h5 class="card-text">This is a sample project sample description.</h5>
  							</div>	
  						</div>
  						<div class="three wide column">
  							<div class="ui vertical labeled icon buttons" style={{float: "right", width: "100%"}}>
  								<button class="ui button">
    								<i class="columns icon"></i>
    								Kanban
  								</button>
  								<button class="ui button">
    								<i class="edit icon"></i>
   								 	Update
  								</button>
  								<button class="ui button">
    								<i class="trash icon"></i>
    								Delete
  								</button>
							</div>
  						</div>
					</div>
  				</div>
			</div>				
		);
	}
}

export default ProjectCard;