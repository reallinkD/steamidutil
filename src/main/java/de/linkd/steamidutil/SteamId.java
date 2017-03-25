package de.linkd.steamidutil;

/**
 * @author github/reallinkD
 * @see <a href="https://developer.valvesoftware.com/wiki/SteamID">https://developer.valvesoftware.com/wiki/SteamID</a>
 */
public class SteamId {
	public static final String STEAM3ID_PATTERN = "\\[U:1:\\d+\\]";
	public static final String STEAMID32_PATTERN = "STEAM_0:[01]:\\d+";
	public static final String STEAMID64_PATTERN = "\\d{17}";
	public static final long STEAMID64_OFFSET = 76561197960265728L;
	private String steam3Id;

	/**
	 * 
	 * @param steamId
	 *            a Steam user's Steam ID, either in the steam3Id, steamId32 or steamId64 format
	 * @throws InvalidSteamIdFormatException
	 *             if steamId is an invalid Steam ID
	 */
	public SteamId(String steamId) {
		if (steamId.matches(STEAM3ID_PATTERN))
			steam3Id = steamId;
		else if (steamId.matches(STEAMID32_PATTERN))
			steam3Id = convertId32To3Id(steamId);
		else if (steamId.matches(STEAMID64_PATTERN))
			steam3Id = convertId64To3Id(steamId);
		else
			throw new InvalidSteamIdFormatException("invalid format for steamId " + steamId);
	}

	/**
	 * 
	 * @return this <code>SteamId</code>'s steam3Id value
	 */
	public String getSteam3Id() {
		return steam3Id;
	}

	/**
	 * 
	 * @return this <code>SteamId</code>'s steamId32 value
	 */
	public String getSteamId32() {
		return convert3IdToId32(steam3Id);
	}

	/**
	 * 
	 * @return this <code>SteamId</code>'s steamId64 value
	 */
	public String getSteamId64() {
		return convert3IdToId64(steam3Id);
	}

	/**
	 * Converts a steam3Id to steamId32 format.
	 * 
	 * @param steam3Id
	 *            the steam3Id to convert
	 * @return the steamId32 value of <code>steam3Id</code>
	 * @see #getSteamId32()
	 */
	public static String convert3IdToId32(String steam3Id) {
		if (!steam3Id.matches(STEAM3ID_PATTERN))
			throw new InvalidSteamIdFormatException("invalid format for steam3Id " + steam3Id);

		int z = Integer.parseInt(steam3Id.replace("[U:1:", "").replace("]", ""));
		return "STEAM_0:" + (z % 2) + ":" + ((z - z % 2) / 2);
	}

	/**
	 * Converts a steam3Id to steamId64 format.
	 * 
	 * @param steam3Id
	 *            the steam3Id to convert
	 * @return the steamId64 value of <code>steam3Id</code>
	 * @see #getSteamId64()
	 */
	public static String convert3IdToId64(String steam3Id) {
		if (!steam3Id.matches(STEAM3ID_PATTERN))
			throw new InvalidSteamIdFormatException("invalid format for steam3Id " + steam3Id);

		return String.valueOf(Long.parseLong(steam3Id.replace("[U:1:", "").replace("]", "")) + STEAMID64_OFFSET);
	}

	/**
	 * Converts a steamId32 value to steam3Id format.
	 * 
	 * @param steamId32
	 *            the steamId32 to convert
	 * @return the steam3Id value of <code>steamId32</code>
	 * @see #getSteam3Id()
	 */
	public static String convertId32To3Id(String steamId32) {
		if (!steamId32.matches(STEAMID32_PATTERN))
			throw new InvalidSteamIdFormatException("invalid format for steamId32 " + steamId32);

		return convertId64To3Id(convertId32ToId64(steamId32));
	}

	/**
	 * Converts a steamId32 value to steamId64 format.
	 * 
	 * @param steamId32
	 *            the steamId32 to convert
	 * @return the steamId64 value of <code>steamId32</code>
	 * @see #getSteamId64()
	 */
	public static String convertId32ToId64(String steamId32) {
		if (!steamId32.matches(STEAMID32_PATTERN))
			throw new InvalidSteamIdFormatException("invalid format for steamId32 " + steamId32);

		int y = Integer.parseInt(steamId32.split(":")[1]);
		int z = Integer.parseInt(steamId32.split(":")[2]);
		return String.valueOf(z * 2 + y + STEAMID64_OFFSET);
	}

	/**
	 * Converts a steamId64 value to steam3Id format.
	 * 
	 * @param steamId64
	 *            the steamId64 to convert
	 * @return the steam3Id value of <code>steamId64</code>
	 * @see #getSteam3Id()
	 */
	public static String convertId64To3Id(String steamId64) {
		if (!steamId64.matches(STEAMID64_PATTERN))
			throw new InvalidSteamIdFormatException("invalid format for steamId64 " + steamId64);
		else if (Long.parseLong(steamId64) < STEAMID64_OFFSET)
			throw new InvalidSteamIdFormatException("the numerical value of steamId64 must be bigger than or equal to " + STEAMID64_OFFSET);

		return "[U:1:" + (Long.parseLong(steamId64) - STEAMID64_OFFSET) + "]";
	}

	/**
	 * Converts a steamId64 value to steamId32 format.
	 * 
	 * @param steamId64
	 *            the steamId64 to convert
	 * @return the steamId32 value of <code>steamId64</code>
	 * @see #getSteamId32()
	 */
	public static String convertId64ToId32(String steamId64) {
		if (!steamId64.matches(STEAMID64_PATTERN))
			throw new InvalidSteamIdFormatException("invalid format for steamId64 " + steamId64);
		else if (Long.parseLong(steamId64) < STEAMID64_OFFSET)
			throw new InvalidSteamIdFormatException("the numerical value of steamId64 must be bigger than or equal to " + STEAMID64_OFFSET);

		return convert3IdToId32(convertId64To3Id(steamId64));
	}

	@Override
	public String toString() {
		return getClass().getName() + "@" + Integer.toHexString(hashCode()) + "[steam3Id=" + steam3Id + "]";
	}
}
