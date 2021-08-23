# prueba-ebusiness
Prueba técnica caso de estudio - ebusiness

The project requires Java SE 11 (LTS), Node.js 14, Angular 12, PostgreSQL 13 and Maven 3.


Get a copy of the code

    >> git clone https://github.com/maferelo/prueba-ebusiness.git


Modify <base-path>\backend\src\main\resources\application.properties with your database credentials.

# Note: Not safe, implement environment varaibles.
spring.datasource.url=jdbc:postgresql:<db-info> 
spring.datasource.username=<db-username>
spring.datasource.password=<db-password>


Run API

    >> cd <base-path>/backend


Run frontend

    >> cd <base-path>/frontend
    >> ng serve


The frontend is located at http://localhost:4200/ and the API is exposed at http://localhost:8080/

To check if sequence is correct POST to http://localhost:8080/verifiedADN/add with Body:

{
    "id": ["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}

returns <Boolean>.

To list checked sequences go to http://localhost:8080/verifiedADN/all

returns List<verifiedADN>.

To get statistics http://localhost:8080/verifiedADN/statistics

{
    "count_incorrect_dna": "0",
    "count_correct_dna": "13",
    "ratio": "1.0"
} 


Compile with

    >> mvn clean compile assembly:single
    >> ng build frontend