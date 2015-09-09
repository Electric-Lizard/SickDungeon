package eii.sickDungeon.server;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.web.bindery.autobean.vm.AutoBeanFactorySource;
import eii.sickDungeon.gwt.shared.AutoBeanModelFactory;
import eii.sickDungeon.gwt.shared.RequestName;
import eii.sickDungeon.gwt.shared.model.ModelFactory;
import eii.sickDungeon.gwt.shared.model.RequestDataSerializer;

/**
 * Created by username on 8/23/15.
 */
public class RequestParser {
    private static ModelFactory modelFactory;
    private static RequestDataSerializer requestDataSerializer;
    private static JsonParser jsonParser = new JsonParser();

    static {
        AutoBeanModelFactory.ModelsAutoBeanFactory factory =
                AutoBeanFactorySource.create(AutoBeanModelFactory.ModelsAutoBeanFactory.class);
        AutoBeanModelFactory autoBeanModelFactory = new AutoBeanModelFactory(factory);
        modelFactory = autoBeanModelFactory;
        requestDataSerializer = autoBeanModelFactory;
    }

    public static ModelFactory getModelFactory() {
        return modelFactory;
    }

    private JsonObject requestJson;

    public RequestParser(String requestJson) {
        this.requestJson = jsonParser.parse(requestJson).getAsJsonObject();
    }

    public static String encodeRequest(RequestName requestName, Object requestData) {
        JsonObject requestJson = new JsonObject();
        requestJson.addProperty("name", requestName.toString());
        requestJson.add("data", jsonParser.parse(requestDataSerializer.serialize(requestData)));
        return requestJson.toString();
    }

    public RequestName getRequestName() {
        return RequestName.valueOf(requestJson.get("name").getAsString());
    }

    public <T> T getRequestData(Class<T> requestDataClass) {
        JsonElement jsonData = requestJson.get("data");
        return jsonData.isJsonNull() ? null : requestDataSerializer.deserialize(jsonData.toString(), requestDataClass);
    }
}
