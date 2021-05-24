package view;

import view.color.ColorPalette;
import view.list.CoinList;
import view.list.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class HomeView extends AppView {

	private static final long serialVersionUID = 4450313830956267453L;

	private List coinList;
	private JScrollPane scrollPane;
	private ColorPalette palette;
	private JLabel infoLabel;
	private JButton logout;
	private JButton wallet;
	private JButton favs;
	private JButton toLow;
	private JButton toHigh;
	private JButton orders;
	private final String userId;

	public HomeView(String userId) {
		this.userId = userId;
		setLayout(null);

		coinList = new CoinList(null);

		logout = new JButton("Logout");
		logout.setFont(new Font("Arial", Font.BOLD, 14));
		logout.setBounds(628, 50, 90, 30);
		add(logout);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(225, 150, 320, 300);
		scrollPane.setViewportView(coinList);
		add(scrollPane);

		favs = new JButton("\u2605");
		favs.setBounds(225, 116, 55, 25);
		favs.setFont(new Font("Arial Unicode MS", Font.BOLD, 20));
		add(favs);

		toLow = new JButton("\u2193");
		toLow.setBounds(440, 116, 50, 25);
		toLow.setFont(new Font("Arial", Font.BOLD, 20));
		add(toLow);

		toHigh = new JButton("\u2191");
		toHigh.setBounds(495, 116, 50, 25);
		toHigh.setFont(new Font("Arial", Font.BOLD, 20));
		add(toHigh);

		orders = new JButton("Orders");
		orders.setBounds(50, scrollPane.getY() + scrollPane.getHeight() + 25, 100, 50);
		orders.setFont(new Font("Arial", Font.BOLD, 20));
		add(orders);

		wallet = new JButton("Wallet");
		wallet.setBounds(50, 50, 100, 50);
		wallet.setFont(new Font("Arial", Font.BOLD, 20));
		add(wallet);

		infoLabel = new JLabel(
				"<html>In every 10 seconds, values are updated automatically.<br>You can sort the list with the arrow buttons.<br>You can see your favorites using star button.<br>You can see the coin info view by clicking twice on a coin.</html>");
		infoLabel.setBounds(225, 450, 600, 100);
		infoLabel.setForeground(Color.CYAN);
		add(infoLabel);

		AppWindow.FRAME.getContentPane().removeAll();
		AppWindow.FRAME.getContentPane().add(this);
	}

	/**
	 * The function returns the selected coin.
	 * 
	 * @param String of selected user name
	 */
	public JLabel getListSelected() {
		return coinList.getSelectedValue();
	}

	@Override
	public void setPalette(ColorPalette palette) {
		this.palette = palette;
		updateColor();

	}

	/**
	 * The function helps for detecting of selecting a outfit of a collection in the
	 * followed users' collections using listener
	 * 
	 * @param listener
	 */
	public void addSelectCoinListener(MouseListener listener) {
		coinList.addMouseListener(listener);
	}

	public void addWalletButtonListener(ActionListener listener) {
		wallet.addActionListener(listener);
	}

	public void addAscendingOrderListener(ActionListener listener) {
		toHigh.addActionListener(listener);
	}

	public void addDescendingOrderListener(ActionListener listener) {
		toLow.addActionListener(listener);
	}

	public void addFavButtonListener(ActionListener listener) {
		favs.addActionListener(listener);
	}

	public void addOrderButtonListener(ActionListener listener) {
		orders.addActionListener(listener);
	}

	public void addLogoutButtonListener(ActionListener listener) {
		logout.addActionListener(listener);
	}

	private void updateColor() {
		setBackground(palette.BACKGROUND);
		logout.setBackground(palette.FIRST_COLOR);
		logout.setForeground(palette.BACKGROUND);
		coinList.setBackground(palette.BACKGROUND);
		scrollPane.setBackground(palette.BACKGROUND);
		favs.setBackground(palette.FIRST_COLOR);
		favs.setForeground(palette.BACKGROUND);
		toLow.setBackground(palette.BACKGROUND);
		toLow.setForeground(palette.SECOND_COLOR);
		toHigh.setBackground(palette.BACKGROUND);
		toHigh.setForeground(palette.SECOND_COLOR);
		wallet.setBackground(palette.BACKGROUND);
		wallet.setForeground(palette.SECOND_COLOR);
		orders.setBackground(palette.BACKGROUND);
		orders.setForeground(palette.SECOND_COLOR);
	}

	@Override
	public void setList(List list) {
		coinList.setModel(list.getList());
		coinList.setCellRenderer(new JListRenderer());
		updateUI();
	}

	@Override
	public List getList() {
		return this.coinList;
	}

	public final String getUserId() {
		return this.userId;
	}
}
