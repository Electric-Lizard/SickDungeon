package eii.sickDungeon.game.field;

/**
 * Created by username on 9/24/15.
 */
public class TileSet {
    private Tile[][] tileSet;
    private int width, height;

    public TileSet(int width, int height) {
        this.width = width;
        this.height = height;
        tileSet = new Tile[width][height];
    }

    public Tile getTile(Coordinates tileCoordinates) {
        try {
            return tileSet[tileCoordinates.getHorizontal()][tileCoordinates.getVertical()];
        } catch (Exception e) {
            throw e;
        }
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

    public boolean isBorder(Coordinates coordinates) {
        int vertical = coordinates.getVertical();
        int horizontal = coordinates.getHorizontal();
        return horizontal == 0 || horizontal == width - 1 || vertical == 0 || vertical == height - 1;
    }

    public boolean isOutside(Coordinates coordinates) {
        int vertical = coordinates.getVertical();
        int horizontal = coordinates.getHorizontal();
        return horizontal < 0 || horizontal >= width  || vertical < 0 || vertical >= height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
