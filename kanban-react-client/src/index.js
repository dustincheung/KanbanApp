/*	
 * 	This file renders our parent App component and creates our redux store using the combined reducers and connects
 *  the redux store to our react app. Also setups up redux devtools and applies redux thunk as a middleware.
 */

import React from "react";
import ReactDOM from "react-dom";
import {Provider} from "react-redux"; //allows react to interact with redux
import {createStore, applyMiddleware, compose} from "redux";
import thunk from "redux-thunk";

import App from "./components/App";
import reducers from "./reducers";

// enabling redux devtools
const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

//creating redux store
const store = createStore(reducers, composeEnhancers(applyMiddleware(thunk)));

ReactDOM.render(
	<Provider store={store}>
		<App/>
	</Provider>, 
	document.querySelector("#root")
);
