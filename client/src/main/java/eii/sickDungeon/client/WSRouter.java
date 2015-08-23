package eii.sickDungeon.client;

import com.google.gwt.typedarrays.shared.ArrayBuffer;
import eii.sickDungeon.client.event.ActionHandler;
import eii.sickDungeon.client.properties.Config;
import eii.sickDungeon.client.sync.DataFetchHandler;
import eii.sickDungeon.client.sync.RequestParser;
import eii.sickDungeon.client.sync.Router;
import eii.sickDungeon.shared.RequestName;
import eii.sickDungeon.shared.model.RoomCollection;
import org.realityforge.gwt.websockets.client.WebSocket;
import org.realityforge.gwt.websockets.client.WebSocketListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by username on 8/20/15.
 */
public class WSRouter implements Router {
    private EndPoint endPoint = new EndPoint();
    private Map<RequestName, List<DataFetchHandler>> requestHandlers = new HashMap<>();

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
    public void getRoomList() {
        sendRequest(RequestName.getRoomCollection, null);
    }

    @Override
    public void addRoomListHandler(DataFetchHandler<RoomCollection> roomListHandler) {
        addRequestHandler(RequestName.roomCollection, roomListHandler, RoomCollection.class);
    }

    @Override
    public void removeRoomListHandler(DataFetchHandler<RoomCollection> roomListHandler) {
        requestHandlers.get(RequestName.roomCollection).remove(roomListHandler);
    }

    private <T> void addRequestHandler(RequestName requestName, DataFetchHandler<T> dataFetchHandler, Class<T> dataType) {
        if (!requestName.getRequestDataType().equals(dataType)) {
            throw new IllegalRequestDataTypeException();
        }

        List<DataFetchHandler> handlerList = requestHandlers.get(requestName);
        if (handlerList == null) {
            handlerList = new ArrayList<>();
            requestHandlers.put(requestName, handlerList);
        }
        handlerList.add(dataFetchHandler);
    }

    private void handleRequest(String encodedRequest) {
        RequestParser requestParser = new RequestParser(encodedRequest);
        RequestName requestName = requestParser.getRequestName();
        for (DataFetchHandler dataFetchHandler : requestHandlers.get(requestName)) {
            dataFetchHandler.handleData(requestParser.getRequestData(requestName.getRequestDataType()));
        }
    }

    private void sendRequest(RequestName requestName, Object requestData) {
        endPoint.sendString(RequestParser.encodeRequest(requestName, requestData));
    }

    /**
     * Created by username on 8/20/15.
     */
    public static class EndPoint implements WebSocketListener {
        WebSocket webSocket;

        List<ActionHandler> openHandlerList = new ArrayList<>();


        public void openConnection() throws WebSocketNotSupportedException {
            webSocket = WebSocket.newWebSocketIfSupported();
            if (webSocket == null) {
                throw new WebSocketNotSupportedException();
            }

            webSocket.setListener(this);
            webSocket.connect(Config.getProperties().webSocketUrl);
        }

        public void sendString(String string) {
            webSocket.send(string);
        }

        //~ Event handlers manipulations ===============================================================================

        public void addOpenHandler(ActionHandler openHandler) {
            openHandlerList.add(openHandler);
        }

        public void removeOpenHandler(ActionHandler openHandler) {
            openHandlerList.remove(openHandler);
        }

        protected void triggerOpenHandlers() {
            for (ActionHandler actionHandler : openHandlerList) {
                actionHandler.doAction();
            }
        }


        //~ WebSocket events ===========================================================================================

        /**
         * Fire a Connected event.
         *
         * @param webSocket
         */
        @Override
        public void onOpen(WebSocket webSocket) {
            triggerOpenHandlers();
        }

        /**
         * Fire a Close event.
         *
         * @param webSocket
         * @param wasClean
         * @param code
         * @param reason
         */
        @Override
        public void onClose(WebSocket webSocket, boolean wasClean, int code, String reason) {

        }

        /**
         * Fire a Message event.
         *
         * @param webSocket
         * @param data
         */
        @Override
        public void onMessage(WebSocket webSocket, String data) {

        }

        /**
         * Fire a Message event.
         *
         * @param webSocket
         * @param data
         */
        @Override
        public void onMessage(WebSocket webSocket, ArrayBuffer data) {

        }

        /**
         * Fire an Error event.
         *
         * @param webSocket
         */
        @Override
        public void onError(WebSocket webSocket) {

        }
    }
}
