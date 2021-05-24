package enums;

/**
 * This enum holds types of the transactions
 */
public enum ETransaction {
	Approved, Pending;

	/**
	 * This function checks wheter a transaction is in system's enums or not
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isType(String value) {
		try {
			ETransaction.valueOf(value); // if converted to enum return true
			return true; // other wise return false
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
}