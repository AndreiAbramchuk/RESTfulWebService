package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import java.sql.SQLSyntaxErrorException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.stereotype.Component;

/**
 *
 * @author Andrei Abramchuk
 */

@Component
public class EmployeeServiceImpl implements EmployeeService{
    private static final Logger LOGGER = 
        LoggerFactory.getLogger(EmployeeServiceImpl.class);
    
    @Autowired
    EmployeeDao dao;
    
    @Override
    public List<Employee> listOfEmployees() {
        LOGGER.info("*****");
        LOGGER.debug("List<Employee> listOfEmployees():: method run.");
        List<Employee>employees=null;
        try{
            employees = dao.listOfEmployees();
            if (employees == null){
                LOGGER.info("List of Employees is empty!");
            }
            else{
                LOGGER.info("List of Employees is provided.");
            }
            LOGGER.debug("List<Employee> listOfEmployees()::  method was "
            + "executed successfully.");
        }catch(CannotGetJdbcConnectionException | BadSqlGrammarException ve){
            LOGGER.error("Please, check your connection and the table "
                + " 'employeedb.employees' ", ve );
            LOGGER.debug("List<Employee> listOfEmployees():: method execution "
                + "was aborted.");
        }catch(Exception exc){
            LOGGER.error("Error. We do apologize.", exc);
            LOGGER.debug("List<Employee> listOfEmployees():: method execution "
                + "was aborted.");
        }
        return employees;
    }
    
    @Override
    public Employee findEmployeeById(Long employeeId) throws Exception{
        LOGGER.info("***");
        LOGGER.debug("findEmployeeById(Long " +employeeId+"):: method run.");
        Employee existingEmployee=null;
        try{
            existingEmployee=dao.findEmployeeById(employeeId);
            LOGGER.info("An Employee with  employeeId::" +employeeId
                    + " was found.");
            LOGGER.debug("findEmployeeById(Long " +employeeId+")::"
                    + " method was executed successfully.");
        }catch (EmptyResultDataAccessException er){
            LOGGER.info("An Employee with employeeId::" +employeeId
                    + " is not exists.");
            LOGGER.debug("findEmployeeById(Long " +employeeId+")::"
                    + " method was executed successfully.");
        }catch(CannotGetJdbcConnectionException | BadSqlGrammarException cgce){
            LOGGER.error("Please, check your connection and the database "
                + "employeedb.", cgce);
            LOGGER.debug("findEmployeeById(Long " +employeeId+"):: method "
                    + "execution was aborted.");
            throw new SQLSyntaxErrorException();
        }catch (Exception e){
            LOGGER.error("Error. We do apologize.", e);
            LOGGER.debug("findEmployeeById(Long " +employeeId+")::"
                    + " method execution was aborted.");
            throw new Exception();
        }
        return existingEmployee;
    }
    
    @Override
    public Employee createEmployee(Employee employee) throws Exception{
        LOGGER.info("*****");
        LOGGER.debug("createEmployee( " +employee+" ):: method run.");
        LOGGER.debug("Inside method createEmployee( " +employee+")-> ");
        Long employeeId=employee.getEmployeeId();
        Employee creatingEmployee = null;
        try{
            creatingEmployee = findEmployeeById(employeeId);
            if (creatingEmployee == null){
            LOGGER.debug("createEmployee(" + employee + "):: method "
                + "was executed successfully.");
            creatingEmployee = dao.createEmployee(employee);
            LOGGER.info("An "+employee + " was successfully created."); 
            }
            else{
            LOGGER.info("An Employee with employeeId:: "+employeeId + " is "
                    + "already exists." + employee + "wasn't created."); 
            LOGGER.debug("createEmployee( " + employee+ "):: method execution "
                + "was aborted.");
            return null;
            }    
        }catch(CannotGetJdbcConnectionException | BadSqlGrammarException 
                | SQLSyntaxErrorException cgce){
            LOGGER.error("Please, check your connection and the database "
                + "employeedb.", cgce);
            LOGGER.debug("createEmployee(" + employee + "):: method "
                + "execution was aborted.");
            throw new SQLSyntaxErrorException();
        }
        return creatingEmployee;
    }
    
    @Override
    public Long deleteEmployeeById(Long employeeId) throws Exception {
        LOGGER.info("*****");
        LOGGER.debug("deleteEmployeeById(Long " +employeeId+"):: method run.");
        LOGGER.debug("Inside method deleteEmployeeById(Long " +employeeId+")->");
        Long deletingEmployeeId = null;
        Employee deletingEmployee = null;
        try{
            deletingEmployee = findEmployeeById(employeeId);
            if (deletingEmployee == null){
                LOGGER.debug("deleteEmployeeById(Long " +employeeId+")::"
                    + " method execution was aborted.");
                return null;
            }
            deletingEmployeeId=dao.deleteEmployeeById(employeeId);
            LOGGER.info("An " + deletingEmployee +" was successfully removed "
                + "from the database.");
            LOGGER.debug("deleteEmployeeById(Long " +employeeId+")::"
                + " method was executed successfully.");
        }catch (EmptyResultDataAccessException er){
            LOGGER.info("An Employee with employeeId::" +employeeId
                + " is not exists.");
            LOGGER.debug("deleteEmployeeById(Long " +employeeId+")::"
                + " method was aborted.");
        }catch(CannotGetJdbcConnectionException | BadSqlGrammarException 
                | SQLSyntaxErrorException cgce){
            LOGGER.error("Please, check your connection and the database "
                + "employeedb.", cgce);
            LOGGER.debug("deleteEmployeeById(Long " +employeeId+"):: method "
                + "execution was aborted.");
            throw new SQLSyntaxErrorException();
        }catch (Exception e){
            LOGGER.error("Error. We do apologize.", e);
            LOGGER.debug("deleteEmployeeById(Long " +employeeId+")::"
                + " method execution was aborted.");
            throw new Exception();
        }    
        return deletingEmployeeId;
    }
    
    @Override
    public Employee updateEmployeeById(Long employeeId, Employee employee) 
        throws Exception {
        LOGGER.info("*****");
        LOGGER.debug("updateEmployeeById(Long " +employeeId+
                ", "+employee+"):: method run.");
        LOGGER.debug("Inside method updateEmployeeById(Long " +employeeId+ ", "
            + employee+")->");
        Employee updatingEmployee= null;
        try{
            if (findEmployeeById(employeeId) == null){
                LOGGER.debug("updateEmployeeById(Long " +employeeId+ ", "
                    + employee+"):: method execution was aborted.");
                return null;
            }
            updatingEmployee=dao.updateEmployeeById(employeeId, employee);
            LOGGER.info("An Employee with employeeId::" +employeeId
                +" was successfully updated.");
            LOGGER.debug("updateEmployeeById(Long " +employeeId+ ", "
                + employee+"):: method was executed successfully.");
        }catch (EmptyResultDataAccessException er){
            LOGGER.info("An Employee with employeeId::" +employeeId
                + " is not exists.");
            LOGGER.debug("updateEmployeeById(Long " +employeeId+ ", "
                + employee+"):: method execution was aborted.");
        }catch(CannotGetJdbcConnectionException | BadSqlGrammarException 
                | SQLSyntaxErrorException cgce){
            LOGGER.error("Please, check your connection and the database "
                + "employeedb.", cgce);
            LOGGER.debug("updateEmployeeById(Long " +employeeId+ ", "
                + employee+"):: method execution was aborted.");
            throw new SQLSyntaxErrorException();
        }catch (Exception e){
            LOGGER.error("Error. We do apologize.", e);
            LOGGER.debug("updateEmployeeById(Long " +employeeId+ ", "
                + employee+"):: method execution was aborted.");
            throw new Exception();
        }    
        return updatingEmployee;
    }
}
