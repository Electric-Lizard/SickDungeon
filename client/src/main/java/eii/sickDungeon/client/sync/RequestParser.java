package eii.sickDungeon.client.sync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import eii.sickDungeon.shared.model.ModelFactory;
import eii.sickDungeon.shared.model.RequestDataSerializer;
import eii.sickDungeon.shared.RequestName;
import eii.sickDungeon.shared.AutoBeanModelFactory;

/**
 * Created by username on 8/22/15.
 */
public class RequestParser {
    private static ModelFactory modelFactory;
    private static RequestDataSerializer requestDataSerializer;

    static {
        AutoBeanModelFactory.ModelsAutoBeanFactory factory = GWT.create(AutoBeanModelFactory.ModelsAutoBeanFactory.class);
        AutoBeanModelFactory autoBeanModelFactory = new AutoBeanModelFactory(factory);
        modelFactory = autoBeanModelFactory;
        requestDataSerializer = autoBeanModelFactory;
    }

    private JSONObject requestJson;

    public RequestParser(String requestJson) {
        this.requestJson = JSONParser.parseStrict(requestJson).isObject();
    }

    public static String encodeRequest(RequestName requestName, Object requestData) {
        JSONObject requestJson = new JSONObject();
        requestJson.put("name", new JSONString(requestName.toString()));
        requestJson.put("data", JSONParser.parseStrict(requestDataSerializer.serialize(requestData)));
        return requestJson.toString();
    }

    public RequestName getRequestName() {
        return RequestName.valueOf(requestJson.get("name").isString().stringValue());
    }

    public <T> T getRequestData(Class<T> requestDataClass) {
        return requestDataSerializer.deserialize(requestJson.get("data").toString(), requestDataClass);
    }
}
