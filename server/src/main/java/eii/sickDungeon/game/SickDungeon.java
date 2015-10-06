package eii.sickDungeon.game;

import eii.sickDungeon.game.field.*;
import eii.sickDungeon.game.player.Player;
import eii.sickDungeon.game.player.Runner;
import eii.sickDungeon.game.player.Seeker;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by username on 9/12/15.
 */
public class SickDungeon {
    private SickDungeonProperties properties;

    private Seeker seeker;
    private Coordinates seekerCoordinates;
    private Runner runner;
    private Coordinates runnerCoordinates;

    private Grid grid;

    public SickDungeon() {
        properties = new SickDungeonProperties();
        grid = new Grid(properties.gridProperties);
        spawnPlayers();
    }

    public Coordinates getSeekerCoordinates() {
        return seekerCoordinates;
    }

    public Coordinates getRunnerCoordinates() {
        return runnerCoordinates;
    }

    protected boolean isTileCharable(Tile tile) {
        return tile.getPassability() == Tile.Passability.Passable;
    }

    protected void spawnPlayers() {
        List<Coordinates> charableCoords = grid.findTiles(this::isTileCharable);
        Random random = new Random();

        runnerCoordinates = charableCoords.get(random.nextInt(charableCoords.size()));

        List<Coordinates> runnerFOV = grid.calculateVisibleTiles(runnerCoordinates, properties.VoFRadius);

        for (Iterator<Coordinates> iterator = charableCoords.iterator(); iterator.hasNext(); ) {
            Coordinates charableCoord = iterator.next();
            if (runnerFOV.contains(charableCoord)) iterator.remove();
        }

        seekerCoordinates = charableCoords.get(random.nextInt(charableCoords.size()));
    }

    public Grid getGrid() {
        return grid;
    }

    public void moveSeeker(Direction direction) {
        Coordinates target = seekerCoordinates.shift(direction);
        if (canMovePlayer(seekerCoordinates, target) && !target.equals(runnerCoordinates))
        seekerCoordinates = target;

    }
    public void moveRunner(Direction direction) {
        Coordinates target = runnerCoordinates.shift(direction);
        if (canMovePlayer(runnerCoordinates, target) && !target.equals(seekerCoordinates))
        runnerCoordinates = target;
    }
    protected boolean canMovePlayer(Coordinates coordinates, Coordinates target) {
        return grid.getTile(target).getPassability() != Tile.Passability.NoPassable;
    }
}
