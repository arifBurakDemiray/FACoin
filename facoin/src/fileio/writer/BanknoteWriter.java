package fileio.writer;

import exception.FileWriteException;
import model.Currency;
import storage.IContainer;

public class BanknoteWriter {

	protected BanknoteWriter() {
	}

	/**
	 * The function returns the file content of banknote container 
	 * 
	 * @param banknotes = banknote container
	 * @return String of content
	 * @throws FileWriteException
	 */
	protected String writeBanknotesJson(IContainer<Currency> banknotes) throws FileWriteException {
		String result = "{";
		for (Currency banknote : banknotes) {
			result += convertBanknoteToJsonString(banknote);
		}
		if (result.endsWith(",")) { // if ends with , ignore it
			result = result.substring(0, result.length() - 1);
		}
		result += "}";
		result = JsonWriteHelper.validateJsonObject(result);
		return result;
	}

	private String convertBanknoteToJsonString(Currency banknote) {

		return "\"" + banknote.getId() + "\":\"" + banknote.getName() + "\",";
	}
}
