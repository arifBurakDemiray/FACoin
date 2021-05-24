package service;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import fileio.repository.IRepository;
import fileio.repository.TransactionRepository;
import model.Currency;
import model.Transaction;
import model.User;
import storage.BanknoteName;
import storage.CoinName;
import view.decorator.Decorator;

public class OrderService {

	private IRepository<Transaction> repo;
	private WalletService service;
	private User user;

	public OrderService(User user) {
		this.user = user;
		repo = new TransactionRepository();
		service = new WalletService();
	}

	/**
	 * The function do the cancel process of the selected pending transaction
	 * 
	 * @param selectedTransaction = selected pending transaction
	 * @param decorator           order list decorator to update order list
	 */
	public void setRejectProcesses(String selectedTransaction, Decorator decorator) {
		// Split the selected transaction label text
		// to get transaction info
		String transactionId = selectedTransaction.split(":")[0].strip();
		String banknoteName = selectedTransaction.split(":")[1].split("/")[1].strip();
		String coinName = selectedTransaction.split(":")[1].split("/")[0].strip();

		try {
			// Try to find transaction in the user's transaction container
			Transaction transaction = user.getTransactions().getById(transactionId);
			user.getTransactions().remove(transaction); // remove transaction from user

			// Update the user's bank wallet and crypto wallet
			if (transaction.getCoinQuantity() > 0) {
				// Give the user's blocked money quantity to user
				Currency banknote = (Currency) user.getBankWallet().getEntities()
						.getByName(new BanknoteName(banknoteName)).getCurrency();
				service.setBankWalletQuantity(user.getBankWallet(), banknote,
						transaction.getCoinValue() * transaction.getCoinQuantity());

			} else if (transaction.getCoinQuantity() < 0) {
				// Give the user's blocked coin quantity to user
				Currency coin = (Currency) user.getCryptoWallet().getEntities().getByName(new CoinName(coinName))
						.getCurrency();
				service.setCryptoWalletQuantity(user.getCryptoWallet(), coin, -1 * transaction.getCoinQuantity());
			}

			repo.removeEntity(transaction); // remove transaction from transaction repo of system

			repo.saveChanges();
			decorator.set(); // update order list
		} catch (ItemNotFoundException | NotSupportedException e) {

		}
	}

}
