package storage;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import model.WalletEntity;

public class WalletEntityContainer extends Container<WalletEntity> {

	@Override
	public WalletEntity getById(String id) throws NotSupportedException {
		throw new NotSupportedException("src.storage.WalletEntityContainer not supports getById()");
	}

	@Override
	public WalletEntity getByName(Name name) throws ItemNotFoundException {
		WalletEntity found = null;
		for (WalletEntity entity : this.getContainer()) {
			if (entity.equals(name)) {
				found = entity;
				break;
			}
		}
		if (found == null) {
			throw new ItemNotFoundException("There is no wallet entity has name " + name);
		} else {
			return found;

		}

	}

	@Override
	public WalletEntity getByName(String name) throws NotSupportedException {
		throw new NotSupportedException(
				"src.storage.WalletEntityContainer.getByName() function is not supported for WalletEntityContainer.");
	}
}
