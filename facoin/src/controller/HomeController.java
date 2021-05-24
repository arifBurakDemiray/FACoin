package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import enums.ESort;
import exception.HttpRequestException;
import mediator.HomeMediator;

/**
 * This class handles home screen requests
 */
public class HomeController extends Consumable {

	private HomeMediator mediator;

	public HomeController(HomeMediator mediator) {
		this.mediator = mediator;
		mediator.getView().addSelectCoinListener((new SelectCoinListener()));
		mediator.getView().addLogoutButtonListener(new LogoutButtonListener());
		mediator.getView().addAscendingOrderListener(new AscendingOrderListener());
		mediator.getView().addDescendingOrderListener(new DescendingOrderListener());
		mediator.getView().addWalletButtonListener(new WalletButtonListener());
		mediator.getView().addFavButtonListener(new FavButtonListener());
		mediator.getView().addOrderButtonListener(new OrderButtonListener());
	}

	// by this class user can select one of the outfits
	class SelectCoinListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() > 1) {
				try {
					mediator.getSelectedCoinView();
				} catch (HttpRequestException e1) {
					// if there is a request error do not show it to user
				}
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

	class WalletButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mediator.goWalletPage();
		}

	}

	class LogoutButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mediator.logout();
		}

	}

	class AscendingOrderListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mediator.sort(ESort.ASCENDING); // show list ascending order
		}

	}

	class DescendingOrderListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mediator.sort(ESort.DESCENDING); // show list descending order
		}

	}

	class FavButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mediator.favListView(); // show favorites
		}

	}

	class OrderButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mediator.ordersView(); // show order view
		}

	}

}
