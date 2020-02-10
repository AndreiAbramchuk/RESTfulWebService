package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author Andrei Abramchuk
 */

@Component
public class EmployeeMapper implements RowMapper<Employee>{
   
    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee= new Employee();
            employee.setEmployeeId(rs.getLong(1));
            employee.setFirstName(rs.getString(2));
            employee.setLastName(rs.getString(3));
            employee.setDepartmentId(rs.getLong(4));
            employee.setJobTitle(rs.getString(5));
            employee.setGender( Gender.valueOf(rs.getString(6)));
            employee.setDateOfBirth(rs.getString(7));
        return employee;
    }  
}
