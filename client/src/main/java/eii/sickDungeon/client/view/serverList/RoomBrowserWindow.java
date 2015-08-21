package eii.sickDungeon.client.view.serverList;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import eii.sickDungeon.shared.Room;

import java.util.List;

/**
 * Created by username on 8/20/15.
 */
public class RoomBrowserWindow extends DialogBox {

    private final FlexTable roomListWidget = new FlexTable();
    private List<Room> roomCollection;

    public RoomBrowserWindow() {
        super(false, true);
        setWidget(roomListWidget);
        delegateEvents();
        //TODO: render window (without attaching)
    }

    public void setRoomCollection(List<Room> roomCollection) {
        this.roomCollection = roomCollection;
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

    private void delegateEvents() {
        Window.addResizeHandler(new ResizeHandler() {
            @Override
            public void onResize(ResizeEvent event) {
                if (isShowing()) center();
            }
        });
    }
}
