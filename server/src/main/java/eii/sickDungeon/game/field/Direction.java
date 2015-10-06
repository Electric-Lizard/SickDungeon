package eii.sickDungeon.game.field;

/**
 * Created by username on 9/13/15.
 */
public enum Direction {
    LEFT(-1, 0), RIGHT(1, 0), TOP(0, -1), BOTTOM(0, 1),
    TOP_RIGHT(1, -1), BOTTOM_RIGHT(1, 1), BOTTOM_LEFT(-1, 1), TOP_LEFT(-1, -1);

    private Coordinates coordinates;

    Direction(int horizontalCoordinate, int verticalCoordinate) {
        this.coordinates = new Coordinates(horizontalCoordinate, verticalCoordinate);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
