package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import exception.HttpRequestException;
import mediator.WalletMediator;

/**
 * This class handles home screen requests
 */
public class WalletController extends Consumable {

	private WalletMediator mediator;

	public WalletController(WalletMediator mediator) {
		this.mediator = mediator; // add listeners to view
		mediator.getView().addSelectCoinListener((new SelectCoinListener()));
		mediator.getView().addBackButtonListener(new BackButtonListener());
		mediator.getView().addBankButtonListener(new BankButtonListener());
		mediator.getView().addCryptoButtonListener(new CryptoButtonListener());
		mediator.getView().addPayListener(new PayButtonListener());
		mediator.getView().addDepositListener(new DepositButtonListener());
	}

	// by this class user can select one of the outfits
	class SelectCoinListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() > 1) {
				try {
					mediator.getSelectedCoinView();
				} catch (HttpRequestException e1) { // if there is an error while making a request
					mediator.showAlert(e1.getMessage()); // print message to user
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

	class BackButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mediator.back(); // go back
		}

	}

	class BankButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mediator.bankView(); // show bank entites
		}

	}

	class CryptoButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mediator.cryptoView(); // show crypto entiites
		}

	}

	class PayButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mediator.depositBanknote(); // deposit money to bank wallet
		}

	}

	class DepositButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mediator.showPay(); // show deposit components
		}
	}
}
