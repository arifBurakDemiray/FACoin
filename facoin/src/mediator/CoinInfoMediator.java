package mediator;

import java.util.ArrayList;
import java.util.List;

import controller.CoinInfoController;
import controller.Consumable;
import controller.IConsumable;
import enums.ECandleType;
import fileio.repository.UserRepository;
import model.User;
import service.CandleChartService;
import service.CoinInfoService;
import service.TransactionService;
import service.WalletServiceParam;
import view.CoinInfoView;
import view.color.ColorPalette;
import view.color.DarkTheme;
import view.decorator.DarkThemeDecorator;
import view.decorator.Decorator;
import view.decorator.TextDecorator;

public class CoinInfoMediator extends Consumable {

	private User user; // logged in user
	private CoinInfoView view;
	private IConsumable controller;
	private String coinName; // selected coin name of pair
	private String banknoteName; // selected banknote name of pair
	private String coinId; // selected coin id of pair
	private String banknoteId;// selected coin id of pair
	private CandleChartService service;
	private CoinInfoService coinInfoService;
	private Decorator textDecorator; // for the coin value which is updated every 10 seconds

	private boolean isDayCandle = true; // to detect which type of candle is wanted to be shown
	private boolean isHourCandle = false; // to detect which type of candle is wanted to be shown

	public CoinInfoMediator(String title, User user) {
		this.user = user;
		coinInfoService = new CoinInfoService();
		setNamesAndIds(title);
		service = new CandleChartService(coinName, banknoteName);
		view = new CoinInfoView();
		new DarkThemeDecorator(view);
		textDecorator = new TextDecorator(view, coinName, banknoteName);
		UpdatePool.POOL.add(textDecorator); // add textDecorator to pool to get currenc coin value
		controller = new CoinInfoController(this);
		service.setViewChart(ECandleType.DAY, view);
		setFavoriteButtonColor(); // set favorite button color
	}

	/**
	 * The function do the back process. CoinInfoView is closed and home view is
	 * open.
	 */
	public void back() {
		controller.supressNotUsed();
		view.setVisible(false);
		UpdatePool.POOL.remove(textDecorator);
		IConsumable mediator = new HomeMediator(user);
		mediator.supressNotUsed();
	}

	/**
	 * Get coin name, banknote name, banknote id and coin id from service
	 * 
	 * @param title = pair like BTC/USD
	 */
	private void setNamesAndIds(String title) {
		String[] namesIds = coinInfoService.setNamesAndIds(title);
		this.coinName = namesIds[0];
		this.banknoteName = namesIds[1];
		this.coinId = namesIds[2];
		this.banknoteId = namesIds[3];
	}

	/**
	 * If selected pair is in the favorites, make favorite button color yellow. Else
	 * make color blue.
	 */
	private void setFavoriteButtonColor() {
		ColorPalette palette = new ColorPalette(new DarkTheme());
		List<String> list = user.getFavorites().get(coinId);
		if (list == null) {
			list = new ArrayList<String>();
		}
		if (list.contains(banknoteId)) {
			view.setFavoriteColor(palette.FIRST_COLOR);
		} else {
			view.setFavoriteColor(palette.SECOND_COLOR);
		}
	}

	/**
	 * Add or remove the pair (coin/banknote) to the user's favorites
	 */
	public void favorite() {
		List<String> str = user.getFavorites().get(coinId);
		ColorPalette palette = new ColorPalette(new DarkTheme());

		// If coin doesn't exist in the dictionary keys, add it to dictionary
		if (str == null) {
			view.setFavoriteColor(palette.FIRST_COLOR); // set button color

			// Add the pair to the user favorites dictionary
			// Add the banknote to the value list of coin key
			List<String> list = new ArrayList<String>();
			list.add(banknoteId);
			user.getFavorites().put(coinId, list);
			(new UserRepository()).saveChanges();

		} else {
			// If banknote is not in the list of key, add bankote to the list
			if (!str.contains(banknoteId)) {
				view.setFavoriteColor(palette.FIRST_COLOR);
				str.add(banknoteId);
				(new UserRepository()).saveChanges();

			}
			// If banknote is in the list of key, remove bankote from the list
			else {
				view.setFavoriteColor(palette.SECOND_COLOR);
				List<String> list = user.getFavorites().get(coinId);
				list.remove(banknoteId);
				if (list.isEmpty()) {
					user.getFavorites().remove(coinId);
				}
				(new UserRepository()).saveChanges();
			}
		}

	}

	public CoinInfoView getView() {
		return view;
	}

	/**
	 * If day button is clicked, set chart view with day candles
	 */
	public void dayCandleChart() {
		if (!isDayCandle) {
			service.setViewChart(ECandleType.DAY, view);
			isDayCandle = true;
			isHourCandle = false;
		}
	}

	/**
	 * If hour button is clicked, set chart view with hour candles
	 */
	public void hourCandleChart() {
		if (!isHourCandle) {
			service.setViewChart(ECandleType.HOUR, view);
			isHourCandle = true;
			isDayCandle = false;
		}
	}

	/**
	 * If user wants to sell coin, get the inputs that are coin quantity and coin
	 * order value. Create a new transaction to sell coin
	 */
	public void sellCoin() {
		String[] coinQuantityAndValue = view.getSell();
		try {
			Double quantity = Double.valueOf(coinQuantityAndValue[0]);
			Double value = Double.valueOf(coinQuantityAndValue[1]);

			// Using service, create transaction, add transaction to the repo and user's
			// transactions
			TransactionService transaction = new TransactionService(user);
			WalletServiceParam params = new WalletServiceParam(coinName, banknoteName, quantity, value, coinId,
					banknoteId);
			boolean result = transaction.sellCoin(params);

			// If process is failed, show message to the user
			if (!result) {
				view.showAlert("You do not have enough coin to sell");
			}
		} catch (Exception e) {
			view.showAlert("Please use \".\" in prices and quantities when using fractions");
		}

	}

	/**
	 * If user wants to buy coin, get the inputs that are coin quantity and coin
	 * order value. Create a new transaction to buy coin
	 */
	public void buyCoin() {
		String[] coinQuantityAndValue = view.getBuy();
		try {
			Double quantity = Double.valueOf(coinQuantityAndValue[0]);
			Double value = Double.valueOf(coinQuantityAndValue[1]);
			// Using service, create transaction, add transaction to the repo and user's
			// transactions
			TransactionService transaction = new TransactionService(user);
			WalletServiceParam params = new WalletServiceParam(coinName, banknoteName, quantity, value, coinId,
					banknoteId);
			boolean result = transaction.buyCoin(params);

			// If process is failed, show message to the user
			if (!result) {
				view.showAlert("You do not have enough banknote");
			}
		} catch (Exception e) {
			view.showAlert("Please use \".\" in prices and quantities when using fractions");
		}
	}

}