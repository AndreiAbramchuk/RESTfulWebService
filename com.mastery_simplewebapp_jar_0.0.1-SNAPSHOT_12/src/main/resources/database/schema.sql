DROP TABLE  IF EXISTS employees;  
CREATE TABLE  employees (
  employeeId   SERIAL   PRIMARY KEY,
  firstName    VARCHAR(20)            NOT NULL,
  lastName     VARCHAR(20)            NOT NULL,
  departmentId BIGINT (15)            NOT NULL,
  jobTitle     VARCHAR(50)            NOT NULL,
  gender ENUM("MALE", "FEMALE")       NOT NULL,   
  dateOfBirth  VARCHAR(10)            NOT NULL
);
  