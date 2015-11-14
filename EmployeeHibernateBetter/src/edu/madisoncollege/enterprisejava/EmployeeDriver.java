package edu.madisoncollege.enterprisejava;

import edu.madisoncollege.enterprisejava.entity.Employee;
import edu.madisoncollege.enterprisejava.persistence.EmployeeDao;

/**
 * Created by Dave on 11/4/2015.
 */

public class EmployeeDriver {
    public static void main(String[] args) {
        EmployeeDao dao = new EmployeeDao();

        // dao.getEmployeeList
        System.out.println("The employees before running: ");
        System.out.println(dao.getEmployeeList());

        // dao.addEmployee
        // Create a new Employee object
        Employee employeeInsert = new Employee(0, "Dean", "Martin","222-33-4444","ET","100","4148887771");
        // Call the add method on the DAO
        int employeeId = dao.addEmployee(employeeInsert);

        System.out.println("The employees after insert : ");
        System.out.println(dao.getEmployeeList());
        System.out.println();

        // dao.addOrUpdateEmployee
        // Update the Employee that we just added
        Employee employeeUpdate = new Employee(employeeId, "Mean", "Dartin","111-00-9999","HR","100","6088068888");
        dao.addOrUpdateEmployee(employeeUpdate);

        System.out.println("The employees after update : ");
        System.out.println(dao.getEmployeeList());
        System.out.println();

        // dao.getEmployee
        // get just one Employee (the one that we just updated)
        System.out.println("Getting one record");
        System.out.println(dao.getEmployee(employeeId));
        System.out.println();

        // dao.getEmployees
        // get a list of Employee for a lastname
        System.out.println("Getting employees for a lastname");
        System.out.println(dao.getEmployees("Martin"));
        System.out.println();

        // dao.deleteEmployeeById
        // Delete the role that we just updated
        dao.deleteEmployeeById(employeeId);

        System.out.println("The employees after delete : ");
        System.out.println(dao.getEmployeeList());
        System.out.println();


    }
}

