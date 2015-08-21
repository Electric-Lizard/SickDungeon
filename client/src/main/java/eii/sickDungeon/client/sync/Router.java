package eii.sickDungeon.client.sync;

import eii.sickDungeon.client.event.ActionHandler;

/**
 * Created by username on 8/20/15.
 */
public interface Router {
    void connect(ActionHandler openHandler, ActionHandler failHandler);
}
