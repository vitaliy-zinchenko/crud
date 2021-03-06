package zinjvi;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import zinjvi.repository.impl.BaseMongoRepository;

/**
 * Created by zinchenko on 23.10.14.
 */
public class ConcreteMongoRepository extends BaseMongoRepository<Bean, String> {

    public static final String NAME_KEY = "name";

    @Override
    protected DBObjectToEntityBuilder getDBObjectToEntityBuilder(DBObject dbObject) {
        return null;
    }

    @Override
    protected EntityToDBObjectBuilder getEntityToDBObjectBuilder(Bean entity) {
        return null;
    }

    @Override
    protected Bean convertToEntity(DBObject dbObject) {
        Bean bean = new Bean();
        String stringId = objectIdToString((ObjectId) dbObject.get(ID_KEY));
        bean.setId(stringId);
        bean.setName((String) dbObject.get(NAME_KEY));
        return bean;
    }

    @Override
    protected DBObject convertToDBObject(Bean entity) {
        DBObject dbObject = new BasicDBObject();
        fillId(dbObject, entity.getId());
        dbObject.put(NAME_KEY, entity.getName());
        return dbObject;
    }

    @Override
    protected String getCollectionName() {
        return "beans";
    }
}
