/*	
 * 	This file manages/modifies errors state based on action recieved
 */

 export default (state = null, action) => {
 	switch(action.type){
 		case "INDEX_ERRORS":
 			return action.payload; 
 		case "CLEAR_ERRORS":
 			return null;
 		default:
 			return state;
 	}
 }