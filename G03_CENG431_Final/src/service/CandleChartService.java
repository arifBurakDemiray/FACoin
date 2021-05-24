package service;

import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.DefaultHighLowDataset;

import enums.ECandleType;
import httpio.repository.DayCandleRepository;
import httpio.repository.HourCandleRepository;
import model.Candle;
import storage.IContainer;
import view.CoinInfoView;

public class CandleChartService {

	private DayCandleRepository dayCandleRepository; // holds requested day candles of coin
	private HourCandleRepository hourCandleRepository; // holds requested hour candles of coin
	private String coinName; // selected coin's name like BTC
	private String banknoteName; // selected coin's trade pair like USD

	public CandleChartService(String coinName, String banknoteName) {
		dayCandleRepository = new DayCandleRepository();
		hourCandleRepository = new HourCandleRepository();
		this.banknoteName = banknoteName;
		this.coinName = coinName;
	}

	/**
	 * Set the view chart according to candle type of day/hour
	 * 
	 * @param type = candle type (day or hour)
	 * @param view
	 */
	public void setViewChart(ECandleType type, CoinInfoView view) {
		DefaultHighLowDataset dataset = createOHLCDataset(type); // set candles' dataset
		final JFreeChart chart = createChart(dataset); // create graph
		view.setChart(chart); // set graph to the view
	}

	/**
	 * Create the graph according to given candles' dataset
	 * 
	 * @param dataset = candles' dataset
	 * @return JFreeChart
	 */
	private JFreeChart createChart(final DefaultHighLowDataset dataset) {
		final JFreeChart chart = ChartFactory.createCandlestickChart("", "", "", dataset, true);
		return chart;
	}

	/**
	 * According to candle type, get the candles' information and return
	 * DefaultHighLowDataset object
	 * 
	 * @param type
	 * @return
	 */
	private DefaultHighLowDataset createOHLCDataset(ECandleType type) {

		IContainer<Candle> candles;

		// Request selected coin:banknote pair's candles from Candle API
		if (type == ECandleType.DAY) {
			candles = dayCandleRepository.day_candles(coinName, banknoteName);
		} else {

			candles = hourCandleRepository.hour_candles(coinName, banknoteName);
		}

		// Set the DefaultHighLowDataset object's variables
		int len = candles.getLength();
		Date[] dates = new Date[len];
		double[] opens = new double[len];
		double[] highs = new double[len];
		double[] lows = new double[len];
		double[] closes = new double[len];
		double[] volumes = new double[len];

		int i = 0;
		for (Candle d : candles.getContainer()) {
			dates[i] = d.getCandleDate();
			opens[i] = Double.valueOf(d.getOpen());
			highs[i] = Double.valueOf(d.getHigh());
			lows[i] = Double.valueOf(d.getLow());
			closes[i] = Double.valueOf(d.getClose());
			volumes[i] = Double.valueOf(d.getVolume());
			i++;
		}

		return new DefaultHighLowDataset(coinName + "/" + banknoteName, dates, highs, lows, opens, closes, volumes);
	}
}
