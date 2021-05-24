package storage;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import model.Wallet;

public class WalletContainer extends Container<Wallet> {

	@Override
	public Wallet getById(String id) throws ItemNotFoundException, NotSupportedException {
		Wallet returnedWallet = null;
		for (Wallet wallet : this.getContainer()) {
			if (wallet.getId().equals(id)) {
				returnedWallet = wallet;
			}
		}
		return returnedWallet;
	}

	@Override
	public Wallet getByName(String name) throws NotSupportedException {
		throw new NotSupportedException(
				"src.storage.WalletContainer.getByName() function is not supported for WalletContainer.");
	}

	@Override
	public Wallet getByName(Name name) throws NotSupportedException {
		throw new NotSupportedException(
				"src.storage.WalletContainer.getByName() function is not supported for WalletContainer.");
	}

}
