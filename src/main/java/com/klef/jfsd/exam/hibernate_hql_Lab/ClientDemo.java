package com.klef.jfsd.exam.hibernate_hql_Lab;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;

public class ClientDemo {
    public static void main(String[] args) {
        // Configure Hibernate
        Session session = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // HQL Update using Positional Parameters
            String hql = "UPDATE Department SET name = ?1, location = ?2 WHERE id = ?3";
            Query query = session.createQuery(hql);
            query.setParameter(1, "Updated Department Name");
            query.setParameter(2, "Updated Location");
            query.setParameter(3, 1);

            int rowsAffected = query.executeUpdate();
            System.out.println("Rows Affected: " + rowsAffected);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
