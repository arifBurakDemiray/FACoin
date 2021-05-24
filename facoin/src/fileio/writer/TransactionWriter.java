package fileio.writer;

import exception.FileWriteException;
import fileio.parser.JSONParser;
import model.Transaction;
import storage.IContainer;

public class TransactionWriter {

	JSONParser jsonParser;

	protected TransactionWriter() {
		jsonParser = new JSONParser();
	}

	/**
	 * The function returns the file content of transaction container
	 * 
	 * @param transactions = transaction container
	 * @return String of content
	 * @throws FileWriteException
	 */
	protected String writeTransactionsJson(IContainer<Transaction> transactions) throws FileWriteException {
		String result = "{";
		for (Transaction transaction : transactions) {
			result += convertTransactionToJsonString(transaction) + ",";
		}
		if (result.endsWith(",")) { // if ends with , ignore it
			result = result.substring(0, result.length() - 1);
		}
		result += "}";
		result = JsonWriteHelper.validateJsonObject(result);
		return result;
	}

	private String convertTransactionToJsonString(Transaction transaction) {
		return "\"" + transaction.getId() + "\":{" + "\"pair\":\"" + transaction.getCoin().getId() + "-"
				+ transaction.getBanknote().getId() + "\"," + "\"coinQ\":\"" + transaction.getCoinQuantity() + "\","
				+ "\"coinValue\":\"" + transaction.getCoinValue() + "\"," + "\"type\":\""
				+ transaction.getTransactionState() + "\"}";
	}
}
