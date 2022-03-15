### RSA Generator

#### Pre-requisite
1. Install jdk 11
2. Install maven (atleast version 3.8.3)

#### Usage Steps
1. clone the project
2. execute maven command
###### command
```java
mvn clean package
```

3. run the application
###### command
```java
java -jar target/RSAGenerator-1.0.jar [keySize]
```

###### e.g.
```java
java -jar target/RSAGenerator-1.0.jar 2048
```
