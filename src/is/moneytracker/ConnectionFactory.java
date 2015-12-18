/**
 *
 */
package is.moneytracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.spi.ServiceException;

import com.mysql.jdbc.CommunicationsException;

/**
 * @author Van-Duyet Le
 *
 */
public class ConnectionFactory {
	private static final SessionFactory sessionFactory = buildSessionFactory();
	private Session session;

    private static SessionFactory buildSessionFactory() {
        try
        {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }

	/**
	 * @return the factory
	 */
	public static SessionFactory getFactory() {
		return sessionFactory;
	}

	/**
	 * @return the session
	 */
	public Session getSession() {
		return session;
	}

	/**
	 * @param session the session to set
	 */
	public void setSession(Session session) {
		this.session = session;
	}
}
