package mediator;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import fileio.repository.UserRepository;
import fileio.repository.DatabaseResult;
import storage.IContainer;
import view.LabelInfo;
import view.color.*;
import model.Transaction;
import model.User;

public class OrderListMediator {

	private final String userId;

	public OrderListMediator(String userId) {
		this.userId = userId;
	}

	/**
	 * The function returns the labelInfo objects of user transactions.
	 * 
	 * @param transactions = user's transaction container
	 * @return List<LabelInfo>
	 */
	private List<LabelInfo> getLabel(IContainer<Transaction> transactions) {

		List<LabelInfo> labels = new ArrayList<LabelInfo>();

		// For each transaction of user, get transaction label
		for (Transaction transaction : transactions) {
			ColorPalette palette = new ColorPalette(new DarkTheme());
			Color color = palette.SECOND_COLOR;
			if (transaction.getTransactionState().equals("Pending")) {
				color = palette.FIRST_COLOR;
			}
			// Create LabelInfo which holds the text to show
			LabelInfo newLabel = new LabelInfo(color, transaction.getCoinQuantity(),
					(transaction.getId() + " : " + transaction.getCoin().getName()), transaction.getCoinValue(),
					transaction.getBanknote().getName());
			labels.add(newLabel); // add label to list
		}
		return labels;
	}

	/**
	 * The function returns the list model of user's transactions
	 * 
	 * @return DefaultListModel<JLabel>
	 */
	public DefaultListModel<JLabel> getList() {
		Thread.LOCK_MUTEX(Thread.MUTEX);
		DefaultListModel<JLabel> list = new DefaultListModel<JLabel>();
		// Get the user's transactions
		DatabaseResult userResult = (new UserRepository()).getById(userId);
		if (userResult.getObject() == null) {
			return list;
		}
		IContainer<Transaction> userTransactions = ((User) userResult.getObject()).getTransactions();

		// set labels with gotten transactions
		setLabels(getLabel(userTransactions), list);
		Thread.UNLOCK_MUTEX(Thread.MUTEX);
		return list;
	}

	/**
	 * For each transaction of user, get created label and add them to the listModel
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
	 * @param label = LabelInfo that holds transaction info
	 * @return
	 */
	public JLabel createJLabel(LabelInfo label) {

		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(4);
		ColorPalette palette = new ColorPalette(new DarkTheme());
		String transactionType = "Approved";
		if (label.color.equals(palette.FIRST_COLOR)) {
			transactionType = "Pending";
		}
		if (label.percent < 0)
			label.percent *= -1;

		// Set label text
		String text = label.coinName + "/" + label.banknote + "/" + transactionType + " :\tQuantity = " + label.percent
				+ " - CoinValue = " + df.format(label.value);

		JLabel jLabel = new JLabel(text);
		jLabel.setForeground(label.color);

		return jLabel;
	}
}
