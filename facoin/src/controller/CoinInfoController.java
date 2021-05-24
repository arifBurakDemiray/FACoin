package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mediator.CoinInfoMediator;

/**
 * This class handles home screen requests
 */
public class CoinInfoController extends Consumable {

	private CoinInfoMediator mediator;

	public CoinInfoController(CoinInfoMediator mediator) {
		this.mediator = mediator; // add listeners
		mediator.getView().addBackButtonListener(new BackButtonListener());
		mediator.getView().addDayCandleButtonListener(new DayCandleButtonListener());
		mediator.getView().addHourCandleButtonListener(new HourCandleButtonListener());
		mediator.getView().addBuyButtonListener(new BuyButtonListener());
		mediator.getView().addSellButtonListener(new SellButtonListener());
		mediator.getView().addFavoriteButtonListener(new FavoriteButtonListener());

	}

	class BackButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mediator.back();
		}

	}

	class DayCandleButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mediator.dayCandleChart(); // show day candles
		}

	}

	class HourCandleButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mediator.hourCandleChart(); // show hour candles
		}

	}

	class BuyButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mediator.buyCoin(); // make buy request
		}

	}

	class SellButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mediator.sellCoin(); // make sell request
		}

	}

	class FavoriteButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mediator.favorite(); // add/remove favorites
		}

	}
}
