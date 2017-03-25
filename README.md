# steamidutil
Handle and convert Steam IDs.

## Usage
Let's create a few Steam IDs first.
```
SteamId steam3Id = new SteamId("STEAM_0:1:102685685");
SteamId steamId32 = new SteamId("[U:1:205371371]");
SteamId steamId64 = new SteamId("76561198165637099");
```
`SteamId`s are represented internally as steam3Id, so it does not matter which format you use to instantiate it with.

But Steam IDs aren't much fun if we're not doing anything with their values, are they?
```
String mySteamId64 = steamId32.getSteamId64(); // "76561198165637099"
```

For convenience, `SteamId` overrides `toString`, for example:
```
System.out.println(new SteamId("76561198165637099"));
// prints "de.linkd.steamidutil.SteamId@15db9742[steam3Id=[U:1:205371371]]"
```


## Conversion
Do you simply want to convert a Steam ID from one format to another? Easy as pie.
```
SteamId.convert3IdToId32("STEAM_0:1:102685685"); // "[U:1:205371371]"
SteamId.convert3IdToId64("STEAM_0:1:102685685"); // "76561198165637099"

SteamId.convertId32To3Id("[U:1:205371371]"); // "STEAM_0:1:102685685"
SteamId.convertId32ToId64("[U:1:205371371]"); // "76561198165637099"

SteamId.convertId64To3Id("76561198165637099"); // "STEAM_0:1:102685685"
SteamId.convertId64ToId32("76561198165637099"); // "[U:1:205371371]"
```
