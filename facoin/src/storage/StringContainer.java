package storage;

import exception.NotSupportedException;

public class StringContainer extends Container<String> {

	@Override
	public String getById(String id) throws NotSupportedException {
		throw new NotSupportedException(
				"src.storage.StringContainer.getById() function is not supported for StringContainer.");
	}

	@Override
	public String getByName(String name) throws NotSupportedException {
		throw new NotSupportedException(
				"src.storage.StringContainer.getByName() function is not supported for StringContainer.");
	}

	
	@Override
	public String getByName(Name name) throws NotSupportedException {
		throw new NotSupportedException(
				"src.storage.StringContainer.getByName() function is not supported for StringContainer.");
	}
	/**
	 * This class simply converts a StringContainer to String[]
	 * 
	 * @return array value of StringContainer
	 */
	public String[] toArray() {
		String[] array = new String[this.getLength()];
		int i = 0;
		for (String value : this.getContainer()) {
			array[i] = value;
			i++;
		}
		return array;
	}
}
