package eii.sickDungeon.shared.model;

import java.util.List;

/**
 * Created by username on 8/22/15.
 */
public interface ModelFactory {
    Room makeRoom();
    RoomCollection makeRoomCollection();
}
