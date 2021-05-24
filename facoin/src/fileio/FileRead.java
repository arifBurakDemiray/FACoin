package fileio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import exception.FileReadException;

public class FileRead {

	protected FileRead() {
	}

	/**
	 * This function reads a file of given path
	 * 
	 * @param fielpath of the file
	 * @returns read file in string format
	 * @throws IOException
	 */
	protected String readFile(String filename) throws FileReadException {

		File file = new File(filename); // opening file
		BufferedReader br;
		br = openFile(file);
		String fileAll = readFileContent(br); // read file
		return fileAll;
	}

	private String readFileContent(BufferedReader reader) throws FileReadException {
		String result = "";
		String line = "";
		try {
			while ((line = reader.readLine()) != null) {
				result += line;// add all lines to the string
			}
			reader.close();
		} catch (IOException e) {
			throw new FileReadException(e.getMessage());
		}
		return result;
	}

	private BufferedReader openFile(File file) throws FileReadException {
		try {
			return new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			throw new FileReadException(e.getMessage());
		}
	}

}
