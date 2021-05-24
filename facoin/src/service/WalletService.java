package service;

import model.Currency;
import model.Wallet;
import model.WalletEntity;
import storage.BanknoteName;
import storage.CoinName;
import storage.Name;
import exception.ItemNotFoundException;
import exception.NotSupportedException;

public class WalletService {

	public WalletService() {
	}

	/**
	 * The function controls that user has enough money for buy order
	 * 
	 * @param bankWallet   = user's bank wallet
	 * @param banknoteName = banknote of trade pair of order like USD (BTC/USD)
	 * @param coinQuantity = coin quantity of order
	 * @param coinValue    = value of coin
	 * @return boolean
	 */
	public boolean hasEnoughMoney(Wallet bankWallet, String banknoteName, Double coinQuantity, Double coinValue) {
		if (bankWallet.getTotal(banknoteName) >= coinQuantity * coinValue) {
			return true;
		}
		return false;
	}

	/**
	 * The function controls that user has enough coin for sell order
	 * 
	 * @param cryptoWallet = user's crypto wallet
	 * @param coinName     = banknote of trade pair of order like BTC (BTC/USD)
	 * @param coinQuantity coin quantity of order
	 * @return boolean
	 */
	public boolean hasEnoughCoin(Wallet cryptoWallet, String coinName, Double coinQuantity) {
		WalletEntity entity;
		try {
			entity = cryptoWallet.getEntities().getByName(new CoinName(coinName));
			if (entity.getQuantity() >= coinQuantity) {
				return true;
			}
			return false;
		} catch (ItemNotFoundException | NotSupportedException e) {
			return false;
		}
	}

	/**
	 * If user has enough money for buy process, block the user's money for buy
	 * order
	 * 
	 * @param bankWallet   = user's bank wallet
	 * @param banknoteName = banknote of trade pair of order like USD (BTC/USD)
	 * @param quantity     = banknote quantity to block
	 */
	public void blockBankWalletQuantity(Wallet bankWallet, String banknoteName, Double quantity) {
		try {
			// Get wallet entity
			WalletEntity entity = bankWallet.getEntities().getByName(new BanknoteName(banknoteName));

			// Update the quantity of entity
			entity.setQuantity(entity.getQuantity() - quantity);

			// If money for the banknote ran out, remove banknote from bank wallet
			if (entity.getQuantity() == 0) {
				bankWallet.getEntities().remove(entity);
			}
		} catch (Exception e) {
		}

	}

	/**
	 * If user has enough coin for sell process, block the user's coin for sell
	 * order
	 * 
	 * @param cryptoWallet = user's crypto wallet
	 * @param cryptoName   = coin of trade pair of order like BTC (BTC/USD)
	 * @param quantity     = coin quantity to block
	 */
	public void blockCryptoWalletQuantity(Wallet cryptoWallet, String cryptoName, Double quantity) {
		try {
			// Get wallet entity
			WalletEntity entity = cryptoWallet.getEntities().getByName(new CoinName(cryptoName));

			// Update the quantity of entity
			entity.setQuantity(entity.getQuantity() - quantity);

			// If quantity of the coin ran out, remove coin from crypto wallet
			if (entity.getQuantity() == 0) {
				cryptoWallet.getEntities().remove(entity);
			}
		} catch (Exception e) {
		}

	}

	/**
	 * The function sets the user's bank wallet after buy/sell process Updates the
	 * banknote's quantity
	 * 
	 * @param bankWallet = user's bank wallet
	 * @param banknote   = banknote like USD
	 * @param quantity   = banknote quantity to update
	 */
	public void setBankWalletQuantity(Wallet bankWallet, Currency banknote, Double quantity) {
		try {
			Name name = new BanknoteName(banknote.getName());

			// Get the wallet entity for banknote
			WalletEntity entity = bankWallet.getEntities().getByName(name);

			// Update the entity's quantity
			entity.setQuantity(entity.getQuantity() + quantity);
			if (entity.getQuantity() == 0) {
				bankWallet.getEntities().remove(entity);
			}
		} catch (Exception e) {
			// If entity is not found, create a new entity for banknote
			WalletEntity newEntity = new WalletEntity(banknote, quantity);
			bankWallet.getEntities().add(newEntity);
		}

	}

	/**
	 * The function sets the user's crypto wallet after buy/sell process Updates the
	 * coin's quantity
	 * 
	 * @param cryptoWallet = user's crypto wallet
	 * @param coin         = coin like BTC
	 * @param quantity     = coin quantity to update
	 */

	public void setCryptoWalletQuantity(Wallet cryptoWallet, Currency coin, Double quantity) {
		try {
			Name name = new CoinName(coin.getName());

			// Get the wallet entity for coin
			WalletEntity entity = cryptoWallet.getEntities().getByName(name);
			// Update the entity's quantity
			entity.setQuantity(entity.getQuantity() + quantity);
		} catch (Exception e) {
			// If entity is not found, create a new entity for coin
			WalletEntity newEntity = new WalletEntity(coin, quantity);
			cryptoWallet.getEntities().add(newEntity);
		}

	}

}
