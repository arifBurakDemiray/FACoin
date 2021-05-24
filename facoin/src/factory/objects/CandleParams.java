package factory.objects;

import java.util.Date;

public class CandleParams{

	public Date candleDate;
	public Double high,low,open,close,volume;
	
	/**
	 * The CandleParams holds necessary information for candle
	 * @param date = candle date
	 * @param high = high value of candle
	 * @param low = low value of candle
	 * @param open = open value of candle
	 * @param close = closed value of candle
	 * @param volume = volume value of candle
	 */
	public CandleParams(Date date, Double high, Double low, Double open, Double close, Double volume){
		this.candleDate = date;
		this.high = high;
		this.low = low;
		this.open = open;
		this.close = close;
		this.volume = volume;
	}	
}