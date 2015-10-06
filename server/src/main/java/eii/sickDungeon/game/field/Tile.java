package eii.sickDungeon.game.field;

/**
 * Created by username on 9/12/15.
 */
public class Tile {
    private Passability passability;
    private Transparency transparency;

    private boolean isGate;
    private boolean isGateOpen;

    Tile(Passability passability, Transparency transparency) {
        this(passability, transparency, false);
    }

    Tile(Passability passability, Transparency transparency, boolean isGate) {
        this.passability = passability;
        this.transparency = transparency;
        this.isGate = isGate;
        if (isGate) isGateOpen = false;
    }

    public Passability getPassability() {
        return passability;
    }

    public Transparency getTransparency() {
        return transparency;
    }

    public boolean isGate() {
        return isGate;
    }

    public boolean isGateOpen() {
        return isGateOpen;
    }

    public enum Passability {Passable, NoPassable}
    public enum Transparency {TRANSPARENT, OPAQUE}
}