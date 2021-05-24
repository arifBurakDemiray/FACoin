package fileio.repository;

import model.Currency;
import storage.IContainer;

public class UpdateHelper {

	protected static String toStringCurrencies(IContainer<Currency> currencies) {
		String result = "";
		for(Currency currency: currencies) {
			result+=toStringCurrencyForHttp(currency)+",";
		}
		if (result.endsWith(",")) { // if ends with , ignore it
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}
	
	private static String toStringCurrencyForHttp(Currency currency) {
		return currency.getName();
	}
	
	
}
