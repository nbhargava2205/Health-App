# Health-App

This is a simple spring-boot application that uses an in-memory database (the data is cleared when the server stops/restarts).

Prerequisites: Java 1.8, maven 3.3.9 or higher
How to run:
  1. Clone into your local machine or download as a zip
  2. Go to the directory containing pom.xml
  3. Run command mvn clean install 
  4. Once complete, run comman mvn spring-boot:run
  
  The app exposes five Rest endpoints.
  
  Request sample:
  {
    "name":"foo foo",
    "activationStatus":"true",
    "birthDate":"08-08-2020",
    "phoneNumber":"8328765677",
    "dependents": [{
        "name": "foo d",
        "birthDate": "05-05-1990"
    },
      {
        "name": "foo dd",
        "birthDate": "04-05-1990"
      }]
  }
  
    1. localhost:8081/enrollee/add   POST  -> Adds a new enrolee and dependents if any
    2, localhost:8081/enrollee/1/retreive GET -> Retreives a enrolee with the id 
    3. localhost:8081/enrollee/update  PUT -> Updates an enrolee. The best way to do this is first fetch the enrolee details, copy the response and modify whatever field you want to update including the dependents. You can modify and add dependents along with enrollee. Deleling dependents is not supported on this method.
    4. localhost:8081/dependent/1/remove  DELETE -> Deletes dependent 
    5. localhost:8081/dependent/1/remove DELETE -> Deletes an enrolee and all its dependents (if any)
  
