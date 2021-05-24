package storage;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import model.Transaction;

public class TransactionContainer extends Container<Transaction> {

	@Override
	public Transaction getById(String id) throws ItemNotFoundException, NotSupportedException {
		Transaction found = null;
		for (Transaction transaction : this.getContainer()) {
			if (transaction.equals(id)) {
				found = transaction;
				break;
			}
		}
		if (found == null) {
			throw new ItemNotFoundException("There is no transaction has id " + id);
		} else {
			return found;
		}
	}

	@Override
	public Transaction getByName(String name) throws NotSupportedException {
		throw new NotSupportedException(
				"src.storage.TransactionContainer.getByName() function is not supported for TransactionContainer.");
	}

	@Override
	public Transaction getByName(Name name) throws NotSupportedException {
		throw new NotSupportedException(
				"src.storage.TransactionContainer.getByName() function is not supported for TransactionContainer.");
	}

}
