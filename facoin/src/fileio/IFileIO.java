package fileio;

import exception.FileReadException;
import exception.FileWriteException;
import model.Currency;
import model.Transaction;
import model.User;
import model.Wallet;
import storage.IContainer;

/**
 * This class handles fileio operations
 */
public interface IFileIO {
	
	/**
	 * This function reads crpyto wallets
	 * 
	 * @param filePath of the crypto_wallets.json file
	 * @returns read crypto_wallets
	 * @throws Exception for fileio exceptions
	 */
	public void readCryptoWallets(String filePath) throws FileReadException;
	
	/**
	 * This function reads bank wallets
	 * 
	 * @param filePath of the bank_wallets.json file
	 * @returns read bank_wallets
	 * @throws Exception for fileio exceptions
	 */
	public void readBankWallets(String filePath) throws FileReadException;


	/**
	 * This function reads users
	 * 
	 * @param filePath of users.xml file
	 * @returns read users
	 * @throws Exception for fileio exceptions
	 */
	public void readUsers(String filePath) throws FileReadException;


	/**
	 * This function reads coins
	 * 
	 * @param filePath of coins.json file
	 * @returns read coins
	 * @throws Exception for fileio exceptions
	 */
	public void readCoins(String filePath) throws FileReadException;
	
	
	/**
	 * This function reads banknotes
	 * 
	 * @param filePath of banknotes.json file
	 * @returns read banknotes
	 * @throws Exception for fileio exceptions
	 */
	public void readBanknotes(String filePath) throws FileReadException;
	
	/**
	 * This function reads transactions
	 * 
	 * @param filePath of transactions.json file
	 * @returns read transactions
	 * @throws Exception for fileio exceptions
	 */
	public void readTransactions(String filePath) throws FileReadException;

	/**
	 * This function writes users
	 * 
	 * @param users    of the system
	 * @param filePath of the file
	 * @throws Exception for write operations
	 */
	public void writeUsers(IContainer<User> users, String filePath) throws FileWriteException;
	
	/**
	 * This function writes crypto wallets
	 * 
	 * @param cryptoWallets    of the system
	 * @param filePath of the file
	 * @throws Exception for write operations
	 */
	public void writeCryptoWallets(IContainer<Wallet> cryptoWallets, String filePath) throws FileWriteException;
	
	
	/**
	 * This function writes bank wallets
	 * 
	 * @param bankWallets    of the system
	 * @param filePath of the file
	 * @throws Exception for write operations
	 */
	public void writeBankWallets(IContainer<Wallet> bankWallets, String filePath) throws FileWriteException;
	
	/**
	 * This function writes coins
	 * 
	 * @param coins    of the system
	 * @param filePath of the file
	 * @throws Exception for write operations
	 */
	public void writeCoins(IContainer<Currency> coins, String filePath) throws FileWriteException;
	
		/**
	 * This function writes users
	 * 
	 * @param banknotes    of the system
	 * @param filePath of the file
	 * @throws Exception for write operations
	 */
	public void writeBanknotes(IContainer<Currency> banknotes, String filePath) throws FileWriteException;
	
	/**
	 * This function writes transactions
	 * 
	 * @param transactions    of the system
	 * @param filePath of the file
	 * @throws Exception for write operations
	 */
	public void writeTransactions(IContainer<Transaction> banknotes, String filePath) throws FileWriteException;


}
