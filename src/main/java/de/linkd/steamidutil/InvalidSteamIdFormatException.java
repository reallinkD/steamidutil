package de.linkd.steamidutil;

/**
 * Thrown to indicate a parsing error due to an invalid Steam ID format.<br />
 * See <a href="https://developer.valvesoftware.com/wiki/SteamID">https://developer.valvesoftware.com/wiki/SteamID</a>.
 * 
 * @author github/reallinkD
 */
public class InvalidSteamIdFormatException extends IllegalArgumentException {
	private static final long serialVersionUID = 2639809056144528065L;

	public InvalidSteamIdFormatException() {
	}

	public InvalidSteamIdFormatException(String message) {
		super(message);
	}

	public InvalidSteamIdFormatException(Throwable throwable) {
		super(throwable);
	}

	public InvalidSteamIdFormatException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
