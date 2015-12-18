package is.moneytracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import is.moneytracker.model.*;

public class TestConnect {

	public static void main(String[] args) {

        Session session = ConnectionFactory.getSessionFactory().openSession();


        User user = new User();
        user.setName("Duyet");
        user.setUsername("duyetdev");
        user.setPassword("xxxx");

        User user2 = user.clone();


        Transaction tx = session.beginTransaction();
        session.save(user);
        session.save(user2);


        System.out.println("Object saved successfully.....!!");
        tx.commit();
        session.close();
        ConnectionFactory.shutdown();
	}

}
