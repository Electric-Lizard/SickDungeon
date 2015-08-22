package eii.sickDungeon.client.sync.websocket;

import eii.sickDungeon.client.event.ActionHandler;
import eii.sickDungeon.client.sync.Router;
import eii.sickDungeon.shared.Room;

import java.util.List;

/**
 * Created by username on 8/20/15.
 */
public class WSRouter implements Router {
    EndPoint endPoint = new EndPoint();

    @Override
    public void connect(ActionHandler openHandler, ActionHandler failHandler) {
        endPoint.addOpenHandler(openHandler);
        try {
            endPoint.openConnection();
        } catch (WebSocketNotSupportedException e) {
            failHandler.doAction();
        }
    }

    @Override
    public List<Room> getRoomList() {
        return null;
    }
}
