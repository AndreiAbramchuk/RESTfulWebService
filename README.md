Simple REST API with Spring Boot 2.2.1 , Spring JDBC and MySQL.

This service accepts HTTP GET, POST, PUT and DELETE requests at <http://localhost:8080/employees/>

HTTP GET request at http://localhost:8080/employees/1

It responds with a JSON representation of an employee with employeeId=1  as the following listing shows:


It will respond with a JSON representation of a greeting, as the following listing shows:

====
[source,json]

----


{"id":1,"content":"Hello, World!"}

----


====

