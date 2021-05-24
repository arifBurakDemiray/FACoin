package factory;

public abstract class CurrencyFactory extends AbstractFactory{	
	
	
	public CurrencyFactory(CurrencyFactory factory) {
		super(factory);
	}
	
	

	@Override
	public abstract Object createEntity(Object args);
	
}