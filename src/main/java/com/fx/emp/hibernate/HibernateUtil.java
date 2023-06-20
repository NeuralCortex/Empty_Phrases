package com.fx.emp.hibernate;

import com.fx.emp.Globals;
import com.fx.emp.hibernate.entity.Part;
import java.sql.Connection;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static Configuration config = null;
    public static boolean connected = false;

    static {
        connected = applyConnection();
    }

    private static boolean applyConnection() {
        boolean ok = false;
        StandardServiceRegistry serviceReg = null;
        try {
            config = loadConfig();
            serviceReg = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
            sessionFactory = config.buildSessionFactory(serviceReg);
            sessionFactory.getCurrentSession().doWork((Connection conn) -> {
                conn.prepareStatement("PRAGMA foreign_keys = ON;").execute();
                conn.prepareStatement("PRAGMA cache_size = -1000000;").execute();
                conn.prepareStatement("PRAGMA synchronous = OFF;").execute();
                conn.prepareStatement("PRAGMA temp_store = MEMORY;").execute();
            });
            ok = true;
        } catch (HibernateException ex) {
            System.out.println(ex.getMessage());
        }
        return ok;
    }

    private static Configuration loadConfig() {
        config = new Configuration();

        /*
        SQLiteConfig liteConfig = new SQLiteConfig();
        liteConfig.setOpenMode(SQLiteOpenMode.READWRITE);
        liteConfig.enforceForeignKeys(true);
        liteConfig.setPageSize(65535);
        liteConfig.setCacheSize(-1000000);
        liteConfig.setSynchronous(SQLiteConfig.SynchronousMode.OFF);
         */
        Properties properties = new Properties();
        properties.put("hibernate.connection.driver_class", "org.sqlite.JDBC");
        String dbPath = Globals.DB_PATH;
        properties.put("hibernate.connection.url", "jdbc:sqlite:" + dbPath);
        properties.put("hibernate.connection.username", "");
        properties.put("hibernate.connection.password", "");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.dialect", "org.sqlite.hibernate.dialect.SQLiteDialect");
        properties.put("hibernate.current_session_context_class", "thread");

        config.addProperties(properties);

        config.addAnnotatedClass(Part.class);

        return config;
    }

    public static boolean isConnected() {
        return connected;
    }

    public static Session getSession() throws HibernateException {
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    public static void closeSession() {
        if (HibernateUtil.getSession().isOpen()) {
            HibernateUtil.getSession().close();
        }
    }

    public static void closeSessionFactory() {
        if (!sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }

    public static Session beginTransaction() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        return session;
    }

    public static void commitTransaction() {
        HibernateUtil.getSession().getTransaction().commit();
    }

    public static void rollbackTransaction() {
        HibernateUtil.getSession().getTransaction().rollback();
    }
}
