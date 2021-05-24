package fileio.writer;

import storage.IContainer;
import exception.FileWriteException;
import model.Transaction;

public class UserTransactionsWriter {

	/**
	 * The function returns the file content of user's transaction container
	 * 
	 * @param transactions = user transaction container
	 * @return String of content
	 * @throws FileWriteException
	 */
	protected static String writeUserTransactionsXml(IContainer<Transaction> transactions) {
		String result = "<transactions>";
		for (Transaction transaction : transactions) {
			result += transaction.getId() + ",";
		}
		if (result.endsWith(",")) { // if ends with , ignore it
			result = result.substring(0, result.length() - 1);
		}
		result += "</transactions>";
		return result;
	}

}