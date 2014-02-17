package com.reader.filefactory;

public class MapObject implements IAttribErrorSet {

	private String key;
	private String desc;
	
	@Override
	public void parseString(String txt) {
		//System.out.println("MapObject.parseString() : " + txt);
		String[] strings = SplitText(txt, "=");
		//System.out.println("strings : " + strings.length);
		
		key = strings[0];
		desc = strings[1];
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public String getDesc() {
		return desc;
	}
	
	public static String[] SplitText(String Subject, String Delimiters) {
		//System.out.println("OdgFileObject.SplitText() : " + Subject + " : " + Delimiters);
		return Subject.split(Delimiters);
	}

}
