package storage;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import model.Currency;

public class CurrencyContainer extends Container<Currency> {

	@Override
	public Currency getById(String id) throws ItemNotFoundException {
		Currency found = null;
		for (Currency currency : this.getContainer()) {
			if (currency.equals(id)) {
				found = currency;
				break;
			}
		}
		if (found == null) {
			throw new ItemNotFoundException("There is no curreny has id " + id);
		} else {
			return found;
		}
	}

	@Override
	public Currency getByName(String name) throws ItemNotFoundException, NotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

}