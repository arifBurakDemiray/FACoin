package model;

import java.util.Dictionary;

/**
 * If a class has a value it should implement this class
 */
public interface ICurrency {

	/**
	 * This function returns values of a currency in dictionary format
	 * 
	 * @return
	 */
	public Dictionary<String, Double> getValue();

	/**
	 * This function returns old values of a currency in dictionary format
	 * 
	 * @return
	 */
	public Dictionary<String, Double> getOldValue();

	/**
	 * This function returns name of a currency
	 * 
	 * @return
	 */
	public String getName();
}