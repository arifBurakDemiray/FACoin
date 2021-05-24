package fileio.repository;

import java.util.Iterator;
import exception.FileWriteException;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import storage.IContainer;
import model.CryptoWallet;
import model.Wallet;

public class CryptoWalletRepository implements IRepository<CryptoWallet>, IRestrictedRepository<CryptoWallet> {

	public CryptoWalletRepository() {

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
	 * The function tries to find the crypto wallet of given id and returns the
	 * database result. If crypto wallet is found > database.result object = crypto
	 * wallet, else database.result object = null
	 * 
	 * @param id = gotten crypto wallet id
	 * @return database result
	 */
	public DatabaseResult getById(String id) {
		// tries to find crypto wallet with given id in the crypto wallet repository of
		// system.
		final IContainer<Wallet> cryptoWallets = BaseRepository.crypto_wallets();
		String message = "";
		Object result = null;
		try {
			// try to find the wallet
			result = cryptoWallets.getById(id);

		} catch (ItemNotFoundException | NotSupportedException e) {
			message += e.getMessage();
		}
		return new DatabaseResult(result, message);
	}

	/**
	 * The function returns the Crypto Wallets' container of system
	 * 
	 * @return Crypto Wallet Container which holds all crypto wallets
	 */
	public final IContainer<Wallet> getCryptoWallets() {
		return BaseRepository.crypto_wallets();
	}

	// Add wallet to the crypto wallet repository
	@Override
	public boolean addEntity(CryptoWallet entity) {
		boolean result = BaseRepository.crypto_wallets().add(entity);
		return result;
	}

	// Remove wallet from the crypto wallet repository
	@Override
	public CryptoWallet removeEntity(CryptoWallet entity) {
		try {
			return (CryptoWallet) BaseRepository.crypto_wallets().remove(entity);
		} catch (ItemNotFoundException e) {
			return null;
		}
	}

	@Override
	public DatabaseResult getByName(String name) {
		return null;
	}

	@Override
	public final Iterator<CryptoWallet> getAll() {
		return null;
	}

	@Override
	public boolean isEmpty() {
		return BaseRepository.crypto_wallets().isEmpty();
	}

}
