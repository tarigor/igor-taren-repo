# igor-taren-repo

Igor Taren's repository

## Task #7

### _Annotations_

#### Description:

### Task 7.1 (difficulty 5)

Change the program configuration mechanism to configuration using annotations:

It is necessary to allocate a separate module that will perform automatic configuration of the program. An object
containing annotated configuration parameters must be submitted to the input. The module must fill in these parameters
with values from the configuration file in accordance with the annotations. The annotation that needs to be implemented
for this should have the following form:

@ConfigProperty(configFileName, propertyName, type) – it can be placed over any field (elementary value, reference,
array or collection) and must ensure the correct configuration of the corresponding element.

All annotation attributes are optional (when using an annotation, you can either specify them or not). By default
сonfigFileName attribute should be the name of the default configuration file,
propertyName - have the form CLASS_NAME.FIELD_NAME, type - convert the value of the configurable parameter to
the current field type, or to String if the type is common;

### Task 6.2 (difficulty 10)

Develop a set of annotations and a separate module for their processing:

The module should allow dependency injections (DI) in the program modules. An object containing annotated fields
should be submitted to the input, and the module should implement dependencies in them.

To organize low coupling between modules of the application developed in previous tasks. For implementation,
it is necessary to use the Dependency injection pattern and the Reflection mechanism. All application modules
must be changed to this solution.

#### Stack

- Java 11;
- Lombok 1.18.26;
- gson 2.10.1

#### UML
