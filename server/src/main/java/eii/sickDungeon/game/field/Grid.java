package eii.sickDungeon.game.field;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by username on 9/12/15.
 */
public class Grid {
    GridProperties properties;
    private TileSet tileSet;
    private Random random = new Random();

    public Grid(GridProperties properties) {
        this.properties = properties;
        generateTileSet();
    }

    public TileSet getTileSet() {
        return tileSet;
    }

    public Tile getTile(Coordinates tileCoordinates) {
        return tileSet.getTile(tileCoordinates);
    }

    public void setTile(Coordinates tileCoordinates, Tile tile) {
        tileSet.setTile(tileCoordinates, tile);
    }

    public boolean isTileExists(Coordinates tileCoordinates) {
        return tileSet.isTileExists(tileCoordinates);
    }

    public List<Coordinates> findTiles(TileFilter tileFilter) {
        List<Coordinates> foundTiles = new ArrayList<>();

        for (int col = 0; col < properties.width; col++) {
            for (int row = 0; row < properties.height; row++) {
                Coordinates coordinates = new Coordinates(col, row);
                Tile tile = getTile(coordinates);
                if (tileFilter.filterTile(tile)) foundTiles.add(coordinates);
            }
        }

        return foundTiles;
    }

    public List<Coordinates> calculateVisibleTiles(Coordinates point, int viewRadius) {
        return FieldOfView.calculateVisibleTiles(tileSet, point, viewRadius);
    }

    protected void generateTileSet() {
        tileSet = new TileSet(properties.width, properties.height);
        properties.dungeonGenerator.composeDungeon(properties.width, properties.height, 50);

        for (int row = 0; row < properties.dungeonGenerator.getHeight(); row++) {
            for (int column = 0; column < properties.dungeonGenerator.getWidth(); column++) {
                DungeonGenerator.TileType tileType = properties.dungeonGenerator.getTile(new Coordinates(column, row));
                Tile tile;
                switch (tileType) {
                    case WALL:
                        tile = TileFactory.makeWall();
                        break;
                    case DOOR:
                        tile = TileFactory.makeDoor();
                        break;
                    case FLOOR:
                        tile = TileFactory.makeFloor();
                        break;
                    default:
                        throw new RuntimeException("Unrecognized tile type");
                }

                setTile(new Coordinates(column, row), tile);
            }
        }
    }
}
