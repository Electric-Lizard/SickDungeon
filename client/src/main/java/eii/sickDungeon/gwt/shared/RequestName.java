package eii.sickDungeon.gwt.shared;

import eii.sickDungeon.gwt.shared.model.RoomCollection;

/**
 * Created by username on 8/22/15.
 */
public enum RequestName {
    getRoomCollection(Void.class),
    roomCollection(RoomCollection.class);

    private Class requestDataType;

    RequestName(Class requestDataType) {
        this.requestDataType = requestDataType;
    }

    public Class getRequestDataType() {
        return requestDataType;
    }
}
