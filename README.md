# igor-taren-repo

Igor Taren's repository

## Task #6

### _Exceptions and Java IO_

#### Description:

### Task 6.1 (difficulty 5)

Add to the program being developed the ability to import and export data of the selected entity (you will need to add an
ID for each entity). The import must support updating records with the same ID and adding new ones. In addition, the
program should automatically establish links between objects. The file format for import/export must be CSV.

The UI should be supplemented with new menu items that allow importing and exporting for each entity.

### Task 6.2 (difficulty 2)

Add high-quality exception handling for possible errors in the application. The program must adequately inform the user
about the problems that have arisen.

### Remarks to task

the IO operations created in separate module hotel-io

import and export folders location:

- import -> *hotel-io/csv/import*
- export -> *hotel-io/csv/export*

Runner method -> com.senla.menu.MenuMain.main

#### Stack

- Java 11;
- Lombok 1.18.26;
- gson 2.10.1

#### UML

[TASK_6_UML.puml](TASK_6_UML.puml)