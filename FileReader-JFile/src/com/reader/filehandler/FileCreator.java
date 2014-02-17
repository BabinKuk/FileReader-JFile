package com.reader.filehandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.reader.filefactory.IFile;

public class FileCreator {
	
	private ArrayList<IFile> listODG;
	private HashMap<String, String> bnkMap;
	private HashMap<String, String> errorMap;
	private HashMap<String, String> attribMap;
	private HashMap<String, String> exceptionMap;
	
	public FileCreator(HashMap<String, String> bnkMap, ArrayList<IFile> listODG, HashMap<String, String> errorMap, HashMap<String, String> attribMap, HashMap<String, String> exceptionMap) {
		this.bnkMap = bnkMap;
		this.listODG = listODG;
		this.errorMap = errorMap;
		this.attribMap = attribMap;
		this.exceptionMap = exceptionMap;
	}
	
	public void handleFiles() throws IOException {
		//System.out.println("FileHandler.handleFiles()");
		
		File file = new File("odgovor.txt");
		
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter(file));
			//br.write("This is line one");
			//br.newLine();
			
			//iterate over arraylists
			// iterate over listODG
			Iterator<IFile> itOdg = listODG.iterator();
			while (itOdg.hasNext()) {
				IFile fileOdg = itOdg.next();
				//System.out.println(fileOdg.getField1() + " " + fileOdg.getField2() + " " + fileOdg.getField3() + " " + fileOdg.getField4());
				String strOdgField1 = fileOdg.getField1();
				String strOdgField2 = fileOdg.getField2();
				String strOdgField3 = fileOdg.getField3();
				String strOdgField4 = fileOdg.getField4();
				
				if (strOdgField1 != null) {
					boolean chkException = exceptionMap.containsKey(strOdgField2);
					//System.out.println("chkException : " + chkException);
					// if not in exception list, write to file
					if (!chkException) {
						br.write("Greska : " + strOdgField2 + "-" + errorMap.get(strOdgField2) + " : " + strOdgField3 + "-" + attribMap.get(strOdgField3)+ " : broj sloga-" + strOdgField4);
						br.newLine();
						br.write(bnkMap.get(strOdgField4));
						br.newLine();
						br.newLine();
					}
				}
				
			}
			//br.write("Last line.");
			
			br.close();
			
		} catch (IOException e) {
			System.out.println("Unable to read file " + file.toString());
		}
		
	}

}
