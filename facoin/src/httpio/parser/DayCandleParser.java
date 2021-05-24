package httpio.parser;

import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import factory.AbstractFactory;
import factory.DayCandleFactory;
import factory.objects.CandleParams;
import fileio.parser.JSONParser;
import fileio.repository.IRepository;
import httpio.repository.DayCandleRepository;
import model.Candle;

public class DayCandleParser {

	private AbstractFactory abstractFactory;

	/**
	 * The DayCandleParser parses the gotten http content and creates candle objects
	 */
	protected DayCandleParser() { // create an factory for day candles
		this.abstractFactory = new AbstractFactory(new DayCandleFactory());
	}

	/**
	 * This function parses day candles
	 * 
	 * @param endpointResult given result
	 * @param coinName       name of the coin
	 * @throws JSONException if there is an error in json content
	 */
	protected void parseDayCandles(String endpointResult, String coinName) throws JSONException {
		IRepository<Candle> dayCandleRepository = new DayCandleRepository(); // take repository snapshot
		JSONObject jsonValues;
		jsonValues = (new JSONParser()).parse(endpointResult); // get json object of file content
		parse(jsonValues, coinName, dayCandleRepository);
	}

	private void parse(JSONObject jsonObject, String coinName, IRepository<Candle> dayCandleRepository)
			throws JSONException {
		String status = jsonObject.getString("Response");
		if (status.equals("Success")) // if response is successfull
		{
			JSONArray data = ((JSONObject) jsonObject.get("Data")).getJSONArray("Data");
			for (int i = 0; i < data.length(); i++) {
				// take candle informations
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
				Candle candle = createDayCandle(params); // if factory created the candle
				if (candle != null) {
					dayCandleRepository.addEntity(candle); // add to repository
				}
			}
		} else {
			throw new JSONException("There is a problem occured while requesting endpoint");
		}
	}

	private Candle createDayCandle(CandleParams args) {
		Candle result = null; // create entity in factory
		result = (Candle) abstractFactory.createEntity(args);
		return result;
	}

}
