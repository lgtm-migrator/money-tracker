/**
 *
 */
package is.moneytracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Van-Duyet Le
 *
 */
public class ConnectionFactory {
	private Configuration cfg;
	private SessionFactory factory;
	private Session session;

	public ConnectionFactory() {
		this.cfg = new Configuration();
	    this.cfg.configure("hibernate.cfg.xml");

	    this.factory = cfg.buildSessionFactory();
	    this.session = this.factory.openSession();
	}

	/**
	 * @return the factory
	 */
	public SessionFactory getFactory() {
		return factory;
	}

	/**
	 * @return the session
	 */
	public Session getSession() {
		return session;
	}

	/**
	 * @param factory the factory to set
	 */
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	/**
	 * @param session the session to set
	 */
	public void setSession(Session session) {
		this.session = session;
	}
}
