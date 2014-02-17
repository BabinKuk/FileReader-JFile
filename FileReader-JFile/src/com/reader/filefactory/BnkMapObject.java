package com.reader.filefactory;

public class BnkMapObject implements IAttribErrorSet {

	private String key;
	private String desc;
	
	@Override
	public void parseString(String txt) {
		//System.out.println("BnkFileObject.parseString() : " + txt);
		//String[] strings = SplitText(txt, ";");
		//System.out.println("strings : " + strings.length);
		
		//field1 = txt;
		
		//System.out.println(field1 + " ; " + field2 + " ; " + field3 + " ; " + field4);
		
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String txt) {
		desc = txt;
	}
	
	private void formatKey(int count) {
		String formatCount = "\"" + String.format("%6s", count).replace(" ", "0") + "\"";
		//System.out.println(formatCount);
		key = formatCount;
	}

	public void setKey(int count) {
		//System.out.println("BnkMapObject.setKey() : " + count);
		formatKey(count);
	}

}
