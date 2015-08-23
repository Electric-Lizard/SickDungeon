package eii.sickDungeon.shared;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import eii.sickDungeon.shared.model.ModelFactory;
import eii.sickDungeon.shared.model.RequestDataSerializer;
import eii.sickDungeon.shared.model.Room;
import eii.sickDungeon.shared.model.RoomCollection;

/**
 * Created by username on 8/22/15.
 */
public class AutoBeanModelFactory implements RequestDataSerializer, ModelFactory {
    private ModelsAutoBeanFactory factory;

    public AutoBeanModelFactory(ModelsAutoBeanFactory factory) {
        this.factory = factory;
    }

    @Override
    public Room makeRoom() {
        return factory.room().as();
    }

    @Override
    public RoomCollection makeRoomCollection() {
        return factory.roomCollection().as();
    }

    @Override
    public String serialize(Object requestData) {
        return AutoBeanCodex.encode(AutoBeanUtils.getAutoBean(requestData)).getPayload();
    }

    @Override
    public <T> T deserialize(String encodedRequestData, Class<T> requestDataClass) {
        return AutoBeanCodex.decode(factory, requestDataClass, encodedRequestData).as();
    }

    public interface ModelsAutoBeanFactory extends AutoBeanFactory {
        AutoBean<Room> room();

        AutoBean<RoomCollection> roomCollection();
    }
}
