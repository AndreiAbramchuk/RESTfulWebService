package com.mastery.java.task.service;

import com.mastery.java.task.dto.Employee;
import java.util.List;

/**
 *
 * @author Andrei Abramchuk
 */

public interface EmployeeService {
    
    Employee createEmployee(Employee employee) throws Exception;
    
    Long deleteEmployeeById(Long employeeId) throws Exception;
    
    Employee findEmployeeById(Long employeeId) throws Exception;
    
    Employee updateEmployeeById(Long id, Employee employee) throws Exception;
    
    List<Employee> listOfEmployees();
}
