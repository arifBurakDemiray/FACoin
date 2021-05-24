package fileio.parser;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import exception.FileReadException;
import factory.TransactionFactory;
import fileio.repository.IRepository;
import fileio.repository.TransactionRepository;
import model.Transaction;

public class TransactionParser {

	private TransactionFactory transactionFactory;

	/**
	 * The TransactionParser parses the gotten transactions.json file content and
	 * creates transaction objects
	 */
	protected TransactionParser() {
		transactionFactory = new TransactionFactory();
	}

	/**
	 * The function parses gotten file content. Add the created objects to the
	 * transaction repository of system
	 * 
	 * @param fileAll = transactions.json file content
	 * @throws FileReadException
	 * @throws JSONException
	 */
	protected void parseTransactions(String fileAll) throws FileReadException, JSONException {
		IRepository<Transaction> transactionRepository = new TransactionRepository();
		JSONObject jsonContracts;
		jsonContracts = (new JSONParser()).parse(fileAll); // get json object of file content
		parse(jsonContracts, transactionRepository); // parse transactions

	}

	private void parse(JSONObject jsonObject, IRepository<Transaction> transactionRepository)
			throws JSONException, FileReadException {

		// iterate json object
		Iterator<?> keys = jsonObject.keys();
		Object transaction = null;
		while (keys.hasNext()) {
			Object keyTemp = keys.next();
			if (!(keyTemp instanceof String))
				throw new JSONException("TransactionParser.parse::Key is not a string");
			// get the coin values and invoke createWallet()
			// to get created wallet
			String transactionId = (String) keyTemp; // transactionId
			transaction = jsonObject.get(transactionId); // transaction values
			if (transaction instanceof JSONObject) {
				String pair = ((JSONObject) transaction).getString("pair"); // pair of transaction
				String coinQuantity = ((JSONObject) transaction).getString("coinQ"); // coin quantity of transaction
				String coinOrderValue = ((JSONObject) transaction).getString("coinValue"); // coin value of transaction
				String transactionType = ((JSONObject) transaction).getString("type");// type of transaction

				// Create transaction
				Transaction createdTransaction = createTransaction(transactionId, pair, coinQuantity, coinOrderValue,
						transactionType);
				transactionRepository.addEntity(createdTransaction); // add created transaction to the
																		// transactionRepository
			}
		}
	}

	private Transaction createTransaction(String transactionId, String pair, String coinQuantity, String coinOrderValue,
			String transactionType) throws FileReadException {

		// Create transaction with given params and return created object
		Transaction createdTransaction = transactionFactory.createTransaction(transactionId, pair, coinQuantity,
				coinOrderValue, transactionType);

		if (createdTransaction != null) {
			return createdTransaction;
		} else {
			throw new FileReadException("Wrong Format for transactions.json");
		}
	}

}
