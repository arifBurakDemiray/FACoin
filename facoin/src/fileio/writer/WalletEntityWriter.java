package fileio.writer;

import exception.FileWriteException;
import model.Currency;
import model.WalletEntity;
import storage.IContainer;

public class WalletEntityWriter {

	/**
	 * The function returns the file content of given wallet entities container
	 * 
	 * @param entitynames
	 * @param entities    = given wallet entity container
	 * @return String of content
	 * @throws FileWriteException
	 */
	protected static String writeWalletEntitiesToJsonArray(String entityNames, IContainer<WalletEntity> entities) {
		String result = "{\"" + entityNames + "\": [";
		for (WalletEntity entity : entities) {
			result += writeWalletEntityToJsonObject(entity);
		}
		if (result.endsWith(",")) { // if ends with , ignore it
			result = result.substring(0, result.length() - 1);
		}
		result += "]}";
		return result;
	}

	private static String writeWalletEntityToJsonObject(WalletEntity entity) {
		Currency currency = (Currency) entity.getCurrency();
		String quantity = String.valueOf(entity.getQuantity());
		return "{\"id\":\"" + currency.getId() + "\",\"quantity\":\"" + quantity + "\"},";
	}
}
