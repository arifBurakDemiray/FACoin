package fileio.parser;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import exception.FileReadException;
import factory.CryptoWalletFactory;
import factory.WalletFactory;
import fileio.repository.CryptoWalletRepository;
import fileio.repository.IRepository;
import model.CryptoWallet;

public class CryptoWalletParser {

	private WalletFactory cryptoWalletFactory;

	/**
	 * The WalletParser parses the gotten crypto_wallets.json file content and
	 * creates wallet objects
	 */
	protected CryptoWalletParser() {
		cryptoWalletFactory = new CryptoWalletFactory();
	}

	/**
	 * The function parses gotten file content and creates crypto wallet objects And
	 * add them to the crypto wallet repository of system
	 * 
	 * @param fileAll = crypto_wallets.json file content
	 * @throws FileReadException
	 * @throws JSONException
	 */
	protected void parseWallets(String fileAll) throws FileReadException, JSONException {
		IRepository<CryptoWallet> cryptoWalletRepository = new CryptoWalletRepository();
		JSONObject jsonContracts;
		jsonContracts = (new JSONParser()).parse(fileAll); // get json object of file content
		parse(jsonContracts, cryptoWalletRepository); // parse wallets

	}

	private void parse(JSONObject jsonObject, IRepository<CryptoWallet> cryptoWalletRepository)
			throws JSONException, FileReadException {

		// iterate json object
		Iterator<?> keys = jsonObject.keys();
		Object val = null;
		while (keys.hasNext()) {
			Object keyTemp = keys.next();
			if (!(keyTemp instanceof String))
				throw new JSONException("CryptoWalletParser.parse::Key is not a string");
			// get the coin values and invoke createWallet()
			// to get created wallet
			String key = (String) keyTemp; // wallet id
			val = jsonObject.get(key); // array of wallet entities
			if (val instanceof JSONObject) {
				JSONArray coins = (JSONArray) ((JSONObject) val).get("coins");

				Dictionary<String, String> coinDictionary = new Hashtable<String, String>();

				// Get each coin entity and add to the coin dictionary of wallet
				for (int i = 0; i < coins.length(); i++) {
					JSONObject coinJson = (JSONObject) coins.get(i); // get entity
					String coinId = coinJson.getString("id"); // get coin id
					String quantity = coinJson.getString("quantity"); // get coin quantity
					coinDictionary.put(coinId, quantity); // put them to the coin dictionary
				}
				CryptoWallet wallet = createWallet(key, coinDictionary); // create a new wallet
				cryptoWalletRepository.addEntity(wallet); // add wallet to the cryptoWalletRepository
			}
		}
	}

	private CryptoWallet createWallet(String walletId, Dictionary<String, String> coins) throws FileReadException {
		// Create a new wallet with given id and coin entities
		CryptoWallet createdWallet = (CryptoWallet) cryptoWalletFactory.createWallet(walletId, coins);
		if (createdWallet != null) {
			return createdWallet;
		} else {
			throw new FileReadException("Wrong Format for crypto_wallet.json");
		}
	}

}
