package factory.validator;

public class WalletEntityValidator {

	/**
	 * The function tries to validate wallet entity with given params The gotten id
	 * string must be integer and quantity must be double
	 * 
	 * @param id       = entity id
	 * @param quantity = quantity
	 * @return ValidationResult
	 */
	public static ValidationResult validateWalletEntity(String id, String quantity) {
		boolean isInteger = Integer.valueOf(id) instanceof Integer;
		boolean isDouble = Double.valueOf(quantity) instanceof Double;
		boolean result = isInteger && isDouble;
		return new ValidationResult(result, "Illegal number format");
	}
}