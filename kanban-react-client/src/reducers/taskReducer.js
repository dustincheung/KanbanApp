/*	
 * 	This file manages/modifies curr task state based on action recieved
 */

 export default (state = null, action) => {
 	switch(action.type){
 		case "GET_TASK":
 			return action.payload;
 		case "CLEAR_PROJECT":
 			return action.payload;
 		default:
 			return state;
 	}
 }