package eii.sickDungeon.app.room;

import eii.sickDungeon.server.RequestParser;
import eii.sickDungeon.gwt.shared.model.RoomCollection;

import java.util.ArrayList;

/**
 * Created by username on 8/23/15.
 */
public class RoomService {
    private RoomCollection roomCollection;

    public RoomService() {
        roomCollection = RequestParser.getModelFactory().makeRoomCollection();
        roomCollection.setRoomList(new ArrayList<>());
    }

    public RoomCollection getRoomCollection() {
        return roomCollection;
    }
}
