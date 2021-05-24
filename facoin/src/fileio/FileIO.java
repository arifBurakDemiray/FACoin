package fileio;

import exception.FileReadException;
import exception.FileWriteException;
import fileio.parser.Parser;
import fileio.writer.Writer;
import model.Currency;
import model.Transaction;
import model.User;
import model.Wallet;
import storage.IContainer;

public class FileIO implements IFileIO {
	private FileRead fRead;
	private FileWrite fWrite;
	private Parser parser;
	private Writer writer;

	public FileIO() {
		this.fRead = new FileRead(); // initialise file read
		this.fWrite = new FileWrite(); // initialise file write
		this.parser = new Parser(); // initialise parser
		this.writer = new Writer(); // initialise write
	}

	@Override
	public void readBanknotes(String filePath) throws FileReadException {
		String fileAll = fRead.readFile(filePath); // read file
		if (!fileAll.isBlank()) // if not blank
			parser.parseBanknotes(fileAll);// parse banknote

	}

	@Override
	public void readBankWallets(String filePath) throws FileReadException {
		String fileAll = fRead.readFile(filePath);// read file
		if (!fileAll.isBlank())// if not blank
			parser.parseBankWallets(fileAll);// parse bank wallets

	}

	@Override
	public void readCoins(String filePath) throws FileReadException {
		String fileAll = fRead.readFile(filePath); // read file
		if (!fileAll.isBlank()) // if not blank
			parser.parseCoins(fileAll); // parse coins

	}

	@Override
	public void readCryptoWallets(String filePath) throws FileReadException {
		String fileAll = fRead.readFile(filePath);// read file
		if (!fileAll.isBlank())// if not blank
			parser.parseCryptoWallets(fileAll); // parse crypto wallets

	}

	@Override
	public void readTransactions(String filePath) throws FileReadException {
		String fileAll = fRead.readFile(filePath); // read file
		if (!fileAll.isBlank()) // if not blank
			parser.parseTransactions(fileAll); // parse transactions

	}

	@Override
	public void readUsers(String filePath) throws FileReadException {
		String fileAll = fRead.readFile(filePath); // read file
		if (!fileAll.isBlank()) // if not blank
			parser.parseUsers(fileAll); // parse users

	}

	@Override
	public void writeBanknotes(IContainer<Currency> banknotes, String filePath) throws FileWriteException {
		String banknoteResult = writer.writeCoinsToString(banknotes); // get writing content of banknotes
		fWrite.writeFileContent(banknoteResult, filePath); // write content to file
	}

	@Override
	public void writeBankWallets(IContainer<Wallet> bankWallets, String filePath) throws FileWriteException {
		String bankWalletResult = writer.writeBankWalletsToString(bankWallets); // get writing content of bank wallets
		fWrite.writeFileContent(bankWalletResult, filePath); // write content to file

	}

	@Override
	public void writeCoins(IContainer<Currency> coins, String filePath) throws FileWriteException {
		String coinResult = writer.writeCoinsToString(coins); // get writing content of coins
		fWrite.writeFileContent(coinResult, filePath); // write content to file

	}

	@Override
	public void writeCryptoWallets(IContainer<Wallet> cryptoWallets, String filePath) throws FileWriteException {
		String cryptoWalletResult = writer.writeCryptoWalletsToString(cryptoWallets); // get writing content of crypto
																						// wallets
		fWrite.writeFileContent(cryptoWalletResult, filePath); // write content to file

	}

	@Override
	public void writeTransactions(IContainer<Transaction> banknotes, String filePath) throws FileWriteException {
		String transactionResult = writer.writeTransactionsToString(banknotes); // get writing content of transactions
		fWrite.writeFileContent(transactionResult, filePath); // write content to file

	}

	@Override
	public void writeUsers(IContainer<User> users, String filePath) throws FileWriteException {
		String userResult = writer.writeUsersToString(users); // get writing content of users
		fWrite.writeFileContent(userResult, filePath); // write content to file
	}

}
