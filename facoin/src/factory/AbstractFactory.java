package factory;
public class AbstractFactory{
	
	private AbstractFactory factory;
	
	/**
	 * This class is for the abstract factory design
	 * @param factory
	 */
	public AbstractFactory(AbstractFactory factory){
		this.factory = factory;
	}
		
	/**
	 * The function creates the entity with given args
	 * @param args = object args
	 * @return object
	 */
	public Object createEntity(Object args){
		return factory.createEntity(args);
	}
	
	/**
	 * The function sets the given subfactory
	 * @param factory = subfactory
	 */
	protected void setFactory(AbstractFactory factory){
		this.factory = factory;
	}
}

