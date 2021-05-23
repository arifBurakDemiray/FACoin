package model;

import java.util.Dictionary;

/**
 * This class is Banknote class whþch values is only 1
 */
public class Banknote extends Currency {

	public Banknote(String id, String name) {
		super(id, name);
	}

	public Banknote(String id, Dictionary<String, Double> value, String name) {
		super(id, value, name);
	}

}
