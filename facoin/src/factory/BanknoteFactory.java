package factory;

import factory.objects.CurrencyFactoryParams;
import factory.validator.BanknoteValidator;
import factory.validator.ValidationResult;
import fileio.repository.BanknoteRepository;
import fileio.repository.IRestrictedRepository;
import model.Banknote;
import model.Currency;
import model.ICurrency;

public class BanknoteFactory extends CurrencyFactory {

	/**
	 * The constructor is for BanknoteFactory. A currency factory is extended for
	 * factory process.
	 */
	public BanknoteFactory() {
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

		// Validate banknote name and banknote id
		ValidationResult vr = BanknoteValidator.validateBanknote(params.name, params.id);

		// If args are valid, create a banknote with args' params
		if (vr.isValid) {
			currency = new Banknote(params.id, params.name);
		}

		// If id is invalid, create an unique id. After that create a banknote with
		// args' params
		else if (vr.messages.contains("Id is not validated.") && vr.messages.length() == 20) {
			String new_id = RandomFactory.randomId();
			IRestrictedRepository<Currency> repository = new BanknoteRepository();
			while (repository.getById(new_id).getObject() != null) {
				new_id = RandomFactory.randomId();
			}

			currency = new Banknote(new_id, params.name);
		}

		return currency;
	}

}
