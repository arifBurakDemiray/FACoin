package fileio.writer;

import exception.FileWriteException;
import model.Currency;
import model.Transaction;
import model.User;
import model.Wallet;
import storage.IContainer;

public class Writer {

	private CoinWriter coinWriter;
	private BanknoteWriter banknoteWriter;
	private UserWriter userWriter;
	private CryptoWalletWriter cryptoWalletWriter;
	private BankWalletWriter bankWalletWriter;
	private TransactionWriter transactionWriter;

	public Writer() {
		coinWriter = new CoinWriter();
		banknoteWriter = new BanknoteWriter();
		userWriter = new UserWriter();
		cryptoWalletWriter = new CryptoWalletWriter();
		bankWalletWriter = new BankWalletWriter();
		transactionWriter = new TransactionWriter();
	}

	public String writeUsersToString(IContainer<User> users) throws FileWriteException {
		String result = userWriter.writeUsersXml(users);
		return result;
	}

	public String writeCoinsToString(IContainer<Currency> coins) throws FileWriteException {
		String result = coinWriter.writeCoinsJson(coins);
		return result;
	}

	public String writeBanknotesToString(IContainer<Currency> banknotes) throws FileWriteException {
		String result = banknoteWriter.writeBanknotesJson(banknotes);
		return result;
	}

	public String writeCryptoWalletsToString(IContainer<Wallet> cryptoWallets) throws FileWriteException {
		String result = cryptoWalletWriter.writeCryptoWalletsJson(cryptoWallets);
		return result;
	}

	public String writeBankWalletsToString(IContainer<Wallet> bankWallets) throws FileWriteException {
		String result = bankWalletWriter.writeBankWalletsJson(bankWallets);
		return result;
	}

	public String writeTransactionsToString(IContainer<Transaction> transactions) throws FileWriteException {
		String result = transactionWriter.writeTransactionsJson(transactions);
		return result;
	}
}
