package zinjvi;

import com.mongodb.*;
import org.bson.types.ObjectId;

import java.net.UnknownHostException;

/**
 * Created by zinchenko on 28.10.14.
 */
public class Test {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB("students");

        DBCollection dbCollection = db.getCollection("grades");

        DBObject homework = new BasicDBObject("type","homework");
        DBObject order = new BasicDBObject("student_id", -1)
                .append("score", 1);
        DBCursor cursor = db.getCollection("grades").find(homework).sort(order);
        int delete = 0;
        ObjectId objectId = null;
        try {
            while(cursor.hasNext()) {
                DBObject dbObject = cursor.next();
                int studentId = (Integer)dbObject.get("student_id");
                objectId = (ObjectId) dbObject.get("_id");
                if(delete != studentId) {
                    delete = studentId;
                    if(objectId != null) {
                        DBObject toDelete = new BasicDBObject("_id", objectId);
                        db.getCollection("grades").remove(toDelete);
                    }
                }
            }
        } finally {
            cursor.close();
        }

    }
}
