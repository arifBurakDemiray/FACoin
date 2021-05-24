package mediator;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import fileio.repository.BankWalletRepository;
import fileio.repository.BanknoteRepository;
import fileio.repository.CryptoWalletRepository;
import fileio.repository.DatabaseResult;
import fileio.repository.IRestrictedRepository;
import model.BankWallet;
import model.CryptoWallet;
import model.Currency;
import model.WalletEntity;
import view.LabelInfo;
import view.color.ColorPalette;
import view.color.DarkTheme;

public class WalletListMediator {

	private IRestrictedRepository<Currency> banknoteRepository;
	private String bankId; // bankWallet id of user
	private String cryptoId; // cryptoWallet id of user
	private final Color COLOR = (new ColorPalette(new DarkTheme())).FIRST_COLOR;

	public WalletListMediator(String cryptoId, String bankId) {
		this.bankId = bankId;
		this.cryptoId = cryptoId;
		banknoteRepository = new BanknoteRepository();
	}

	/**
	 * The function returns the labelInfo objects of user wallet entities.
	 * 
	 * @param walletEntity = user's wallet's entities
	 * @return List<LabelInfo>
	 */
	private List<LabelInfo> getCryptoLabel(WalletEntity walletEntity) {

		List<LabelInfo> labels = new ArrayList<LabelInfo>();
		final Iterator<Currency> banknoteIterator = banknoteRepository.getAll();
		Currency banknote = null;
		// get entity's coin object
		Currency currency = (Currency) walletEntity.getCurrency();
		// get entity's quantity
		double quantity = walletEntity.getQuantity();

		// For each banknote in the system, get entity coin's label
		// Example : entity coin is BTC
		// Get BTC/USD and BTC/TRY labels
		while (banknoteIterator.hasNext()) {

			banknote = banknoteIterator.next();

			// Get the coin's value according to banknote
			Double price = currency.getValue().get(banknote.getName());
			if (price == null) {
				price = (double) 0;
			}
			Double value = price * quantity;
//			if (value.equals(null)) {
//				value = (double) 0;
//			}

			// Create LabelInfo which holds the text to show
			LabelInfo newLabel = new LabelInfo(COLOR, quantity, currency.getName(), value, banknote.name);
			labels.add(newLabel); // add label to list
		}
		return labels;
	}

	private List<LabelInfo> getBanknoteLabel(WalletEntity walletEntity) {

		List<LabelInfo> labels = new ArrayList<LabelInfo>();
		Currency currency = (Currency) walletEntity.getCurrency();
		double quantity = walletEntity.getQuantity();

		Double value = 1 * quantity;
		if (value.equals(null)) {
			value = (double) 0;
		}
		LabelInfo newLabel = new LabelInfo(COLOR, quantity, currency.getName(), value, "");
		labels.add(newLabel);
		return labels;
	}

	/**
	 * The function returns the list model of crypto wallet's entities
	 * 
	 * @return DefaultListModel<JLabel>
	 */
	public DefaultListModel<JLabel> getCryptoWalletList() {
		DefaultListModel<JLabel> list = new DefaultListModel<JLabel>();
		// Get the user's crypto wallet
		DatabaseResult walletResult = (new CryptoWalletRepository()).getById(cryptoId);
		if (walletResult.getObject() != null) {

			CryptoWallet wallet = (CryptoWallet) walletResult.getObject();

			// For each coin entity in the user crypto wallet,
			// get created label and add them to the listModel
			for (WalletEntity walletEntity : wallet.getEntities().getContainer()) {
				List<LabelInfo> labels = getCryptoLabel(walletEntity);
				setLabels(labels, list);
			}
		}

		return list;
	}

	/**
	 * The function returns the list model of bank wallet's entities
	 * 
	 * @return DefaultListModel<JLabel>
	 */
	public DefaultListModel<JLabel> getBankWalletList() {
		DefaultListModel<JLabel> list = new DefaultListModel<JLabel>();

		// Get the user's bank wallet
		DatabaseResult walletResult = (new BankWalletRepository()).getById(bankId);
		if (walletResult.getObject() != null) {

			BankWallet wallet = (BankWallet) walletResult.getObject();

			// For each banknote entity in the user banknote wallet,
			// get created label and add them to the listModel
			for (WalletEntity walletEntity : wallet.getEntities().getContainer()) {
				List<LabelInfo> labels = getBanknoteLabel(walletEntity);
				setLabels(labels, list);
			}
		}

		return list;
	}

	/**
	 * For each entity of wallet, get created label and add them to the listModel
	 * 
	 * @param labels    = JLabels' info object
	 * @param listModel
	 */
	public void setLabels(List<LabelInfo> labels, DefaultListModel<JLabel> listModel) {
		int index = 0;
		for (LabelInfo labelInfo : labels) {
			JLabel label = createJLabel(labelInfo);
			listModel.add(index, label);
			index++;
		}
	}

	/**
	 * Create JLabel according to gotten label info
	 * 
	 * @param label = LabelInfo that holds wallet entity info
	 * @return
	 */
	public JLabel createJLabel(LabelInfo label) {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(4);

		// Set label text
		String text = label.coinName + "/" + label.banknote + " : \tQuantity : " + label.percent + " - Value : "
				+ df.format(label.value);
		JLabel jLabel = new JLabel(text);
		jLabel.setForeground(label.color);
		return jLabel;
	}

}
