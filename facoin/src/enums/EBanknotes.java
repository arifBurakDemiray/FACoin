package enums;

/**
 * This enum holds banknote values of the system, if new banknote type will be
 * added it also should be added here
 */
public enum EBanknotes {
	USD, TRY;

	public static boolean isBanknote(String value) {
		try {
			EBanknotes.valueOf(value);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
}
