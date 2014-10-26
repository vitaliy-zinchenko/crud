package zinjvi.repository.impl;

import com.mongodb.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import zinjvi.bean.IdShower;
import zinjvi.repository.Repository;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseMongoRepository<T extends IdShower, I>  implements Repository<T, I> {
    
    protected static final String ID_KEY = "_id";

    @Autowired
    protected DB db;

    protected abstract T convertToEntity(DBObject dbObject);
    
    protected abstract DBObject convertToDBObject(T entity);

    protected abstract String getCollectionName();

    protected String objectIdToString(ObjectId objectId) {
        return objectId.toString();
    }

    protected ObjectId stringIdToObjectId(String stringId) {
        return new ObjectId(stringId);
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