package eii.sickDungeon.gwt.shared.model;

/**
 * Created by username on 8/22/15.
 */
public interface RequestDataSerializer {
    String serialize(Object requestData);
    <T> T deserialize(String encodedRequestData, Class<T> requestDataClass);
}
