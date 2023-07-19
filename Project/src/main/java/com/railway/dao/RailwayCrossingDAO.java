package com.railway.dao;

import com.railway.models.RailwayCrossing;
import com.railway.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RailwayCrossingDAO {

    public static void saveRailwayCrossing(RailwayCrossing crossing) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(crossing);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static List<RailwayCrossing> getAllRailwayCrossings() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM RailwayCrossing", RailwayCrossing.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static RailwayCrossing getRailwayCrossingById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(RailwayCrossing.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void updateRailwayCrossing(RailwayCrossing crossing) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(crossing);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public static void deleteRailwayCrossing(int crossingId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(session.load(RailwayCrossing.class, crossingId)); // Use session.load to get the entity for deletion
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


	public static List<RailwayCrossing> searchRailwayCrossingsByName(String searchString) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM RailwayCrossing WHERE name LIKE :searchString", RailwayCrossing.class)
                    .setParameter("searchString", "%" + searchString + "%")
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
		return null;
        }
	}
}
