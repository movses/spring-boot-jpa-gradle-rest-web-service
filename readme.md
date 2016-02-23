## Web Service Usage
GET: Get list of all companies:
```sh
$ curl ip:port/company
```

GET: Get details about a company
```sh
$  curl ip:port/company/{id}
```



POST: Create new company
```sh
$  curl -H "Accept: application/json" -H "Content-Type: application/json" -X POST -d @company.json ip:port/company
```
PUT: Update the company
```sh
$  curl -H "Accept: application/json" -H "Content-Type: application/json" -X PUT -d @company.json ip:port/company
```
PATCH: add new beneficial owner(s)
```sh
$  curl -H "Accept: application/json" -H "Content-Type: application/json" -X PATCH -d owners.json ip:port/company/{id}
``` 
or
```sh
$  curl -H "Accept: application/json" -H "Content-Type: application/json" -X PATCH -d "[\"firstOwnerName\", \"secondOwnerName\"]" ip:port/company/{id}
```

@compamy.json is a json template for company: you can use example.json, find more specific samples under src/main/resources/ folder or create one in your own.


## building from source code
#### Server Side Configuration
- install and start mariadb or mysql
- create database company_db 
- create user company_user, set password to 1234 and grant all privileges
- install tomcat

#### Start Web Service
-- build spring boot application
```sh
$ gradle clean build
```
--start web service
```sh
$ java -jar  build/libs/company-web-service-0.0.1.jar
```
