package state;

public abstract class State {

	/**
	 * The function updates the state of given transaction type as next state
	 * 
	 * @param state transaction type object
	 */
	public abstract void nextState(TransactionType state);

	/**
	 * The function returns the state.
	 * 
	 * @return string state name
	 */
	public abstract String getState();
}
