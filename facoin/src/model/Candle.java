package model;

import java.util.Date;
import factory.objects.CandleParams;

/**
 * This class stores Candle informations
 */
public abstract class Candle {
	private Date candleDate;
	private Double high, low, open, close, volume;

	public Candle(CandleParams params) {
		this(params.candleDate, params.high, params.low, params.open, params.close, params.volume);
	}

	public Candle(Date candleDate, Double high, Double low, Double open, Double close, Double volume) {

		this.candleDate = candleDate;
		this.high = high;
		this.low = low;
		this.open = open;
		this.close = close;
		this.volume = volume;
	}

	public Date getCandleDate() {
		return this.candleDate;
	}

	public Double getHigh() {
		return this.high;
	}

	/**
	 * @return the open
	 */
	public Double getOpen() {
		return open;
	}

	/**
	 * @return the volume
	 */
	public Double getVolume() {
		return volume;
	}

	/**
	 * @return the low
	 */
	public Double getLow() {
		return low;
	}

	/**
	 * @return the close
	 */
	public Double getClose() {
		return close;
	}

}