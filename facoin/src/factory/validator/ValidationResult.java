package factory.validator;

public class ValidationResult{
	public boolean isValid;
	public String messages;
	public ValidationResult(boolean validity, String messages){
		this.isValid = validity;
		this.messages = messages;
	}
	
	public ValidationResult(String messages){
		this(false,messages);
	}
	
	public ValidationResult(){
		this(false,"Not Validated");
	}
	
	public void addMessage(String message){
		this.messages+=message;
	}
}
