/*	
 * 	This file manages/modifies tasks state (tasks are technically from the backlog) based on action recieved
 */

export default (state = [], action) => {
	switch(action.type){
		case "INDEX_TASKS":
			return action.payload;
		//added UPDATE TASK handler b/c taskCard component was not rerendering with latest tasks state data
		case "UPDATE_TASK":
			const index = state.findIndex(task => task.id === action.payload.id);
			
			return [
				...state.slice(0, index), action.payload, ...state.slice(index + 1)
			];
		case "DELETE_TASK":
			return state.filter(task => task.taskTag !== action.payload);
		default:
			return state;
	}
}