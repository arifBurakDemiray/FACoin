package factory.validator;

import factory.RandomFactory;
import fileio.repository.CryptoWalletRepository;
import fileio.repository.DatabaseResult;
import fileio.repository.IRestrictedRepository;
import model.CryptoWallet;
import model.WalletEntity;

public class CryptoWalletValidator {

	/**
	 * The function validates crypto wallet with given id
	 * 
	 * @param id = wallet id
	 * @return ValidationResult
	 */
	public static ValidationResult validateCryptoWallet(String id) {

		// Try to find that crypto wallet with that id exists in the repository
		// If exists create a new unique id for wallet
		IRestrictedRepository<CryptoWallet> wr = new CryptoWalletRepository();
		DatabaseResult dr = wr.getById(id);

		// If there is no wallet with that id, return true
		if (dr.getObject() == null) {
			return new ValidationResult(true, "Validated");
		}

		// If there is a wallet with that id, create a new unique id for wallet
		String new_id = RandomFactory.randomId();
		while (wr.getById(new_id).getObject() != null) {
			new_id = RandomFactory.randomId();
		}

		// Return created id in the validation result
		return new ValidationResult(new_id);

	}

	/**
	 * The function controls that given entity of crypto wallet is valid or not
	 * 
	 * @param entity = entity of crypto wallet like BTC AVAx ETH
	 * @return boolean
	 */
	public static boolean isEntityCoin(WalletEntity entity) {

		// getCurrencyName returns class simple name
		// BTC AVAX etc is Coin object
		return entity.getCurrencyName().equals("Coin");
	}

}
