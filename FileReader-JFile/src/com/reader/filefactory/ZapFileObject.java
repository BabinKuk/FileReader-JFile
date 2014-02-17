package com.reader.filefactory;

public class ZapFileObject implements IFile {

	private String field1;
	private String field2;
	private String field3;
	private String field4;
	
	@Override
	public void parseString(String txt) {
		//System.out.println("OdgFileObject.parseString() : " + txt);
		//
		String[] strings = SplitText(txt, ";");
		//System.out.println("strings : " + strings.length);
		
		if (strings[0].equals("\"JZO\"")) {
			field1 = strings[0];
			field2 = strings[1];
			field3 = strings[2];
			field4 = strings[3];
		}
		//System.out.println(field1 + " ; " + field2 + " ; " + field3 + " ; " + field4);
	}

	@Override
	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	@Override
	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	@Override
	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}

	@Override
	public String getField4() {
		return field4;
	}

	public void setField4(String field4) {
		this.field4 = field4;
	}
	
	public static String[] SplitText(String Subject, String Delimiters) {
		//System.out.println("OdgFileObject.SplitText() : " + Subject + " : " + Delimiters);
		return Subject.split(Delimiters);
	}
	
}
