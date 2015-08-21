package eii.sickDungeon.client.sync.websocket;

import com.google.gwt.typedarrays.shared.ArrayBuffer;
import com.google.gwt.user.client.Window;
import eii.sickDungeon.client.event.ActionHandler;
import eii.sickDungeon.client.properties.Config;
import org.realityforge.gwt.websockets.client.WebSocket;
import org.realityforge.gwt.websockets.client.WebSocketListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by username on 8/20/15.
 */
public class EndPoint implements WebSocketListener {
    WebSocket webSocket;

    List<ActionHandler> openHandlerList = new ArrayList<>();


    public void openConnection() throws WebSocketNotSupportedException {
        webSocket = WebSocket.newWebSocketIfSupported();
        if (webSocket == null) {
            throw new WebSocketNotSupportedException();
        }

        webSocket.connect(Config.getProperties().webSocketUrl);
    }

    public void sendString(String string) {
        webSocket.send(string);
    }

    //~ Event handlers manipulations ===================================================================================

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


    //~ WebSocket events ===============================================================================================

    /**
     * Fire a Connected event.
     *
     * @param webSocket
     */
    @Override
    public void onOpen(WebSocket webSocket) {
        this.webSocket = webSocket;
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
