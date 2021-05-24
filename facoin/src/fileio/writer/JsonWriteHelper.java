package fileio.writer;

import org.json.JSONException;
import org.json.JSONObject;

import exception.FileWriteException;
import fileio.parser.JSONParser;

public class JsonWriteHelper {

	private static JSONParser jsonParser = new JSONParser();
	
	protected static String validateJsonObject(String fileContent) throws FileWriteException{
		String result = "";
		try {
			JSONObject jso = jsonParser.parse(fileContent);
			result = jso.toString(4);
		} catch (JSONException e) {
			throw new FileWriteException(e.getMessage());
		}
		return result;
		
	}
}
