import React from "react";

import ProjectDashboard from "./projects/ProjectDashboard";
import Menu from "./layout/Menu";

const App = () => {
	return(
		<div className="ui container" style={{width: "65%", padding: "1%"}}>
			<div>
				<Menu/>
				<ProjectDashboard/>
			</div>
		</div>
	);
}

export default App;