package eii.sickDungeon.client.sync;

import eii.sickDungeon.client.event.ActionHandler;
import eii.sickDungeon.shared.model.Room;
import eii.sickDungeon.shared.model.RoomCollection;

import java.util.List;

/**
 * Created by username on 8/20/15.
 */
public interface Router {
    void connect(ActionHandler openHandler, ActionHandler failHandler);

    void getRoomList();

    void addRoomListHandler(DataFetchHandler<RoomCollection> roomListHandler);
    void removeRoomListHandler(DataFetchHandler<RoomCollection> roomListHandler);
}
