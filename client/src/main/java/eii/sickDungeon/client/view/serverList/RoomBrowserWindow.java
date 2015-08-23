package eii.sickDungeon.client.view.serverList;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Hyperlink;
import eii.sickDungeon.shared.model.Room;
import eii.sickDungeon.shared.model.RoomCollection;

import java.util.Iterator;

/**
 * Created by username on 8/20/15.
 */
public class RoomBrowserWindow extends DialogBox {

    private final FlexTable roomListWidget = new FlexTable();
    private RoomCollection roomCollection;

    public RoomBrowserWindow() {
        super(false, true);
        setWidget(roomListWidget);
        delegateEvents();
        setStyleName("roomBrowser");
        //TODO: render window (without attaching)
    }

    public void resetRoomList(RoomCollection roomCollection) {
        this.roomCollection = roomCollection;
        roomListWidget.clear();
    }

    public void show() {
        super.show();
        super.center();
        //TODO: implement
    }

    public void hide() {
        super.hide();
        //TODO: implement
    }

    public void enableProcessing() {
        setText("loading...");
    }

    public void disableProcessing() {
        setText("");
    }

    private void populateRoomList(RoomCollection roomCollection) {
        roomListWidget.setText(0, 0, "id");
        roomListWidget.setText(0, 1, "players");
        roomListWidget.setText(0, 3, "connect");

        int row = 1;
        for (Iterator<Room> iterator = roomCollection.getRoomList().iterator(); iterator.hasNext(); row++) {
            Room room = iterator.next();
            roomListWidget.setText(row, 0, String.valueOf(room.getId()));
            roomListWidget.setText(row, 1, String.valueOf(room.getPlayers().size()));
            roomListWidget.setWidget(row, 3, new Button("Connect"));
        }
    }

    private void delegateEvents() {
        Window.addResizeHandler(new ResizeHandler() {
            @Override
            public void onResize(ResizeEvent event) {
                if (isShowing()) center();
            }
        });
    }

}
