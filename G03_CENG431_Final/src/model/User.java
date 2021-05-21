package model;

import java.util.Dictionary;
import storage.IContainer;

public class User  extends AbstractEntity{
	
	private String userId;
	private String userName;
	private String password;
	private Wallet cryptoWallet;
	private Wallet bankWallet;
	private Dictionary<String,String> favorites;
	private IContainer<Transaction> transactionContainer;
	
	public User(String id, String userName, String password, Wallet cryptoWallet, Wallet bankWallet, Dictionary<String,String> favorites, IContainer<Transaction> transactionContainer)
	{
		super(id);
		this.userName=userName;
		this.password = password;
		this.cryptoWallet=cryptoWallet;
		this.bankWallet = bankWallet;
		this.favorites = favorites;
		this.transactionContainer = transactionContainer;
	}
		
	public String getPassword() {
		return password;
	}
	public String getUserId() {
		return userId;
	}
	public String getUserName() {
		return userName;
	}
	public Wallet getCryptoWallet() {
		return cryptoWallet;
	}
	public Wallet getBankWallet() {
		return bankWallet;
	}

	public Dictionary<String,String> getFavorites()
	{
		return favorites;
	}	
	
	public boolean equals(int gottenId)
	{
		return (this.getId().equals(String.valueOf(gottenId)));		
	}
	
	public IContainer<Transaction> getTransactions(){
		return this.transactionContainer;
	}
	
	public boolean equals(String userName)
	{
		return this.userName.equals(userName);		
	}
	// TODO combo box bankaya para y�kleme
}
