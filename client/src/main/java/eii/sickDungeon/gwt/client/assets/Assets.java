package eii.sickDungeon.gwt.client.assets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

/**
 * Created by username on 8/30/15.
 */
public interface Assets extends ClientBundle {
    Assets INSTANCE =  GWT.create(Assets.class);

    @Source("css/serverList.css")
    ServerListCss serverListCss();
}
