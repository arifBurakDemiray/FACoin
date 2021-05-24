package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import mediator.OrderMediator;

/**
 * This class takes requests in Order view and sends them to mediator
 */
public class OrderController extends Consumable {

	private OrderMediator mediator;

	public OrderController(OrderMediator mediator) {
		this.mediator = mediator;
		this.mediator.getView().addBackButtonListener(new BackButtonListener());
		this.mediator.getView().addRejectButtonListener(new RejectButtonListener());
		this.mediator.getView().addTransactionListener(new TransactionListListener());

	}

	class BackButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mediator.back();
		}

	}

	class TransactionListListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() > 1) {
				mediator.rejectTransactionBridge(); // show cancel button and password field
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	}

	class RejectButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mediator.rejectTransaction(); // cancel a transaction if not approved
		}
	}

}
