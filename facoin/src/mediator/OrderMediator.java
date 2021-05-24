package mediator;

import java.awt.Color;
import javax.swing.JLabel;

import controller.Consumable;
import controller.IConsumable;
import controller.OrderController;
import model.User;
import service.OrderService;
import view.OrderView;
import view.color.ColorPalette;
import view.color.DarkTheme;
import view.decorator.DarkThemeDecorator;
import view.decorator.Decorator;
import view.decorator.OrderListDecorator;

public class OrderMediator extends Consumable {

	private final User user; // logged in user
	private final OrderView view;
	private final IConsumable controller;
	private Color approvedColor;
	private Decorator listDecorator; // for the transactions which is updated every 10 seconds
	private OrderService service;

	public OrderMediator(User user) {
		this.approvedColor = (new ColorPalette(new DarkTheme())).SECOND_COLOR;
		this.user = user;
		view = new OrderView(user.getId());
		new DarkThemeDecorator(view);
		listDecorator = new OrderListDecorator(view);
		UpdatePool.POOL.add(listDecorator);
		controller = new OrderController(this);
		service = new OrderService(user);

	}

	/**
	 * The function do the back process. OrderView is closed and home view is open.
	 */
	public void back() {
		controller.supressNotUsed();
		UpdatePool.POOL.remove(listDecorator);
		getView().setVisible(false);
		IConsumable mediator = new HomeMediator(user);
		mediator.supressNotUsed();

	}

	/**
	 * If user wants to cancel a pending transaction Get the selected pending
	 * transaction If user types password correctly, cancel transaction using order
	 * service
	 */
	public void rejectTransaction() {
		// Get typed password
		String password = view.getPassword();
		view.hideReject();

		// If password is wrong, don't do process
		if (!password.equals(user.getPassword())) {
			view.showAlert();
			return;
		}
		// Get selected transaction
		JLabel label = view.getListSelected();
		if (label == null)
			return;

		// Cancel transaction
		service.setRejectProcesses(label.getText(), listDecorator);
		UpdatePool.POOL.add(listDecorator);
	}

	/**
	 * The function controls that selected transaction's type is pending or not
	 */
	public void rejectTransactionBridge() {
		JLabel label = view.getListSelected();
		if (label == null)
			return;

		// If it is a approved transaction, don't do process
		if (label.getForeground().equals(approvedColor))
			return;
		UpdatePool.POOL.remove(listDecorator);
		view.showReject();
	}

	/**
	 * @return the view
	 */
	public OrderView getView() {
		return view;
	}

}
