package model;

import state.TransactionType;

/**
 * This class holds infos for a transaction
 */
public class Transaction extends AbstractEntity {

	private Currency coin;
	private Currency banknote;
	private TransactionType type;
	private Double coinQuantity;
	private Double coinValue;

	public Transaction(String id, Currency coin, Currency banknote, TransactionType type, Double coinQuantity,
			Double coinValue) {
		super(id);
		this.coin = coin;
		this.banknote = banknote;
		this.type = type;
		this.coinQuantity = coinQuantity;
		this.coinValue = coinValue;
	}

	public Transaction(String id, Currency coin, Currency banknote, Double coinQuantity, Double coinValue) {
		super(id);
		this.coin = coin;
		this.banknote = banknote;
		this.type = new TransactionType();
		this.coinQuantity = coinQuantity;
		this.coinValue = coinValue;
	}

	public Double getCoinQuantity() {
		return this.coinQuantity;
	}

	public Double getCoinValue() {
		return this.coinValue;
	}

	public String getTransactionState() {
		return this.type.getState();
	}

	public Currency getCoin() {
		return this.coin;
	}

	public Currency getBanknote() {
		return this.banknote;
	}

	/**
	 * This function approves a pending transaction
	 */
	public void approveTransaction() {
		this.type.Approve();
	}

	public boolean equals(String id) {
		return this.getId().equals(id);
	}

}
