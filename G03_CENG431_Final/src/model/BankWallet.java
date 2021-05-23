package model;

import storage.IContainer;

/**
 * This class is the bank wallet of an user
 */
public class BankWallet extends Wallet {

	public BankWallet(String id, IContainer<WalletEntity> entities) {
		super(id, entities);
	}

}
