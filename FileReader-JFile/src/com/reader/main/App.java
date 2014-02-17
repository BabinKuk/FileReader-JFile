package com.reader.main;

import com.reader.filehandler.FileHandler;

/**
 * reading files with FileReader
 * 
 * @author nbabic
 * 
 */
public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileHandler fileHandler = new FileHandler();
		fileHandler.handleInputs();
	}

}
