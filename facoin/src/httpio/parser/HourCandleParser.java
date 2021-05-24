package httpio.parser;

import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import factory.AbstractFactory;
import factory.HourCandleFactory;
import factory.objects.CandleParams;
import fileio.parser.JSONParser;
import fileio.repository.IRepository;
import httpio.repository.HourCandleRepository;
import model.Candle;

public class HourCandleParser {

	private AbstractFactory abstractFactory;

	/**
	 * The DayCandleParser parses the gotten http content and creates candle objects
	 */
	protected HourCandleParser() { // hour candle factory
		this.abstractFactory = new AbstractFactory(new HourCandleFactory());
	}

	/**
	 * This function parses hour candles
	 * 
	 * @param endpointResult given result
	 * @param coinName       name of the coin
	 * @throws JSONException if there is an error in json content
	 */
	protected void parseHourCandles(String endpointResult, String coinName) throws JSONException {
		IRepository<Candle> hourCandleRepository = new HourCandleRepository();
		JSONObject jsonValues;
		jsonValues = (new JSONParser()).parse(endpointResult); // get json object of file content
		parse(jsonValues, coinName, hourCandleRepository);

	}

	private void parse(JSONObject jsonObject, String coinName, IRepository<Candle> hourCandleRepository)
			throws JSONException {

		String status = jsonObject.getString("Response");

		if (status.equals("Success")) {
			JSONArray data = ((JSONObject) jsonObject.get("Data")).getJSONArray("Data");
			// iterate json object
			for (int i = 0; i < data.length(); i++) {
				JSONObject candleData = (JSONObject) data.get(i);
				Double high, low, open, close, volume;
				Long time;
				time = candleData.getLong("time");
				high = candleData.getDouble("high");
				low = candleData.getDouble("low");
				open = candleData.getDouble("open");
				close = candleData.getDouble("close");
				volume = candleData.getDouble("volumeto");
				Date date = new Date(Long.valueOf(time));
				CandleParams params = new CandleParams(date, high, low, open, close, volume);
				Candle candle = createCandle(params);
				if (candle != null) {
					hourCandleRepository.addEntity(candle);
				}
			}
		} else {
			throw new JSONException("There is a problem occured while requesting endpoint");
		}

	}

	private Candle createCandle(CandleParams params) {
		Candle result = null;
		result = (Candle) abstractFactory.createEntity(params);
		return result;
	}

}
