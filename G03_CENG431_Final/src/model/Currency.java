package model;

import java.util.Dictionary;
import java.util.Hashtable;

import storage.Name;

/**
 * This class is stores values and old values of anc currency
 */
public abstract class Currency extends AbstractEntity implements ICurrency {

	private Dictionary<String, Double> value; // it stores like for example BTC has values of type USD, ETH etc
	private Dictionary<String, Double> old_value;
	public final String name;

	public Currency(String id, String name) {
		super(id);
		this.value = new Hashtable<String, Double>();
		this.old_value = new Hashtable<String, Double>();
		this.name = name;
	}

	public Currency(String id, Dictionary<String, Double> value, String name) {
		super(id);
		this.value = value;
		this.name = name;
		this.old_value = value;
	}

	public Dictionary<String, Double> getValue() {
		return this.value;
	}

	public Dictionary<String, Double> getOldValue() {
		return this.old_value;
	}

	public void setValue(Dictionary<String, Double> value) {
		this.value = value;
	}

	public Double addValue(String banknoteName, Double value) {

		return this.value.put(banknoteName, value);
	}

	public void addOldValue(String banknoteName, Double value) {
		this.old_value.put(banknoteName, value);
	}

	public String getName() {
		return this.name;
	}

	public boolean equals(String id) {
		return this.getId().equals(id);
	}

	public boolean equals(Name Name) {
		return this.name.equals(Name.name);
	}

}
