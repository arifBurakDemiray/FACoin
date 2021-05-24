package factory;

import factory.objects.CandleParams;
import factory.validator.CandleValidator;
import factory.validator.ValidationResult;
import model.Candle;
import model.DayCandle;

public class DayCandleFactory extends CandleFactory {
	public DayCandleFactory() {
		super(null);
		super.setFactory(this);
	}

	@Override
	public Object createEntity(Object args) {
		Candle result = null;

		// If gotten args are not CandleParams, return null
		if (!(args instanceof CandleParams))
			return result;
		CandleParams tempArgs = (CandleParams) args;
		// Validate gotten args
		ValidationResult vr = CandleValidator.validateDayCandle(tempArgs);

		// If args not validated, return null
		if (!vr.isValid) {
			return result;
		}
		// If args are valid, create a day candle with args' params
		result = new DayCandle(tempArgs);
		return result;
	}

}