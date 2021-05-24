package fileio.parser;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import exception.FileReadException;
import factory.BankWalletFactory;
import factory.WalletFactory;
import fileio.repository.BankWalletRepository;
import fileio.repository.IRepository;
import model.BankWallet;

public class BankWalletParser {

	private WalletFactory bankWalletFactory;

	/**
	 * The WalletParser parses the gotten bank_wallets.json file content and creates
	 * wallet objects
	 */
	protected BankWalletParser() {
		bankWalletFactory = new BankWalletFactory();
	}

	/**
	 * The function parses gotten file content and creates bank wallet objects And
	 * add them to the bank wallet repository of system
	 * 
	 * @param fileAll = bank_wallets.json file content
	 * @throws FileReadException
	 * @throws JSONException
	 */
	protected void parseWallets(String fileAll) throws FileReadException, JSONException {
		IRepository<BankWallet> bankWalletRepository = new BankWalletRepository();
		JSONObject jsonContracts;
		jsonContracts = (new JSONParser()).parse(fileAll); // get json object of file content
		parse(jsonContracts, bankWalletRepository); // parse wallets

	}

	private void parse(JSONObject jsonObject, IRepository<BankWallet> bankWalletRepository)
			throws JSONException, FileReadException {

		// iterate json object
		Iterator<?> keys = jsonObject.keys();
		Object val = null;
		while (keys.hasNext()) {
			Object keyTemp = keys.next();
			if (!(keyTemp instanceof String))
				throw new JSONException("BankWalletParser.parse::Key is not a string");
			// get the bank wallet values and invoke createWallet()
			// to get created wallet
			String key = (String) keyTemp; // wallet id
			val = jsonObject.get(key); // array of wallet entities

			if (val instanceof JSONObject) {

				JSONArray banknotes = (JSONArray) ((JSONObject) val).get("banknotes");
				Dictionary<String, String> banknoteDictionary = new Hashtable<String, String>();

				// Get each banknote entity and add to the banknote dictionary of wallet
				for (int i = 0; i < banknotes.length(); i++) {
					JSONObject banknoteJson = (JSONObject) banknotes.get(i); // get entity
					String banknoteId = banknoteJson.getString("id"); // get banknote id
					String quantity = banknoteJson.getString("quantity"); // get banknote quantity
					banknoteDictionary.put(banknoteId, quantity); // put them to the banknote dictionary
				}
				BankWallet wallet = createWallet(key, banknoteDictionary); // create a new wallet
				bankWalletRepository.addEntity(wallet); // add wallet to the bankWalletRepository
			}
		}
	}

	private BankWallet createWallet(String walletId, Dictionary<String, String> banknotes) throws FileReadException {

		// Create a new wallet with given id and banknote entities
		BankWallet createdWallet = (BankWallet) bankWalletFactory.createWallet(walletId, banknotes);
		if (createdWallet != null) {
			return createdWallet;
		} else {
			throw new FileReadException("Wrong Format for bank_wallets.json");
		}
	}

}
