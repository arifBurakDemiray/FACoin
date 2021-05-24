package factory;

import java.util.Dictionary;

import model.Wallet;
import model.WalletEntity;

public abstract class WalletFactory {

	public WalletFactory() {
	}

	/*
	 * The function creates wallet with given id and wallet params that includes
	 * currencies
	 */
	public abstract Wallet createWallet(String id, Dictionary<String, String> params);

	/**
	 * The function creates the wallet entity with gotten currencyId and quantity
	 * from params' element
	 * 
	 * @param currencyId
	 * @param quantity   = currency quantity that exists in the wallet
	 * @return WalletEntity
	 */
	protected abstract WalletEntity createWalletEntity(String currencyId, String quantity);

}
