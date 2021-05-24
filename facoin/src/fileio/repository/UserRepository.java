package fileio.repository;

import java.util.Iterator;
import exception.FileWriteException;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import storage.IContainer;
import model.User;

public class UserRepository implements IRepository<User>, IRestrictedRepository<User> {

	public UserRepository() {

	}

	/**
	 * The function tries to write the repositories contents to the files
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
	 * The function tries to find the user of given name and returns the database
	 * result. If user is found > database.result object = user, else
	 * database.result object = null
	 * 
	 * @param name = gotten user name
	 * @return database result
	 */
	public DatabaseResult getUserByName(String name) {
		// tries to find user with given name in the user repository of
		// system.
		final IContainer<User> users = BaseRepository.users();
		String message = "";
		Object result = null;
		try {
			// try to find the user
			result = users.getByName(name);
		} catch (ItemNotFoundException | NotSupportedException e) {
			message += e.getMessage();
		}
		return new DatabaseResult(result, message);
	}

	/**
	 * The function tries to find the user of given id and returns the database
	 * result. If user is found > database.result object = user, else
	 * database.result object = null
	 * 
	 * @param id = gotten user id
	 * @return database result
	 */
	public DatabaseResult getById(String id) {
		// tries to find user with given id in the user repository of
		// system.
		final IContainer<User> users = BaseRepository.users();
		String message = "";
		Object result = null;
		try {
			// try to find the user
			result = users.getById(id);
		} catch (ItemNotFoundException | NotSupportedException e) {
			message += e.getMessage();
		}
		return new DatabaseResult(result, message);
	}

	/**
	 * The function returns the user container of system
	 * 
	 * @return User Container which holds all users
	 */
	public final IContainer<User> getUsers() {
		return BaseRepository.users();
	}

	// Add user to the user repository
	@Override
	public boolean addEntity(User entity) {
		return BaseRepository.users().add(entity);
	}

	// Remove user from the user repository
	@Override
	public User removeEntity(User entity) {
		try {
			return BaseRepository.users().remove(entity);
		} catch (ItemNotFoundException e) {
			return null;
		}
	}

	@Override
	public DatabaseResult getByName(String name) {
		return null;
	}

	@Override
	public Iterator<User> getAll() {
		return BaseRepository.users().iterator();
	}

	@Override
	public boolean isEmpty() {
		return BaseRepository.users().isEmpty();
	}
}
