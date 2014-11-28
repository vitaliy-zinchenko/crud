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


        DB db = (DB) context.getBean("db");
        DBCollection dbCollection = db.getCollection("grades");


        DBCursor cursor = db.getCollection("grades").find();
        try {
            while(cursor.hasNext()) {
                System.out.println(cursor.next());
            }
        } finally {
            cursor.close();
        }

    }

}
