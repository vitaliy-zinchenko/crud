package zinjvi;

import com.mongodb.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import zinjvi.repository.Repository;

import java.util.List;

/**
 * Created by zinchenko on 23.10.14.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Start");
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:zinjvi/main.xml");

        Repository<Bean, String> repository = (Repository<Bean, String>) context.getBean("concreteMongoRepository");

//        Bean bean = new Bean();
//        bean.setName("name-"+System.currentTimeMillis());
//        repository.save(bean);

        //////////////////

//        Bean bean = new Bean();
//        bean.setId("544921ebe4b068a38b74cc80");
//        bean.setName("nn");
//        repository.update(bean);
//
//        List<Bean> beans = repository.findAll();
//        System.out.println(beans);

        ///////////////////

        Bean bean = repository.find("544921ebe4b068a38b74cc80");
        System.out.println(bean);


//        DB db = (DB) context.getBean("db");
//        DBCollection dbCollection = db.getCollection("beans");
//        DBObject dbObject = new BasicDBObject();
//        dbObject.put("name", "name_" + System.currentTimeMillis());
//        dbCollection.insert(dbObject);
//
//        DBCursor cursor = db.getCollection("beans").find();
//        try {
//            while(cursor.hasNext()) {
//                System.out.println(cursor.next());
//            }
//        } finally {
//            cursor.close();
//        }

    }

}
