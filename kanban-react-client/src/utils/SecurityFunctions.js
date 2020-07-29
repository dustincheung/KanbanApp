/*	
 * 	This file contains security util functions
 */

import axios from "axios";

//sets authorization header for all axios requests to the token
export const setTokenInHeader = (token) => {
	if(token){
		axios.defaults.headers.common["Authorization"] = token;
	}
};