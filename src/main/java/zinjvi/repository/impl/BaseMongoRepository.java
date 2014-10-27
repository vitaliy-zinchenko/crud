package zinjvi.repository.impl;

import com.mongodb.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import zinjvi.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMongoRepository<T, I>  implements Repository<T, I> {
    
    protected static final String ID_KEY = "_id";

    @Autowired
    protected DB db;

    protected abstract DBObjectToEntityBuilder getDBObjectToEntityBuilder(DBObject dbObject);

    protected abstract EntityToDBObjectBuilder getEntityToDBObjectBuilder(T entity);

    protected T convertToEntity(DBObject dbObject) {
        return (T) getDBObjectToEntityBuilder(dbObject).build();
    }

    protected DBObject convertToDBObject(T entity) {
        return getEntityToDBObjectBuilder(entity).build();
    }

    protected abstract String getCollectionName();

    //TODO | remove
    protected String objectIdToString(ObjectId objectId) {
        return objectId.toString();
    }

    protected ObjectId stringIdToObjectId(Object id) {
        return new ObjectId(id.toString());
    }

    protected DBCollection getCollection() {
        return db.getCollection(getCollectionName());
    }

    protected DBObject getIdDBObject(DBObject dbObject) {
        return new BasicDBObject(ID_KEY, dbObject.get(ID_KEY));
    }

    protected DBObject getIdDBObject(I id) {
        ObjectId objectId = new ObjectId(String.valueOf(id));
        return new BasicDBObject(ID_KEY, objectId);
    }

    protected void fillId(DBObject dbObject, Object id) {
        if (id != null) {
            ObjectId objectId = stringIdToObjectId(id);
            dbObject.put(ID_KEY, objectId);
        }
    }

    protected List<T> cursorToList(DBCursor cursor) {
        List<T> list = new ArrayList<T>();
        try {
            while(cursor.hasNext()) {
                list.add(convertToEntity(cursor.next()));
            }
        } finally {
            cursor.close();
        }
        return list;
    }

    protected abstract class EntityToDBObjectBuilder<T> {

        protected T entity;

        protected DBObject postDbObject;

        public EntityToDBObjectBuilder(T entity) {
            this.entity = entity;
        }

        public abstract DBObject build();

    }

    protected DBObjectBuilder creteDBObjectBuilder() {
        return new DBObjectBuilder();
    }

    protected abstract class DBObjectToEntityBuilder<T> {

        protected T entity;

        protected DBObject dbObject;

        public DBObjectToEntityBuilder(DBObject dbObject) {
            this.dbObject = dbObject;
        }

        public abstract T build();

    }

    public class DBObjectBuilder {

        private DBObject dbObject = new BasicDBObject();

        public DBObject build() {
            return dbObject;
        }

        public void buildTo(List list) {
            list.add(build());
        }

        public DBObjectBuilder put(String key, Object value) {
            dbObject.put(key, value);
            return this;
        }

        public DBObjectBuilder putId(Object id) {
            fillId(dbObject, id);
            return this;
        }

    }

	public List<T> findAll() {
        DBCursor cursor = getCollection().find();
        return cursorToList(cursor);
	}

    public T find(I id) {
        DBObject dbObject = getCollection().findOne(getIdDBObject(id));
        return convertToEntity(dbObject);
	}

    public void save(T entity) {
		getCollection().insert(convertToDBObject(entity));
	}

    public void update(T entity) {
        DBObject dbObject = convertToDBObject(entity);
        DBObject idDbObject = getIdDBObject(dbObject);
		getCollection().update(idDbObject, dbObject);
	}

    public void deleteById(I id) {
        getCollection().remove(getIdDBObject(id));
	}

    public void delete(T entity) {
        getCollection().remove(convertToDBObject(entity));
	}

    public Long getSize() {
        return getCollection().count();
	}

    public DB getDb() {
        return db;
    }

    public void setDb(DB db) {
        this.db = db;
    }

}