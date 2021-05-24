package factory;

import factory.validator.TransactionValidator;
import factory.validator.ValidationResult;
import fileio.repository.BanknoteRepository;
import fileio.repository.CoinRepository;
import fileio.repository.DatabaseResult;
import model.Currency;
import model.Transaction;
import state.*;
import state.TransactionType;

public class TransactionFactory {

	/**
	 * The function creates transaction object according to given params
	 * 
	 * @param transactionId
	 * @param pair            = trade pair like BTC-USD
	 * @param coinQuantity    = coin order quantity like 1 BTC
	 * @param coinOrderValue  = coin order value like 35000 to buy/sell coin
	 * @param transactionType = approved or pending transaction
	 * @return Transaction object
	 */
	public Transaction createTransaction(String transactionId, String pair, String coinQuantity, String coinOrderValue,
			String transactionType) {

		String[] pairNames = pair.split("-"); // split currency names ( example BTC-USD -> BTC USD )

		// Validate currencies' name
		ValidationResult pairResult = TransactionValidator.validatePair(pairNames[0], pairNames[1]);

		// Validate coin order quantity and coin order value
		ValidationResult quantityResult = TransactionValidator.validateQuantites(coinQuantity, coinOrderValue);

		// Validate type
		ValidationResult typeResult = TransactionValidator.validateType(transactionType);

		boolean isValidated = pairResult.isValid && quantityResult.isValid && typeResult.isValid;

		// If params include invalid param, return null
		if (!isValidated) {
			return null;
		}

		// Validate id is unique or not
		ValidationResult idResult = TransactionValidator.validateTransactionId(transactionId);
		String id = transactionId;

		// If id is not unique, get the new unique id
		if (!idResult.isValid)
			id = idResult.messages;

		// Get the coin object from coin repository ( like BTC object )
		DatabaseResult gottenCoin = (new CoinRepository()).getById(pairNames[0]);

		// Get the banknote object from banknote repository ( like USD object )
		DatabaseResult gottenBanknote = (new BanknoteRepository()).getById(pairNames[1]);

		// Create state for transaction type
		State state = new Pending();
		if (transactionType.equals("Approved")) {
			state = new Approved();
		}
		TransactionType type = new TransactionType(state);

		// Create transaction
		Transaction transaction = new Transaction(id, (Currency) gottenCoin.getObject(),
				(Currency) gottenBanknote.getObject(), type, Double.valueOf(coinQuantity),
				Double.valueOf(coinOrderValue));

		return transaction;
	}

}