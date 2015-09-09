package eii.sickDungeon.gwt.client.sync;

import eii.sickDungeon.gwt.client.event.ActionHandler;
import eii.sickDungeon.gwt.shared.model.RoomCollection;

/**
 * Created by username on 8/20/15.
 */
public interface Router {
    void connect(ActionHandler openHandler, ActionHandler failHandler);

    void getRoomList();

    void addRoomListHandler(DataFetchHandler<RoomCollection> roomListHandler);
    void removeRoomListHandler(DataFetchHandler<RoomCollection> roomListHandler);
}
