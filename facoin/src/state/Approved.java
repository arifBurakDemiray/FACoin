package state;
public class Approved extends State {
	
	@Override
	public void nextState(TransactionType state) {
	}

	/**
	 * The function returns the state name
	 * 
	 * @return String state name
	 */
	@Override
	public String getState() {
		return "Approved";
	}
}
