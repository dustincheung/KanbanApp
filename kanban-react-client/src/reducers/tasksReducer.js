/*	
 * 	This file manages/modifies tasks state (tasks are technically from the backlog) based on action recieved
 */

export default (state = [], action) => {
	switch(action.type){
		case "INDEX_TASKS":
			return action.payload;
		case "DELETE_TASK":
			return state.filter(task => task.taskTag !== action.payload);
		default:
			return state;
	}
}