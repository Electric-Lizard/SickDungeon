package eii.sickDungeon.game;

import eii.sickDungeon.game.field.GridProperties;

/**
 * Created by username on 9/19/15.
 */
public class SickDungeonProperties {
    public GridProperties gridProperties;
    public int VoFRadius = 10;

    public SickDungeonProperties() {
        gridProperties = new GridProperties();
        gridProperties.width = 50;
        gridProperties.height = 10;
    }
}
