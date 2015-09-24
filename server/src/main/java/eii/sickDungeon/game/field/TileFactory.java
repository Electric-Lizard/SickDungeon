package eii.sickDungeon.game.field;

/**
 * Created by username on 9/12/15.
 */
public class TileFactory {
    public static Tile makeWall() {
        return Tile.WALL;
    }
    public static Tile makeFloor() {
        return Tile.FLOOR;
    }
    public static Tile makeDoor() {
        return Tile.DOOR;
    }
}
