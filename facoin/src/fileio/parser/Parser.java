package fileio.parser;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.xml.sax.SAXException;

import exception.FileReadException;

/**
 * This function is a middleware for parser classes
 *
 */
public class Parser {
	private UserParser userParser;
	private CryptoWalletParser cryptoWalletParser;
	private BankWalletParser bankWalletParser;
	private CoinParser coinParser;
	private BanknoteParser banknoteParser;
	private TransactionParser transactionParser;

	public Parser() {
		this.userParser = new UserParser();
		this.cryptoWalletParser = new CryptoWalletParser();
		this.bankWalletParser = new BankWalletParser();
		this.coinParser = new CoinParser();
		this.banknoteParser = new BanknoteParser();
		this.transactionParser = new TransactionParser();
	}

	/**
	 * The function parses gotten file content of users.xml
	 * 
	 * @param fileAll = users.xml file content
	 * @throws FileReadException
	 */
	public void parseUsers(String fileAll) throws FileReadException {
		try {
			userParser.parseUsers(fileAll);
		} catch (SAXException | IOException | ParserConfigurationException e) {
			throw new FileReadException(e.getMessage());
		}
	}

	/**
	 * The function parses gotten file content of crypto_wallets.json
	 * 
	 * @param fileAll = crypto_wallets.json file content
	 * @throws FileReadException
	 */
	public void parseCryptoWallets(String fileAll) throws FileReadException {
		try {
			cryptoWalletParser.parseWallets(fileAll);
		} catch (JSONException e) {
			throw new FileReadException(e.getMessage());
		}
	}

	/**
	 * The function parses gotten file content of bank_wallets.json
	 * 
	 * @param fileAll = bank_wallets.json file content
	 * @throws FileReadException
	 */
	public void parseBankWallets(String fileAll) throws FileReadException {
		try {
			bankWalletParser.parseWallets(fileAll);
		} catch (JSONException e) {
			throw new FileReadException(e.getMessage());
		}
	}

	/**
	 * The function parses gotten file content of coins.json
	 * 
	 * @param fileAll = coins.json file content
	 * @throws FileReadException
	 */
	public void parseCoins(String fileAll) throws FileReadException {
		try {
			coinParser.parseCurrencies(fileAll);
		} catch (JSONException e) {
			throw new FileReadException(e.getMessage());
		}
	}

	/**
	 * The function parses gotten file content of banknotes.json
	 * 
	 * @param fileAll = banknotes.json file content
	 * @throws FileReadException
	 */
	public void parseBanknotes(String fileAll) throws FileReadException {
		try {
			banknoteParser.parseCurrencies(fileAll);
		} catch (JSONException e) {
			throw new FileReadException(e.getMessage());
		}
	}

	/**
	 * The function parses gotten file content of transactions.json
	 * 
	 * @param fileAll = transactions.json file content
	 * @throws FileReadException
	 */
	public void parseTransactions(String fileAll) throws FileReadException {
		try {
			transactionParser.parseTransactions(fileAll);
		} catch (JSONException e) {
			throw new FileReadException(e.getMessage());
		}
	}

}
