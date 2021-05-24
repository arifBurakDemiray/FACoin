package fileio.parser;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import exception.FileReadException;
import factory.AbstractFactory;
import factory.BanknoteFactory;
import factory.objects.CurrencyFactoryParams;
import fileio.repository.BanknoteRepository;
import fileio.repository.IRepository;
import model.Banknote;
import model.Currency;

public class BanknoteParser {

	private AbstractFactory abstractFactory;

	/**
	 * The BanknoteParser parses the gotten banknotes.json file content and creates
	 * banknote objects
	 */
	protected BanknoteParser() {
		abstractFactory = new AbstractFactory(new BanknoteFactory());
	}

	/**
	 * The function parses gotten file content creates banknote objects. Add the
	 * created objects to the banknote repository of system
	 * 
	 * @param fileAll = banknotes.json file content
	 * @throws FileReadException
	 * @throws JSONException
	 */
	protected void parseCurrencies(String fileAll) throws FileReadException, JSONException {
		IRepository<Currency> banknoteRepository = new BanknoteRepository();
		JSONObject jsonCurrencies;
		jsonCurrencies = (new JSONParser()).parse(fileAll); // get json object of file content
		parse(jsonCurrencies, banknoteRepository); // parse banknotes

	}

	private void parse(JSONObject jsonObject, IRepository<Currency> banknoteRepository)
			throws JSONException, FileReadException {
		// iterate json object
		Iterator<?> keys = jsonObject.keys();
		Object val = null;
		while (keys.hasNext()) {
			Object keyTemp = keys.next();
			if (!(keyTemp instanceof String))
				throw new JSONException("BanknoteParser.parse::Key is not a string");
			// get the banknote values and invoke createCurrency()
			// to get created banknotes
			String key = (String) keyTemp;  // banknote id
			val = jsonObject.get(key); // banknote name
			Banknote banknote = createCurrency(key, (String) val); // created banknote
			banknoteRepository.addEntity(banknote); // add banknote to the banknote repository
		}
	}

	private Banknote createCurrency(String currencyId, String currencyName) throws FileReadException {
		//Create CurrencyFactoryParams with banknote id and banknote name
		CurrencyFactoryParams params = new CurrencyFactoryParams(currencyName, currencyId);
		
		//Create banknote object and return it
		Banknote createdCurrency = (Banknote) abstractFactory.createEntity(params);
		if (createdCurrency != null) {
			return createdCurrency;
		} else {
			throw new FileReadException("Wrong Format for banknotes.json");
		}
	}

}
