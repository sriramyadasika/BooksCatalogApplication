# BooksCatalogApplication

This application "BooksCatalogApplication" has below three microservices :
1. books-info-service
This service provide the book details response with bookId and name and has two rest end points exposed :
-- /books  which provides the list of all the books information present in the database.
-- /books/{bookId}  which provides book details for a given bookId

2. books-rating-service
This service provide the list of books user has rated and has one rest end points exposed :
-- /rating/{userId} which provides response as list of rating Object withbookId and rating parameters 

3. book-catalog-service
This service provide the list of book catalog response for a given user and has one rest end points exposed :
-- /catalog/{userId} which provides response as list of BookCatalog Object with bookId, name and rating - combining the response from above two services

-- Inorder to have the communication between these services, have implemented spring cloud netfix eureka server for service discovery.
   The service-discovery service acts as a discovery server so that the other services can register and to it and communicate via the eureka server.
   This has been enbled on default port 8761
   http://localhost:8761/
   
   
  Solution implementation
  
  All the three services are implemented using Java8, Springboot 2.2 version and maven as build tool and integrated with swagger2 to provide the API contarct details
  http://localhost:port/swagger-ui.html
  Junit test cases have been implemented for each of the rest API.
  Please refer readme of respective project for implementation details of each microservice . 
  
  Commands:
  Build : mvn clean install
  Start the application : From terminal, navigate to target folder of the project and exceute below command
                          java -jar ..service.jar
                          
                          
  
