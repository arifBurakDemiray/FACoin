package httpio;

import java.util.Date;
import java.util.List;

import exception.HttpRequestException;
import fileio.repository.UpdateData;

/**
 * This interface identifies http read operations for the system
 */
public interface IHttpIO {

	/**
	 * This function makes a request and gives coin values to system
	 * 
	 * @param endpointPath of the coin value request path
	 * @return updated values
	 * @throws HttpRequestException if there is an error
	 */
	public List<UpdateData> getValues(String endpointPath) throws HttpRequestException;

	/**
	 * This function gets epoch time from a time server
	 * 
	 * @param endpointPath of the request api path
	 * @return coming date if there is an error returns null
	 */
	public Date getTime(String endpointPath);

	/**
	 * This function reads day candles by given endpoint path
	 * 
	 * @param endpointPath of the trading pair candles
	 * @throws HttpRequestException if there is an error
	 */
	public void readDayCandles(String endpointPath) throws HttpRequestException;

	/**
	 * This function reads hour candles by given endpoint path
	 * 
	 * @param endpointPath of the trading pair candles
	 * @throws HttpRequestException if there is an error
	 */
	public void readHourCandles(String endpointPath) throws HttpRequestException;
}
