package eii.sickDungeon.game.field;

import eii.sickDungeon.game.SickDungeon;
import eii.sickDungeon.game.field.Grid;
import eii.sickDungeon.game.field.Tile;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by username on 9/12/15.
 */
public class GridTest {
    private static Map<Tile.Passability, Character> layout;
    static {
        layout = new HashMap<>();
        layout.put(Tile.Passability.NoPassable, '#');
        layout.put(Tile.Passability.Passable, '.');
        layout.put(Tile.Passability.Optional, '/');
    }

    @Test
    public void testWallGeneration() {
        System.out.print(drawRandomDungeon(new Dimension(100, 30)));
    }

    public String drawRandomDungeon(Dimension size) {
        StringBuilder output = new StringBuilder();
        try {
            Random random = new Random();
            Grid gameGrid = new Grid(size);
            for (int i = 0; i < 100; i++) {
                gameGrid.generateWall();
            }
            Tile[][] tileSet = gameGrid.getTileSet();
            output.append("\n");
            for (Tile[] tileRow : tileSet) {
                for (Tile tile : tileRow) {
                    String character = layout.get(tile.getPassability()).toString();
                    output.append(character);
                }
                output.append("\n");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw e;
        }
        return output.toString();
    }
}
