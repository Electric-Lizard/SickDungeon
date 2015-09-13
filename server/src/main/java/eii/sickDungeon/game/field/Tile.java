package eii.sickDungeon.game.field;

/**
 * Created by username on 9/12/15.
 */
public class Tile {
    private Passability passability;

    public Passability getPassability() {
        return passability;
    }

    public void setPassability(Passability passability) {
        this.passability = passability;
    }

    public enum Passability {Passable, NoPassable, Optional}
}