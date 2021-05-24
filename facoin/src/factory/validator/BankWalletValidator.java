package factory.validator;

import factory.RandomFactory;
import fileio.repository.BankWalletRepository;
import fileio.repository.DatabaseResult;
import fileio.repository.IRestrictedRepository;
import model.BankWallet;
import model.WalletEntity;

public class BankWalletValidator {

	/**
	 * The function validates bank wallet with given id
	 * 
	 * @param id = wallet id
	 * @return ValidationResult
	 */
	public static ValidationResult validateBankWallet(String id) {

		// Try to find that bank wallet with that id exists in the repository
		// If exists create a new unique id for wallet
		IRestrictedRepository<BankWallet> wr = new BankWalletRepository();
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
	 * The function controls that given entity of bank wallet is valid or not
	 * 
	 * @param entity = entity of bank wallet like USD TRY
	 * @return boolean
	 */
	public static boolean isEntityBanknote(WalletEntity entity) {
		// getCurrencyName returns class simple name
		// USD TRY etc is Banknote object
		return entity.getCurrencyName().equals("Banknote");
	}

}
