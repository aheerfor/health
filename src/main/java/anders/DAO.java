package anders;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
    private static final Logger logger = Logger.getLogger(DAO.class.getName());
    public static final String DB_URL =
            //"jdbc:mysql://uranus.heerfordt.dk:3306/anders";
            "jdbc:mysql://172.232.195.43:3306/anders";
    //uranus2 172.232.195.43
    //uranus 139.162.232.213
    public static final String DRIVER =
            "com.mysql.cj.jdbc.Driver";
    public static final String USER = "anders";
    public static final String PASS = "vallernyt0";
    static int depth = 0;
    public static int connection_count;

    public static Connection con ;
    static {
        con = null;
        try {
            Class.forName(DRIVER);
        } catch (Exception e) {
            logger.trace("com.anders.DAO static: "+e);
            Breaker.breaker();
        }

    }

    public static boolean  openConnection() {
        int pos = -1;
        try {
            depth++;
            if (depth < 2) {
                //logger.trace("Openconnection");
                con = DriverManager.getConnection
                        (DB_URL, USER, PASS);
                connection_count++;
                pos = 1;
                if (!session.getTransaction().isActive()) {
                    pos = 2;
                    session.beginTransaction();
                }
                pos = 3;

                return true;
            }
            //logger.trace("Open connection depth = "+depth);
            return true;
        } catch (Exception e) {
            logger.error("openConnection "+depth+ " "+e);
            logger.error("com.anders.DAO open: "+e + " pos="+pos);
            Breaker.breaker();
        }
        logger.error("Couldnt open connection");
        Popup.showError("Couldnt open connection");
        return false;

    }

    public static boolean  closeConnection() {
        int pos = -1;
        try {
            depth--;
            if (depth < 1) {
                pos = 1;
                session.getTransaction().commit();
                pos = 3;
                con.close();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.trace("com.anders.DAO close: "+e+ " pos="+pos);
            Breaker.breaker();
        }
        Popup.showError("Couldnt close connection");
        return false;
    }

    public static void  ConnectionisOpen(int where) {
        try {
            logger.trace("Closed?="+con.isClosed()+ " "+where);
        } catch (Exception e) {
            logger.trace("com.anders.DAO closed?: "+e);
            Breaker.breaker();
        }

    }

    private static final SessionFactory sessionFactory = buildSessionFactory();
    static Session session;
    static EntityManager em;
    static {
        session = DAO.getSessionFactory().openSession();
        //CoinTransaction.updateTimes();
        em = session.getEntityManagerFactory().createEntityManager();

    }
    private static SessionFactory buildSessionFactory() {
        try {
            //File hibernatecfg = new File("src/main/resources/hibernate.cfg.xml");
            //File mapping = new File("src/main/resources/named-queries.hbm.xml");
            // Create the SessionFactory from hibernate.cfg.xml
            //return new Configuration().configure("hibernate.cfg.xml").addResource("named-queries.hbm.xml").buildSessionFactory();
            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            //return new Configuration().buildSessionFactory();

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

    public static String cut(String x, int n) {
        String ret = x;
        if (ret.length() <= n) return x;
        return x.substring(0,n);
    }

}