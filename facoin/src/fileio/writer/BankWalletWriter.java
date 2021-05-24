package fileio.writer;

import exception.FileWriteException;
import model.Wallet;
import storage.IContainer;

public class BankWalletWriter {

	protected BankWalletWriter() {

	}

	/**
	 * The function returns the file content of bank wallet container 
	 * 
	 * @param bankWallets = bank wallet container
	 * @return String of content
	 * @throws FileWriteException
	 */
	protected String writeBankWalletsJson(IContainer<Wallet> bankWallets) throws FileWriteException {
		String result = "{";
		for (Wallet bankWallet : bankWallets) {
			result += convertBankWalletToJsonString(bankWallet);
		}
		if (result.endsWith(",")) { // if ends with , ignore it
			result = result.substring(0, result.length() - 1);
		}
		result += "}";
		result = JsonWriteHelper.validateJsonObject(result);
		return result;
	}

	private String convertBankWalletToJsonString(Wallet cryptoWallet) {
		return "\"" + cryptoWallet.getId() + "\":"
				+ WalletEntityWriter.writeWalletEntitiesToJsonArray("banknotes", cryptoWallet.getEntities()) + ",";
	}
}
