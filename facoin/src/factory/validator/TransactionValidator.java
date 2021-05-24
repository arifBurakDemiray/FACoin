package factory.validator;

import enums.ETransaction;
import factory.RandomFactory;
import fileio.repository.BanknoteRepository;
import fileio.repository.CoinRepository;
import fileio.repository.DatabaseResult;
import fileio.repository.IRestrictedRepository;
import fileio.repository.TransactionRepository;
import model.Currency;
import model.Transaction;

public class TransactionValidator {

	/**
	 * The function tries to validate transaction's pair are valid Pair is like that
	 * 1-1 = CoinId-BanknoteId
	 * 
	 * @param coinId
	 * @param banknoteId
	 * @return ValidationResult
	 */
	public static ValidationResult validatePair(String coinId, String banknoteId) {

		// Validate coin, coin with given id must be existing
		ValidationResult coinResult = validateCoin(coinId);

		// Validate banknote, banknote with given id must be existing
		ValidationResult baknoteResult = validateBanknote(banknoteId);

		boolean areValidationsOK = coinResult.isValid && baknoteResult.isValid;

		ValidationResult validationResult = new ValidationResult("Invalid pair id.");

		// If ids are valid, return true. Else return false validation result
		if (areValidationsOK) {
			validationResult = new ValidationResult(true, "Validated.");
			return validationResult;
		}

		return validationResult;
	}

	/**
	 * The function tries to validate transaction's type Type must be Approved or
	 * Pending
	 * 
	 * @param transactionType
	 * @return ValidationResult
	 */
	public static ValidationResult validateType(String transactionType) {
		ValidationResult validationResult = new ValidationResult("Invalid type.");

		// If gotten type is in the Transaction Type enums, return true validation
		// Else return false validation because type is not valid.
		if (ETransaction.isType(transactionType)) {
			validationResult = new ValidationResult(true, "Validated.");
			return validationResult;
		}

		return validationResult;
	}

	/**
	 * The function tries to validate transaction's values are double or not If
	 * gotten strings are not double value, return false validation
	 * 
	 * @param coinQuantity
	 * @param coinOrderValue
	 * @return ValidationResult
	 */
	public static ValidationResult validateQuantites(String coinQuantity, String coinOrderValue) {
		ValidationResult validationResult = new ValidationResult("Invalid quantity.");
		try {
			Double.valueOf(coinQuantity);
			Double.valueOf(coinOrderValue);
			validationResult = new ValidationResult(true, "Validated.");
			return validationResult;
		} catch (Exception e) {
		}
		return validationResult;
	}

	/**
	 * The function tries to validate transaction's coin id
	 * 
	 * @param coinId
	 * @return ValidationResult
	 */
	private static ValidationResult validateCoin(String coinId) {
		ValidationResult validationResult = new ValidationResult("Coin is not found in the CoinRepository");

		// If there is a coin with given id in the system's coin repository
		// return true validation, else return false validation
		IRestrictedRepository<Currency> coinRepository = new CoinRepository();

		DatabaseResult result = coinRepository.getById(coinId);
		if (result.getObject() != null) {
			validationResult = new ValidationResult(true, "");
		}
		return validationResult;
	}

	/**
	 * The function tries to validate transaction's banknote id
	 * 
	 * @param banknoteId
	 * @return ValidationResult
	 */
	private static ValidationResult validateBanknote(String banknoteId) {
		ValidationResult validationResult = new ValidationResult("Banknote is not found in the BanknoteRepository");

		// If there is a banknote with given id in the system's banknote repository
		// return true validation, else return false validation
		IRestrictedRepository<Currency> banknoteRepository = new BanknoteRepository();

		DatabaseResult result = banknoteRepository.getById(banknoteId);
		if (result.getObject() != null) {
			validationResult = new ValidationResult(true, "");
		}
		return validationResult;
	}

	/**
	 * The function tries to validate transaction's id
	 * 
	 * @param transactionId
	 * @return ValidationResult
	 */
	public static ValidationResult validateTransactionId(String transactionId) {
		if (transactionId == null) {
			return new ValidationResult(false, "Invalidated");
		}

		// If there is a transaction with given id in the system's transaction
		// repository
		// create a new unique id for transaction and return created new unique id in
		// the validation result

		// If there is no transaction with given id in the system's transaction
		// repository return true validation
		IRestrictedRepository<Transaction> transactionRepository = new TransactionRepository();
		DatabaseResult transactionIdResult = transactionRepository.getById(transactionId);
		if (transactionIdResult.getObject() == null) {
			return new ValidationResult(true, "Validated");
		}
		String new_id = RandomFactory.randomId();
		while (transactionRepository.getById(new_id).getObject() != null) {
			new_id = RandomFactory.randomId();
		}
		return new ValidationResult(new_id);
	}

}
