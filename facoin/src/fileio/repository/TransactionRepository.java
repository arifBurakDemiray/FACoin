package fileio.repository;

import java.util.Iterator;
import exception.FileWriteException;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import storage.IContainer;
import model.Transaction;

public class TransactionRepository implements IRepository<Transaction>, IRestrictedRepository<Transaction> {

	public TransactionRepository() {

	}

	/**
	 * The function tries to write the repositories contents to the necessary file.
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
	 * The function tries to find the transaction of given id and returns the
	 * database result. If transaction is found > database.result object =
	 * transaction, else database.result object = null
	 * 
	 * @param id = gotten transaction id
	 * @return database result
	 */
	public DatabaseResult getById(String id) {
		// tries to find transaction with given id in the transaction repository of
		// system.
		final IContainer<Transaction> transactions = BaseRepository.transactions();
		String message = "";
		Object result = null;
		try {
			// try to find the transaction
			result = transactions.getById(id);

		} catch (ItemNotFoundException | NotSupportedException e) {
			message += e.getMessage();
		}
		return new DatabaseResult(result, message);
	}

	/**
	 * The function returns the Transactions' container of system
	 * 
	 * @return Transaction Container which holds all transactions
	 */
	public final IContainer<Transaction> getTransactions() {
		return BaseRepository.transactions();
	}

	// Add transaction to the transaction repository
	@Override
	public boolean addEntity(Transaction entity) {
		boolean result = BaseRepository.transactions().add(entity);
		return result;
	}

	// Remove transaction from the transaction repository
	@Override
	public Transaction removeEntity(Transaction entity) {
		try {
			return (Transaction) BaseRepository.transactions().remove(entity);
		} catch (ItemNotFoundException e) {
			return null;
		}
	}

	@Override
	public DatabaseResult getByName(String name) {
		return null;
	}

	@Override
	public final Iterator<Transaction> getAll() {
		return null;
	}

	@Override
	public boolean isEmpty() {
		return BaseRepository.transactions().isEmpty();
	}

}
