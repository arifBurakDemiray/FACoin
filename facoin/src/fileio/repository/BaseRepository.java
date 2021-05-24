package fileio.repository;

import exception.FileReadException;
import exception.FileWriteException;
import model.Currency;
import model.Transaction;
import model.User;
import model.Wallet;
import storage.IContainer;

public class BaseRepository {

	// FileController to read data, write data and get read content of files.
	private static FileController fileController;

	public BaseRepository() {
		fileController = new FileController();
	}

	/**
	 * The function reads all files and initialises containers.
	 * 
	 * @throws FileReadException
	 */
	public void initDatabase() throws FileReadException {
		fileController.readAll();
	}

	/**
	 * The function returns the read users' container
	 * 
	 * @return User Container
	 */
	protected static final IContainer<User> users() {
		return fileController.users();
	}

	/**
	 * The function returns the read coins' container
	 * 
	 * @return Coin Container
	 */
	protected static final IContainer<Currency> coins() {
		return fileController.coins();
	}

	/**
	 * The function returns the read banknotes' container
	 * 
	 * @return Banknote Container
	 */
	protected static final IContainer<Currency> banknotes() {
		return fileController.banknotes();
	}

	/**
	 * The function returns the read Crypto Wallets' container
	 * 
	 * @return Wallet Container
	 */
	protected static final IContainer<Wallet> crypto_wallets() {
		return fileController.crypto_wallets();
	}

	/**
	 * The function returns the read Bank Wallets' container
	 * 
	 * @return Wallet Container
	 */
	protected static final IContainer<Wallet> bank_wallets() {
		return fileController.bank_wallets();
	}

	/**
	 * The function returns the read Transactions' container
	 * 
	 * @return Wallet Container
	 */
	protected static final IContainer<Transaction> transactions() {
		return fileController.transactions();
	}

	/**
	 * The function writes all data to necessary files.
	 */
	public static void saveChanges() throws FileWriteException {
		fileController.writeAll();
	}
}
