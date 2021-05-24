package httpio;

import java.util.Date;
import java.util.List;

import org.json.JSONException;
import enums.ECoins;
import enums.ERequestType;
import exception.HttpRequestException;
import fileio.repository.UpdateData;
import httpio.parser.Parser;

public class HttpIO implements IHttpIO {

	private Parser parser;
	private HttpRequest httpRequest;

	public HttpIO() {
		parser = new Parser();
		httpRequest = new HttpRequest();
	}

	public List<UpdateData> getValues(String endpointPath) {
		httpRequest.setParams(endpointPath, ERequestType.GET); // create request
		String httpContext = httpRequest.getRequest(); // make request
		List<UpdateData> updateDataList = parser.parseUpdatedValues(httpContext); // parse response
		return updateDataList; // return parsed data
	}

	@Override
	public Date getTime(String endpointPath) {// same logic as above
		httpRequest.setParams(endpointPath, ERequestType.GET);
		String httpContext = httpRequest.getRequest();
		Date date = parser.parseDate(httpContext);
		return date;
	}

	@Override
	public void readDayCandles(String endpointPath) throws HttpRequestException {// same logic as below
		httpRequest.setParams(endpointPath, ERequestType.GET);
		String httpContext = httpRequest.getRequest();
		String coinName = extractCoinName(endpointPath).name();
		try {
			parser.parseDayCandles(httpContext, coinName);
		} catch (JSONException e) {
			throw new HttpRequestException(e.getMessage());
		}

	}

	@Override
	public void readHourCandles(String endpointPath) throws HttpRequestException {
		httpRequest.setParams(endpointPath, ERequestType.GET); // create request
		String httpContext = httpRequest.getRequest(); // make request
		String coinName = extractCoinName(endpointPath).name(); // extract coin nmae
		try {
			parser.parseHourCandles(httpContext, coinName); // try to parse response
		} catch (JSONException e) {
			throw new HttpRequestException(e.getMessage()); // throw exception
		}
	}

	/**
	 * This function extracts coin name from enpoint
	 * 
	 * @param url of the request
	 * @returns {@link ECoins}
	 */
	private ECoins extractCoinName(String url) {
		int eqIndex = url.indexOf("=") + 1;
		int andIndex = url.indexOf("&");
		String coinName = url.substring(eqIndex, andIndex);
		return ECoins.valueOf(coinName);
	}

}
