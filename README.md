#### _igor-taren-repo_

# HOTEL SERVICE

### TASK#18

### _Testing_

#### Description:

Task 18 (difficulty 10)

Add unit-tests to the application, at least for all methods of the services layer. 
Use Mock approach for testing (for example, for DAO dependencies in services). 
Use JUnit 5 and Mockito as test libraries.

#### Stack

- java.version 17
- apache-tomcat 10.1.15
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
- java-jwt 4.4.0
- spring-security-core 6.1.5
- spring-security-config 6.1.5
- spring-security-web 6.1.5

#### REST API

context path -> /hotel

| Menu Item                                                                                                            | ROLE                   | Endpoint                                                                                                                                 | Description                                                                         |
|----------------------------------------------------------------------------------------------------------------------|------------------------|------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------|
| 1=List of rooms sorted by price                                                                                      | ANY                    | `GET /hotel/api/any/rooms/sort?getOnlyAvailable={par1}&sortBy=PRICE&sortOrder={par2}`                                                    | {par1}: `true/false`<br/>{par2}: `ASC/DESC`                                         |
| 2=List of rooms sorted by capacity                                                                                   | ANY                    | `GET /hotel/api/any/rooms/sort?getOnlyAvailable={par1}&sortBy=CAPACITY&sortOrder={par2}`                                                 | {par1}: `true/false`<br/>{par2}: `ASC/DESC`                                         |
| 3=List of rooms sorted by number of <br/>stars                                                                       | ANY                    | `GET /hotel/api/any/rooms/sort?getOnlyAvailable={par1}&sortBy=RATING&sortOrder={par2}`                                                   | {par1}: `true/false`<br/>{par2}: `ASC/DESC`                                         |
| 4=List of guests and their rooms <br/>sorted alphabetically                                                          | ROLE_ADMIN             | `GET /hotel/api/admin/guests/rooms/alphabet`                                                                                             |                                                                                     |
| 5=List of guests and their rooms <br/>sorted by check-out date                                                       | ROLE_ADMIN             | `GET /hotel/api/admin/guests/rooms/checkout`                                                                                             |                                                                                     |
| 6=Total number of available rooms                                                                                    | ROLE_ADMIN             | `GET /hotel/api/admin/rooms/available`                                                                                                   |                                                                                     |
| 7=Total number of guests                                                                                             | ROLE_ADMIN             | `GET /hotel/api/admin/guests/total`                                                                                                      |                                                                                     |
| 8=List of rooms that will be <br/>available on a certain date in the future                                          | ANY                    | `GET /hotel/api/any/rooms/{par1}`                                                                                                        | {par1}: date format `dd-MM-yyy`                                                     |
| 9=The amount of payment for the room <br/>to be paid by the guest                                                    | ROLE_GUEST, ROLE_ADMIN | `GET /hotel/api/guest/room/payment/byGuestId/{par1}`                                                                                     | {par1}: number `long` format                                                        |
| 10=View the last 3 guests of the room <br/>and the dates of their stay                                               | ROLE_ADMIN             | `GET /hotel/api/admin/last/guestAmount/{par1}/room/{par2}`                                                                               | {par1}: number `long` format <br/>{par2}: number `long` format                      |
| 11=View the list of guest services <br/>and their price (sorted by PRICE,DATE) in ASC(DESC) manner                   | ROLE_GUEST, ROLE_ADMIN | `GET /hotel/api/guest/guests/services?guestId={par1}&sortBy={par2}&sortOrder={par3}`                                                     | {par1}: number `long` format<br/>{par1}: `PRICE/DATE`<br/>{par3}: `ASC/DESC`        |
| 12=Prices of services and rooms <br/>(sorted by CAPACITY,PRICE,AVAILABILITY,SERVICE,RATING) <br/>in ASC(DESC) manner | ANY                    | `GET /hotel/api/any/rooms/prices?sortBy={par1}&sortOrder={par2}`                                                                         | {par1}: `CAPACITY/PRICE/AVAILABILITY/SERVICE/RATING`<br/>{par2}: `ASC/DESC`         |
| 13=Room services (ordered by ROOM_SERVICES,PRICE) <br/>in ASC(DESC) manner                                           | ANY                    | `GET /hotel/api/any/rooms/services?sortBy={par1}&sortOrder={par2}`                                                                       | {par1}: `ROOM_SERVICES/PRICE`<br/>{par2}: `ASC/DESC`                                |
| 14=Show the details of a separate room                                                                               | ROLE_ADMIN             | `GET /hotel/rooms/{par1}`                                                                                                                | {par1}: number `long` format                                                        |
| 15=Import the certain entity from the CSV file                                                                       | ROLE_ADMIN             | `GET /hotel/api/admin/csv/importing/{par1}`                                                                                              | {par1}: `BOOKING/GUEST/GUESTSERVICE/ROOM/ROOMSERVICE`                               |
| 16=Export the certain entity                                                                                         | ROLE_ADMIN             | `GET /hotel/api/admin/csv/exporting/{par1}`                                                                                              | {par1}: `BOOKING/GUEST/GUESTSERVICE/ROOM/ROOMSERVICE`                               |
| 17=Do serialization of entity                                                                                        | ROLE_ADMIN             | `GET /hotel/api/admin/serialization/{par1}`                                                                                              | {par1}: `BOOKING/GUEST/GUESTSERVICE/ROOM/ROOMSERVICE`                               |
| 18=Do de-serialization of entity                                                                                     | ROLE_ADMIN             | `GET /hotel/api/admin/deserialization/{par1}`                                                                                            | {par1}: `BOOKING/GUEST/GUESTSERVICE/ROOM/ROOMSERVICE`                               |
| 19=Do check-in/check-out from the room                                                                               | ROLE_ADMIN             | `GET /hotel/api/admin/rooms/operation/{par1}/{par2}`                                                                                     | {par1}: `checkin/checkout`<br/>{par2}: number `long` format                         |
| User login                                                                                                           | ANY                    | `POST /hotel/api/any/login` Request body: `{"login":{par1},"password":{par2}}`                                                           | {par1}:email, {par2}:password                                                       |
| User registration                                                                                                    | ANY                    | `POST /hotel/api/any/registration` Request body: `{"firstName":{par1},"lastName":{par2},"email":{par3},"password":{par4},"role":{par5}}` | {par1}:"firstName",{par2}:"lastName",{par3}:"email",{par4}:"password",{par5}:"role" |

All the entities have the main CRUD endpoints

| Endpoint                    | ROLE        | Description                       |
|-----------------------------|-------------|-----------------------------------|
| `GET /hotel/entity`         | ROLE_ADMIN  | Retrieve a list of entities.      |
| `GET /hotel/entity/{id}`    | ROLE_ADMIN  | Retrieve a specific entity by ID. |
| `POST /hotel/entity`        | ROLE_ADMIN  | Create a new entity record.       |
| `PUT /hotel/entity/{id}`    | ROLE_ADMIN  | Update an entity record.          |
| `DELETE /hotel/entity/{id}` | ROLE_ADMIN  | Delete an entity record.          |

entity -> `booking/guest/guestservice/room/roomservice`

#### passwords

| User                                  | Password   |
|---------------------------------------|------------|
| `admin@mail.com`                      | `admin`    | 
| any other guest -> login: guest email | `guest`    | 

#### DB schema

![](HOTEL_DB.png)

#### attachments

export of requests from Postman application -> [POSTMAN export](Hotel Service project.postman_collection.json)

postman screenshot
![](postman.png)

#### application start

- start tomcat(10.1.15) -> catalina.bat start
- _mvn clean package_
- _mvn -pl hotel-web tomcat7:deploy_
- use API above 