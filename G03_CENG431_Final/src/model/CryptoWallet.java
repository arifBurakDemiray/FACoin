package model;

import storage.IContainer;

/**
 * This class is the crypto wallet of an user
 */
public class CryptoWallet extends Wallet {

	public CryptoWallet(String id, IContainer<WalletEntity> entities) {
		super(id, entities);
	}

}
