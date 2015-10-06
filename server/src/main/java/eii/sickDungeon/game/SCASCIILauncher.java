package eii.sickDungeon.game;

import eii.sickDungeon.game.field.Coordinates;
import eii.sickDungeon.game.field.Direction;
import eii.sickDungeon.game.field.Tile;
import eii.sickDungeon.game.field.TileSet;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by username on 10/5/15.
 */
public class SCASCIILauncher implements Runnable {
    private SickDungeon sickDungeon;
    private TileSet tileSet;

    public static void main(String... args) throws IOException {
        SCASCIILauncher launcher = new SCASCIILauncher(new SickDungeon());
        launcher.run();
    }

    public SCASCIILauncher(SickDungeon sickDungeon) {
        this.sickDungeon = sickDungeon;
        this.tileSet = sickDungeon.getGrid().getTileSet();
    }

    @Override
    public void run() {
        try {
            draw();
            InputStreamReader input = new InputStreamReader(System.in);
            while (true) {
                char c = (char) input.read();
                switch (c) {
                    case 'w': sickDungeon.moveSeeker(Direction.TOP);
                        break;
                    case 'd': sickDungeon.moveSeeker(Direction.RIGHT);
                        break;
                    case 's': sickDungeon.moveSeeker(Direction.BOTTOM);
                        break;
                    case 'a': sickDungeon.moveSeeker(Direction.LEFT);
                        break;
                    case 'i': sickDungeon.moveRunner(Direction.TOP);
                        break;
                    case 'l': sickDungeon.moveRunner(Direction.RIGHT);
                        break;
                    case 'k': sickDungeon.moveRunner(Direction.BOTTOM);
                        break;
                    case 'j': sickDungeon.moveRunner(Direction.LEFT);
                        break;
                }

                draw();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw() {
        print('\n');
        print('\n');
        List<Coordinates> runnerFOV = sickDungeon.getGrid().calculateVisibleTiles(sickDungeon.getRunnerCoordinates(), 10);

        for (int row = 0; row < tileSet.getHeight(); row++) {
            for (int col = 0; col < tileSet.getWidth(); col++) {
                if (runnerFOV.contains(new Coordinates(col, row)))
                print(getTileView(new Coordinates(col, row)));
                else print(' ');
            }
            print('\n');
        }
    }

    protected void print(char c) {
        System.out.print(c);
    }

    protected char getTileView(Coordinates coordinates) {
        if (coordinates.equals(sickDungeon.getRunnerCoordinates())) return 'r';
        if (coordinates.equals(sickDungeon.getSeekerCoordinates())) return 'S';

        Tile tile = tileSet.getTile(coordinates);
        if (tile.isGate()) return '/';
        if (tile.getPassability() == Tile.Passability.NoPassable) return '#';
        else return '.';
    }
}
