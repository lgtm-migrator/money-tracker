package is.moneytracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import is.moneytracker.model.*;

public class TestConnect {

	public static void main(String[] args) {

        Session session = ConnectionFactory.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();
        session.save(new User("Duyet", "duyetdev", "duyetdev", "ok"));
        session.save(new User("Trang", "trangtran", "trangtran", "ok"));


        System.out.println("Object saved successfully.....!!");
        tx.commit();
        session.close();
        ConnectionFactory.shutdown();
	}

}
