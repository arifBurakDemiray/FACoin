package factory;

import factory.validator.UserValidator;
import factory.validator.ValidationResult;
import fileio.repository.BankWalletRepository;
import fileio.repository.BanknoteRepository;
import fileio.repository.CoinRepository;
import fileio.repository.CryptoWalletRepository;
import fileio.repository.DatabaseResult;
import fileio.repository.IRestrictedRepository;
import fileio.repository.TransactionRepository;
import model.BankWallet;
import model.CryptoWallet;
import model.Transaction;
import model.User;
import model.Wallet;
import storage.IContainer;
import storage.TransactionContainer;
import java.util.List;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class UserFactory {

	/**
	 * The user factory creates user object according to given params
	 */
	public UserFactory() {

	}

	/**
	 * Create user according to gotten params
	 * 
	 * @param userId         = user's unique id
	 * @param userName       = user name
	 * @param password       = user password
	 * @param cryptoWalletId = user's unique crypto wallet id
	 * @param bankWalletId   = user's unique bank wallet id
	 * @param favorites      = user's favorite coins' ids
	 * @param transactionIds = user's transactions' ids
	 * @return User
	 */
	public User createUser(String userId, String userName, String password, String cryptoWalletId, String bankWalletId,
			String favorites, String transactionIds) {

		User userResult = null;

		// Validate userName and user password
		ValidationResult isUserOK = UserValidator.validateUser(userName, password);

		String id = userId;

		// Validate user ID
		ValidationResult idResult = UserValidator.validateUserId(userId);

		// If id is not valid, get new created id
		if (!idResult.isValid)
			id = idResult.messages;

		// If username or password is not valid, return null
		if (!isUserOK.isValid) {
			return userResult;
		}
		CryptoWalletRepository cryptoRepository = new CryptoWalletRepository();
		// Try to get the crypto wallet object of user from crypto wallet repository
		DatabaseResult resultCryptoWallet = cryptoRepository.getById(cryptoWalletId);

		BankWalletRepository bankRepo =  new BankWalletRepository();
		// Try to get the bank wallet object of user from bank wallet repository
		DatabaseResult resultBankWallet = bankRepo.getById(bankWalletId);
		Object cryptoWallet = resultCryptoWallet.getObject();
		Object bankWallet = resultBankWallet.getObject();

		if (cryptoWallet == null) {
			Dictionary<String, String> coins = new Hashtable<String, String>();
			cryptoWallet = (new CryptoWalletFactory()).createWallet("0", coins);
			cryptoRepository.addEntity((CryptoWallet) cryptoWallet);
		}
		if (bankWallet == null) {
			Dictionary<String, String> banknotes = new Hashtable<String, String>();
			bankWallet = (new BankWalletFactory()).createWallet("0", banknotes);
			bankRepo.addEntity((BankWallet) bankWallet);
		}

		// If wallets exist, create user's favorites dictionary, create user's
		// transactions container and create user
		// Create user's favorite coins' dictionary ( for example = BTC/USD, BTC/TRY,
		// AVAX/TRY )
		Dictionary<String, List<String>> favoritesDictionary = createDictionary(favorites);

		// Create user's transactions container
		IContainer<Transaction> transactions = createTransactions(transactionIds);

		// Create user
		userResult = new User(id, userName, password, (Wallet) cryptoWallet, (Wallet) bankWallet, favoritesDictionary,
				transactions);

		return userResult;

	}

	/**
	 * The function creates user's favorite coins' dictionary ( for example =
	 * BTC/USD, BTC/TRY, AVAX/TRY )
	 * 
	 * @param favorites = favorite coins string that includes ids ( example 1-1,1-2,
	 *                  4-2 / CoinId-BanknoteId)
	 * @return
	 */
	private Dictionary<String, List<String>> createDictionary(String favorites) {
		Dictionary<String, List<String>> favoritesDictionary = new Hashtable<String, List<String>>();

		// If there is no favorites, return empty dictionary
		if (favorites.isEmpty())
			return favoritesDictionary;

		// Split favorites string that includes ids ( example = 1-1,1-2,4-2 /
		// CoinId-BanknoteId)
		String[] splittedFavorites = favorites.split(",");
		for (String tradePair : splittedFavorites) {

			// TradePair is like that 1-1 means BTC-USD
			String[] pair = tradePair.split("-");

			// pair[0] splited coin id
			DatabaseResult resultOfCoin = (new CoinRepository()).getById(pair[0]);
			// pair[1] splitted banknote id
			DatabaseResult resultOfBanknote = (new BanknoteRepository()).getById(pair[1]);
			// Try to get coin object and banknote object
			Object coin = resultOfCoin.getObject();
			Object banknote = resultOfBanknote.getObject();

			// If objects are not null, create a list for pair's banknote
			// Example 1 (BTC id) is the key and List<1,2> is the value
			// List<1,2> includes banknotes. Example 1 = USD, 2= TRY
			// And dictionary can contain BTC key with USD and TRY values
			if (coin != null && banknote != null) {
				List<String> list = favoritesDictionary.get(pair[0]);
				if (list == null) {
					list = new ArrayList<String>();
				}
				list.add(pair[1]);
				favoritesDictionary.put(pair[0], list);
			}
		}
		return favoritesDictionary;
	}

	/**
	 * The function creates Transaction container that includes user's transactions
	 * 
	 * @param transactionIds = Transactions' ids string like that ( 53,213,546 )
	 * @return Transaction Container
	 */
	private IContainer<Transaction> createTransactions(String transactionIds) {
		IContainer<Transaction> transactions = new TransactionContainer();
		IRestrictedRepository<Transaction> transactionRepository = new TransactionRepository();

		// If there is no transaction in the transaction repository of system, return
		// empty container
		if (transactionRepository.isEmpty())
			return transactions;

		// Split transaction ids like that 53,213,546 -> 53 213 546
		String[] splittedTransactionIds = transactionIds.split(",");
		for (String id : splittedTransactionIds) {

			// Try to get the transaction object
			DatabaseResult resultOfTransaction = transactionRepository.getById(id);
			Object transaction = resultOfTransaction.getObject();

			// If transaction exists, add it to the container
			if (transaction != null) {
				transactions.add((Transaction) transaction);
			}
		}
		return transactions;
	}
}
