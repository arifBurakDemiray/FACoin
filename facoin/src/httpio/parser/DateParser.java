package httpio.parser;

import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;
import fileio.parser.JSONParser;

public class DateParser {

	/**
	 * The DateParser parses the gotten http content and creates date object
	 */
	protected DateParser() {
	}

	/**
	 * The function parses endpoint result and creates date object. If there is an
	 * error while making a request returns null
	 * 
	 * @param endpointResult
	 * @return parsed date object
	 */
	protected Date parseDate(String endpointResult) {
		JSONObject jsonValues;
		Date date;
		try {
			jsonValues = (new JSONParser()).parse(endpointResult); // get json object of file content
			date = parse(jsonValues);
		} catch (JSONException | NumberFormatException e) {
			date = null;
		}
		return date;
	}

	private Date parse(JSONObject jsonObject) throws JSONException, NumberFormatException {
		Date date = null;
		Object timestamp = jsonObject.get("UnixTimeStamp"); // take date
		if (timestamp instanceof String) {
			Long time = validateDate(timestamp); // validate date
			date = new Date(time);
		}
		return date;
	}

	private Long validateDate(Object date) throws NumberFormatException {
		return Long.valueOf((String) date);
	}

}
