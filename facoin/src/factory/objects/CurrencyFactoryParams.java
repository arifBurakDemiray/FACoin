package factory.objects;
public class CurrencyFactoryParams {
	public String id; // currency id
	public String name; // currency name
	public CurrencyFactoryParams(String name, String id){
		this.id = id;
		this.name = name;
	}
}