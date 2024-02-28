package Dao;

import java.util.List;

import beans.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



    public class UserDao {



        private static SessionFactory sessionFactory;

        static {
            try {
                Configuration configuration = new Configuration();
                configuration.configure("hibernate.cfg.xml");
                sessionFactory = configuration.buildSessionFactory();
            } catch (Throwable ex) {
                System.err.println("Initial SessionFactory creation failed." + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }


//        public static int save(User u) {
//            int status = 0;
//            Transaction transaction = null;
//            try (Session session = sessionFactory.openSession()) {
//                transaction = session.beginTransaction();
//                session.save(u);
//                transaction.commit();
//                status = 1;
//            } catch (Exception e) {
//                if (transaction != null) {
//                    transaction.rollback();
//                }
//                e.printStackTrace();
//            }
//
//            return status;
//        }
public static int save(User u) {
    int status = 0;
    Session session = hibernateutil.getSessionFactory().openSession();
    Transaction transaction = null;

    try {
        transaction = session.beginTransaction();
        session.save(u);
        transaction.commit();
        status = 1;
    } catch (Exception e) {
        if (transaction != null) {
            transaction.rollback();
        }
        e.printStackTrace();
    } finally {
        session.close();
    }
    return status;
}




        public static int update(User u) {
            int status = 0;
            Transaction transaction = null;
            try (Session session = sessionFactory.openSession()) {
                transaction = session.beginTransaction();
                session.update(u);
                transaction.commit();
                status = 1;
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
            return status;
        }

        public static int delete(User u) {
            int status = 0;
            Transaction transaction = null;
            try (Session session = sessionFactory.openSession()) {
                transaction = session.beginTransaction();
                session.delete(u);
                transaction.commit();
                status = 1;
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
            return status;
        }

        public static List<User> getAllRecords() {
            try (Session session = sessionFactory.openSession()) {
                return session.createQuery("FROM User", User.class).list();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        public static User getRecordById(int id) {
            try (Session session = sessionFactory.openSession()) {
                return session.get(User.class, id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }


