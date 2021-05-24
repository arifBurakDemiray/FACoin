package fileio.repository;

import java.util.Iterator;
import exception.FileWriteException;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import storage.IContainer;
import model.Currency;

public class BanknoteRepository implements IRepository<Currency>, IRestrictedRepository<Currency> {

	public BanknoteRepository() {

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
	 * The function tries to find the banknote of given id and returns the database
	 * result. If banknote is found > database.result object = banknote, else
	 * database.result object = null
	 * 
	 * @param id = gotten banknote id
	 * @return database result
	 */
	public DatabaseResult getById(String id) {

		// tries to find banknote with given id in the banknote repository of system.
		final IContainer<Currency> currencies = BaseRepository.banknotes();
		String message = "";
		Object result = null;
		try {
			// try to find the banknote
			result = currencies.getById(id);
		} catch (ItemNotFoundException | NotSupportedException e) {
			message += e.getMessage();
		}
		return new DatabaseResult(result, message);
	}
	
	/**
	 * The function returns the BankNotes container of system
	 * 
	 * @return Bank Note Container which holds all bank notes
	 */
	public IContainer<Currency> getBanknotes() {
		return BaseRepository.banknotes();
	}
	
	// Add currency to the banknote repository
	public boolean addEntity(Currency currency) {
		return BaseRepository.banknotes().add(currency);
	}

	// Remove currency from the banknote repository
	public Currency removeEntity(Currency currency) {
		try {
			return BaseRepository.banknotes().remove(currency);
		} catch (ItemNotFoundException e) {
			return null;
		}
	}

	/**
	 * The function tries to find the banknote of given name and returns the
	 * database result. If banknote is found > database.result object = bankote,
	 * else database.result object = null
	 * 
	 * @param name = gotten banknote name
	 * @return database result
	 */
	@Override
	public DatabaseResult getByName(String name) {
		// tries to find banknote with given name in the banknote repository of system.
		final IContainer<Currency> currencies = BaseRepository.banknotes();
		String message = "";
		Object result = null;
		try {
			// try to find the banknote
			result = currencies.getByName(name);
		} catch (ItemNotFoundException | NotSupportedException e) {
			message += e.getMessage();
		}
		return new DatabaseResult(result, message);
	}

	@Override
	public Iterator<Currency> getAll() {
		return BaseRepository.banknotes().iterator();
	}

	@Override
	public boolean isEmpty() {
		return BaseRepository.banknotes().isEmpty();
	}

}
