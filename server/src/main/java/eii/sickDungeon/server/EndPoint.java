package eii.sickDungeon.server;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by username on 8/19/15.
 */
@ServerEndpoint("/lol")
public class EndPoint {

    @OnOpen
    public void onOpen(Session session) {
        int af = 3;
    }
}
