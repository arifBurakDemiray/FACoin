package service;

/**
 * The WalletServiceParam is used in the order process to hold transaction info
 */
public class WalletServiceParam {
	protected String coinName;
	protected String banknoteName;
	protected Double coinQuantity;
	protected Double coinValue;
	protected String coinId;
	protected String banknoteId;

	public WalletServiceParam(String coinName, String banknoteName, Double coinQuantity, Double coinValue,
			String coinId, String banknoteId) {
		this.coinName = coinName;
		this.banknoteName = banknoteName;
		this.coinQuantity = coinQuantity;
		this.coinValue = coinValue;
		this.coinId = coinId;
		this.banknoteId = banknoteId;
	}

}