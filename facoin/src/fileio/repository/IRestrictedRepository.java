package fileio.repository;

import java.util.Iterator;

public interface IRestrictedRepository<T> {

	/**
	 * The function tries to find the object of given id in the repository
	 * 
	 * @param id
	 * @return DatabaseResult
	 */
	public DatabaseResult getById(String id);

	/**
	 * The function tries to find the object of given name in the repository
	 * 
	 * @param name
	 * @return DatabaseResult
	 */
	public DatabaseResult getByName(String name);

	/**
	 * The function returns the iterator of container of repository
	 * 
	 * @return Iterator
	 */
	public Iterator<T> getAll();

	/**
	 * The function writes the repository content to the file
	 * 
	 * @return DatabaseResult
	 */
	public DatabaseResult saveChanges();

	/**
	 * The function controls that repository is empty or not
	 * 
	 * @return boolean
	 */
	public boolean isEmpty();
}