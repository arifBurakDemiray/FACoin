package httpio.repository;

import java.util.Date;

import exception.HttpRequestException;
import httpio.HttpIO;
import httpio.IHttpIO;
import model.Candle;
import settings.AppSettings;
import storage.IContainer;
import storage.CandleContainer;

public class HttpController {
	private static IHttpIO httpIO = new HttpIO();

	private IContainer<Candle> dayCandles;
	private IContainer<Candle> hourCandles;

	public HttpController() {
		dayCandles = new CandleContainer();
		hourCandles = new CandleContainer();

	}

	/**
	 * This function reads trading pair's candles
	 * 
	 * @param coinName
	 * @param banknoteName
	 * @throws HttpRequestException
	 */
	public void readDayCandles(String coinName, String banknoteName) throws HttpRequestException {
		dayCandles.clear();// clear day candles why clear becuase candles are read when user wants to see
							// trading pair's data
		httpIO.readDayCandles(String.format(AppSettings.DAY_CANDLE_ENDPOINT, coinName, banknoteName));// read new
																										// candles
		// by formatting day candle endpoint

	}

	/**
	 * This function reads trading pair's candles
	 * 
	 * @param coinName
	 * @param banknoteName
	 * @throws HttpRequestException
	 */
	public void readHourCandles(String coinName, String banknoteName) throws HttpRequestException {
		hourCandles.clear();// clear hour candle repo
		httpIO.readHourCandles(String.format(AppSettings.HOUR_CANDLE_ENDPOINT, coinName, banknoteName));// read new
																										// candles
	}

	/**
	 * This function reads date
	 * 
	 * @return responded date
	 */
	protected static Date updateDate() {
		Date date = null;
		date = httpIO.getTime(AppSettings.DATE_ENDPOINT);
		return date;
	}

	protected IContainer<Candle> dayCandles() {
		return this.dayCandles;
	}

	protected IContainer<Candle> hourCandles() {
		return this.hourCandles;
	}
}
