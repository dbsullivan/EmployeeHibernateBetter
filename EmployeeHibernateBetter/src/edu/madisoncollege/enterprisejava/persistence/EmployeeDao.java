package edu.madisoncollege.enterprisejava.persistence;

import edu.madisoncollege.enterprisejava.entity.Employee;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dave on 11/4/2015.
 */

public class EmployeeDao {

    /* Method to CREATE a employee in the database */
    public Integer addEmployee(Employee employee) {

        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        Integer employeeId = null;
        try {
            tx = session.beginTransaction();
            employeeId = (Integer) session.save(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employeeId;
    }

    /** Method to CREATE or UPDATE a employee in the database
     * If an id of 0 is passed in, a new Employee entry will be created
     * If an id of an existing record is passed in, the Employee is updated
     *
     * @param employee The UserRole to be added or created
     **/
    public void addOrUpdateEmployee(Employee employee) {

        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        Integer employeeId = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /** Method to list all employees in the database
     *
     * @return all employees
     **/
    public List getEmployeeList() {
        List<Employee> employees = new ArrayList<Employee>();
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        // Criteria is used to narrow searches.
        // In this case we are using the simplest criteria to get all Employee rows
        Criteria criteria=session.createCriteria(Employee.class);
        try {
            employees = criteria.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employees;
    }

    /** Method to delete a Employee
     *
     * @param employeeId of the Employee to be deleted
     **/
    public void deleteEmployeeById(int employeeId) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Employee employee = (Employee)session.get(Employee.class, employeeId);
            if (employee != null) {
                session.delete(employee);
            }
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    /** Method to retrieve a Employee by Id
     *
     * @param employeeId of the Employee to be retrieved
     * @return Employee matching the employeeId requested
     **/
    public Employee getEmployee(int employeeId) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Employee employee = null;
        try {
            employee = (Employee)session.get(Employee.class, employeeId);
            if(employee != null){
                return employee;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employee;
    }

    /** Method to retrieve Employees by lastName
     * Note the use of Criteria and Restrictions
     * Learn more about advanced queries in hibernate here:
     * http://www.developer.com/db/using-criteria-in-hibernate-for-advanced-queries.html
     *
     *
     * @param EmployeeLastName of the Employees to be retrieved
     * @return Employees matching the last name requested
     **/
    public List getEmployees(String EmployeeLastName) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Employee.class);
        // A restriction is used to limit search results
        criteria.add(Restrictions.like("lastName", EmployeeLastName));
        List<Employee> employees = null;
        try {
            employees = criteria.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employees;
    }

}
