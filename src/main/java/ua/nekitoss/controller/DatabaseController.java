package ua.nekitoss.controller;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ua.nekitoss.model.heroes.Hero;

import javax.persistence.criteria.CriteriaQuery;
import javax.swing.*;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class DatabaseController {

    private static SessionFactory sessionFactory = null;
    private static DatabaseController instance = null;

    static {
      if (sessionFactory == null) {
        try {
//        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE); // Disable this line if you want to see all hibernate logs
          sessionFactory = new Configuration().configure().buildSessionFactory();
//      sessionFactory = new Configuration().configure().addAnnotatedClass(TableTestClass.class).buildSessionFactory();
        } catch (Throwable ex) {
          System.err.println("Initial SessionFactory creation failed." + ex);
          throw new ExceptionInInitializerError(ex);
        }
      }
    }

  public static DatabaseController getInstance(){
    if (instance == null){
      instance = new DatabaseController();
    }
    return instance;
  }

    public int add(Hero t) throws SQLException {
      Session session = null;
      Transaction tx = null;
      int id = 0;
      try {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        id = (int) session.save(t); //return id of save
        tx.commit(); //session.getTransaction().commit();
      } catch (Exception e) {
        if (tx != null) tx.rollback();
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при вставке", JOptionPane.OK_OPTION);
      } finally {
        if (session != null && session.isOpen()) {
          session.close();
        }
      }
      return id;
    }

    public void saveOrUpdate(Hero t) throws SQLException {
      Session session = null;
      Transaction tx = null;
      try {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        session.saveOrUpdate(t);
        tx.commit();
      } catch (Exception e) {
        if (tx != null) tx.rollback();
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при вставке", JOptionPane.OK_OPTION);
      } finally {
        if (session != null && session.isOpen()) {
          session.close();
        }
      }
    }

    public static void getAllData() throws Exception {

      Session session = sessionFactory.openSession();
      org.hibernate.Transaction tr = session.beginTransaction();
      CriteriaQuery cq = session.getCriteriaBuilder().createQuery(Hero.class);

      cq.from(Hero.class);
      List<Hero> employeeList = session.createQuery(cq).getResultList();

//      for (Hero employee : employeeList) {
//        System.out.println("ID: " + employee.getId());
//        System.out.println("Name: " + employee.getName());
//      }

      tr.commit();
      session.close();
    }

    // Method to  READ all the heroes
    public List<Hero> listHero() {
      Session session = sessionFactory.openSession();
      Transaction tx = null;
      List<Hero> heroList = null;

      try {
        tx = session.beginTransaction();
        heroList = session.createQuery("FROM Hero").list();
//        for (Iterator iterator = heroList.iterator(); iterator.hasNext(); ) {
//          Hero ttOne = (Hero) iterator.next();
////          System.out.print("Id: " + ttOne.getId());
////          System.out.println("  Name: " + ttOne.getName());
//        }
        tx.commit();
      } catch (HibernateException e) {
        if (tx != null) tx.rollback();
        e.printStackTrace();
      } finally {
        session.close();
      }
      return heroList;
    }

    // Method to UPDATE salary for an employee
    /*public void update(Integer id, String newName) {
      Session session = sessionFactory.openSession();
      Transaction tx = null;

      try {
        tx = session.beginTransaction();
        TableTestClass ttOne = (TableTestClass) session.get(TableTestClass.class, id);
        ttOne.setName(newName);
        session.update(ttOne);
        tx.commit();
      } catch (HibernateException e) {
        if (tx != null) tx.rollback();
        e.printStackTrace();
      } finally {
        session.close();
      }
    }*/

    // Method to DELETE an employee from the records
    /*public void delete(Integer id) {
      Session session = sessionFactory.openSession();
      Transaction tx = null;

      try {
        tx = session.beginTransaction();
        TableTestClass ttOne = (TableTestClass) session.get(TableTestClass.class, id);
        session.delete(ttOne);
        tx.commit();
      } catch (HibernateException e) {
        if (tx != null) tx.rollback();
        e.printStackTrace();
      } finally {
        session.close();
      }
    }*/

    public static String[] getHeroNamesListFromHeroList(List <Hero> heroList){
      String names[] = new String[heroList.size()];

      for (int i = 0; i < names.length; i++){

        names[i] = (i + 1) + ": " + heroList.get(i).getName();
      }

      return names;
    }

}
