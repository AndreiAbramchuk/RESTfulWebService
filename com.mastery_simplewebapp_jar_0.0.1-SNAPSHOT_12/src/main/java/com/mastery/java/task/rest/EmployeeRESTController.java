package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import java.sql.SQLSyntaxErrorException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Andrei Abramchuk
 */

@RestController
@EnableAutoConfiguration
public class EmployeeRESTController {
      
    @Autowired
    private EmployeeService service;
        
        @GetMapping("/")
        public @ResponseBody String greeting() {
            return "Hello, User!";
        }
               
	@GetMapping("/employees")
	public ResponseEntity listOfEmployees(){
            List<Employee> employees=null;
            try{
                employees=service.listOfEmployees();
                if(employees.isEmpty()){
                    return new ResponseEntity("The list of Employees is empty! " 
                        + employees, HttpStatus.OK);    
                }    
                return  new ResponseEntity(employees, HttpStatus.OK);    
            }catch(NullPointerException npe){
                return new ResponseEntity("The list of Employees is not exists!"
                    +" Please, check your connection and make sure  that the "
                    + "'employeedb.employees' table "
                    + "exists! ", HttpStatus.NOT_FOUND);
            }finally{
                employees=null;
            }
        }
           
        @GetMapping("/employees/{id}")
	public ResponseEntity findEmployeeById(@PathVariable("id") Long id) {
            Employee employee = null;
            try{
            employee = service.findEmployeeById(id);
		if(employee==null){
                    return new ResponseEntity("No Employee was found by "
                        + "employeeId: "+ id, HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity(employee, HttpStatus.OK);
            }catch(SQLSyntaxErrorException e){
                return new ResponseEntity("No Employee was found by "
                    + " employeeId: " + id +". Please, check your connection "
                    + "and make sure  that the 'employeedb.employees' table "
                    + "exists! ", HttpStatus.NOT_FOUND);
            }catch(Exception e){
            return new ResponseEntity("Error. We do apologize.", 
                    HttpStatus.INTERNAL_SERVER_ERROR);
            }            
            finally{
                employee = null;
            }
        }

        @PostMapping(value = "/employees")
        public ResponseEntity createEmployee(@RequestBody Employee employee){
            Long employeeId=employee.getEmployeeId();
            Employee candidate = null;
            try{
                candidate = service.createEmployee(employee);
                if(candidate==null ){
                    return new ResponseEntity("An Employee with id::"+employeeId 
                    + " is already exists! New Employee with employeeId :" 
                    + employeeId+ " was not added to the "
                    + "database. Please, try with unique id!", 
                    HttpStatus.FORBIDDEN);
                }
                else{
                    return new ResponseEntity(employee, HttpStatus.CREATED);      
                }
            }catch(SQLSyntaxErrorException e){
                return new ResponseEntity("The " + employee + " wasn't created."
                    + " Please, check your connection and make sure that "
                    + "the 'employeedb.employees' table "
                    + "exists! " , HttpStatus.NOT_FOUND);    
            }catch(Exception e){
            return new ResponseEntity("Error. We do apologize.", 
                    HttpStatus.INTERNAL_SERVER_ERROR);              
            }finally{
                candidate = null;
            }   
        }
	
        @DeleteMapping("/employees/{employeeId}")
	public ResponseEntity deleteEmployee(@PathVariable("employeeId") 
                                                              Long employeeId){
            Long deletingEmployeeId= null;
            try{
                deletingEmployeeId = service.deleteEmployeeById(employeeId); 
                if(deletingEmployeeId == null){
                    return new ResponseEntity("No Employee was found by "
                    + "employeeId: "+employeeId, HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity("An Employee with employeeId: " 
                   + deletingEmployeeId + " was successfully removed from "
                   + "the database", HttpStatus.OK);
                
            }catch(SQLSyntaxErrorException e){
                return new ResponseEntity("No Employee was found by "
                    + " employeeId: " + employeeId +". Please, check your "
                    + "connection and make sure that the 'employeedb.employees'"
                    + " table exists! ", HttpStatus.NOT_FOUND);
            }catch(Exception e){
            return new ResponseEntity("Error. We do apologize.", 
                    HttpStatus.INTERNAL_SERVER_ERROR);              
            }finally{
                deletingEmployeeId= null;
            }   
	}
        
        @PutMapping("/employees/{employeeId}")
	public ResponseEntity updateEmployee(@PathVariable("employeeId")
                        Long employeeId, @RequestBody Employee employee){
            Employee updatedEmployee= null;
            try{
                updatedEmployee =
                    service.updateEmployeeById(employeeId, employee);
                if(updatedEmployee == null){
                    return new ResponseEntity("No Employee was found by "
                    + "employeeId: "+employeeId, HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity("An Employee with employeeId: " 
                   + employeeId + " was successfully updated: "+updatedEmployee,
                   HttpStatus.CREATED);
            }catch(SQLSyntaxErrorException e){
                return new ResponseEntity("No Employee was found by "
                    + " employeeId: " + employeeId +". Please, check your "
                    + "connection and make sure that the 'employeedb.employees'"
                    + " table exists! ", HttpStatus.NOT_FOUND);    
            }catch(Exception e){
                return new ResponseEntity("Error. We do apologize.", 
                    HttpStatus.INTERNAL_SERVER_ERROR);          
            }finally{
                updatedEmployee=null;
            }
	}   
}