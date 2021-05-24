package fileio.repository;

import java.util.Iterator;
import exception.FileWriteException;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import storage.IContainer;
import model.Currency;

public class CoinRepository implements IRepository<Currency>, IRestrictedRepository<Currency> {

	public CoinRepository() {

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
	 * The function tries to find the coin of given id and returns the database
	 * result. If coin is found > database.result object = coin, else
	 * database.result object = null
	 * 
	 * @param id = gotten coin id
	 * @return database result
	 */
	public DatabaseResult getById(String id) {
		// tries to find coin with given id in the coin repository of system.
		final IContainer<Currency> currencies = BaseRepository.coins();
		String message = "";
		Object result = null;
		try {
			// try to find the coin
			result = currencies.getById(id);
		} catch (ItemNotFoundException | NotSupportedException e) {
			message += e.getMessage();
		}
		return new DatabaseResult(result, message);
	}
	
	/**
	 * The function returns the Coin container of system
	 * 
	 * @return Coin Container which holds all coins
	 */
	public IContainer<Currency> coins() {
		return BaseRepository.coins();
	}

	// Add currency to the coin repository
	public boolean addEntity(Currency currency) {
		return BaseRepository.coins().add(currency);
	}

	// Remove currency from the coin repository
	public Currency removeEntity(Currency currency) {
		try {
			return BaseRepository.coins().remove(currency);
		} catch (ItemNotFoundException e) {
			return null;
		}
	}

	/**
	 * The function tries to find the coin of given name and returns the database
	 * result. If coin is found > database.result object = coin, else
	 * database.result object = null
	 * 
	 * @param name = gotten coin name
	 * @return database result
	 */
	@Override
	public DatabaseResult getByName(String name) {
		// tries to find coin with given name in the coin repository of system.
		final IContainer<Currency> currencies = BaseRepository.coins();
		String message = "";
		Object result = null;
		try {
			// try to find the coin
			result = currencies.getByName(name);
		} catch (ItemNotFoundException | NotSupportedException e) {
			message += e.getMessage();
		}
		return new DatabaseResult(result, message);
	}

	@Override
	public final Iterator<Currency> getAll() {
		return BaseRepository.coins().iterator();
	}

	@Override
	public boolean isEmpty() {
		return BaseRepository.coins().isEmpty();
	}
}
