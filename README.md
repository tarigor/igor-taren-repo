#### _igor-taren-repo_

# HOTEL SERVICE

### TASK#16

### _Spring MVC_

#### Description:

Task 16 (difficulty 10)

Modify the application from previous tasks to a web application.

Task requirements:

- For implementation use Spring MVC;
- Add DTO (Data transfer objects) to the project to describe controller responses;
- Responses to requests must be in JSON and/or XML format;
- The application must have a REST architecture (use the Postman tool for verification);
- All the requirements for the functionality of the application must be available in the controllers;
- Processing and issuing exceptions to the user should be implemented using @ExceptionHandler and @Controlleradvice;
- Rewrite previously implemented work with transactions via Hibernate to use the @Transactional annotation in the
  service layer;

Build the application in WAR and deploy it on Tomcat or Jetty.

#### Stack

- java.version 17
- maven.compiler.plugin.version 3.11.0
- maven.surefire.plugin.version 3.1.2
- maven.checkstyle.plugin.version 3.3.0
- maven.site.plugin 3.12.0
- lombok.version 1.18.22
- gson.version 2.10.1
- jackson.annotations.version 2.15.2
- jackson.core.version 2.15.2
- jackson.databind.version 2.15.2
- mysql.connector.version 8.1.0
- maven.surefire-plugin.version 3.1.2
- maven.compiler-plugin.version 3.11.0
- logback.core.version 1.4.11
- slf4j.api.version 2.0.9
- logback.classic.version 1.4.11
- maven.checkstyle.plugin.version 3.3.0
- maven.site.plugin.version 3.12.0
- hibernate.core 6.3.1.Final
- spring-boot-starter 3.1.5
- spring-boot-starter-data-jpa 3.1.5
- spring-boot-maven-plugin 3.1.5
- spring-boot-starter-json 3.1.5
- spring-boot-starter-tomcat 3.1.5
- spring-boot-starter-test 3.1.5
- spring-boot-starter-data-rest 3.1.5
- spring-boot-starter-web 3.1.5
- spring-boot-starter-validation 3.1.5
- modelmapper 3.2.0

#### REST API

| Menu Item                                                                                                            | Endpoint                                                        | Description                                                                  |
|----------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------|------------------------------------------------------------------------------|
| 1=List of rooms sorted by price                                                                                      | `GET /rooms/sort?getOnlyAvailable={par1}&sortBy=PRICE&sortOrder={par2}`    | {par1}: `true/false`<br/>{par2}: `ASC/DESC`                                  |
| 2=List of rooms sorted by capacity                                                                                   | `GET /rooms/sort?getOnlyAvailable={par1}&sortBy=CAPACITY&sortOrder={par2}` | {par1}: `true/false`<br/>{par2}: `ASC/DESC`                                  |
| 3=List of rooms sorted by number of <br/>stars                                                                       | `GET /rooms/sort?getOnlyAvailable={par1}&sortBy=RATING&sortOrder={par2}`   | {par1}: `true/false`<br/>{par2}: `ASC/DESC`                                  |
| 4=List of guests and their rooms <br/>sorted alphabetically                                                          | `GET /bookings/guests/rooms/alphabet`                                      |                                                                              |
| 5=List of guests and their rooms <br/>sorted by check-out date                                                       | `GET /bookings/guests/rooms/checkout`                                      |                                                                              |
| 6=Total number of available rooms                                                                                    | `GET /rooms/available`                                                     |                                                                              |
| 7=Total number of guests                                                                                             | `GET /bookings/guests/total`                                               |                                                                              |
| 8=List of rooms that will be <br/>available on a certain date in the future                                          | `GET /bookings/rooms/{par1}`                                               | {par1}: date format `dd-MM-yyy`                                              |
| 9=The amount of payment for the room <br/>to be paid by the guest                                                    | `GET /bookings/room/payment/byGuestId/{par1}`                              | {par1}: number `long` format                                                 |
| 10=View the last 3 guests of the room <br/>and the dates of their stay                                               | `GET /bookings/last/guestAmount/{par1}/room/{par2}`                        | {par1}: number `long` format <br/>{par2}: number `long` format               |
| 11=View the list of guest services <br/>and their price (sorted by PRICE,DATE) in ASC(DESC) manner                   | `GET /guestServices?guestId={par1}&sortBy={par2}&sortOrder={par3}`         | {par1}: number `long` format<br/>{par1}: `PRICE/DATE`<br/>{par3}: `ASC/DESC` |
| 12=Prices of services and rooms <br/>(sorted by CAPACITY,PRICE,AVAILABILITY,SERVICE,RATING) <br/>in ASC(DESC) manner | `GET /rooms/prices?sortBy={par1}&sortOrder={par2}`                         | {par1}: `CAPACITY/PRICE/AVAILABILITY/SERVICE/RATING`<br/>{par2}: `ASC/DESC`  |
| 13=Room services (ordered by ROOM_SERVICES,PRICE) <br/>in ASC(DESC) manner                                           | `GET /roomServices?sortBy={par1}&sortOrder={par2}`                         | {par1}: `ROOM_SERVICES/PRICE`<br/>{par2}: `ASC/DESC`                         |
| 14=Show the details of a separate room                                                                               | `GET /rooms/{par1}`                                                        | {par1}: number `long` format                                                 |
| 15=Import the certain entity from the CSV file                                                                       | `GET /utility/csv/importing/{par1}`                                        | {par1}: `BOOKING/GUEST/GUESTSERVICE/ROOM/ROOMSERVICE`                        |
| 16=Export the certain entity                                                                                         | `GET /utility/csv/exporting/{par1}`                                        | {par1}: `BOOKING/GUEST/GUESTSERVICE/ROOM/ROOMSERVICE`                        |
| 17=Do serialization of entity                                                                                        | `GET /utility/serialization/{par1}`                                        | {par1}: `BOOKING/GUEST/GUESTSERVICE/ROOM/ROOMSERVICE`                        |
| 18=Do de-serialization of entity                                                                                     | `GET /utility/deserialization/{par1}`                                      | {par1}: `BOOKING/GUEST/GUESTSERVICE/ROOM/ROOMSERVICE`                        |
| 19=Do check-in/check-out from the room                                                                               | `GET /rooms/operation/{par1}/{par2}`                                       | {par1}: `checkin/checkout`<br/>{par2}: number `long` format                  |

All the entities have the main CRUD endpoints

| Endpoint               | Description                        |
|------------------------|------------------------------------|
| `GET /entity`          | Retrieve a list of entities.       |
| `GET /entity/{id}`     | Retrieve a specific entity by ID.  |
| `POST /entity`         | Create a new entity record.        |
| `PUT /entity/{id}`     | Update an entity record.           |
| `DELETE /entity/{id}`  | Delete an entity record.           |

#### attachments

export of requests from Postman application -> [POSTMAN export](Hotel Service project.postman_collection.json)

postman screenshot
![postman](postman.png)

#### cmd to application start

_mvn -pl hotel-ui spring-boot:run_