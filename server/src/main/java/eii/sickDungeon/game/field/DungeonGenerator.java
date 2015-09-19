package eii.sickDungeon.game.field;

/**
 * Created by username on 9/19/15.
 */
public interface DungeonGenerator {
    boolean isTilePresent(Coordinates coordinates);

    void composeDungeon(int width, int height, int wallPercentage);

    TileType getTile(Coordinates coordinates);

    void setTile(Coordinates coordinates, TileType tileType);

    int getWidth();

    int getHeight();

    enum TileType {
        WALL, FLOOR, DOOR
    }
}
