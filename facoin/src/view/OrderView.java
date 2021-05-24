package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.Timer;

import view.color.ColorPalette;
import view.list.List;
import view.list.OrderList;

public class OrderView extends AppView {

	private static final long serialVersionUID = -1948466354580780395L;

	private List orderList;
	private JScrollPane scrollPane;
	private ColorPalette palette;
	private JButton back;
	private JButton reject;
	private JPasswordField password;
	private JLabel alert;
	private final String userId;
	private JLabel infoLabel;

	public OrderView(String userId) {
		this.userId = userId;
		setLayout(null);

		back = new JButton("Back");
		back.setBounds(628, 50, 90, 30);
		back.setFont(new Font("Arial", Font.BOLD, 20));
		add(back);

		orderList = new OrderList(null);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(175, 50, 370, 300);
		scrollPane.setViewportView(orderList);
		add(scrollPane);

		reject = new JButton("Cancel");
		reject.setBounds(scrollPane.getWidth() / 2 + scrollPane.getX() - 50,
				scrollPane.getY() + scrollPane.getHeight() + 70, 100, 40);
		reject.setFont(new Font("Arial", Font.BOLD, 20));
		reject.setVisible(false);
		add(reject);

		password = new JPasswordField("Password");
		password.setBounds(reject.getX(), reject.getY() - 50, 100, 40);
		password.setFont(new Font("Arial", Font.PLAIN, 20));
		password.setVisible(false);
		add(password);

		alert = new JLabel("Password is wrong");
		alert.setBounds(reject.getX() + reject.getWidth() / 2 - 85, reject.getY() + 40, 200, 40);
		alert.setFont(new Font("Arial", Font.BOLD, 20));
		alert.setVisible(false);
		add(alert);

		infoLabel = new JLabel(
				"<html>In every 10 seconds, transactions are executed automatically.<br>You can cancel pending transaction<br>by clicking twice on it and typing password</html>");
		infoLabel.setBounds(175, 400, 500, 200);
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
		return orderList.getSelectedValue();
	}

	/**
	 * The function helps for detecting of selecting a outfit of a collection in the
	 * followed users' collections using listener
	 * 
	 * @param listener
	 */
	public void addTransactionListener(MouseListener listener) {
		orderList.addMouseListener(listener);
	}

	public void addBackButtonListener(ActionListener listener) {
		back.addActionListener(listener);
	}

	public void addRejectButtonListener(ActionListener listener) {
		reject.addActionListener(listener);
	}

	private void updateColor() {
		setBackground(palette.BACKGROUND);
		orderList.setBackground(palette.BACKGROUND);
		scrollPane.setBackground(palette.BACKGROUND);
		back.setBackground(palette.FIRST_COLOR);
		back.setForeground(palette.BACKGROUND);
		reject.setBackground(Color.RED);
		reject.setForeground(palette.BACKGROUND);
		password.setBackground(palette.BACKGROUND);
		password.setForeground(palette.FIRST_COLOR);
		alert.setForeground(palette.SECOND_COLOR);
	}

	@Override
	public void setPalette(ColorPalette palette) {
		this.palette = palette;
		updateColor();
	}

	@Override
	public void setList(List list) {
		orderList.setModel(list.getList());// set model and set its list renderer
		orderList.setCellRenderer(new JListRenderer());
		updateUI();
	}

	/**
	 * This function shows reject button and password field
	 */
	public void showReject() {
		reject.setVisible(true);
		password.setVisible(true);
	}

	/**
	 * This function hides reject button and password field
	 */
	public void hideReject() {
		reject.setVisible(false);
		password.setVisible(false);
	}

	/**
	 * This function shows alert for the wrong password
	 */
	public void showAlert() {
		alert.setVisible(true);
		new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				alert.setVisible(false);
			}
		}).start();
	}

	public String getPassword() {
		return new String(password.getPassword());
	}

	@Override
	public List getList() {
		return this.orderList;
	}

	public final String getUserId() {
		return this.userId;
	}
}
