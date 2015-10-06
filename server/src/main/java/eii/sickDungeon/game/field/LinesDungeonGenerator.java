package eii.sickDungeon.game.field;


import java.util.*;

/**
 * Created by username on 9/17/15.
 */
public class LinesDungeonGenerator implements DungeonGenerator {
    private int maxRandomWallSearchingTries = 100;
    private int width;
    private int height;
    private TileType[][] tileSet;
    Random random = new Random();

    protected boolean isBorder(Coordinates coordinates) {
        int vertical = coordinates.getVertical();
        int horizontal = coordinates.getHorizontal();
        return horizontal == 0 || horizontal == width - 1 || vertical == 0 || vertical == height - 1;
    }

    @Override
    public boolean isTilePresent(Coordinates coordinates) {
        try {
            getTile(coordinates);
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public double calculateWallProportion() {
        int wallCount = calculateTileCount(TileType.WALL) + calculateTileCount(TileType.DOOR);
        int width = tileSet.length;
        int height = width > 0 ? tileSet[0].length : 0;
        return (double) wallCount / (width * height);
    }

    public int calculateTileCount(TileType tileType) {
        int tileCount = 0;
        for (TileType[] tileTypes : tileSet) {
            for (TileType type : tileTypes) {
                if (type == tileType) tileCount++;
            }
        }
        return tileCount;
    }

    public void generateBaseBox(int width, int height) {
        this.width = width;
        this.height = height;
        tileSet = new TileType[width][height];

        for (int column = 0, tileSetLength = tileSet.length; column < tileSetLength; column++) {
            TileType[] tileRow = tileSet[column];
            for (int row = 0, tileRowLength = tileRow.length; row < tileRowLength; row++) {
                TileType tile = tileRow[row];
                if (isBorder(new Coordinates(column, row))) {
                    tile = TileType.WALL;
                } else {
                    tile = TileType.FLOOR;
                }
                tileRow[row] = tile;
            }
        }
    }

    public void generateWall() {
        generateWall(0);
    }
    protected void generateWall(int searchingTries) {
        if (searchingTries > 500) {
            throw new RuntimeException("Too much tries to generate a wall");
        }
        Coordinates randomWallTileCoordinates = findRandomTile(TileType.WALL);
        List<Coordinates> randomWallLineCoordinates = getRandomWallLineCoordinates(randomWallTileCoordinates);
        if (randomWallLineCoordinates == null) {
            generateWall(++searchingTries);
        } else {
            for (Coordinates coordinates : randomWallLineCoordinates) {
                setTile(coordinates, TileType.WALL);
            }
            for (int i = 0, ii = randomWallLineCoordinates.size() / 30; i < ii || i == 0; i++) {
                setTile(randomWallLineCoordinates.get(random.nextInt(randomWallLineCoordinates.size())), TileType.DOOR);
            }
        }
    }

    public Coordinates findRandomTile(TileType tileType) {
        for (int i = 0; true; i++) {
            Coordinates coordinates = new Coordinates(random.nextInt(width),
                    random.nextInt(height));
            TileType randomTile = getTile(coordinates);
            if (randomTile == tileType) {
                return coordinates;
            }

            if (i > maxRandomWallSearchingTries) {
                throw new RuntimeException("Can not find wall");
            }
        }
    }

    public List<Coordinates> getRandomWallLineCoordinates(Coordinates pointCoordinates) {
        List<Coordinates> wallLineCoordinates = null;
        List<Direction> directions = Arrays.asList(Direction.TOP, Direction.RIGHT, Direction.BOTTOM, Direction.LEFT);
        directions = new ArrayList<>(directions);

        while (directions.size() > 0) {
            int randomDirectionIndex = random.nextInt(directions.size());
            Direction randomDirection = directions.get(randomDirectionIndex);
            wallLineCoordinates = checkWallPossibility(pointCoordinates, randomDirection);
            if (wallLineCoordinates != null) {
                break;
            } else directions.remove(randomDirectionIndex);
        }

        return wallLineCoordinates;
    }

    public List<Coordinates> checkWallPossibility(Coordinates coordinates, Direction direction) {
        Coordinates nextTileCoordinates = coordinates.shift(direction.getCoordinates());
        if (!isTilePresent(nextTileCoordinates))
            return null;
        List<Coordinates> tileLine = getTileLine(nextTileCoordinates, direction);

        if (tileLine.size() <= 1)
            return null;
        if (getTile(tileLine.get(0)) != TileType.FLOOR)
            return null;

        Direction[] paddingDirections = direction == Direction.BOTTOM || direction == Direction.TOP ?
                new Direction[] {Direction.LEFT, Direction.RIGHT} : new Direction[] {Direction.TOP, Direction.BOTTOM};
        for (Coordinates linePointCoordinates : tileLine) {
            if (!checkPadding(linePointCoordinates, TileType.FLOOR, 1, paddingDirections))
                return null;
        }
        Coordinates closingCoordinates = tileLine.get(tileLine.size() - 1).shift(direction.getCoordinates());
        if (isTilePresent(closingCoordinates) && getTile(coordinates) != TileType.WALL)
            return null;

        return tileLine;
    }

    protected boolean checkPadding(Coordinates pointCoordinates, TileType paddingType, int paddingLength,
                                   Direction... paddingDirections) {
        for (Direction paddingDirection : paddingDirections) {
            List<Coordinates> tileLine = getTileLine(pointCoordinates.shift(paddingDirection.getCoordinates()), paddingDirection);
            if (tileLine.size() < paddingLength) return false;
            if (paddingLength > 0 && getTile(tileLine.get(0)) != paddingType) return false;
        }

        return true;
    }

    public List<Coordinates> getTileLine (Coordinates beginningCoordinates, Direction lineDirection) {
        List<Coordinates> tileLine = new ArrayList<>();
        TileType lineTileType = getTile(beginningCoordinates);

        Coordinates pointCoordinates = beginningCoordinates;
        while (isTilePresent(pointCoordinates) && getTile(pointCoordinates) == lineTileType) {
            tileLine.add(pointCoordinates);
            pointCoordinates = pointCoordinates.shift(lineDirection.getCoordinates());
        }
        return tileLine;
    }

    @Override
    public void composeDungeon(int width, int height, int wallPercentage) {
        generateBaseBox(width, height);

        while (wallPercentage > calculateWallProportion() * 100) {
            generateWall();
        }
    }

    @Override
    public TileType getTile(Coordinates coordinates) {
        return tileSet[coordinates.getHorizontal()][coordinates.getVertical()];
    }

    @Override
    public void setTile(Coordinates coordinates, TileType tileType) {
        tileSet[coordinates.getHorizontal()][coordinates.getVertical()] = tileType;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public int getMaxRandomWallSearchingTries() {
        return maxRandomWallSearchingTries;
    }

    public void setMaxRandomWallSearchingTries(int maxRandomWallSearchingTries) {
        this.maxRandomWallSearchingTries = maxRandomWallSearchingTries;
    }
}
