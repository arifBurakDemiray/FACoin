package service;

import java.util.Iterator;

import fileio.repository.BanknoteRepository;
import fileio.repository.DatabaseResult;
import fileio.repository.IRestrictedRepository;
import model.Currency;
import model.Wallet;
import view.WalletView;

public class DepositPayService {

	private WalletService service;
	private IRestrictedRepository<Currency> banknotes;

	public DepositPayService() {
		service = new WalletService();
		banknotes = new BanknoteRepository();
	}

	/**
	 * To show the banknotes of the system in the deposit process get each banknote
	 * and add the names to the comboBox
	 * 
	 * @param view
	 */
	public void setComboBoxValues(WalletView view) {

		// Get banknotes of the system
		Iterator<Currency> banknotesIT = banknotes.getAll();
		Currency banknote = null;
		String names = "";

		// Get each banknote name and add to the comboBox
		while (banknotesIT.hasNext()) {
			banknote = banknotesIT.next();
			names += banknote.getName() + ",";
		}
		if (names.endsWith(",")) { // if ends with , ignore it
			names = names.substring(0, names.length() - 1);
		}
		view.setComboBox(names.split(","));
	}

	/**
	 * The function executes deposit process. According to selected banknote and
	 * given quantity do the deposit.
	 * 
	 * @param bankWallet   = user's bank wallet
	 * @param banknoteName = selected banknote like USD
	 * @param quantity     = given quantity to add to bank wallet
	 * @return
	 */
	public boolean processDepositRequest(Wallet bankWallet, String banknoteName, String quantity) {
		Double banknoteQuantity = 0.0;
		try {
			banknoteQuantity = Double.valueOf(quantity);
		} catch (NumberFormatException e) {
			return false;
		}
		DatabaseResult drResult = banknotes.getByName(banknoteName);
		if (drResult.getObject() == null) {
			return false;
		}
		Currency banknote = (Currency) drResult.getObject();
		service.setBankWalletQuantity(bankWallet, banknote, banknoteQuantity);
		return true;
	}
}
