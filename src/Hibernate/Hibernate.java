package Hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;
import java.util.Scanner;


/**
 * Tutorial (tons of it is deprecated):
 * https://www.lynda.com/Java-tutorials/NetBeans-setup/534635/584496-4.html
 *
 * Requirements:
 * 1. The MySQL Connector/J library must be added to the project. (https://dev.mysql.com/downloads/connector/j/)
 * 2. The required jar files of Hibernate must be added to the project. (http://hibernate.org/orm/releases/5.4/)
 * 2. A database named "messagerepository" must be created and hosted at "localhost:3306". The username must be "root" and the password must be blank ("").
 * 3. A table named "message" must be added via the following SQL statement:
 * CREATE TABLE Message (
 * id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
 * message VARCHAR (50),
 * PRIMARY KEY (id)
 * );
 */

public class Hibernate {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a message: ");
        String text = scanner.nextLine();

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }

        Transaction transaction = null;
        Short msgId = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            MessageEntity messageEntity = new MessageEntity(text);
            msgId = (Short) session.save(messageEntity);

            List messages = session.createQuery("FROM MessageEntity").list();
            for (Object object : messages) {
                MessageEntity message = (MessageEntity) object;
                System.out.println("Message ID: " + message.getId() + ". Message: " + message.getMessage());
            }

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            e.printStackTrace();
        }
    }
}

