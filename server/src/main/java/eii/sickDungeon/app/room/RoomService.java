package eii.sickDungeon.app.room;

import eii.sickDungeon.server.RequestParser;
import eii.sickDungeon.shared.model.Room;
import eii.sickDungeon.shared.model.RoomCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by username on 8/23/15.
 */
public class RoomService {
    private RoomCollection roomCollection;

    public RoomService() {
        roomCollection = RequestParser.getModelFactory().makeRoomCollection();
        roomCollection.setRoomList(new ArrayList<>());
        Room tmpRoom = RequestParser.getModelFactory().makeRoom();
        tmpRoom.setId(0);
        roomCollection.getRoomList().add(tmpRoom);
    }

    public RoomCollection getRoomCollection() {
        return roomCollection;
    }
}
