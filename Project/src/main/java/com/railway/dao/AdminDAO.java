package com.railway.dao;

import com.railway.models.Admin;
import com.railway.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class AdminDAO {

    public static void saveAdmin(Admin admin) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(admin);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static Admin getAdminByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Admin WHERE email = :email", Admin.class)
                    .setParameter("email", email)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isAdminExists(String email, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Admin admin = session.createQuery("FROM Admin WHERE email = :email AND password = :password", Admin.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .uniqueResult();
            return admin != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
