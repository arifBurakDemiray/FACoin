package httpio.repository;

import java.util.Date;

/**
 * This class holds system date informations and system creation date
 */
public class SystemProperties {

	private final static Date SYSTEM_CREATION_DATE = new Date(1620604800); // 10 MAY 2021 00:00:00
	private static Date SYSTEM_DATE = SYSTEM_CREATION_DATE;

	public final static Date SYSTEM_DATE() {
		UPDATE_DATE(); // update date and return system date
		return SYSTEM_DATE;
	}

	protected static void setDate(Date date) {
		SYSTEM_DATE = date;
	}

	private static void UPDATE_DATE() {
		Date HTTP_DATE = HttpController.updateDate(); // update date by http controller
		if (isDateValid(HTTP_DATE)) { // if coming date is valid set system date
			setDate(HTTP_DATE);
		}
	}

	private static boolean isDateValid(Date DATE) {
		if (DATE != null) { // if date not null
			return validateTimeRange(DATE);
		} else {
			return false;
		}
	}

	// and and creation date is before coming date
	private static boolean validateTimeRange(Date DATE) {
		return SYSTEM_CREATION_DATE.before(DATE);
	}
}
