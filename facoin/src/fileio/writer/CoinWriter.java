package fileio.writer;

import exception.FileWriteException;
import model.Currency;
import storage.IContainer;

public class CoinWriter {

	protected CoinWriter() {
	}

	/**
	 * The function returns the file content of coin container
	 * 
	 * @param coins = coin container
	 * @return String of content
	 * @throws FileWriteException
	 */
	protected String writeCoinsJson(IContainer<Currency> coins) throws FileWriteException {
		String result = "{";
		for (Currency coin : coins) {
			result += convertCoinToJsonString(coin);
		}
		if (result.endsWith(",")) { // if ends with , ignore it
			result = result.substring(0, result.length() - 1);
		}
		result += "}";
		result = JsonWriteHelper.validateJsonObject(result);
		return result;
	}

	private String convertCoinToJsonString(Currency coin) {

		return "\"" + coin.getId() + "\":\"" + coin.getName() + "\",";
	}
}