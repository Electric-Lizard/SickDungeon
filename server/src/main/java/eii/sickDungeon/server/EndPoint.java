package eii.sickDungeon.server;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by username on 8/19/15.
 */
@ServerEndpoint("/lol")
public class EndPoint {
    Router router = Router.getInstance();

    @OnOpen
    public void onOpen(Session session) {
        int af = 3;
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        router.handlerMessage(session, message);
    }
}
