package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Andrei Abramchuk
 */

@Repository
public class EmployeeDaoImpl implements EmployeeDao{
   
    private  JdbcTemplate jdbcTemplate;
    
    private  NamedParameterJdbcTemplate namedParameterjdbcTemplate;
    
    private Map<String, Object> params;
   
    @Autowired
    public EmployeeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterjdbcTemplate=
                                  new NamedParameterJdbcTemplate(jdbcTemplate);
    }
   
     /**Method geretates Map <String, Object> params to create a query 
     * with  namedParameterjdbcTemplate
     * @param emp
     * @return params
     */
    public Map <String, Object> getParams(Employee emp) {
            params = new HashMap<>();
            params.put("employeeId", emp.getEmployeeId());
            params.put("firstName", emp.getFirstName());
            params.put("lastName", emp.getLastName());
            params.put("departmentId", emp.getDepartmentId()); 
            params.put("jobTitle", emp.getJobTitle());
            params.put("gender", String.valueOf(emp.getGender()));
            params.put("dateOfBirth", emp.getDateOfBirth());
        return params;
    }
    
    @Override
    public Employee createEmployee(Employee emp) {
        String SQL = "INSERT INTO employees (employeeId, firstName, lastName, "
                + "departmentId, jobTitle, gender, dateOfBirth) "
                + "VALUES (:employeeId, :firstName, :lastName, :departmentId, "
                + ":jobTitle, :gender, :dateOfBirth)";
        params=null;
        namedParameterjdbcTemplate.update(SQL, getParams(emp));
        params=null;
        return emp;
    }
    
    @Override
    public Long deleteEmployeeById(Long employeeId) {
        String SQL = "DELETE FROM employees WHERE employeeId = ?";
        jdbcTemplate.update(SQL, employeeId);
        return employeeId;
    }

    @Override
    public Employee findEmployeeById(Long employeeId) {
        String SQL = "SELECT * FROM employees WHERE employeeId = ?";
        return jdbcTemplate.queryForObject(SQL, new EmployeeMapper(),
                                           employeeId );      
    }                   

    @Override
    public Employee updateEmployeeById(Long employeeId, Employee emp){
        String SQL = "UPDATE employees SET firstName= :firstName, lastName= "
                + ":lastName, departmentId= :departmentId, jobTitle= :jobTitle,"
                + " gender= :gender,  dateOfBirth= :dateOfBirth "
                   + " WHERE employeeId= :employeeId";
        params=null;
        namedParameterjdbcTemplate.update(SQL, getParams(emp));
        params=null;       
        emp.setEmployeeId(employeeId);
        return emp;
    }
    
    @Override
    public List<Employee> listOfEmployees() {
        String SQL = "SELECT * FROM employees";
        List<Employee>employees = jdbcTemplate.query(SQL, new EmployeeMapper());
        return employees;
    }    
}