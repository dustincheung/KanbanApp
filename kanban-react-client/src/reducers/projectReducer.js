/*	
 * 	This file manages/modifies curr project state based on action recieved
 */

 export default (state = null, action) => {
 	switch(action.type){
 		case "GET_PROJECT":
 			return action.payload;
 		default:
 			return state;
 	}
 }