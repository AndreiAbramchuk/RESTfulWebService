# RESTfulWebService
Simple REST API with Spring Boot 2.2.1 , Spring JDBC and MySQL.
This service accepts HTTP GET, POST, PUT and DELETE requests at http://localhost:8080/employees/

HTTP GET request at http://localhost:8080/employees/

It responds with a JSON representation of list of employees as the following listing shows:

----
{
        "employeeId": 1,
        "firstName": "Huckleberry",
        "lastName": "Finn",
        "departmentId": 4,
        "jobTitle": "SQA",
        "gender": "MALE",
        "dateOfBirth": "1986-12-21"
    },
----
    {
        "employeeId": 2,
        "firstName": "Tom",
        "lastName": "Soyer",
        "departmentId": 4,
        "jobTitle": "SQA",
        "gender": "MALE",
        "dateOfBirth": "1985-12-21"
    },
----
    {
        "employeeId": 3,
        "firstName": "Becky",
        "lastName": "Thatcher",
        "departmentId": 4,
        "jobTitle": "SQA",
        "gender": "FEMALE",
        "dateOfBirth": "1986-03-22"
    },
----
    {
        "employeeId": 4,
        "firstName": "Jim",
        "lastName": "Hawkins",
        "departmentId": 4,
        "jobTitle": "SD",
        "gender": "MALE",
        "dateOfBirth": "1990-08-08"
    }, 
----
    {
        "employeeId": 5,
        "firstName": "Billy",
        "lastName": "Bones",
        "departmentId": 4,
        "jobTitle": "SD",
        "gender": "MALE",
        "dateOfBirth": "1970-05-04"
    }, 
----
    {
        "employeeId": 6,
        "firstName": "John",
        "lastName": "Silver",
        "departmentId": 4,
        "jobTitle": "PM",
        "gender": "MALE",
        "dateOfBirth": "1975-07-27"
    }
----
