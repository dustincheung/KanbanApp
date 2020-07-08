/*	
 * 	This file manages/modifies projects state based on action recieved
 */

export default (state = [], action) => {
	switch(action.type){
		case "INDEX_PROJECTS":
			return action.payload;
		default:
			return state;
	}
}