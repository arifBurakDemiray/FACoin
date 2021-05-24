package service;

import javax.swing.JLabel;
import fileio.repository.CoinRepository;
import fileio.repository.DatabaseResult;
import model.ICurrency;

public class JLabelService {
	private final CoinRepository currencies;

	public JLabelService() {
		currencies = new CoinRepository();
	}

	/**
	 * Return the JLabel Set the label text accoring to given coin name and banknote
	 * name
	 * 
	 * @param coinName
	 * @param banknoteName
	 * @return
	 */
	public JLabel getCoinLabel(String coinName, String banknoteName) {
		JLabel label = new JLabel();
		// Get coin object from the coin repository
		DatabaseResult coinResult = currencies.getByName(coinName);
		if (coinResult.getObject() != null) {
			ICurrency coin = (ICurrency) coinResult.getObject();
			// Set text
			label.setText(coinName + "/" + banknoteName + " " + coin.getValue().get(banknoteName));
		}
		return label;
	}
}
