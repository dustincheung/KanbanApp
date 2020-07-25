/*	
 * 	This file manages/modifies current user state based on action recieved
 */

 export default (state = null, action) => {
 	switch(action.type){
 		case "SET_USER":
 			return action.payload; 
 		default:
 			return state;
 	}
 }