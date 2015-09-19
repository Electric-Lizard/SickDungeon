package eii.sickDungeon.game;

import eii.sickDungeon.game.field.Grid;
import eii.sickDungeon.game.player.Runner;
import eii.sickDungeon.game.player.Seeker;

/**
 * Created by username on 9/12/15.
 */
public class SickDungeon {
    SickDungeonProperties properties;
    Seeker seeker;
    Runner runner;
    Grid grid;

    public SickDungeon() {
        properties = new SickDungeonProperties();
        grid = new Grid(properties.gridProperties);
    }
}
