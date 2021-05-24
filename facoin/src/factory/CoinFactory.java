package factory;

import factory.objects.CurrencyFactoryParams;
import factory.validator.CoinValidator;
import factory.validator.ValidationResult;
import fileio.repository.CoinRepository;
import fileio.repository.IRestrictedRepository;
import model.Coin;
import model.Currency;
import model.ICurrency;

public class CoinFactory extends CurrencyFactory {

	/**
	 * The constructor is for CoinFactory. A currency factory is extended for
	 * factory process.
	 */
	public CoinFactory() {
		super(null);
		super.setFactory(this);
	}

	@Override
	public Object createEntity(Object args) {
		ICurrency currency = null;

		// If gotten args are not CurrencyFactoryParams, return null
		if (!(args instanceof CurrencyFactoryParams)) {
			return currency;
		}

		CurrencyFactoryParams params = (CurrencyFactoryParams) args;

		// Validate coin name and coin id
		ValidationResult vr = CoinValidator.validatCoin(params.name, params.id);

		// If args are valid, create a coin with args' params
		if (vr.isValid) {
			currency = new Coin(params.id, params.name);
		}

		// If id is invalid, create an unique id. After that create a coin with
		// args' params
		else if (vr.messages.contains("Id is not validated.") && vr.messages.length() == 20) {
			IRestrictedRepository<Currency> repository = new CoinRepository();
			String new_id = RandomFactory.randomId();
			while (repository.getById(new_id).getObject() != null) {
				new_id = RandomFactory.randomId();
			}

			currency = new Coin(new_id, params.name);
		}

		return currency;
	}

}
