package com.reader.filehandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.reader.filefactory.BnkMapObject;
import com.reader.filefactory.FileFactory;
import com.reader.filefactory.IAttribErrorSet;
import com.reader.filefactory.IFile;

public class Reader {
	
	private File file;
	private int type;
		
	public Reader(File file, int type) {
		this.file = file;
		this.type = type;
	}

	public ArrayList<IFile> readOdgFile() {
		
		System.out.println("ReadFile.read()");
		
		ArrayList<IFile> list = new ArrayList<IFile>();
		
		BufferedReader br = null;
	     
	    try {
	        FileReader fr = new FileReader(file);
	        br = new BufferedReader(fr);
	         
	        String line;
	         
	        while ((line = br.readLine()) != null) {
	            //System.out.println(line);
	            //create object from each line
	            IFile fileObject = FileFactory.getFileObject(type);
	            fileObject.parseString(line);
	            list.add(fileObject);
	        }
	         
	    } catch (FileNotFoundException e) {
	        System.out.println("File not found: " + file.toString());
	    } catch (IOException e) {
	        System.out.println("Unable to read file: " + file.toString());
	    } finally {
	        try {
	            br.close();
	        } catch (IOException e) {
	            System.out.println("Unable to close file: " + file.toString());
	        }
	        catch(NullPointerException ex) {
	            // File was probably never opened!
	        }
	    }
		return list;
	}
	
	public HashMap<String, String> readBnkFile() {
		System.out.println("ReadFile.readBnkFile()");
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		BufferedReader br = null;
		 
		try {
			FileReader fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line;
			int count = 1;
			while ((line = br.readLine()) != null) {
				//System.out.println(line + " - " + count);
				//create object from each line
				BnkMapObject mapObject = FileFactory.getBnkMap(type);
				mapObject.setDesc(line);
				mapObject.setKey(count);
				map.put(mapObject.getKey(), mapObject.getDesc());
				count++;
			}
     
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + file.toString());
		} catch (IOException e) {
			System.out.println("Unable to read file: " + file.toString());
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				System.out.println("Unable to close file: " + file.toString());
			} catch(NullPointerException ex) {
				// File was probably never opened!
			}
		}
		return map;
	}
	
	public HashMap<String, String> readMap() {
		System.out.println("ReadFile.readList()");
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		BufferedReader br = null;
	     
	    try {
	        FileReader fr = new FileReader(file);
	        br = new BufferedReader(fr);
	         
	        String line;
	         
	        while ((line = br.readLine()) != null) {
	            //System.out.println(line);
	            //create object from each line
	            IAttribErrorSet mapObject = FileFactory.getMap(type);
	            mapObject.parseString(line);
	            map.put(mapObject.getKey(), mapObject.getDesc());
	        }
	         
	    } catch (FileNotFoundException e) {
	        System.out.println("File not found: " + file.toString());
	    } catch (IOException e) {
	        System.out.println("Unable to read file: " + file.toString());
	    }
	    finally {
	        try {
	            br.close();
	        } catch (IOException e) {
	            System.out.println("Unable to close file: " + file.toString());
	        }
	        catch(NullPointerException ex) {
	            // File was probably never opened!
	        }
	    }
		return map;
	}
	
}
