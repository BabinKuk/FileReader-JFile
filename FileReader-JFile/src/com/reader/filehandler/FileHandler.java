package com.reader.filehandler;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.reader.filefactory.FileFactory;
import com.reader.filefactory.IFile;

public class FileHandler {

	static ArrayList<IFile> listODG = new ArrayList<IFile>();
	static HashMap<String, String> bnkMap = new HashMap<String, String>();
	static HashMap<String, String> errorMap = new HashMap<String, String>();
	static HashMap<String, String> attribMap = new HashMap<String, String>();
	static HashMap<String, String> exceptionMap = new HashMap<String, String>();

	private static final String FILE_BNK_EXT = ".BNK";
	private static final String FILE_ZAP_EXT = ".ZAP";

	public void handleInputs() {
		
		System.out.println("--- Finding BNK ---");
		String bnkFileName = findFile(FILE_BNK_EXT);
		if (bnkFileName == null) {
			System.err.println("BNK file is not entered. Exit...");
			System.exit(1);
		}
		File fileBNK = new File(bnkFileName);
		
		System.out.println("\n--- Reading BNK ---");
		Reader readBnkFile = new Reader(fileBNK, FileFactory.BNK);
		bnkMap = readBnkFile.readBnkFile();
		//System.out.println(bnkMap);
		
		System.out.println("\n--- Finding ZAP ---");
		String odgkFileName = findFile(FILE_ZAP_EXT);
		if (odgkFileName == null) {
			System.err.println("ZAP file is not entered. Exit...");
			System.exit(1);
		}
		File fileODG = new File(odgkFileName);
		
		System.out.println("\n--- Reading ZAP ---");
		Reader readOdgFile = new Reader(fileODG, FileFactory.ZAP);
		listODG = readOdgFile.readOdgFile();
		// System.out.println(listODG.size());
		
		System.out.println("\n--- Reading ErrList ---");
		File fileError = new File("ErrorList.txt");
		if (fileError.exists()) {
			Reader readErrList = new Reader(fileError, FileFactory.ERR);
			errorMap = readErrList.readMap();
			//System.out.println(errorMap);
		} else {
			System.err.println("ErrorList.txt is not entered. Exit...");
			System.exit(1);
		}
		
		System.out.println("\n--- Reading AttributeList ---");
		File fileAttribList = new File("AttributeList.txt");
		if (fileAttribList.exists()) {
			Reader readAttribList = new Reader(fileAttribList, FileFactory.ATTR);
			attribMap = readAttribList.readMap();
			//System.out.println(attribMap);
		} else {
			System.err.println("AttributeList.txt is not entered. Exit...");
			System.exit(1);
		}
		
		System.out.println("\n--- Reading ExceptionList ---");
		File fileExceptionList = new File("ExceptionList.txt");
		if (fileExceptionList.exists()) {
			Reader readExceptionList = new Reader(fileExceptionList, FileFactory.EXCP);
			exceptionMap = readExceptionList.readMap();
			//System.out.println(attribMap);
		} else {
			System.err.println("ExceptionList.txt is not entered. Exit...");
			System.exit(1);
		}
	
		// go!
		handleFiles();

	}

	private void handleFiles() {
		System.out.println("\n--- Handling files ---");
		FileCreator fileCreator = new FileCreator(bnkMap, listODG, errorMap,
				attribMap, exceptionMap);
		try {
			fileCreator.handleFiles();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to create file");
		}

	}

	public String findFile(String ext) {

		GenericExtFilter filter = new GenericExtFilter(ext);

		String workingDir = System.getProperty("user.dir");
		//System.out.println(workingDir);

		File dir = new File(workingDir);
		
		boolean checkDir = dir.isDirectory();
		if (checkDir == false) {
			System.out.println("Directory does not exists : " + workingDir);
			return null;
		}

		// list out all the file name and filter by the extension
		String[] list = dir.list(filter);
		
		if (list.length == 0) {
			System.out.println("no files end with : " + ext);
			return null;
		}
		
		String temp = new String();
		for (String file : list) {
			temp = new StringBuffer(workingDir).append(File.separator)
					.append(file).toString();
			System.out.println("File "+ ext + " : " + temp);
		}
		return temp;
	}

	// inner class, generic extension filter
	public class GenericExtFilter implements FilenameFilter {

		private String ext;

		public GenericExtFilter(String ext) {
			this.ext = ext;
		}

		public boolean accept(File dir, String name) {
			return (name.endsWith(ext));
		}
	}

}
