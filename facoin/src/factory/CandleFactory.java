package factory;


public abstract class CandleFactory extends AbstractFactory {
	
	/**
	 * The candle factory creates the candles for requested coin as day candles/hour candles
	 * @param factory
	 */
	public CandleFactory(CandleFactory factory) {
		super(factory);
	}

	@Override
	public abstract Object createEntity(Object args);
	
}