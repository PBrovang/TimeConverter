package org.tool.time.parser;

public class HelpMessage {
	private final String flag;
	private final String text;
	
	public HelpMessage(String flag, String text) {
		super();
		this.flag    = replaceNullWithEmpty(flag);
		this.text = replaceNullWithEmpty(text);
	}

	private static String replaceNullWithEmpty(String text) {
		return (text == null ? "" : text);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HelpMessage other = (HelpMessage) obj;
		if (!flag.equals(other.flag))
			return false;
		if (!text.equals(other.text))
			return false;
		return true;
	}

	public String getFlag() {
		return flag;
	}

	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [-" + flag + ", " + text + "]";
	}	
}