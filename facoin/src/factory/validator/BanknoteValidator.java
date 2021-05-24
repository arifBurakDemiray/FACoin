package factory.validator;

import enums.EBanknotes;
import fileio.repository.BanknoteRepository;
import fileio.repository.DatabaseResult;
import fileio.repository.IRestrictedRepository;
import model.Currency;

public class BanknoteValidator {

	/**
	 * The function validates banknote that is wanted to be created
	 * 
	 * @param currencyName = banknote name
	 * @param currencyId   = banknote id
	 * @return ValidationResult
	 */
	public static ValidationResult validateBanknote(String currencyName, String currencyId) {

		// Validate name
		ValidationResult nameResult = validateCurrencyName(currencyName);

		// Validate id
		ValidationResult idResult = validateCurrencyId(currencyId);

		boolean isValid = nameResult.isValid && idResult.isValid;

		if (isValid) {
			// If name and id are valid, return true validation
			return new ValidationResult(true, "Validated.");
		}

		// Else return false validation that means banknote with these params are not
		// valid.
		return new ValidationResult(nameResult.messages + " - " + idResult.messages);
	}

	/**
	 * The function tries to validate banknote name
	 * 
	 * @param currencyName = banknote name
	 * @return ValidationResult
	 */
	private static ValidationResult validateCurrencyName(String currencyName) {
		ValidationResult validationResult = new ValidationResult("Given currency is not a banknote");
		// Control that gotten banknote name exists or not in the system ( is in the
		// Banknote enums )
		boolean isBanknote = EBanknotes.isBanknote(currencyName);
		if (isBanknote) {
			// return true validation
			validationResult = new ValidationResult(true, "Name is validated.");
		}
		// Else return false validation that means banknote name is not valid
		return validationResult;
	}

	/**
	 * The function tries to validate banknote id
	 * 
	 * @param currencyId = banknote id
	 * @return ValidationResult
	 */
	public static ValidationResult validateCurrencyId(String currencyId) {
		// If banknote doesn't exist in the system banknote repository, return true
		// If banknote is created before, return true
		IRestrictedRepository<Currency> currencyRepository = new BanknoteRepository();
		DatabaseResult currencyIdResult = currencyRepository.getById(currencyId);
		if (currencyIdResult.getObject() == null) {
			return new ValidationResult(true, "Validated");
		}

		return new ValidationResult(false, "Id is not validated.");
	}

}
