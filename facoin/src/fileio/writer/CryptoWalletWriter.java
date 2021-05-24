package fileio.writer;

import exception.FileWriteException;
import fileio.parser.JSONParser;
import model.Wallet;
import storage.IContainer;

public class CryptoWalletWriter {

	JSONParser jsonParser;

	protected CryptoWalletWriter() {
		jsonParser = new JSONParser();
	}

	/**
	 * The function returns the file content of crypto wallet container
	 * 
	 * @param cryptoWallets = crypto wallet container
	 * @return String of content
	 * @throws FileWriteException
	 */
	protected String writeCryptoWalletsJson(IContainer<Wallet> cryptoWallets) throws FileWriteException {
		String result = "{";
		for (Wallet crytoWallet : cryptoWallets) {
			result += convertCryptoWalletToJsonString(crytoWallet);
		}
		if (result.endsWith(",")) { // if ends with , ignore it
			result = result.substring(0, result.length() - 1);
		}
		result += "}";
		result = JsonWriteHelper.validateJsonObject(result);
		return result;
	}

	private String convertCryptoWalletToJsonString(Wallet cryptoWallet) {
		return "\"" + cryptoWallet.getId() + "\":"
				+ WalletEntityWriter.writeWalletEntitiesToJsonArray("coins", cryptoWallet.getEntities()) + ",";
	}
}
