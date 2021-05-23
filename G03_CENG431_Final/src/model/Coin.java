package model;

import java.util.Dictionary;

/**
 * This class is Coin class whþch values depends on Banknotes
 */
public class Coin extends Currency {

	public Coin(String id, Dictionary<String, Double> value, String name) {
		super(id, value, name);
	}

	public Coin(String id, String name) {
		super(id, name);
	}
}