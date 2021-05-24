package fileio.repository;

public interface IRepository<T> {

	/**
	 * The function adds given entity to the repository
	 * 
	 * @param entity
	 * @return boolean
	 */
	public boolean addEntity(T entity);

	/**
	 * The function removes given entity from the repository
	 * 
	 * @param entity
	 * @return Entity
	 */
	public T removeEntity(T entity);

	/**
	 * The function writes the repository content to the file
	 * 
	 * @return DatabaseResult
	 */
	public DatabaseResult saveChanges();
}