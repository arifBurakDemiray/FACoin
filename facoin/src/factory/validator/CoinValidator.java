package factory.validator;

import enums.ECoins;
import fileio.repository.CoinRepository;
import fileio.repository.DatabaseResult;
import fileio.repository.IRestrictedRepository;
import model.Currency;

public class CoinValidator {

	/**
	 * The function validates coin that is wanted to be created
	 * 
	 * @param currencyName = coin name
	 * @param currencyId   = coin id
	 * @return ValidationResult
	 */
	public static ValidationResult validatCoin(String currencyName, String currencyId) {

		// Validate name
		ValidationResult nameResult = validateCurrencyName(currencyName);

		// Validate id
		ValidationResult idResult = validateCurrencyId(currencyId);

		boolean isValid = nameResult.isValid && idResult.isValid;

		if (isValid) {
			// If name and id are valid, return true validation
			return new ValidationResult(true, "Validated.");
		}

		// Else return false validation that means coin with these params are not
		// valid.
		return new ValidationResult(nameResult.messages + " - " + idResult.messages);
	}

	/**
	 * The function tries to validate coin name
	 * 
	 * @param currencyName = coin name
	 * @return ValidationResult
	 */
	private static ValidationResult validateCurrencyName(String currencyName) {
		ValidationResult validationResult = new ValidationResult("Given currency is not a coin");

		// Control that gotten coin name exists or not in the system ( is in the
		// Coin enums )
		boolean isCoin = ECoins.isCoin(currencyName);
		if (isCoin) {
			// return true validation
			validationResult = new ValidationResult(true, "Name is validated.");
		}
		// Else return false validation that means coin name is not valid
		return validationResult;
	}

	/**
	 * The function tries to validate coin id
	 * 
	 * @param currencyId = coin id
	 * @return ValidationResult
	 */
	public static ValidationResult validateCurrencyId(String currencyId) {
		// If coin doesn't exist in the system coin repository, return true
		// If coin is created before, return true
		IRestrictedRepository<Currency> currencyRepository = new CoinRepository();
		DatabaseResult currencyIdResult = currencyRepository.getById(currencyId);
		if (currencyIdResult.getObject() == null) {
			return new ValidationResult(true, "Validated");
		}

		return new ValidationResult(false, "Id is not validated.");
	}

}
