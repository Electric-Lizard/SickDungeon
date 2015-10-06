package eii.sickDungeon.game;

import eii.sickDungeon.game.field.Coordinates;
import eii.sickDungeon.game.field.Tile;
import org.junit.Test;

/**
 * Created by username on 10/5/15.
 */
public class SickDungeonTest {
    @Test
    public void testPlayerSpawn() {
        SickDungeon sickDungeon = new SickDungeon();
        sickDungeon.spawnPlayers();
        Coordinates runnerCoords = sickDungeon.getRunnerCoordinates();
        Coordinates seekerCoords = sickDungeon.getSeekerCoordinates();
        System.out.format("\n%d:%d - %d:%d\n", runnerCoords.getHorizontal(), runnerCoords.getVertical(),
                seekerCoords.getHorizontal(), seekerCoords.getVertical());
    }
}
