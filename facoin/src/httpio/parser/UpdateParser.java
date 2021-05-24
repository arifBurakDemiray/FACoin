package httpio.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import enums.EBanknotes;
import enums.ECoins;
import fileio.parser.JSONParser;
import fileio.repository.UpdateData;

public class UpdateParser {

	/**
	 * The UpdateParser parses the gotten http content and creates update data
	 * objects and returns it
	 */
	protected UpdateParser() {
	}

	/**
	 * The function parses gotten file content creates update data objects.
	 * 
	 * @param endpointResult = http content
	 */

	protected List<UpdateData> parseValues(String endpointResult) {
		JSONObject jsonValues;
		List<UpdateData> updateDataList = null;
		try {
			jsonValues = (new JSONParser()).parse(endpointResult); // get json object of file content
			updateDataList = parse(jsonValues);
		} catch (JSONException e) {
			updateDataList = null;
		}
		return updateDataList;
	}

	private List<UpdateData> parse(JSONObject jsonObject) throws JSONException {
		// iterate json object
		List<UpdateData> updateDataList = new ArrayList<UpdateData>();
		Iterator<?> coinsIterator = jsonObject.keys();
		JSONObject currencyValues = null;
		while (coinsIterator.hasNext()) {
			Object coinKey = coinsIterator.next();
			if (!(coinKey instanceof String))
				throw new JSONException("UpdateParser.parse::Key is not a string");
			String coinName = (String) coinKey;
			if (!coinName.equals("Error")) { // if result is not error
				currencyValues = (JSONObject) jsonObject.get(coinName);
				Iterator<?> valuesIterator = ((JSONObject) currencyValues).keys();
				Object banknoteValue = null;
				UpdateData updateData = new UpdateData(coinName); // create update data
				while (valuesIterator.hasNext()) {// while coin has values
					Object valueKey = valuesIterator.next();
					if (!(valueKey instanceof String))
						throw new JSONException("UpdateParser.parse::Key is not a string");
					String banknoteName = (String) valueKey;
					banknoteValue = currencyValues.get(banknoteName); // get banknotevalue
					if (validateResult(coinName, banknoteName, banknoteValue)) { // if validated
						updateData.addKeyValue(banknoteName, ((Double) banknoteValue));// add key value pair
					}
				}
				updateDataList.add(updateData);
			}
		}
		return updateDataList;
	}

	// validate coming result
	private boolean validateResult(String coinName, String banknoteName, Object banknoteValue) {
		boolean isValidCoin = ECoins.isCoin(coinName);
		boolean isValidBanknote = EBanknotes.isBanknote(banknoteName);
		boolean isValidDouble;
		try {
			Double val = ((Double) banknoteValue);
			isValidDouble = val instanceof Double;
		} catch (Exception e) {
			isValidDouble = false;
		}
		return (isValidCoin && isValidBanknote && isValidDouble);
	}
}
