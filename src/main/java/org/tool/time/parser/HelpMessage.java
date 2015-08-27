package org.tool.time.parser;

public class HelpMessage {
	private static final String FLAG_IS_NULL_EXCEPTION = "Flag can not be null";
	private final Flag flag;
	private final String text;
	
	public HelpMessage(Flag flag, String text) throws IllegalArgumentException {
		super();
		if (flag == null)
			throw new IllegalArgumentException(FLAG_IS_NULL_EXCEPTION);
		this.flag = flag;
		this.text = (text == null ? "" : text);
	}
	
	public HelpMessage(String value, String text) {
		this((new Flag(value)), text);
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

	public Flag getFlag() {
		return flag;
	}

	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [" + flag + ", " + text + "]";
	}	
}