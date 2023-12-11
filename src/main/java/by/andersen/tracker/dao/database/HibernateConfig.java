package by.andersen.tracker.dao.database;
import java.util.Properties;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import by.andersen.tracker.model.Employee;
import by.andersen.tracker.model.Task;
import by.andersen.tracker.model.Time;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateConfig {
    private static final SessionFactory sessionFactory;
    static {
        try {
            Configuration configuration = new Configuration();
            Properties prop = new Properties();
            prop.setProperty("hibernate.connection.url", "jdbc:mysql://10.186.0.5:3306/trackerDB");
            prop.setProperty("hibernate.connection.username", "dev");
            prop.setProperty("hibernate.connection.password", "!1Aarootroot");
            prop.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            prop.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            prop.setProperty(Environment.SHOW_SQL, "true");
            configuration.setProperties(prop);
            configuration.addAnnotatedClass(Employee.class);
            configuration.addAnnotatedClass(Task.class);
            configuration.addAnnotatedClass(Time.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

}
