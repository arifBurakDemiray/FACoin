package factory.validator;

import factory.RandomFactory;
import fileio.repository.DatabaseResult;
import fileio.repository.IRestrictedRepository;
import fileio.repository.UserRepository;
import model.User;

public class UserValidator {

	public static ValidationResult validateUser(String userName, String password, String userId) {
		ValidationResult userNameResult = validateUserName(userName);
		ValidationResult passwordResult = validatePassword(password);
		ValidationResult userIdResult = validateUserId(userId);

		boolean areValidationsOK = userNameResult.isValid && passwordResult.isValid && userIdResult.isValid;
		ValidationResult validationResult = new ValidationResult("Not validated.");
		if (areValidationsOK) {
			validationResult = new ValidationResult(true, "Validated.");
			return validationResult;
		}

		return validationResult;
	}

	private static ValidationResult validateUserName(String userName) {
		ValidationResult validationResult = new ValidationResult("Username is already in User Repository");
		IRestrictedRepository<User> userRepository = new UserRepository();
		
		DatabaseResult resultUserName; resultUserName = userRepository.getByName(userName);
		if (resultUserName.getObject() == null) {
			validationResult = new ValidationResult(true, "");
		}

		
		return validationResult;

	}

	private static ValidationResult validatePassword(String password) {
		boolean isAboveLength = password.length() >= 6;
		return new ValidationResult(isAboveLength, "Password is not valid");
	}

	private static ValidationResult validateUserId(String userId) {
		IRestrictedRepository<User> userRepository = new UserRepository();
		DatabaseResult userIdResult = userRepository.getById(userId);
		if (userIdResult.getObject() == null) {
			return new ValidationResult(true, "Validated");
		}
		String new_id = RandomFactory.randomId();
		while (userRepository.getById(new_id).getObject() != null) {
			new_id = RandomFactory.randomId();
		}
		return new ValidationResult(new_id);
	}

}