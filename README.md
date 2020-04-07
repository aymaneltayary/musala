# musala

Below are some notes:

DMS is MYSQl . src\main\resources\musala.sql should be executed to create the scheme and the required DB

Swagger is implemented in this project so the API documentation can be found as below (if run localy)

http://localhost:8080//v2/api-docs 
http://localhost:8080/swagger-ui.html
Also , the file api-docs.json could help

Junit test is implementd . src\test\resources\test-data.sql file is used to populate the DB with some data before executing the test

Mavne is our build tool

Below are the supported API operation with URL assuming that the deployment is done locly:

Get http://localhost:8080/nt/gateways return all gateways

Get http://localhost:8080/gateways/pages/{pageNo} return all gateways using paging

Get http://localhost:8080/nt/gateways/{sn3} retrun specific gateway using sn where sn is the serial number

Post http://localhost:8080/gateways Store gateway with its devices

Delete http://localhost:8080/nt/devices/{uid} Delete device using uid

Post http://localhost:8080/nt/gateways/{sn}/device Add device to gateway using gateway serial number
