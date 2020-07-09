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

export const updateProject = (projTag, formValues) => {
	return async (dispatch, getState) => {
		try{

			//we want to use the same project (primary id and projTag) but update the fields that were edited
			const project = {
				...getState().project,
				projTitle: formValues.projTitle,
				description: formValues.description,
				startDate: formValues.startDate,
				endDate: formValues.endDate
			}

			//b/c our proj has same primary key, mysql db will know to save over the proj with same primary key
			//otherwise we would have to find and replace project using projTag in backend
			const response = await axios.put(uri + "/projects/" + projTag, project);
			history.push("/projects");
		}catch(err){
			dispatch({type: "INDEX_ERRORS", payload: err.response.data});
		}
	}
}

//************************************************
//			PROJECT ACTION CREATORS
//************************************************

export const getProject = (projTag) => {
	return async (dispatch) => {
		try{
			const response = await axios.get(uri + "/projects/" + projTag);
			dispatch({type: "GET_PROJECT", payload: response.data});
		}catch(err){
			dispatch({type: "INDEX_ERRORS", payload: err.response.data});
		}
	}
}
