package view.decorator;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import mediator.OrderListMediator;
import view.AppView;
import view.OrderView;
import view.list.OrderList;

/**
 * This class sets a view jList to orders list
 */
public class OrderListDecorator extends JListDecorator {

	public OrderListDecorator(AppView view) {
		super(view);
	}

	@Override
	public void set() {
		update();
		view.setList(this.list);
	}

	@Override
	public void update() {
		String userId = ((OrderView) this.view).getUserId(); // get user id from view
		OrderListMediator mediator = new OrderListMediator(userId); // pass to order list mediator
		DefaultListModel<JLabel> listModel = mediator.getList(); // get orders list from mediator
		this.list = new OrderList(listModel); // set list
	}

}
