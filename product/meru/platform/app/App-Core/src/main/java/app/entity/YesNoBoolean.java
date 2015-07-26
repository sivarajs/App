package app.entity;

public enum YesNoBoolean {

	Yes('Y'),
	No('N');
	
	private char ch;
	
	private YesNoBoolean(char ch) {
		this.ch = ch;
	}
	
	public char getValue() {
		return ch;
	}
}
