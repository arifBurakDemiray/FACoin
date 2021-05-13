package fileio.repository;

import exception.FileFormatException;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import storage.IContainer;
import model.User;

public class UserRepository implements IRepository<User>,IRestrictedRepository<User> {

	public UserRepository() {

	}

	/**
	 * The function tries to write the users to the necessary file.
	 * 
	 * @return DatabaseResult(null, message);
	 */
	public DatabaseResult saveChanges() {
		String message = "";
		try {
			BaseRepository.saveChanges();
		} catch (FileFormatException e) {
			message += e.getMessage();
		}
		return new DatabaseResult(null, message);
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
		// get user container of system which holds all users
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
	 * The function tries to find the user of given name and returns the database
	 * result. If user is found > database.result object = user, else
	 * database.result object = null
	 * 
	 * @param name = gotten user name
	 * @return database result
	 */
	public DatabaseResult getById(String id) {
		// get user container of system which holds all users
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

	@Override
	public boolean addEntity(User entity) {
		return BaseRepository.users().add(entity);
	}

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
		// TODO Auto-generated method stub
		return null;
	}
}