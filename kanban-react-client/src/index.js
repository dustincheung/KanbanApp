/*	
 * 	This file renders our parent App component and creates our redux store using the combined reducers and connects
 *  the redux store to our react app. Also setups up redux devtools and applies redux thunk as a middleware. In this file
 *  it also persists user state if token in app's local storage is still not expired.
 */

import React from "react";
import ReactDOM from "react-dom";
import {Provider} from "react-redux"; //allows react to interact with redux
import {createStore, applyMiddleware, compose} from "redux";
import thunk from "redux-thunk";
import jwt_decode from "jwt-decode";

import App from "./components/App";
import reducers from "./reducers";
import {setTokenInHeader} from "./utils/SecurityFunctions";
import {logoutUser} from "./actions";

// enabling redux devtools
const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

//creating redux store
const store = createStore(reducers, composeEnhancers(applyMiddleware(thunk)));

//if there is still a valid token in local storage it will set the token in the header of req and 
//persist the user state, this allows users to refresh the page without losing their user state
const token = localStorage.token;

if(token){
	setTokenInHeader(token);
	const currUserInfo = jwt_decode(token);

	store.dispatch({type: "SET_USER", payload: currUserInfo});

	const currTime = Date.now() / 1000;

	if(currUserInfo.exp < currTime){
		//clear token in local storage and clear user state
		store.dispatch(logoutUser());
		window.location.href = "/";
	}
}

ReactDOM.render(
	<Provider store={store}>
		<App/>
	</Provider>, 
	document.querySelector("#root")
);