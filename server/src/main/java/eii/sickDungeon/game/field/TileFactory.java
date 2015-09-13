package eii.sickDungeon.game.field;

/**
 * Created by username on 9/12/15.
 */
public class TileFactory {
    public static Tile makeWall() {
        Tile wall = new Tile();
        wall.setPassability(Tile.Passability.NoPassable);
        return wall;
    }
    public static Tile makeFloor() {
        Tile wall = new Tile();
        wall.setPassability(Tile.Passability.Passable);
        return wall;
    }
    public static Tile makeDoor() {
        Tile wall = new Tile();
        wall.setPassability(Tile.Passability.Optional);
        return wall;
    }
}
