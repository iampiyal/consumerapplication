Spring Boot Application to calculate the shortest distance between a given source city and and a number of destination cities 
===============================
Template for a SpringBoot application using REST Web services.

###1. Technologies used
* Maven 3.0
* Spring Boot v1.2.0.RELEASE
* 

### 2. Local file
* It  needs two local file as the source of input 
* The first file consists of a list of source and destination cities
* The second file consists of a list of cities along with their latitudes and longitudes

### 3 Application url
* This is a Full Stack Microservice that calculates the shortest distance between a series of destination cities starting from a *source city
*The application once up, can  be accessed by http://localhost:8090/cities/{chosencity} 
* {chosencity} is a source city. Since currently we are using the local settings (no embedded database like HSQL or Apache Derby )
* {chosencity} can be madrid or rome (and few others) etc. madrid has more data than others 
* This does not require any explicit Servlet container because the same will be provided by Spring Boot
* The port number mentioned above has to be replaced by port number used in the system in which it is run by changing *application.properties file
* Local file paths have been hardcoded in the application which has been used as the source. 
* The user of this application has to replace the path accordingly
* ShortestPathCalculatorController is the main controller class.
* PLEASE NOTE that the latitudes and longitudes used in the source file are NOT actual and used as samples only. 
* Actually, we need to replace them with  actual values and the Algorithm used here (Nearest Neighbour Travelling Salesman Problem ) 
* will yield the EXACT shortest route. 


