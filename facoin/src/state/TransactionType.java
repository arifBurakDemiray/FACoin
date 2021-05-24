package state;

public class TransactionType {
	private State state; // one of state objects Pending,Approved

	/**
	 * The constructor for Transaction Type object.
	 * 
	 * @param state = one of state objects Pending,Approved
	 */
	public TransactionType(State state) {
		this.state = state;
	}

	/**
	 * The default constructor for Transaction Type object whose state defined as
	 * Pending
	 */
	public TransactionType() {
		this.state = new Pending();
	}

	/**
	 * The function updates the state as next state.
	 */
	public void Approve() {
		this.state.nextState(this);
	}

	public String getState() {
		return this.state.getState();
	}

	protected void setState(State state) {
		this.state = state;
	}
}