package zinjvi.repository.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by zinchenko on 23.10.14.
 */
public class HibernateRepository {

    private SessionFactory sessionFactory;

    protected Criteria createCriteria(Class clazz) {
        return getSession().createCriteria(clazz);
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
