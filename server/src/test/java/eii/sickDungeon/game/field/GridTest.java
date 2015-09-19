package eii.sickDungeon.game.field;

import junit.framework.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by username on 9/12/15.
 */
public class GridTest {
    private static Map<LinesDungeonGenerator.TileType, Character> layout;

    static {
        layout = new HashMap<>();
        layout.put(LinesDungeonGenerator.TileType.WALL, '#');
        layout.put(LinesDungeonGenerator.TileType.FLOOR, '.');
        layout.put(LinesDungeonGenerator.TileType.DOOR, '/');
    }

    @Test
    public void testWallGeneration() {
        System.out.print(drawRandomDungeon(50, 10));
    }

    @Test
    public void testNearlyWallsFailing() {
        LinesDungeonGenerator dungeonGenerator = new LinesDungeonGenerator();
        dungeonGenerator.generateBaseBox(100, 30);

        Assert.assertFalse(dungeonGenerator.checkWallPossibility(new Coordinates(1, 0), Direction.Down) != null);
    }

    public String drawRandomDungeon(int width, int height) {
        try {
            LinesDungeonGenerator dungeonGenerator = new LinesDungeonGenerator();
            dungeonGenerator.composeDungeon(width, height, 50);
            return drawDungeon(dungeonGenerator);

        } catch (ArrayIndexOutOfBoundsException e) {
            throw e;
        }
    }

    public static String drawDungeon(LinesDungeonGenerator dungeonGenerator) {
        StringBuilder output = new StringBuilder();

        output.append("\n");

        for (int row = 0; row < dungeonGenerator.getHeight(); row++) {
            for (int column = 0; column < dungeonGenerator.getWidth(); column++) {
                LinesDungeonGenerator.TileType tile = dungeonGenerator.getTile(new Coordinates(column, row));
                String character = layout.get(tile).toString();
                output.append(character);
            }
            output.append("\n");
        }
        return output.toString();
    }
}
