package eii.sickDungeon.game.field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by username on 9/23/15.
 */
public class FieldOfView {
    public static List<Coordinates> calculateBresenhamsLine(Coordinates startPoint, Coordinates endPoint) {
        int verticalDiff = Math.abs(endPoint.getVertical() - startPoint.getVertical());
        int horizontalDiff = Math.abs(endPoint.getHorizontal() - startPoint.getHorizontal());

        boolean isHorizontalIterating = Math.abs(horizontalDiff) > Math.abs(verticalDiff);
        int iteratingCoordinate;
        int iterationEndCoordinate;
        double slopingCoordinate;
        int slopingEndCoordinate;
        double slope;
        if (isHorizontalIterating) {
            iteratingCoordinate = startPoint.getHorizontal();
            iterationEndCoordinate = endPoint.getHorizontal();
            slopingCoordinate = startPoint.getVertical();
            slopingEndCoordinate = endPoint.getVertical();
            slope = (double) verticalDiff / horizontalDiff;
        } else {
            iteratingCoordinate = startPoint.getVertical();
            iterationEndCoordinate = endPoint.getVertical();
            slopingCoordinate = startPoint.getHorizontal();
            slopingEndCoordinate = endPoint.getHorizontal();
            slope = (double) horizontalDiff / verticalDiff;
        }
        int iterationIncrementer = iterationEndCoordinate - iteratingCoordinate > 0 ? 1 : -1;
        boolean isSlopingIncreases = slopingEndCoordinate - slopingCoordinate > 0;

        List<Coordinates> line = new ArrayList<>();
        line.add(startPoint);
        while (iteratingCoordinate != iterationEndCoordinate) {
            iteratingCoordinate += iterationIncrementer;
            slopingCoordinate = isSlopingIncreases ? slopingCoordinate + slope : slopingCoordinate - slope;
            Coordinates coordinates = isHorizontalIterating ?
                    new Coordinates(iteratingCoordinate, (int) Math.round(slopingCoordinate)) :
                    new Coordinates((int) Math.round(slopingCoordinate), iteratingCoordinate);
            line.add(coordinates);
        }
        return line;
    }

    public static List<Coordinates> calculateVisibleTiles(TileSet tileSet, Coordinates observerPoint, int radius) {
        List<Coordinates> visibleCoordinates = new ArrayList<>();
        List<Coordinates> viewRangeCoordinates = calculateCircle(tileSet, observerPoint, radius);
        for (Coordinates coordinate : viewRangeCoordinates) {
            if (isPointVisible(tileSet, observerPoint, coordinate)) {
                visibleCoordinates.add(coordinate);
            }
        }

        for (Coordinates coordinate : viewRangeCoordinates) {
            if (!visibleCoordinates.contains(coordinate) &&
                    tileSet.getTile(coordinate).getTransparency() == Tile.Transparency.OPAQUE) {
                boolean hasNearlyVisible = false;
                for (Direction d : Direction.values()) {
                    Coordinates nearlyPoint =  coordinate.shift(d.getCoordinates());
                    if (!tileSet.isOutside(nearlyPoint) &&
                            tileSet.getTile(nearlyPoint).getTransparency() == Tile.Transparency.TRANSPARENT &&
                            visibleCoordinates.contains(nearlyPoint)) {
                        hasNearlyVisible = true;
                        break;
                    }
                }
                if (hasNearlyVisible) visibleCoordinates.add(coordinate);
            }
        }
        return visibleCoordinates;
    }

    public static boolean isPointVisible(TileSet tileSet, Coordinates observerCoordinates, Coordinates pointCoordinates) {
        List<Coordinates> line = calculateBresenhamsLine(observerCoordinates, pointCoordinates);
        for (Coordinates linePoint : line) {
            Tile tile = tileSet.getTile(linePoint);
            if (!linePoint.equals(observerCoordinates) && !linePoint.equals(pointCoordinates) &&
                    tile.getTransparency() == Tile.Transparency.OPAQUE) {
                return false;
            }
        }
        return true;
    }

    public static List<Coordinates> calculateCircle(TileSet tileSet, Coordinates center, int radius) {
        List<Coordinates> circle = new ArrayList<>();
        int left = center.getHorizontal() - radius;
        int right = center.getHorizontal() + radius;
        int lastX = tileSet.getWidth() - 1;
        int top = center.getVertical() - radius;
        int bottom = center.getVertical() + radius;
        int lastY = tileSet.getHeight() - 1;


        for (int x = left < 0 ? 0 : left, x1 = right > lastX ? lastX : right; x <= x1; x++) {
            for (int y = top < 0 ? 0 : top, y1 = bottom > lastY ? lastY : bottom; y <= y1; y++) {
                Coordinates point = new Coordinates(x, y);
                if (isPointInCircle(center, radius, point)) {
                    circle.add(point);
                }
            }
        }
        return circle;
    }

    public static boolean isPointInCircle(Coordinates circleCenter, int circleRadius, Coordinates pointCoordinates) {
        return Math.pow((pointCoordinates.getHorizontal() - circleCenter.getHorizontal()), 2) +
                Math.pow((pointCoordinates.getVertical() - circleCenter.getVertical()), 2) <
                Math.pow(circleRadius + 1, 2);
    }
}
