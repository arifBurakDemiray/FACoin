package factory;

import java.util.Dictionary;
import java.util.Enumeration;
import factory.validator.ValidationResult;
import factory.validator.WalletEntityValidator;
import fileio.repository.CoinRepository;
import fileio.repository.DatabaseResult;
import factory.validator.CryptoWalletValidator;
import model.CryptoWallet;
import model.ICurrency;
import model.Wallet;
import model.WalletEntity;
import storage.IContainer;
import storage.WalletEntityContainer;

public class CryptoWalletFactory extends WalletFactory {

	@Override
	public Wallet createWallet(String id, Dictionary<String, String> params) {
		String walletId = id;
		Wallet walletResult = null;

		// Validate gotten wallet id
		ValidationResult vr = CryptoWalletValidator.validateCryptoWallet(walletId);
		if (!vr.isValid) {
			// If id is not valid, get the new created id
			walletId = vr.messages;
		}
		boolean isNotEligible = false;

		// entities are the coins of wallet
		IContainer<WalletEntity> entities = new WalletEntityContainer();

		// If there is no entity to create, assign empty entities
		if (params.isEmpty()) {
			// If there is no coin in wallet, create empty wallet.
			walletResult = new CryptoWallet(walletId, entities);
			return walletResult;
		}

		// Else, get each coin and create a wallet entity for each coin
		Enumeration<String> keys = params.keys();
		String key = "";
		while (keys.hasMoreElements()) {

			key = keys.nextElement();

			// Create a wallet entity for coin
			WalletEntity result = createWalletEntity(key, params.get(key));
			if (result == null) {
				isNotEligible = true;
				break;
			}

			if (!CryptoWalletValidator.isEntityCoin(result)) {
				isNotEligible = true;
				break;
			}
			// add coin to the entities.
			entities.add(result);
		}
		if (isNotEligible) {
			return walletResult;
		}

		// Create crypto wallet
		walletResult = new CryptoWallet(walletId, entities);
		return walletResult;

	}

	@Override
	protected WalletEntity createWalletEntity(String currencyId, String quantity) {
		
		// Validate banknoteId and its quantity
		ValidationResult vr = WalletEntityValidator.validateWalletEntity(currencyId, quantity);
		WalletEntity entityResult = null;
		
		// If id or quantity is invalid, return null
		if (!vr.isValid) {
			return entityResult;
		}
		DatabaseResult result = null;
		
		//Get the coin object from coin repository
		result = (new CoinRepository()).getById(currencyId);

		Object resultObject = result.getObject();
		if (resultObject != null) {
			// add the coin object to the entity with gotten quantity.
			entityResult = new WalletEntity((ICurrency) resultObject, Double.valueOf(quantity));
		}
		return entityResult;
	}

}