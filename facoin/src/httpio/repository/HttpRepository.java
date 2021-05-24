package httpio.repository;

import exception.HttpRequestException;
import model.Candle;
import storage.IContainer;

/**
 * This class responsible for http database
 */
public class HttpRepository {

	// HttpController to read data, write data and get read content of endpoints.
	private static HttpController httpController;

	public HttpRepository() {
		httpController = new HttpController();
	}

	public void initHttpDatabase() {

	}

	/**
	 * Read candles by trading pair
	 * 
	 * @param coinName
	 * @param banknoteName
	 * @throws HttpRequestException
	 */
	protected static void initHourCandles(String coinName, String banknoteName) throws HttpRequestException {
		httpController.readHourCandles(coinName, banknoteName);
	}

	/**
	 * Read candles by trading pair
	 * 
	 * @param coinName
	 * @param banknoteName
	 * @throws HttpRequestException
	 */
	protected static void initDayCandles(String coinName, String banknoteName) throws HttpRequestException {
		httpController.readDayCandles(coinName, banknoteName);
	}

	/**
	 * This function returns day candle container
	 * 
	 * @return IContainer<Candle>
	 */
	protected static final IContainer<Candle> day_candles() {
		return httpController.dayCandles();
	}

	/**
	 * This function returns hour candle container
	 * 
	 * @return IContainer<Candle>
	 */
	protected static final IContainer<Candle> hour_candles() {
		return httpController.hourCandles();
	}

}
