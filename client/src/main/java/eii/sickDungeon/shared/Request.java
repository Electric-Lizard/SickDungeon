package eii.sickDungeon.shared;

/**
 * Created by username on 8/20/15.
 */
public interface Request<DataType> {
    enum Name {}
    Name getName();
    DataType getData();
}
