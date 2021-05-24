package httpio.repository;

import exception.HttpRequestException;
import fileio.repository.DatabaseResult;
import fileio.repository.IRepository;
import storage.IContainer;
import model.Candle;

public class DayCandleRepository implements IRepository<Candle> {

	public DayCandleRepository() {

	}
	
	/**
	 * see {@link HourCandleRepository}
	 * @param coinName
	 * @param banknoteName
	 * @return specified trading pair's candles
	 */
	public IContainer<Candle> day_candles(String coinName, String banknoteName) {
		try {
			HttpRepository.initDayCandles(coinName, banknoteName);
		} catch (HttpRequestException e) {
		}
		return HttpRepository.day_candles();
	}

	public boolean addEntity(Candle candle) {
		return HttpRepository.day_candles().add(candle);
	}

	public Candle removeEntity(Candle candle) {
		return null;
	}

	@Override
	public DatabaseResult saveChanges() {
		return null; // becuase we do not write candles this function not supported by day candles
	}

}
