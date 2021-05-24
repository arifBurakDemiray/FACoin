package model;

import storage.IContainer;

/**
 * This class helps a wallet to get a banknotes value from entity container
 */
public class WalletHelper {
	protected static Double getTotal(IContainer<WalletEntity> entities, String banknoteName) {
		Double result = (double) 0;
		for (WalletEntity entity : entities) {
			if (banknoteName.equals(entity.getCurrency().getName())) // if banknotename is same
				result += entity.getTotal(banknoteName); // add value to result
		}
		return result;
	}
}
