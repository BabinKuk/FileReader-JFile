package com.reader.filefactory;

/**
 * factory class that creates objects
 * 
 * @author nbabic
 * 
 */
public abstract class FileFactory {

	public static final int BNK = 0;
	public static final int ZAP = 1;
	public static final int ERR = 2;
	public static final int ATTR = 3;
	public static final int EXCP = 4;

	// factory method - returns object
	public static IFile getFileObject(int type) {
		// System.out.println("FileFactory.getFileObject() : " + type);

		switch (type) {
		case ZAP:
			return new ZapFileObject();
		default:
			return null;
		}
	}

	// factory method - returns object
	public static IAttribErrorSet getMap(int type) {
		//System.out.println("FileFactory.getSet()");
		switch (type) {
		case ERR:
			return new MapObject();
		case ATTR:
			return new MapObject();
		case EXCP:
			return new MapObject();
		default:
			return null;
		}
	}

	public static BnkMapObject getBnkMap(int type) {
		switch (type) {
		case BNK:
			return new BnkMapObject();
		default:
			return null;
		}
	}
	
}
