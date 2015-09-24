package eii.sickDungeon.game.field;

/**
 * Created by username on 9/24/15.
 */
public class TileSet {
    private Tile[][] tileSet;

    public TileSet(int width, int height) {
        tileSet = new Tile[width][height];
    }

    public Tile getTile(Coordinates tileCoordinates) {
        return tileSet[tileCoordinates.getHorizontal()][tileCoordinates.getVertical()];
    }

    public void setTile(Coordinates tileCoordinates, Tile tile) {
        tileSet[tileCoordinates.getHorizontal()][tileCoordinates.getVertical()] = tile;
    }

    public boolean isTileExists(Coordinates tileCoordinates) {
        try {
            getTile(tileCoordinates);
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }
}
