package eii.sickDungeon.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import eii.sickDungeon.client.event.ActionHandler;
import eii.sickDungeon.client.sync.DataFetchHandler;
import eii.sickDungeon.client.sync.Router;
import eii.sickDungeon.client.view.serverList.RoomBrowserWindow;
import eii.sickDungeon.shared.model.RoomCollection;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class SickDungeon implements EntryPoint {

    private final RootPanel rootPanel = RootPanel.get();
    private final RoomBrowserWindow roomBrowser = new RoomBrowserWindow();

    private Router router;

    /**
     * This is the entry point method.
     */
    @Override
    public void onModuleLoad() {
        establishConnection();
    }

    protected void establishConnection() {
        router = new WSRouter();
        router.connect(
                new ActionHandler() {
                    @Override
                    public void doAction() {
                        buildUI();
                    }
                },
                new ActionHandler() {
                    @Override
                    public void doAction() {
                        rootPanel.add(new Label("Your browser does not support websocket connections."));
                    }
                }
        );
    }

    protected void buildUI() {
        rootPanel.add(roomBrowser);
        roomBrowser.show();
        roomBrowser.enableProcessing();
        router.addRoomListHandler(new DataFetchHandler<RoomCollection>() {
            @Override
            public void handleData(RoomCollection data) {
                roomBrowser.resetRoomList(data);
                roomBrowser.disableProcessing();
            }
        });
        router.getRoomList();
    }
}
