/*	
 * 	This file imports all of our reducers and combines them.  The states in the 
 *  combineReducers call are the states that will exist in our redux store.
 */

import {combineReducers} from "redux";
import {reducer as formReducer} from "redux-form";

import projectsReducer from "./projectsReducer";
import projectReducer from "./projectReducer";
import errorsReducer from "./errorsReducer";

export default combineReducers({
 projects: projectsReducer,
 project: projectReducer,
 errors: errorsReducer,
 form: formReducer				//formReducer is an already built in reducer that is imported from redux-form
});