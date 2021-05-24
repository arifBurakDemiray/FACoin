package httpio.repository;

import exception.HttpRequestException;
import fileio.repository.DatabaseResult;
import fileio.repository.IRepository;
import storage.IContainer;
import model.Candle;

public class HourCandleRepository implements IRepository<Candle> {

	public HourCandleRepository() {

	}

	/**
	 * This function return candle container for wanted trading pair
	 * 
	 * @param coinName
	 * @param banknoteName
	 * @return hour candles
	 */
	public IContainer<Candle> hour_candles(String coinName, String banknoteName) {
		try {
			HttpRepository.initHourCandles(coinName, banknoteName);// read candles
		} catch (HttpRequestException e) { // if there was an error do ot throw
		}
		return HttpRepository.hour_candles();
	}

	public boolean addEntity(Candle candle) { // add to container
		return HttpRepository.hour_candles().add(candle);
	}

	public Candle removeEntity(Candle candle) {
		return null;
	}

	@Override
	public DatabaseResult saveChanges() {
		return null; // becuase we do not write candles this function not supported by hour candles
	}

}
