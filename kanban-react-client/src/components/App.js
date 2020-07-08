/*	
 * 	Parent Component, that uses react router to handle navigation around the app
 */

import React from "react";
import {Router, Route, Switch} from "react-router-dom";

import history from "../history";
import Landing from "./layout/Landing";
import ProjectDashboard from "./projects/ProjectDashboard";
import ProjectCreate from "./projects/ProjectCreate";
import Menu from "./layout/Menu";

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
					</Switch>
				</div>
			</Router>
		</div>
	);
}

export default App;