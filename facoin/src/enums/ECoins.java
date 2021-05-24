package enums;

/**
 * This enum holds coins of the system, if new coin will ve added to system it
 * also should be added here
 */
public enum ECoins {
	BTC, LTC, DOGE, AVAX, BTT, MATIC, LINK, HT, ETH, FIL, XRP, HOT, DOT, ADA, ALGO;

	public static boolean isCoin(String value) {
		try {
			ECoins.valueOf(value); // if value is coin return true
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

}
