package eii.sickDungeon.game.field;

/**
 * Created by username on 9/12/15.
 */
public class TileFactory {
    public static Tile makeWall() {
        return new Tile(Tile.Passability.NoPassable, Tile.Transparency.OPAQUE);
    }
    public static Tile makeFloor() {
        return new Tile(Tile.Passability.Passable, Tile.Transparency.TRANSPARENT);
    }
    public static Tile makeDoor() {
        return new Tile(Tile.Passability.NoPassable, Tile.Transparency.OPAQUE, true);
    }
}
