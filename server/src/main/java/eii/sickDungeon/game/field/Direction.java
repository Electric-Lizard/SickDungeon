package eii.sickDungeon.game.field;

/**
 * Created by username on 9/13/15.
 */
public enum Direction {
    Left(-1, 0), Right(1, 0), Up(0, -1), Down(0, 1);

    private Coordinates coordinates;

    Direction(int horizontalCoordinate, int verticalCoordinate) {
        this.coordinates = new Coordinates(horizontalCoordinate, verticalCoordinate);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
