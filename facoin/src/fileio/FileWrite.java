package fileio;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import exception.FileWriteException;

public class FileWrite {

	protected FileWrite() {

	}
	
	/**
	 * The function writes content to the file of given path
	 * @param fileContent
	 * @param filePath
	 * @throws FileWriteException
	 */
	protected void writeFileContent(String fileContent, String filePath) throws FileWriteException {
		FileWriter fw = openFile(filePath);
		Writer writer = new BufferedWriter(fw);
		writeContentToFile(writer,fileContent);
		
	}
	
	private void writeContentToFile(Writer writer, String fileContent) throws FileWriteException {
		try {
			writer.write(fileContent);
			writer.close();
		} catch (IOException e) {
			throw new FileWriteException(e.getMessage());
		}
	}
	
	private FileWriter openFile(String filePath) throws FileWriteException{
		FileWriter fw;
		try {
			fw = new FileWriter(filePath);
		} catch (IOException e) {
			throw new FileWriteException(e.getMessage());
		}
		return fw;
	}
}
