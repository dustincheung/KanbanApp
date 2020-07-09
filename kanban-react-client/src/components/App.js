/*	
 * 	Parent Component, that uses react router to handle navigation around the app
 */

import React from "react";
import {Router, Route, Switch} from "react-router-dom";

import history from "../history";
import Menu from "./layout/Menu";
import Landing from "./layout/Landing";
import ProjectDashboard from "./projects/ProjectDashboard";
import ProjectCreate from "./projects/ProjectCreate";
import ProjectEdit from "./projects/ProjectEdit";

const App = () => {
	return(
		<div className="ui container" style={{width: "75%", padding: "1%"}}>
			<Router history={history}>
				<div>
					<Menu/>
					<Switch>
						<Route path="/" exact component={Landing}/>
						<Route path="/projects" exact component={ProjectDashboard}/>
						<Route path="/projects/new" exact component={ProjectCreate}/>
						<Route path="/projects/:projTag/edit" exact component={ProjectEdit}/>
					</Switch>
				</div>
			</Router>
		</div>
	);
}

export default App;