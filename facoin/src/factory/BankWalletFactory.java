package factory;

import java.util.Dictionary;
import java.util.Enumeration;
import factory.validator.BankWalletValidator;
import factory.validator.ValidationResult;
import factory.validator.WalletEntityValidator;
import fileio.repository.BanknoteRepository;
import fileio.repository.DatabaseResult;
import model.BankWallet;
import model.ICurrency;
import model.Wallet;
import model.WalletEntity;
import storage.IContainer;
import storage.WalletEntityContainer;

public class BankWalletFactory extends WalletFactory {

	@Override
	public Wallet createWallet(String id, Dictionary<String, String> params) {

		String walletId = id;
		Wallet walletResult = null;

		// Validate gotten wallet id
		ValidationResult vr = BankWalletValidator.validateBankWallet(walletId);
		if (!vr.isValid) {
			// If id is not valid, get the new created id
			walletId = vr.messages;
		}

		boolean isNotEligible = false;

		// entites are the banknotes of wallet
		IContainer<WalletEntity> entities = new WalletEntityContainer();

		// If there is no entity to create, assign empty entities
		if (params.isEmpty()) {
			// If there is no banknotes in wallet, create empty wallet.
			walletResult = new BankWallet(walletId, entities);
			return walletResult;
		}

		// Else, get each banknote and create a wallet entity for each banknote
		Enumeration<String> keys = params.keys();
		String key = "";
		
		while (keys.hasMoreElements()) {
			key = keys.nextElement();
			
			//Create a wallet entity for banknote
			WalletEntity result = createWalletEntity(key, params.get(key));
			if (result == null) {
				isNotEligible = true;
				break;
			}

			if (!BankWalletValidator.isEntityBanknote(result)) {
				isNotEligible = true;
				break;
			}
			
			//add banknote to the entities.
			entities.add(result);
		}
		if (isNotEligible)
			return walletResult;
		
		//Create bank wallet
		walletResult = new BankWallet(walletId, entities);
		return walletResult;
	}

	@Override
	protected WalletEntity createWalletEntity(String currencyId, String quantity) {
		
		//Validate banknoteId and its quantity
		ValidationResult vr = WalletEntityValidator.validateWalletEntity(currencyId, quantity);
		WalletEntity entityResult = null;
		
		//If id or quantity is invalid, return null
		if (!vr.isValid) {
			return entityResult;
		}
		
		DatabaseResult result = null;
		
		//Get the banknote object from banknote repository
		result = (new BanknoteRepository()).getById(currencyId);

		Object resultObject = result.getObject();
		if (resultObject != null) {
			
			// add the banknote object to the entity with gotten quantity.
			entityResult = new WalletEntity((ICurrency) resultObject, Double.valueOf(quantity));
		}
		return entityResult;
	}

}