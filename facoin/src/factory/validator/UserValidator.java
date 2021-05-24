package factory.validator;

import factory.RandomFactory;
import fileio.repository.DatabaseResult;
import fileio.repository.IRestrictedRepository;
import fileio.repository.UserRepository;
import model.User;

public class UserValidator {

	/**
	 * The function tries to validate user with given user name and password
	 * 
	 * @param userName
	 * @param password
	 * @return ValidationResult
	 */
	public static ValidationResult validateUser(String userName, String password) {

		// Validate user name
		ValidationResult userNameResult = validateUserName(userName);

		// Validate password
		ValidationResult passwordResult = validatePassword(password);

		boolean areValidationsOK = userNameResult.isValid && passwordResult.isValid;
		ValidationResult validationResult = new ValidationResult("Not validated.");

		// If validation is OK, return true validation,
		// else return false validation
		if (areValidationsOK) {
			validationResult = new ValidationResult(true, "Validated.");
			return validationResult;
		}

		return validationResult;
	}

	/**
	 * The function tries to validate user name
	 * 
	 * @param userName
	 * @return ValidationResult
	 */
	private static ValidationResult validateUserName(String userName) {
		ValidationResult validationResult = new ValidationResult("Username is already in User Repository");

		// If there is already an user with given name in the system's user repository
		// return false validation, else return true validation
		IRestrictedRepository<User> userRepository = new UserRepository();

		DatabaseResult resultUserName = userRepository.getByName(userName);
		if (resultUserName == null) {
			validationResult = new ValidationResult(true, "");
		}

		return validationResult;

	}

	/**
	 * The function tries to validate password, password length must be >= 6
	 * 
	 * @param password
	 * @return ValidationResult
	 */
	private static ValidationResult validatePassword(String password) {
		boolean isAboveLength = password.length() >= 6;
		return new ValidationResult(isAboveLength, "Password is not valid");
	}

	/**
	 * The function tries to validate user id, if id is not unique, create a new id
	 * 
	 * @param userId
	 * @return ValidationResult
	 */
	public static ValidationResult validateUserId(String userId) {
		if (userId == null) {
			return new ValidationResult(false, "Invalidated");
		}

		// If there is an user with given id in the system's user repository
		// create a new unique id for user and return id in the validation result

		// If there is no user with given id in the system's user repository
		// return true validation
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
