package com.railway.dao;


import com.railway.models.FavoriteCrossing;
import com.railway.models.User;
import com.railway.util.HibernateUtil;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserDAO {

    public static void saveUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public static int authenticateUser(String email, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Query to check if the user exists with the provided email and password
            Query<User> query = session.createQuery("FROM User WHERE email = :email AND password = :password", User.class);
            query.setParameter("email", email);
            query.setParameter("password", password);

            // Get the user with the given email and password
            User user = query.uniqueResult();
            if (user != null) {
                // If the user exists, return the user_id
                return user.getUserId();
            } else {
                // If the user does not exist or the password is incorrect, return -1
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public static boolean isUserExists(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
            query.setParameter("email", email);
            return query.uniqueResult() != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static User getUserById(int userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(User.class, userId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static User getUserByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM User WHERE email = :email", User.class)
                    .setParameter("email", email)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static List<FavoriteCrossing> getFavoriteCrossingsByUserId(int userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<FavoriteCrossing> query = session.createQuery(
                    "FROM FavoriteCrossing WHERE user.id = :userId", FavoriteCrossing.class);
            query.setParameter("userId", userId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isEmailRegistered(String email) {
        User user = getUserByEmail(email);
        return user != null;
    }

    public static boolean isUser(String email, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            User user = session.createQuery("FROM User WHERE email = :email", User.class)
                    .setParameter("email", email)
                    .uniqueResult();
            if (user != null && user.getPassword().equals(password)) {
                return true; // User with the given email and password exists in the database
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
	}
}
