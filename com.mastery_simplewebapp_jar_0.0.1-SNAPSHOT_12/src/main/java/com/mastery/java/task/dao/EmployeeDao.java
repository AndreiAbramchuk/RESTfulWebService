package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 *
 * @author Andrei Abramchuk
 */

@Component
public interface EmployeeDao {
    
    Employee createEmployee(Employee employee);
    
    Long deleteEmployeeById(Long employeeId);
    
    Employee findEmployeeById(Long employeeId);
    
    Employee updateEmployeeById(Long id, Employee employee);
    
    List <Employee>listOfEmployees();
    
    
}
