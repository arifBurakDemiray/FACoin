package factory.validator;

import factory.objects.CandleParams;

public class CandleValidator {

	/**
	 * The function validates hour candles with gotten params
	 * 
	 * @param params = CandleParams that includes candle's info
	 * @return ValidationResult
	 */
	public static ValidationResult validateHourCandle(CandleParams params) {

		// Validate that high>low
		ValidationResult highLowResult = validateHighLowValues(params.high, params.low);
		boolean isValid = highLowResult.isValid;

		if (isValid) {
			// If high low result is valid, return true validation
			return new ValidationResult(true, "Validated.");
		}
		// Else return false validation
		return new ValidationResult(highLowResult.messages);

	}

	/**
	 * The function validates day candles with gotten params
	 * 
	 * @param params = CandleParams that includes candle's info
	 * @return ValidationResult
	 */
	public static ValidationResult validateDayCandle(CandleParams params) {

		// Validate that high>low
		ValidationResult highLowResult = validateHighLowValues(params.high, params.low);
		boolean isValid = highLowResult.isValid;

		if (isValid) {
			// If high low result is valid, return true validation
			return new ValidationResult(true, "Validated.");
		}

		// Else return false validation
		return new ValidationResult(highLowResult.messages);
	}

	/**
	 * The function validates that high>low. If high is lower than low, return false
	 * 
	 * @param high = high value of candle
	 * @param low  = low value of candle
	 * @return ValidationResult
	 */
	private static ValidationResult validateHighLowValues(Double high, Double low) {
		ValidationResult result = new ValidationResult("Invalid hig-low result.");

		if (high >= low) {
			result = new ValidationResult(true, "Valid high low");
		}
		return result;
	}

}
