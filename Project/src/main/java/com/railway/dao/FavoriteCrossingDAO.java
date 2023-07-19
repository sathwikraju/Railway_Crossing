package com.railway.dao;

import com.railway.models.FavoriteCrossing;
import com.railway.models.RailwayCrossing;
import com.railway.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.stream.Collectors;

public class FavoriteCrossingDAO {

    public static void saveFavoriteCrossing(FavoriteCrossing favoriteCrossing) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(favoriteCrossing);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void removeFavoriteCrossing(int crossingId, int userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query<?> query = session.createQuery("DELETE FROM FavoriteCrossing WHERE railwayCrossing.id = :crossingId AND userId = :userId");
        query.setParameter("crossingId", crossingId);
        query.setParameter("userId", userId);
        query.executeUpdate();

        session.getTransaction().commit();
        session.close();
    }
    public static List<RailwayCrossing> getFavoriteCrossingsByUserId(int userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<RailwayCrossing> query = session.createQuery(
                    "SELECT fc.railwayCrossing FROM FavoriteCrossing fc WHERE fc.userId = :userId", RailwayCrossing.class);
            query.setParameter("userId", userId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<RailwayCrossing> getFavoriteCrossings(int userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<FavoriteCrossing> query = session.createQuery(
                    "FROM FavoriteCrossing WHERE userId = :userId", FavoriteCrossing.class);
            query.setParameter("userId", userId);
            List<FavoriteCrossing> favoriteCrossings = query.list();

            // Extract the RailwayCrossing objects from the list of FavoriteCrossing objects
            // and return the list of favorite crossings
            return favoriteCrossings.stream()
                    .map(FavoriteCrossing::getRailwayCrossing)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
