package view;

import view.color.ColorPalette;
import view.list.List;
import view.list.WalletEntityList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class WalletView extends AppView {

	private static final long serialVersionUID = -1948466354580780395L;

	private List walletEntityList;
	private JScrollPane scrollPane;
	private ColorPalette palette;
	private JButton back;
	private final String cryptoWalletId;
	private final String bankWalletId;
	private JButton bankWallet;
	private JButton cryptoWallet;
	private JComboBox<String> comboBox;
	private JButton deposit;
	private JTextField quantity;
	private JButton pay;
	private JLabel infoLabel;
	private JLabel alert;

	public WalletView(String cryId, String bankId) {
		this.cryptoWalletId = cryId;
		this.bankWalletId = bankId;
		setLayout(null);
		walletEntityList = new WalletEntityList(null);

		back = new JButton("Back");
		back.setFont(new Font("Arial", Font.BOLD, 14));
		back.setBounds(628, 50, 90, 30);
		add(back);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(225, 150, 320, 300);
		scrollPane.setViewportView(walletEntityList);
		add(scrollPane);

		bankWallet = new JButton("BANK");
		bankWallet.setBounds(225, 100, 90, 30);
		add(bankWallet);

		cryptoWallet = new JButton("CRYPTO");
		cryptoWallet.setBounds(455, 100, 90, 30);
		add(cryptoWallet);

		comboBox = new JComboBox<String>();
		comboBox.setBounds(scrollPane.getX() - 150, scrollPane.getY() + 50, 100, 30);
		comboBox.setVisible(false);
		add(comboBox);

		deposit = new JButton("Deposit");
		deposit.setBounds(comboBox.getX(), comboBox.getY() - 50, 100, 30);
		deposit.setFont(new Font("Arial", Font.BOLD, 14));
		add(deposit);

		quantity = new JTextField("Quantity");
		quantity.setBounds(comboBox.getX(), comboBox.getY() + 50, 100, 30);
		quantity.setFont(new Font("Arial", Font.BOLD, 14));
		quantity.setVisible(false);
		add(quantity);

		pay = new JButton("Pay");
		pay.setBounds(comboBox.getX(), comboBox.getY() + 100, 100, 30);
		pay.setFont(new Font("Arial", Font.BOLD, 16));
		pay.setVisible(false);
		add(pay);

		infoLabel = new JLabel(
				"<html>In every 10 seconds, corresponding values of<br>crypto wallet and bank wallet are updated automatically."
						+ "<br>You can see coin info view by clicking twice on the coin"
						+ "<br>You can add money using deposit.</html>");
		infoLabel.setBounds(200, 400, 500, 200);
		infoLabel.setForeground(Color.CYAN);
		add(infoLabel);

		alert = new JLabel("");
		alert.setBounds(0, 0, 500, 50);
		alert.setFont(new Font("Arial", Font.BOLD, 14));
		alert.setVisible(false);
		add(alert);

		AppWindow.FRAME.getContentPane().removeAll();
		AppWindow.FRAME.getContentPane().add(this);
	}

	/**
	 * The function returns the selected coin.
	 * 
	 * @param String of selected user name
	 */
	public JLabel getListSelected() {
		return walletEntityList.getSelectedValue();
	}

	@Override
	public void setPalette(ColorPalette palette) {
		this.palette = palette;
		updateColor();

	}

	/**
	 * This function sets combo box values
	 * 
	 * @param values
	 */
	public void setComboBox(String[] values) {
		for (String string : values) {
			comboBox.addItem(string);
		}
	}

	/**
	 * This function gets deposit values from combo box and quantity text field
	 * 
	 * @return
	 */
	public String[] getValues() {
		String[] values = { (String) comboBox.getSelectedItem(), quantity.getText() };
		return values;
	}

	/**
	 * The function helps for detecting of selecting a outfit of a collection in the
	 * followed users' collections using listener
	 * 
	 * @param listener
	 */
	public void addSelectCoinListener(MouseListener listener) {
		walletEntityList.addMouseListener(listener);
	}

	public void addPayListener(ActionListener listener) {
		pay.addActionListener(listener);
	}

	public void addDepositListener(ActionListener listener) {
		deposit.addActionListener(listener);
	}

	private void updateColor() {
		setBackground(palette.BACKGROUND);
		back.setBackground(palette.FIRST_COLOR);
		back.setForeground(palette.BACKGROUND);
		walletEntityList.setBackground(palette.BACKGROUND);
		scrollPane.setBackground(palette.BACKGROUND);
		cryptoWallet.setBackground(palette.BACKGROUND);
		cryptoWallet.setForeground(palette.SECOND_COLOR);
		bankWallet.setBackground(palette.BACKGROUND);
		bankWallet.setForeground(palette.SECOND_COLOR);
		deposit.setBackground(palette.FIRST_COLOR);
		deposit.setForeground(palette.BACKGROUND);
		pay.setBackground(palette.BACKGROUND);
		pay.setForeground(palette.SECOND_COLOR);
		quantity.setBackground(palette.BACKGROUND);
		quantity.setForeground(palette.FIRST_COLOR);
		comboBox.setBackground(palette.BACKGROUND);
		comboBox.setForeground(palette.FIRST_COLOR);
		alert.setBackground(palette.BACKGROUND);
		alert.setForeground(palette.FIRST_COLOR);
	}

	public void addBackButtonListener(ActionListener listener) {
		back.addActionListener(listener);
	}

	public void addBankButtonListener(ActionListener listener) {
		bankWallet.addActionListener(listener);
	}

	public void addCryptoButtonListener(ActionListener listener) {
		cryptoWallet.addActionListener(listener);
	}

	@Override
	public void setList(List list) {
		walletEntityList.setModel(list.getList());
		walletEntityList.setCellRenderer(new JListRenderer());
		updateUI();
	}

	/**
	 * This function hides elements given below
	 */
	public void hidePay() {
		pay.setVisible(false);
		quantity.setVisible(false);
		comboBox.setVisible(false);
	}

	/**
	 * This function shows pay button, quantity field and combo box
	 */
	public void showPay() {
		pay.setVisible(true);
		quantity.setVisible(true);
		comboBox.setVisible(true);

	}

	/**
	 * This function shows an alert by given string
	 * 
	 * @param string
	 */
	public void showAlert(String string) {
		alert.setText(string);
		alert.setVisible(true);
		new Timer(2000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				alert.setVisible(false);
			}
		}).start();
	}

	@Override
	public List getList() {
		return this.walletEntityList;
	}

	public final String getBankId() {
		return this.bankWalletId;
	}

	public final String getCryptoId() {
		return this.cryptoWalletId;
	}
}
