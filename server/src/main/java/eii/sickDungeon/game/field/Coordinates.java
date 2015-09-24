package eii.sickDungeon.game.field;

import java.util.HashMap;

/**
 * Created by username on 9/13/15.
 */
public class Coordinates implements Cloneable {
    private final int horizontal;
    private final int vertical;

    public Coordinates(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public int getHorizontal() {
        return horizontal;
    }

    public int getVertical() {
        return vertical;
    }

    public Coordinates shift(Coordinates shiftingCoordinates) {
        return new Coordinates(
                horizontal + shiftingCoordinates.getHorizontal(),
                vertical + shiftingCoordinates.getVertical()
        );
    }

    public boolean equals(Coordinates coordinates) {
        return horizontal == coordinates.getHorizontal() && vertical == coordinates.getVertical();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinates) return equals((Coordinates) obj);
        else return super.equals(obj);
    }
}
