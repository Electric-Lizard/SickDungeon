package eii.sickDungeon.game.field;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by username on 9/12/15.
 */
public class Grid {
    private Tile[][] tileSet;
    private Dimension size;
    private Random random = new Random();

    public Grid(Dimension size) {
        this.size = size;
        tileSet = new Tile[(int) size.getHeight()][(int) size.getWidth()];
        generateWalls();
    }

    public Tile[][] getTileSet() {
        return tileSet;
    }

    public Tile getTile(Coordinates tileCoordinates) {
        return tileSet[tileCoordinates.getVertical()][tileCoordinates.getHorizontal()];
    }

    public void setTile(Coordinates tileCoordinates, Tile tile) {
        tileSet[tileCoordinates.getVertical()][tileCoordinates.getHorizontal()] = tile;
    }
    protected void generateWalls() {
        for (int row = 0, tileSetLength = tileSet.length; row < tileSetLength; row++) {
            Tile[] tileRow = tileSet[row];
            for (int column = 0, tileRowLength = tileRow.length; column < tileRowLength; column++) {
                Tile tile = tileRow[column];
                if (isBorder(new Coordinates(column, row))) {
                    tile = TileFactory.makeWall();
                } else {
                    tile = TileFactory.makeFloor();
                }
                tileRow[column] = tile;
            }
        }
    }

    protected void generateWall() {
        Coordinates randomWallCoordinates;
        Tile randomWall;
        for (int i = 0; true; i++) {
            randomWallCoordinates = new Coordinates(random.nextInt((int) size.getWidth()),
                    random.nextInt((int) size.getHeight()));
            randomWall = getTile(randomWallCoordinates);
            Direction cleanDirection = findCleanDirection(randomWallCoordinates);
            if (randomWall.getPassability() == Tile.Passability.NoPassable &&
                    cleanDirection != null) {
                generateWall(randomWallCoordinates, cleanDirection);
                break;
            } else if (i > 50) {
                throw new RuntimeException("Can not find wall");
            }
        }
    }

    protected void generateWall(Coordinates beginningWallCoordinates, Direction direction) {
        List<Coordinates> passableTileCoordinates = getPassableTileCoordinates(beginningWallCoordinates, direction);
        if (passableTileCoordinates.size() < 2) return;//TODO: throw some exception?

        List<Coordinates> excludedCoordinates = new ArrayList<>();
        int randomIndex = random.nextInt(passableTileCoordinates.size());
        excludedCoordinates.add(passableTileCoordinates.get(randomIndex));

        passableTileCoordinates.removeAll(excludedCoordinates);

        for (Coordinates tileCoordinate : passableTileCoordinates) {
            setTile(tileCoordinate, TileFactory.makeWall());
        }
        for (Coordinates excludedCoordinate : excludedCoordinates) {
            setTile(excludedCoordinate, TileFactory.makeDoor());
        }
    }

    protected List<Coordinates> getPassableTileCoordinates(Coordinates beginningWallCoordinates, Direction direction) {
        List<Coordinates> passableTileCoordinates = new ArrayList<>();
        Coordinates directionCoordinates = direction.getCoordinates();

        Tile nextWall = null;
        Coordinates wallCoordinates = beginningWallCoordinates;
        do {
            if (nextWall != null) passableTileCoordinates.add(wallCoordinates);
            wallCoordinates = wallCoordinates.shift(directionCoordinates);
            try {
                nextWall = getTile(wallCoordinates);
            } catch (Exception e) {
                throw e;
            }
        } while (nextWall.getPassability() != Tile.Passability.NoPassable);

        return passableTileCoordinates;
    }

    protected Direction findCleanDirection(Coordinates beginningWallCoordinates) {
        List<Direction> directions = new ArrayList<>(Arrays.asList(Direction.Up, Direction.Right, Direction.Down, Direction.Left));

        Tile nextWall;
        Direction direction;
        do {
            int directionIndex = random.nextInt(directions.size());
            direction = directions.get(directionIndex);
            directions.remove(directionIndex);
            Coordinates directionCoordinates = direction.getCoordinates();
            Coordinates nextWallCoordinates = beginningWallCoordinates.shift(directionCoordinates);
            try {
                nextWall = getTile(nextWallCoordinates);
            } catch (ArrayIndexOutOfBoundsException e) {
                nextWall = null;
            }
            if (directions.size() == 0) {
                direction = null;
                break;
            }
        } while (nextWall == null || nextWall.getPassability() != Tile.Passability.Passable);
        return direction;
    }

    protected boolean isBorder(Coordinates coordinates) {
        int vertical = coordinates.getVertical();
        int horizontal = coordinates.getHorizontal();
        return horizontal == 0 || horizontal == size.getWidth()-1 || vertical == 0 || vertical == size.getHeight()-1;
    }
}
