package eii.sickDungeon.shared.model;

import java.util.List;

/**
 * Created by username on 8/20/15.
 */
public interface Room {
    int getId();
    void setId(int id);
    List<User> getPlayers();
    void setPlayers(List<User> players);
}