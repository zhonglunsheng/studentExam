package com.lipop.dao;

import com.lipop.model.Manager;
import com.lipop.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

public class ManagerDao {
    public Manager login(Manager Manager)throws Exception{
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Manager as m where m.userName=:userName and m.password=:password");
        query.setString("userName",Manager.getUserName())
                .setString("password",Manager.getPassword());
        Manager resultMan = (Manager)query.uniqueResult();
        session.getTransaction().commit();
        return resultMan;
    }

    public void updateManager(Manager Manager)throws Exception{
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(Manager);
        session.getTransaction().commit();
    }
}
