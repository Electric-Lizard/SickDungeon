package eii.sickDungeon.app;

import eii.sickDungeon.app.room.RoomService;

/**
 * Created by username on 8/23/15.
 */
public class Application {
    private RoomService roomService = new RoomService();

    public RoomService getRoomService() {
        return roomService;
    }
}
