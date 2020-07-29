/*
 *	This file holds all the action creators that will be called by components.  Action creators will dispatch an action
 *  that will make changes to the state by modifying the redux store. Redux-thunk middleware allows us to return functions 
 *  with dispatch and getState params, which is essential for async network requests to backend express server or API requests
 */

import axios from "axios";
import history from "../history";
import jwt_decode from "jwt-decode";

import {setTokenInHeader} from "../utils/SecurityFunctions";

//************************************************
//			PROJECTS ACTION CREATORS
//************************************************

export const getProjects = () => {
	return async (dispatch, getState) => {
		const response = await axios.get("/projects");

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
				endDate: formValues.endDate
			}

			await axios.post("/projects", project);
			history.push("/projects");
			dispatch({type: "CLEAR_ERRORS", payload: null});
		}catch(err){
			dispatch({type: "INDEX_ERRORS", payload: err.response.data});
		}
	}
}

export const deleteProject = (projTag) => {
	return async (dispatch) => {
		if(window.confirm("Deleting a project will also delete all data associated with it.  Are you sure you want to delete?")){
			await axios.delete("/projects/" + projTag + "/delete");
			dispatch({type: "DELETE_PROJECT", payload: projTag});
		}
	}
}

//************************************************
//			PROJECT ACTION CREATORS
//************************************************
export const getProject = (projTag) => {
	return async (dispatch) => {
		try{
			const response = await axios.get("/projects/" + projTag);
			dispatch({type: "GET_PROJECT", payload: response.data});
			dispatch({type: "CLEAR_ERRORS", payload: null});
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
			await axios.put("/projects/" + projTag, project);
			history.push("/projects");

			dispatch({type: "CLEAR_PROJECT", payload: null});
			dispatch({type: "CLEAR_ERRORS", payload: null});
		}catch(err){
			dispatch({type: "INDEX_ERRORS", payload: err.response.data});
		}
	}
}

export const clearProject = () => {
	return({type: "CLEAR_PROJECT", payload: null});
}

//************************************************
//			TASKS ACTION CREATORS
//************************************************

export const getTasks = (projTag) => {
	return async (dispatch) => {
		const response = await axios.get("/projects/" + projTag + "/tasks");
		
		dispatch({type: "INDEX_TASKS", payload: response.data});
	}
}

export const createTask = (formValues, projTag) => {
	return async (dispatch) => {
		try{
			const task = {
				taskTitle: formValues.taskTitle,
				taskDescription: formValues.taskDescription,
				acceptCriteria: formValues.acceptCriteria,
				dueDate: formValues.dueDate,
				priority: formValues.priority,
				status: formValues.status
			}

			await axios.post("/projects/" + projTag + "/tasks", task);
			history.push("/projects/" + projTag + "/tasks");
			dispatch({type: "CLEAR_ERRORS", payload: null});
		}catch(err){
			dispatch({type: "INDEX_ERRORS", payload: err.response.data});
		}
	}
}

export const deleteTask = (projTag, taskTag) => {
	return async (dispatch) => {
		if(window.confirm("Deleting a task will also delete all data associated with it.  Are you sure you want to delete?")){
			await axios.delete("/projects/" + projTag + "/tasks/" + taskTag + "/delete");
			dispatch({type: "DELETE_TASK", payload: taskTag});
		}
	}
}

//************************************************
//			TASK ACTION CREATORS
//************************************************

export const getTask = (projTag, taskTag) => {
	return async (dispatch) => {
		try{
			const response = await axios.get("/projects/" + projTag + "/tasks/" + taskTag);
			dispatch({type: "GET_TASK", payload: response.data});
			dispatch({type: "CLEAR_ERRORS", payload: null});
		}catch(err){
			dispatch({type: "INDEX_ERRORS", payload: err.response.data});
		}
	}
}

export const updateTask = (projTag, taskTag, formValues) => {
	return async (dispatch, getState) => {
		try{
			//we want to use the same project (task id and taskTag) but update the fields that were edited
			const task = {
				...getState().task,
				taskTitle: formValues.taskTitle, 
				dueDate: formValues.dueDate, 
				taskDescription: formValues.taskDescription, 
				acceptCriteria: formValues.acceptCriteria,
				priority: formValues.priority,
				status: formValues.status
			}

			//b/c our task has same primary key, mysql db will know to save over the task with same primary key
			//otherwise we would have to find and replace task using taskTag in backend
			await axios.put("/projects/" + projTag + "/tasks/" + taskTag, task);
			history.push("/projects/" + projTag + "/tasks");

			dispatch({type: "CLEAR_TASK", payload: null});
			dispatch({type: "CLEAR_ERRORS", payload: null});
		}catch(err){
			dispatch({type: "INDEX_ERRORS", payload: err.response.data});
		}
	}
}

export const clearTask = () => {
	return({type: "CLEAR_TASK", payload: null});
}

//************************************************
//			USER ACTION CREATORS
//************************************************

export const createUser = (formValues) => {
	return async (dispatch) => {

		//create user obj from formValues
		const user = {
			name: formValues.name,
			username: formValues.username,
			password: formValues.password,
			confirmPassword: formValues.confirmPassword
		}

		try{
			await axios.post("/users/register", user);
			dispatch({type: "CLEAR_ERRORS", payload: null});
			history.push("/users/login")
		}catch(err){
			dispatch({type: "INDEX_ERRORS", payload: err.response.data});
		}
	}
}

export const loginUser = (formValues) => {
	return async (dispatch) => {
		
		const loginRequest = {
			username: formValues.username,
			password: formValues.password
		}

		try{
			//post request w/ credentials to login
			const response = await axios.post("/users/login", loginRequest);
			
			//if successful, extract token from response
			const token = response.data["token"];

			//store token in localStorage
			localStorage.setItem("token", token);

			//set token in header for all requests
			setTokenInHeader(token);  
			
			//extract user info from jwt
			const currUserInfo = jwt_decode(token);

			dispatch({type: "SET_USER", payload: currUserInfo});
			dispatch({type: "CLEAR_ERRORS", payload: null});
			history.push("/projects");
		}catch(err){
			dispatch({type: "INDEX_ERRORS", payload: err.response.data});
		}
	}
}

export const logoutUser = () => {
	//clear out token from local storage
	localStorage.removeItem("token");

	//clear out request header
	setTokenInHeader(false);

	//redirect to landing page
	history.push("/");

	//clear out user state
	return({type: "SET_USER", payload: null});
}

//************************************************
//			ERRORS ACTION CREATORS
//************************************************
export const clearErrors = () => {
	return({type: "CLEAR_ERRORS", payload: null});
}