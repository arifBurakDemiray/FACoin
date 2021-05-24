package fileio.repository;

import model.User;
import model.Wallet;
import storage.IContainer;
import storage.*;
import model.Currency;
import model.Transaction;
import exception.FileReadException;
import exception.FileWriteException;
import fileio.FileIO;
import fileio.IFileIO;

/**
 * This class holds all informations
 *
 */
public class FileController {

	private IContainer<User> users;
	private IContainer<Wallet> cryptoWallets;
	private IContainer<Wallet> bankWallets;
	private IContainer<Currency> banknotes;
	private IContainer<Currency> coins;
	private IContainer<Transaction> transactions;
	private IFileIO fileIO;

	protected FileController() {
		fileIO = new FileIO();
		users = new UserContainer();
		cryptoWallets = new WalletContainer();
		bankWallets = new WalletContainer();
		banknotes = new CurrencyContainer();
		coins = new CurrencyContainer();
		transactions = new TransactionContainer();
	}

	/**
	 * This function reads all entities from files and assign the contents to the
	 * containers
	 * 
	 * @throws FileReadException
	 */
	protected void readAll() throws FileReadException {
		fileIO.readBanknotes("data\\banknotes.json");
		fileIO.readCoins("data\\coins.json");
		fileIO.readCryptoWallets("data\\crypto_wallets.json");
		fileIO.readBankWallets("data\\bank_wallets.json");
		fileIO.readTransactions("data\\transactions.json");
		fileIO.readUsers("data\\users.xml");
	}

	/**
	 * This function writes all entities to necessary files
	 * 
	 * @throws FileReadException
	 */
	protected void writeAll() throws FileWriteException {
		fileIO.writeUsers(users, "data\\users.xml");
		fileIO.writeCryptoWallets(cryptoWallets, "data\\crypto_wallets.json");
		fileIO.writeBankWallets(bankWallets, "data\\bank_wallets.json");
		fileIO.writeCoins(coins, "data\\coins.json");
		fileIO.writeBanknotes(banknotes, "data\\banknotes.json");
		fileIO.writeTransactions(transactions, "data\\transactions.json");
	}

	protected IContainer<User> users() {
		return users;
	}

	protected IContainer<Wallet> crypto_wallets() {
		return cryptoWallets;
	}

	protected IContainer<Wallet> bank_wallets() {
		return bankWallets;
	}

	protected IContainer<Currency> coins() {
		return coins;
	}

	protected IContainer<Currency> banknotes() {
		return banknotes;
	}

	protected IContainer<Transaction> transactions() {
		return transactions;
	}

}
