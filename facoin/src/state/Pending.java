package state;

public class Pending extends State {
	/**
	 * The function updates the state of given transaction type object as Approved
	 * state object.
	 * 
	 * @param state transaction type object
	 */
	@Override
	public void nextState(TransactionType state) {
		state.setState(new Approved());
	}

	/**
	 * The function returns the state name
	 * 
	 * @return String state name
	 */
	@Override
	public String getState() {
		return "Pending";
	}
}
