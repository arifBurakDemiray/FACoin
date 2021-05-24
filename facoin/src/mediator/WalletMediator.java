package mediator;

import javax.swing.JLabel;

import controller.Consumable;
import controller.IConsumable;
import controller.WalletController;
import enums.ECoins;
import exception.HttpRequestException;
import model.User;
import service.DepositPayService;
import view.WalletView;
import view.decorator.BankWalletListDecorator;
import view.decorator.CryptoWalletListDecorator;
import view.decorator.DarkThemeDecorator;
import view.decorator.JListDecorator;

public class WalletMediator extends Consumable {

	private User user; // logged in user
	private WalletView view;
	private IConsumable controller;
	private JListDecorator decorator;
	private boolean isBankView = false;
	private boolean isCryptoView = true;
	private DepositPayService service; // service to add money to bank wallet

	private boolean isDeposit = false;

	public WalletMediator(User user) {
		this.user = user;
		view = new WalletView(user.getCryptoWallet().getId(), user.getBankWallet().getId());
		decorator = new CryptoWalletListDecorator(view);
		UpdatePool.POOL.add(decorator);
		new DarkThemeDecorator(view);
		service = new DepositPayService();
		service.setComboBoxValues(view);
		controller = new WalletController(this);
	}

	public WalletView getView() {
		return view;
	}

	/**
	 * The function do the back process. Pool is updated. WalletView is closed and
	 * home view is open.
	 */
	public void back() {
		controller.supressNotUsed();
		view.setVisible(false);
		UpdatePool.POOL.remove(decorator);
		IConsumable mediator = new HomeMediator(user);
		mediator.supressNotUsed();
	}

	/**
	 * If a coin is selected in the list of crypto wallet coins open the coin info
	 * view of that coin.
	 * 
	 * @throws HttpRequestException
	 */
	public void getSelectedCoinView() throws HttpRequestException {

		JLabel label = view.getListSelected();

		if (label == null) {
			return;
		}
		String[] splittedTrade = label.getText().split("/");
		if (!ECoins.isCoin(splittedTrade[0])) {
			return;
		}

		String[] splittedItem = label.getText().split(":");
		view.setVisible(false);
		IConsumable mediator = new CoinInfoMediator(splittedItem[0], user);
		mediator.supressNotUsed();

	}

	/**
	 * If user clicks on Crypto button, set the coins' list of user crypto wallet
	 */
	public void cryptoView() {
		if (!isCryptoView) {
			UpdatePool.POOL.remove(decorator);
			decorator = new CryptoWalletListDecorator(this.view);
			UpdatePool.POOL.add(decorator);
			isCryptoView = true;
			isBankView = false;
		}

	}

	/**
	 * If user clicks on Bank button, set the banknotes' list of user bank wallet
	 */
	public void bankView() {
		if (!isBankView) {
			UpdatePool.POOL.remove(decorator);
			decorator = new BankWalletListDecorator(this.view);
			UpdatePool.POOL.add(decorator);
			isBankView = true;
			isCryptoView = false;
		}
	}

	/**
	 * If user clicks on Pay button, get the selected banknote input and quantity
	 * input, add the banknote quantity of given input
	 */
	public void depositBanknote() {
		String[] values = view.getValues();
		if (values[0] != null && values[1] != null) {
			// Add the given quantity of selected banknote to user bank wallet
			boolean result = service.processDepositRequest(user.getBankWallet(), values[0], values[1]);
			if (!result)
				view.showAlert("Please use \".\" in prices and quantities when using fractions");
		}
		view.hidePay();
	}

	/**
	 * If user clicks on Deposit button, show input boxes
	 */
	public void showPay() {
		if (!isDeposit) {
			view.showPay();
			isDeposit = true;
		} else {
			view.hidePay();
			isDeposit = false;
		}
	}

	public void showAlert(String message) {
		view.showAlert(message);

	}

}
