package de.linkd.steamidutil;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SteamIdTest {
	@Test
	public void instantiate3Id() {
		new SteamId("[U:1:0]");
		new SteamId("[U:1:205371371]");
	}

	@Test
	public void instantiateId32() {
		new SteamId("STEAM_0:0:0");
		new SteamId("STEAM_0:1:102685685");
	}

	@Test
	public void instantiateId64() {
		new SteamId(String.valueOf(SteamId.STEAMID64_OFFSET));
		new SteamId("76561198165637099");
	}

	@Test
	public void convert3IdToId32() {
		assertEquals("STEAM_0:0:0", SteamId.convert3IdToId32("[U:1:0]"));
		assertEquals("STEAM_0:1:102685685", SteamId.convert3IdToId32("[U:1:205371371]"));
	}

	@Test
	public void convert3IdToId64() {
		assertEquals(String.valueOf(SteamId.STEAMID64_OFFSET), SteamId.convert3IdToId64("[U:1:0]"));
		assertEquals("76561198165637099", SteamId.convert3IdToId64("[U:1:205371371]"));
	}

	@Test
	public void convertId32To3Id() {
		assertEquals("[U:1:0]", SteamId.convertId32To3Id("STEAM_0:0:0"));
		assertEquals("[U:1:205371371]", SteamId.convertId32To3Id("STEAM_0:1:102685685"));
	}

	@Test
	public void convertId32ToId64() {
		assertEquals(String.valueOf(SteamId.STEAMID64_OFFSET), SteamId.convertId32ToId64("STEAM_0:0:0"));
		assertEquals("76561198165637099", SteamId.convertId32ToId64("STEAM_0:1:102685685"));
	}

	@Test
	public void convertId64To3Id() {
		assertEquals("[U:1:0]", SteamId.convertId64To3Id(String.valueOf(SteamId.STEAMID64_OFFSET)));
		assertEquals("[U:1:205371371]", SteamId.convertId64To3Id("76561198165637099"));
	}

	@Test
	public void convertId64ToId32() {
		assertEquals("STEAM_0:0:0", SteamId.convertId64ToId32(String.valueOf(SteamId.STEAMID64_OFFSET)));
		assertEquals("STEAM_0:1:102685685", SteamId.convertId64ToId32("76561198165637099"));
	}
}
