package eii.sickDungeon.server;

import eii.sickDungeon.gwt.shared.RequestName;

import javax.websocket.Session;

/**
 * Created by username on 8/23/15.
 */
public interface RequestHandler<DataType> {
    void handleRequest(Session session, RequestName requestName, DataType data);
}