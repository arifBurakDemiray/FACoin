package httpio.parser;

import java.util.Date;
import java.util.List;

import org.json.JSONException;

import fileio.repository.UpdateData;

/**
 * This function is a middleware for parser classes in httpio
 *
 */
public class Parser {

	private UpdateParser updateParser;
	private DateParser dateParser;
	private DayCandleParser dayParser;
	private HourCandleParser hourParser;

	public Parser() {
		this.updateParser = new UpdateParser();
		this.dateParser = new DateParser();
		this.hourParser = new HourCandleParser();
		this.dayParser = new DayCandleParser();
	}

	/**
	 * This function takes updated coin values it not throws any error becuase
	 * system should run while updating a coin value, values can be updated in the
	 * next run
	 * 
	 * @param fileAll of the trading pair values
	 * @returns updated data for the coin values if there is an error returns null
	 */
	public List<UpdateData> parseUpdatedValues(String fileAll) {
		List<UpdateData> datas = updateParser.parseValues(fileAll);
		return datas;
	}

	/**
	 * This function parses a date by given http get request. It does not throws any
	 * error it can use system date
	 * 
	 * @param fileAll of the request context
	 * @returns date object
	 */
	public Date parseDate(String fileAll) {
		Date date = dateParser.parseDate(fileAll);
		return date;
	}

	/**
	 * This function parses day candles, if there is an error throws JSONException
	 * 
	 * @param fileAll  of the http context
	 * @param coinName of the candles
	 * @throws JSONException if context is not json
	 */
	public void parseDayCandles(String fileAll, String coinName) throws JSONException {
		dayParser.parseDayCandles(fileAll, coinName);
	}

	/**
	 * This function parses hour candles, if there is an error throws JSONException
	 * 
	 * @param fileAll  of the http context
	 * @param coinName of the candles
	 * @throws JSONException if context is not json
	 */
	public void parseHourCandles(String fileAll, String coinName) throws JSONException {
		hourParser.parseHourCandles(fileAll, coinName);
	}

}
