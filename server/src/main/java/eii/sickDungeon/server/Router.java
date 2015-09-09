package eii.sickDungeon.server;

import eii.sickDungeon.app.SickDungeon;
import eii.sickDungeon.gwt.shared.RequestName;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by username on 8/23/15.
 */
public class Router {
    private static Router ourInstance = new Router();

    public static Router getInstance() {
        return ourInstance;
    }

    private Map<RequestName, List<RequestHandler>> requestHandlers = new HashMap<>();

    private SickDungeon app = new SickDungeon();

    private Router() {
        addRequestHandler(RequestName.getRoomCollection, new GetRoomListHandler());
    }

    public void handlerMessage(Session session, String message) {
        RequestParser requestParser = new RequestParser(message);
        RequestName requestName = requestParser.getRequestName();

        List<RequestHandler> requestHandlerList = requestHandlers.get(requestName);
        if (requestHandlerList == null) {
            throw new UnsupportedRequestNameException();
        }
        for (RequestHandler requestHandler : requestHandlerList) {
            requestHandler.handleRequest(session, requestName, requestParser.getRequestData(requestName.getRequestDataType()));
        }
    }

    public void sendRequest(Session session, RequestName requestName, Object requestData) {
        session.getAsyncRemote().sendText(RequestParser.encodeRequest(requestName, requestData));
    }

    public void addRequestHandler(RequestName requestName, RequestHandler requestHandler) {
        List<RequestHandler> requestHandlerList = requestHandlers.get(requestName);
        if (requestHandlerList == null) {
            requestHandlerList = new ArrayList<>();
            requestHandlers.put(requestName, requestHandlerList);
        }

        requestHandlerList.add(requestHandler);
    }

    //~ Request handlers ===============================================================================================
    class GetRoomListHandler implements RequestHandler<Void> {

        @Override
        public void handleRequest(Session session, RequestName requestName, Void data) {
            sendRequest(session, RequestName.roomCollection, app.getRoomService().getRoomCollection());
        }
    }
}
