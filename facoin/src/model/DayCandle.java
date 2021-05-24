package model;

import java.util.Date;
import factory.objects.CandleParams;

/**
 * This class holds day candle infos
 */
public class DayCandle extends Candle {

	public DayCandle(Date candleDate, Double high, Double low, Double open, Double close, Double volume) {
		super(candleDate, high, low, open, close, volume);
	}

	public DayCandle(CandleParams params) {
		super(params);
	}
}