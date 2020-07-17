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
import TaskBoard from "./tasks/TaskBoard";
import TaskCreate from "./tasks/TaskCreate";
import TaskEdit from "./tasks/TaskEdit";

const App = () => {
	return(
		<div>
			<Router history={history}>
				<Menu/>
				<div className="ui container" style={{width: "70%", padding: "1%"}}>
					<Switch>
						<Route path="/" exact component={Landing}/>
						<Route path="/projects" exact component={ProjectDashboard}/>
						<Route path="/projects/new" exact component={ProjectCreate}/>
						<Route path="/projects/:projTag/edit" exact component={ProjectEdit}/>
						<Route path="/projects/:projTag/tasks" exact component={TaskBoard}/>
						<Route path="/projects/:projTag/tasks/new" exact component={TaskCreate}/>
						<Route path="/projects/:projTag/tasks/:taskTag/edit" exact component={TaskEdit}/>
					</Switch>
				</div>
			</Router>
		</div>
	);
}

export default App;