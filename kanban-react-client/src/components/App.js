/*	
 * 	Parent Component, that uses react router to handle navigation around the app
 */

import React from "react";
import {Router, Route, Switch} from "react-router-dom";

import history from "../history";
import ProjectDashboard from "./projects/ProjectDashboard";
import Menu from "./layout/Menu";

const App = () => {
	return(
		<div className="ui container" style={{width: "75%", padding: "1%"}}>
			<Router history={history}>
				<div>
					<Menu/>
					<Switch>
						<Route path="/projects" exact component={ProjectDashboard}/>
					</Switch>
				</div>
			</Router>
		</div>
	);
}

export default App;