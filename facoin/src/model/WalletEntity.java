package model;

import storage.Name;

/**
 * This class represents a wallet entity
 */
public class WalletEntity {
	private ICurrency currency; // if a currency has value it can put to the wallet
	private Double quantity; // quantity of that currency

	public WalletEntity(ICurrency currency, Double quantity) {
		this.currency = currency;
		this.quantity = quantity;
	}

	public double getTotal(String banknoteName) { // this function gets total of an entity by given banknote type
		Double value = this.currency.getValue().get(banknoteName);
		if (value == null)
			value = 1.0;
		return value * quantity;
	}

	public ICurrency getCurrency() {
		return this.currency;
	}

	public Double getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public String getCurrencyName() {
		return currency.getClass().getSimpleName();
	}

	public boolean equals(Name gottenName) {
		return this.currency.getName().equals(gottenName.name);
	}
}