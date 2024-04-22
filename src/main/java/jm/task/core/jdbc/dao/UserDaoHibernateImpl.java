package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final Util util;


    public UserDaoHibernateImpl() {
        util = new Util();
    }


    @Override
    public void createUsersTable() {
        try (Session session = util.getHibernateConnection().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("create table if not exists USERS ("
                    + "Id bigint not null auto_increment, "
                    + "NAME varchar(50) not null, "
                    + "LASTNAME varchar(50) not null, "
                    + "AGE tinyint not null," +
                    "primary key (Id))").executeUpdate();
            session.getTransaction().commit();

        } catch(Exception e) {
            e.getStackTrace();

        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = util.getHibernateConnection().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("drop table if exists USERS").executeUpdate();
            session.getTransaction().commit();

        } catch(Exception e) {
            e.getStackTrace();

        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = util.getHibernateConnection().openSession()) {
            session.beginTransaction();
//            session.persist(new User(name, lastName, age));
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        } catch(Exception e) {
            e.getStackTrace();

        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = util.getHibernateConnection().openSession()) {
            session.beginTransaction();
            User userId = session.get(User.class, id);
            session.delete(userId);
            session.getTransaction().commit();

        } catch(Exception e) {
            e.getStackTrace();

        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = util.getHibernateConnection().openSession()) {
            return session.createQuery("from User", User.class).getResultList();
        } catch(Exception e) {
            e.getStackTrace();
            return null;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = util.getHibernateConnection().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("delete from USERS").executeUpdate();
            session.getTransaction().commit();

        } catch(Exception e) {
            e.getStackTrace();

        }
    }
}
