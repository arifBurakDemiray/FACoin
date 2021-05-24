package mediator;

import fileio.repository.IRestrictedRepository;
import fileio.repository.TransactionRepository;
import model.Transaction;
import model.User;
import service.TransactionService;

public class TransactionMediator implements IUpdatable {

	private User user;
	private TransactionService transactionService;
	private IRestrictedRepository<Transaction> transactions;

	public TransactionMediator(User user) {
		this.user = user;
		this.transactionService = new TransactionService(user);
		transactions = new TransactionRepository();
	}

	@Override
	public void set() {
		checkTransactions();
	}

	/**
	 * In every 10 seconds, travel for each pending transactions and execute order
	 * If order value is reached, do execution process and make transaction approved
	 * Update user's wallets.
	 */
	public void checkTransactions() {
		for (Transaction transaction : user.getTransactions()) {
			if (transaction.getTransactionState().equals("Pending")) {
				Double realValue = transaction.getCoin().getValue().get(transaction.getBanknote().getName());
				transactionService.executeOrder(transaction, realValue);
			}
		}
		transactions.saveChanges();
	}
}
