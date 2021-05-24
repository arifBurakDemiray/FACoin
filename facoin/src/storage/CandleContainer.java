package storage;

import exception.NotSupportedException;
import model.Candle;

public class CandleContainer extends Container<Candle> {

	@Override
	public Candle getById(String id) throws NotSupportedException {
		throw new NotSupportedException(
				"src.storage.CandleContainer.getById() function is not supported for CandleContainer.");
	}

	@Override
	public Candle getByName(Name name) throws  NotSupportedException {
		throw new NotSupportedException(
				"src.storage.CandleContainer.getByName() function is not supported for CandleContainer.");
	}

	@Override
	public Candle getByName(String name) throws NotSupportedException {
		throw new NotSupportedException(
				"src.storage.CandleContainer.getByName() function is not supported for CandleContainer.");
	}


}
