package fileio.repository;

import java.util.Iterator;
import exception.FileWriteException;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import storage.IContainer;
import model.BankWallet;
import model.Wallet;

public class BankWalletRepository implements IRepository<BankWallet>, IRestrictedRepository<BankWallet> {

	public BankWalletRepository() {

	}

	/**
	 * The function tries to write the repositories contents to the files
	 * 
	 * @return DatabaseResult(null, message);
	 */
	public DatabaseResult saveChanges() {
		String message = "";
		try {
			BaseRepository.saveChanges();
		} catch (FileWriteException e) {
			message += e.getMessage();
		}
		return new DatabaseResult(message, message);
	}

	/**
	 * The function tries to find the bank wallet of given id and returns the
	 * database result. If bank wallet is found > database.result object = bank
	 * wallet, else database.result object = null
	 * 
	 * @param id = gotten bank wallet id
	 * @return database result
	 */
	public DatabaseResult getById(String id) {
		// tries to find bank wallet with given id in the bank wallet repository of
		// system.
		final IContainer<Wallet> bankWallets = BaseRepository.bank_wallets();
		String message = "";
		Object result = null;
		try {
			// try to find the wallet
			result = bankWallets.getById(id);
		} catch (ItemNotFoundException | NotSupportedException e) {
			message += e.getMessage();
		}
		return new DatabaseResult(result, message);
	}

	/**
	 * The function returns the Bank Wallets' container of system
	 * 
	 * @return Bank Wallet Container which holds all bank wallets
	 */
	public final IContainer<Wallet> getBankWallets() {
		return BaseRepository.bank_wallets();
	}

	// Add wallet to the bank wallet repository
	@Override
	public boolean addEntity(BankWallet entity) {
		boolean result = BaseRepository.bank_wallets().add(entity);
		return result;
	}

	// Remove wallet from the bank wallet repository
	@Override
	public BankWallet removeEntity(BankWallet entity) {
		try {
			return (BankWallet) BaseRepository.bank_wallets().remove(entity);
		} catch (ItemNotFoundException e) {
			return null;
		}
	}

	@Override
	public DatabaseResult getByName(String name) {
		return null;
	}

	@Override
	public Iterator<BankWallet> getAll() {
		return null;
	}

	@Override
	public boolean isEmpty() {
		return BaseRepository.bank_wallets().isEmpty();
	}

}
