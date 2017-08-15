package com.honey.storage;

public class MsgObj {
	private String text;
	
	public MsgObj(String text) {
		this.setText(text);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o==null) return false;
		if(!(o instanceof MsgObj)) return false;
		if(this==o) return true;
		MsgObj o2 = (MsgObj) o;
		if(o2.getText().equals(this.text))
			return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = result*31+text !=null ? text.hashCode():0;
		return result;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
