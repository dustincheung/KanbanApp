/*	
 * 	Parent Component, that uses react router to handle navigation around the app
 */

import React from "react";
import {Router, Route, Switch} from "react-router-dom";

import history from "../history";
import Menu from "./layout/Menu";
import Landing from "./layout/Landing";
import RegisterForm from "./users/RegisterForm";
import LoginForm from "./users/LoginForm";
import ProjectDashboard from "./projects/ProjectDashboard";
import ProjectCreate from "./projects/ProjectCreate";
import ProjectEdit from "./projects/ProjectEdit";
import TaskBoard from "./tasks/TaskBoard";
import TaskCreate from "./tasks/TaskCreate";
import TaskEdit from "./tasks/TaskEdit";
import SecureRouting from "../utils/SecureRouting"; //require user auth to access

const App = () => {
	return(
		<div>
			<Router history={history}>
				<Menu/>
					<Switch>
						<Route path="/" exact component={Landing}/> 
						<div className="ui container" style={{width: "70%", padding: "1%"}}>
							<Route path="/users/register" exact component={RegisterForm}/>
							<Route path="/users/login" exact component={LoginForm}/>
							{
								//Private Routes that require user authentication to access
							}
							<SecureRouting path="/projects" exact component={ProjectDashboard}/>
							<SecureRouting path="/projects/new" exact component={ProjectCreate}/>
							<SecureRouting path="/projects/:projTag/edit" exact component={ProjectEdit}/>
							<SecureRouting path="/projects/:projTag/tasks" exact component={TaskBoard}/>
							<SecureRouting path="/projects/:projTag/tasks/new" exact component={TaskCreate}/>
							<SecureRouting path="/projects/:projTag/tasks/:taskTag/edit" exact component={TaskEdit}/>
						</div>
					</Switch>
			</Router>
		</div>
	);
}

export default App;