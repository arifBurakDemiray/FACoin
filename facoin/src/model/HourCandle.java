package model;

import java.util.Date;
import factory.objects.CandleParams;

/**
 * This class holds hour candle infos
 */
public class HourCandle extends Candle {

	public HourCandle(Date candleDate, Double high, Double low, Double open, Double close, Double volume) {
		super(candleDate, high, low, open, close, volume);
	}

	public HourCandle(CandleParams params) {
		super(params);
	}

}