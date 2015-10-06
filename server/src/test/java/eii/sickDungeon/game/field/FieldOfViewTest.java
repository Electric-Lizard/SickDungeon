package eii.sickDungeon.game.field;

import org.junit.Test;

import java.util.List;
import java.util.Random;

/**
 * Created by username on 9/23/15.
 */
public class FieldOfViewTest {
    @Test
    public void testLineCalculation() {
        int width = 10;
        int height = 10;

        char[][] grid = new char[width][height];
        for (int column = 0; column < width; column++) {
            for (int row = 0; row < height; row++) {
                grid[column][row] = '.';
            }
        }

        Random random = new Random();
        List<Coordinates> line = FieldOfView.calculateBresenhamsLine(
                new Coordinates(random.nextInt(width), random.nextInt(height)),
                new Coordinates(random.nextInt(width), random.nextInt(height))
        );

        for (Coordinates coordinates : line) {
            grid[coordinates.getHorizontal()][coordinates.getVertical()] = '#';
        }

        StringBuilder output = new StringBuilder();

        for (char[] column : grid) {
            for (char aChar : column) {
                output.append(aChar);
            }
            output.append("\n");
        }

        System.out.println(output.toString());
    }

    @Test
    public void testCircleCalculation() {
        int width = 50;
        int height = 50;

        char[][] grid = new char[width][height];
        for (int column = 0; column < width; column++) {
            for (int row = 0; row < height; row++) {
                grid[column][row] = '.';
            }
        }

        Random random = new Random();
        List<Coordinates> circle = FieldOfView.calculateCircle(
                new TileSet(50, 50),
                new Coordinates(23, 23),
                10
        );

        for (Coordinates coordinates : circle) {
            grid[coordinates.getHorizontal()][coordinates.getVertical()] = '#';
        }

        StringBuilder output = new StringBuilder();

        for (char[] column : grid) {
            for (char aChar : column) {
                output.append(aChar);
            }
            output.append("\n");
        }

        System.out.println(output.toString());
    }

    @Test
    public void testFieldOfView() {
        TileSet tileSet = new TileSet(50, 50);

        for (int column = 0; column < 50; column++) {
            for (int row = 0; row < 50; row++) {
                Tile tile = column == 26 && row != 24 ? TileFactory.makeWall() : TileFactory.makeFloor();
                tileSet.setTile(new Coordinates(column, row), tile);
            }
        }

        List<Coordinates> fov = FieldOfView.calculateVisibleTiles(tileSet, new Coordinates(23, 23), 10);

        StringBuilder output = new StringBuilder();
        for (int row = 0; row < 50; row++) {
            for (int column = 0; column < 50; column++) {
                Coordinates tileCoordinates = new Coordinates(column, row);
                Tile tile = tileSet.getTile(tileCoordinates);
                char aChar;
                if (tile.getPassability() == Tile.Passability.NoPassable) {
                    aChar = fov.contains(tileCoordinates) ? '#' : ' ';
                } else {
                    aChar = fov.contains(tileCoordinates) ? '.' : ' ';
                }
                if (tileCoordinates.equals(new Coordinates(23, 23))) aChar = '@';
                output.append(aChar);
            }
            output.append("\n");
        }
        System.out.println(output.toString());
    }
}
