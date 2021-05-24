package service;

import factory.TransactionFactory;
import model.Transaction;
import fileio.repository.IRepository;
import fileio.repository.TransactionRepository;
import model.User;

public class TransactionService {

	private User user;
	private WalletService walletService;
	private IRepository<Transaction> repo;

	public TransactionService(User user) {
		this.user = user;
		walletService = new WalletService();
		repo = new TransactionRepository();
	}

	/**
	 * The function do the buying process
	 * 
	 * @param params = WalletServiceParam that holds necessary info for buy process
	 * @return boolean
	 */
	public boolean buyCoin(WalletServiceParam params) {
		// Control that user has enough money
		boolean hasEnoughMoney = walletService.hasEnoughMoney(user.getBankWallet(), params.banknoteName,
				params.coinQuantity, params.coinValue);

		if (hasEnoughMoney) {
			// Create a new transaction for buy process
			TransactionFactory factory = new TransactionFactory();
			Transaction transaction = factory.createTransaction("0", (params.coinId + "-" + params.banknoteId),
					String.valueOf(params.coinQuantity), String.valueOf(params.coinValue), "Pending");

			// If transaction is created successfully,
			// block the user's money for buy process
			// add transaction to the transaction repo of system
			// add transaction to the user's transaction container
			if (transaction != null) {
				walletService.blockBankWalletQuantity(user.getBankWallet(), params.banknoteName,
						params.coinQuantity * params.coinValue);
				repo.addEntity(transaction);
				user.getTransactions().add(transaction);
				repo.saveChanges();

				return true;
			}
		}
		return false;
	}

	/**
	 * The function do the selling process
	 * 
	 * @param params = WalletServiceParam that holds necessary info for sell process
	 * @return boolean
	 */
	public boolean sellCoin(WalletServiceParam params) {
		// Control that user has enough coin
		boolean hasEnoughCoin = walletService.hasEnoughCoin(user.getCryptoWallet(), params.coinName,
				params.coinQuantity);
		if (hasEnoughCoin) {

			// Create a new transaction for sell process
			TransactionFactory factory = new TransactionFactory();
			Transaction transaction = factory.createTransaction("0", (params.coinId + "-" + params.banknoteId),
					String.valueOf(params.coinQuantity * -1), String.valueOf(params.coinValue), "Pending");

			// If transaction is created successfully,
			// block the user's coin for sell process
			// add transaction to the transaction repo of system
			// add transaction to the user's transaction container
			if (transaction != null) {
				walletService.blockCryptoWalletQuantity(user.getCryptoWallet(), params.coinName, params.coinQuantity);
				repo.addEntity(transaction);
				user.getTransactions().add(transaction);
				repo.saveChanges();
				return true;
			}
		}
		return false;
	}

	/**
	 * If order value is reached, execute the transaction using this function In
	 * every 10 seconds, coins' values are updated and transaction order values are
	 * controlled If an order value is reached, do the transaction
	 * 
	 * @param transaction
	 * @param coinUpdatedValue
	 */
	public void executeOrder(Transaction transaction, Double coinUpdatedValue) {

		Double transactionQuantity = transaction.getCoinQuantity();

		// If the transaction is buy order
		if (transactionQuantity > 0) {
			// If order value is reached for buy order, do the buy order
			// and update the transaction's type as approved
			if (transaction.getCoinValue() >= coinUpdatedValue) {
				walletService.setCryptoWalletQuantity(user.getCryptoWallet(), transaction.getCoin(),
						transactionQuantity);
				transaction.approveTransaction();
			}

		}

		// If the transaction is sell order
		if (transactionQuantity < 0) {
			if (transaction.getCoinValue() > coinUpdatedValue)
				return;

			// If order value is reached for sell order, do the sell order
			// and update the transaction's type as approved
			walletService.setBankWalletQuantity(user.getBankWallet(), transaction.getBanknote(),
					transaction.getCoinValue() * (transactionQuantity * -1));
			transaction.approveTransaction();
		}
	}

}
