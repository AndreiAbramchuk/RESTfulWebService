package com.mastery.java.task.dto;

/**
 *
 * @author Andrei Abramchuk
 */

public class Employee {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private Long departmentId;
    private String jobTitle;
    private Gender gender;
    // "YYYY-MM-DD"
    private String dateOfBirth;

    public Employee(){
    }  
    
    public Employee(Long employeeId){
        this.employeeId = employeeId;
    }
   
    public Employee(Long employeeId, String firstName, String lastName, 
        Long departmentId, String jobTitle, Gender gender, String dateOfBirth) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentId = departmentId;
        this.jobTitle = jobTitle;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }
    
    public Long getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public Gender getGender() {
        return gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Employee{" + "employeeId=" + employeeId + ", "+ 
                             "firstName=" + firstName + ", "+ 
                             "lastName=" + lastName + ", "+
                             "departmentId=" + departmentId + ", "+
                             "jobTitle=" + jobTitle + ", "+
                             "gender=" + gender + ", "+
                             "dateOfBirth=" + dateOfBirth + '}';
    }
    
}