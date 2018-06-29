package gurbx.ultimateroguelike.world.generator;

public class TileTextureExtensionCreator {
	
	public static String getTileExtension(final String COMPARATOR, String[][] tiles, int x, int y) {
		String extension = "_";
		if (tiles[x][y+1].equals(COMPARATOR)) extension += "_t";
		if (tiles[x-1][y+1].equals(COMPARATOR)) extension += "_tr";
		if (tiles[x+1][y].equals(COMPARATOR)) extension += "_r";
		if (tiles[x+1][y-1].equals(COMPARATOR)) extension += "_rb";
		if (tiles[x][y-1].equals(COMPARATOR)) extension += "_b";
		if (tiles[x-1][y-1].equals(COMPARATOR)) extension += "_bl";
		if (tiles[x-1][y].equals(COMPARATOR)) extension += "_l";
		if (tiles[x-1][y+1].equals(COMPARATOR)) extension += "_lt";
		return extension;
	}
}
