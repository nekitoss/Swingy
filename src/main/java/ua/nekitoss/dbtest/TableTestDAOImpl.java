package ua.nekitoss.dbtest;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaQuery;
import javax.swing.*;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class TableTestDAOImpl {

  private static SessionFactory sessionFactory = null;

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

  public int addTT(SuperClass t) throws SQLException {
    Session session = null;
    Transaction tx = null;
    int id = 0;
    try {
      session = sessionFactory.openSession();
      tx = session.beginTransaction();

      id = (int) session.save(t); //return id of save
//      session.saveOrUpdate(t);
      tx.commit(); //session.getTransaction().commit();
//      System.out.println("id=" + id);
      System.out.println(t.getId());
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

  public void saveOrUpdate(SuperClass t) throws SQLException {
    Session session = null;
    Transaction tx = null;
    try {
      session = sessionFactory.openSession();
      tx = session.beginTransaction();
      session.saveOrUpdate(t);
      tx.commit();
      System.out.println(t.getId());
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
    CriteriaQuery cq = session.getCriteriaBuilder().createQuery(TableTestClass.class);

    cq.from(TableTestClass.class);
    List<TableTestClass> employeeList = session.createQuery(cq).getResultList();

    for (TableTestClass employee : employeeList) {
      System.out.println("ID: " + employee.getId());
      System.out.println("Name: " + employee.getName());
    }

    tr.commit();
    System.out.println("Data printed");
    session.close();
  }

  // Method to  READ all the employees
  public void listEmployees() {
    Session session = sessionFactory.openSession();
    Transaction tx = null;

    try {
      tx = session.beginTransaction();
      List employees = session.createQuery("FROM TableTestClass").list();
      for (Iterator iterator = employees.iterator(); iterator.hasNext(); ) {
        TableTestClass ttOne = (TableTestClass) iterator.next();
        System.out.print("Id: " + ttOne.getId());
        System.out.println("  Name: " + ttOne.getName());
      }
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) tx.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
  }

  // Method to UPDATE salary for an employee
  public void updateEmployee(Integer id, String newName) {
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
  }

  // Method to DELETE an employee from the records
  public void deleteEmployee(Integer id) {
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
  }

}

/*
public class ManageEmployee {
   private static SessionFactory factory;
   public static void main(String[] args) {

      try {
         factory = new Configuration().configure().buildSessionFactory();
      } catch (Throwable ex) {
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex);
      }

      ManageEmployee ME = new ManageEmployee();

      // Add few employee records in database
Integer empID1 = ME.addEmployee("Zara", "Ali", 1000);
  Integer empID2 = ME.addEmployee("Daisy", "Das", 5000);
  Integer empID3 = ME.addEmployee("John", "Paul", 10000);

// List down all the employees
      ME.listEmployees();

              // Update employee's records
              ME.updateEmployee(empID1, 5000);

              // Delete an employee from the database
              ME.deleteEmployee(empID2);

              // List down new list of the employees
              ME.listEmployees();
              }

// Method to CREATE an employee in the database
public Integer addEmployee(String fname, String lname, int salary){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer employeeID = null;

        try {
        tx = session.beginTransaction();
        Employee employee = new Employee(fname, lname, salary);
        employeeID = (Integer) session.save(employee);
        tx.commit();
        } catch (HibernateException e) {
        if (tx!=null) tx.rollback();
        e.printStackTrace();
        } finally {
        session.close();
        }
        return employeeID;
        }

// Method to  READ all the employees
public void listEmployees( ){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
        tx = session.beginTransaction();
        List employees = session.createQuery("FROM Employee").list();
        for (Iterator iterator = employees.iterator(); iterator.hasNext();){
        Employee employee = (Employee) iterator.next();
        System.out.print("First Name: " + employee.getFirstName());
        System.out.print("  Last Name: " + employee.getLastName());
        System.out.println("  Salary: " + employee.getSalary());
        }
        tx.commit();
        } catch (HibernateException e) {
        if (tx!=null) tx.rollback();
        e.printStackTrace();
        } finally {
        session.close();
        }
        }

// Method to UPDATE salary for an employee
public void updateEmployee(Integer EmployeeID, int salary ){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
        tx = session.beginTransaction();
        Employee employee = (Employee)session.get(Employee.class, EmployeeID);
        employee.setSalary( salary );
        session.update(employee);
        tx.commit();
        } catch (HibernateException e) {
        if (tx!=null) tx.rollback();
        e.printStackTrace();
        } finally {
        session.close();
        }
        }

// Method to DELETE an employee from the records
public void deleteEmployee(Integer EmployeeID){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
        tx = session.beginTransaction();
        Employee employee = (Employee)session.get(Employee.class, EmployeeID);
        session.delete(employee);
        tx.commit();
        } catch (HibernateException e) {
        if (tx!=null) tx.rollback();
        e.printStackTrace();
        } finally {
        session.close();
        }
        }
        }
*/
