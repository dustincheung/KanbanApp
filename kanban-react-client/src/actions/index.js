/*
 *	This file holds all the action creators that will be called by components.  Action creators will dispatch an action
 *  that will make changes to the state by modifying the redux store. Redux-thunk middleware allows us to return functions 
 *  with dispatch and getState params, which is essential for async network requests to backend express server or API requests
 */

 import axios from "axios";
 import history from "../history";

//prod and dev uri
const uri = process.env.REACT_APP_BACKEND_URI || "http://localhost:8080";

//************************************************
//			PROJECTS ACTION CREATORS
//************************************************

export const getProjects = () => {
	return async (dispatch, getState) => {
		const response = await axios.get(uri + "/projects");

		dispatch({type: "INDEX_PROJECTS", payload: response.data});
	}
}

export const createProject = (formValues) => {
	return async (dispatch) => {
		try{
			const project = {
				projTitle: formValues.projTitle,
				projTag: formValues.projTag,
				description: formValues.description,
				startDate: formValues.startDate,
				stopDate: formValues.stopDate
			}

			const response = await axios.post(uri + "/projects", project);
			history.push("/projects");
		}catch(err){
			dispatch({type: "INDEX_ERRORS", payload: err.response.data});
		}
	}
}