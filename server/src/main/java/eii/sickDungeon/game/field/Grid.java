package eii.sickDungeon.game.field;

import java.util.Random;

/**
 * Created by username on 9/12/15.
 */
public class Grid {
    private Tile[][] tileSet;
    GridProperties properties;
    private Random random = new Random();

    public Grid(GridProperties properties) {
        this.properties = properties;
        generateTileSet();
    }

    public Tile[][] getTileSet() {
        return tileSet;
    }

    public Tile getTile(Coordinates tileCoordinates) {
        return tileSet[tileCoordinates.getHorizontal()][tileCoordinates.getVertical()];
    }

    public void setTile(Coordinates tileCoordinates, Tile tile) {
        tileSet[tileCoordinates.getHorizontal()][tileCoordinates.getVertical()] = tile;
    }

    public boolean isTileExists(Coordinates tileCoordinates) {
        try {
            getTile(tileCoordinates);
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    protected void generateTileSet() {
        properties.dungeonGenerator.composeDungeon(properties.width, properties.height, 50);

        for (int row = 0; row < properties.dungeonGenerator.getHeight(); row++) {
            for (int column = 0; column < properties.dungeonGenerator.getWidth(); column++) {
                DungeonGenerator.TileType tileType = properties.dungeonGenerator.getTile(new Coordinates(column, row));
                Tile tile;
                switch (tileType) {
                    case WALL: tile = TileFactory.makeWall();
                        break;
                    case DOOR: tile = TileFactory.makeDoor();
                        break;
                    case FLOOR: tile = TileFactory.makeDoor();
                        break;
                    default: throw new RuntimeException("Unrecognized tile type");
                }

                setTile(new Coordinates(column, row), tile);
            }
        }
    }

    protected boolean isBorder(Coordinates coordinates) {
        int vertical = coordinates.getVertical();
        int horizontal = coordinates.getHorizontal();
        return horizontal == 0 || horizontal == properties.width - 1 || vertical == 0 || vertical == properties.height - 1;
    }
}
