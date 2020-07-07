import React from "react";
import {Router, Route, Swithc} from "react-routger-dom";

import ProjectDashboard from "./projects/ProjectDashboard";
import Menu from "./layout/Menu";

const App = () => {
	return(
		<div className="ui container" style={{width: "75%", padding: "1%"}}>
			<div>
				<Menu/>
				<ProjectDashboard/>
			</div>
		</div>
	);
}

export default App;