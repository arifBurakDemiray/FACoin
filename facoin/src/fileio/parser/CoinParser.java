package fileio.parser;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import exception.FileReadException;
import factory.AbstractFactory;
import factory.CoinFactory;
import factory.objects.CurrencyFactoryParams;
import fileio.repository.CoinRepository;
import fileio.repository.IRepository;
import model.Coin;
import model.Currency;

public class CoinParser {

	private AbstractFactory abstractFactory;

	/**
	 * The CoinParser parses the gotten coins.json file content and creates coin
	 * objects. After each creation adds them to the CoinRepository of system
	 */
	protected CoinParser() {
		abstractFactory = new AbstractFactory(new CoinFactory());
	}

	/**
	 * The function parses gotten file content and creates coin objects
	 * 
	 * @param fileAll = coins.json file content
	 * @throws FileReadException
	 * @throws JSONException
	 */
	protected void parseCurrencies(String fileAll) throws FileReadException, JSONException {
		IRepository<Currency> currencyRepository = new CoinRepository();
		JSONObject jsonCurrencies;
		jsonCurrencies = (new JSONParser()).parse(fileAll); // get json object of file content
		parse(jsonCurrencies, currencyRepository); // parse coins

	}

	private void parse(JSONObject jsonObject, IRepository<Currency> currencyRepository)
			throws JSONException, FileReadException {
		// iterate json object
		Iterator<?> keys = jsonObject.keys();
		Object val = null;
		while (keys.hasNext()) {
			Object keyTemp = keys.next();
			if (!(keyTemp instanceof String))
				throw new JSONException("CoinParser.parse::Key is not a string");
			// get the coin values and invoke createCurrency()
			// to get created coin
			String key = (String) keyTemp; // coin id
			val = jsonObject.get(key); // coin name
			Coin coin = createCurrency(key, (String) val); // created coin
			currencyRepository.addEntity(coin); // add coin to the coin repository
		}
	}

	private Coin createCurrency(String currencyId, String currencyName) throws FileReadException {
		// Create CurrencyFactoryParams with coin id and coin name
		CurrencyFactoryParams params = new CurrencyFactoryParams(currencyName, currencyId);

		// Create coin object and return it
		Coin createdCurrency = (Coin) abstractFactory.createEntity(params);
		if (createdCurrency != null) {
			return createdCurrency;
		} else {
			throw new FileReadException("Wrong Format for coins.json");
		}
	}

}
