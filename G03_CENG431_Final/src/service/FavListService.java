package service;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;

import fileio.repository.BanknoteRepository;
import fileio.repository.CoinRepository;
import fileio.repository.DatabaseResult;
import fileio.repository.IRestrictedRepository;
import fileio.repository.UserRepository;
import mediator.CoinListMediator;
import view.list.CoinList;
import view.list.List;
import model.Currency;
import model.User;
import java.util.Dictionary;
import java.util.Enumeration;

public class FavListService {

	private final String userId;
	private final IRestrictedRepository<User> users;
	private final IRestrictedRepository<Currency> coins;
	private final IRestrictedRepository<Currency> banknotes;

	public FavListService(String userId) {
		this.userId = userId;
		users = new UserRepository();
		coins = new CoinRepository();
		banknotes = new BanknoteRepository();
	}

	/**
	 * The function returns the List object of user's favorite coins
	 * 
	 * @return List
	 */
	public List getList() {
		List list = null;
		DatabaseResult dr = users.getById(userId);
		if (dr.getObject() == null) {
			return list;
		}

		list = getFavorites((User) dr.getObject());
		return list;
	}

	/**
	 * The function gets the user favorite coins and return a List of these coins
	 * 
	 * @param user = logged in user
	 * @return List of favorite coins
	 */
	public List getFavorites(User user) {
		CoinListMediator mediator = new CoinListMediator();

		// Get the coins of the system using listModel
		DefaultListModel<JLabel> listModel = mediator.getList();
		DefaultListModel<JLabel> favoritesModel = new DefaultListModel<JLabel>();

		// Get user favorite coins
		Dictionary<String, java.util.List<String>> favorites = user.getFavorites();

		Enumeration<String> keys = favorites.keys();

		// iterate using enumeration object for each favorite coin
		while (keys.hasMoreElements()) {

			String coinId = keys.nextElement(); // get coin id from dictionary

			// Get the value of key. Value is the JavaList<String>
			// that holds the banknote names
			// For example BTC/USD and BTC/TRY both can be favorite
			// So we can hold USD and TRY version in the one key using java list

			// Example : {"1":{"1","2"}, "4":{"1"}} means that:
			// favorites includes BTC/USD BTC/TRY AVAX/USD
			java.util.List<String> banknoteFavs = favorites.get(coinId);

			// Get coin object from system
			DatabaseResult coinResult = coins.getById(coinId);

			// Get coin name
			String coin = ((Currency) coinResult.getObject()).getName();

			// For each favored banknote of favored coin keys
			// get the JLabel
			for (String banknoteId : banknoteFavs) {

				// Get baknote object from system
				DatabaseResult banknoteResult = banknotes.getById(banknoteId);

				// Get banknote name
				String banknote = ((Currency) banknoteResult.getObject()).getName();

				// Get label
				JLabel label = findLabel(listModel, coin, banknote);
				if (label != null) {
					int index = favoritesModel.size();
					favoritesModel.add(index, label); // add label to defaultListModel
				}
			}
		}

		return new CoinList(favoritesModel);

	}

	/**
	 * Return the JLabel for the give coin/banknote pair using listModel
	 * 
	 * @param listModel = coin info list's list model that holds all pairs of system
	 * @param coin      like BTC
	 * @param banknote  like USD
	 * @return JLabel
	 */
	private JLabel findLabel(DefaultListModel<JLabel> listModel, String coin, String banknote) {
		JLabel returnedLabel = null;
		JLabel label = null;
		for (int i = 0; i < listModel.size(); i++) {
			// find the pair label in the list model
			label = listModel.get(i);

			// set text
			String text = label.getText();
			if (text.contains(coin + "/" + banknote)) {
				returnedLabel = label;
				break;
			}
		}
		return returnedLabel;

	}

}
