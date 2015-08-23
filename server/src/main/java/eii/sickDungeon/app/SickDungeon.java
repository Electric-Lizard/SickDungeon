package eii.sickDungeon.app;

import eii.sickDungeon.app.room.RoomService;

/**
 * Created by username on 8/23/15.
 */
public class SickDungeon {
    private RoomService roomService = new RoomService();

    public RoomService getRoomService() {
        return roomService;
    }
}
