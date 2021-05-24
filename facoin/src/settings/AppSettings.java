package settings;

import javax.swing.ImageIcon;

/**
 * This class holds project wide informations for classes to use
 */
public class AppSettings {

	public final static String CRYPTO_API_KEY = "YOUR_API_KEY";
	public final static String DATE_ENDPOINT = "https://showcase.api.linx.twenty57.net/UnixTime/tounixtimestamp?datetime=now";
	public final static String CANDLE_PERIOD = "30";
	public static String DAY_CANDLE_ENDPOINT = "https://min-api.cryptocompare.com/data/v2/histoday?fsym=%s&tsym=%s&limit="
			+ CANDLE_PERIOD + "&api_key=" + CRYPTO_API_KEY;
	public static String HOUR_CANDLE_ENDPOINT = "https://min-api.cryptocompare.com/data/v2/histohour?fsym=%s&tsym=%s&limit="
			+ CANDLE_PERIOD + "&api_key=" + CRYPTO_API_KEY;
	public final static int HEIGHT = 600;
	public final static int WIDTH = 800;
	public final static ImageIcon LOGO = new ImageIcon("data//FaclogoLittle.png");
	public final static ImageIcon ICON = new ImageIcon("data//FacIcon.png");
	public static int SCREEN_X = 0;
	public static int SCREEN_Y = 0;
}
