/*	
 * 	This file manages/modifies errors state based on action recieved
 */

 export default (state = null, action) => {
 	switch(action.type){
 		case "INDEX_ERRORS":
 			return action.payload; 
 		default:
 			return state;
 	}
 }