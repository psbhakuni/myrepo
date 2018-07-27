## Flight Listing Demo
This project lists set of flights between origin and destination locations provided by user. 
The result will be sorted by 
- **Price** first in ascending order (lowest price first) and by 
- **Departure Time** next (earlier flight first).

This application does not have any backend database. Instead it takes the input from three properties files  
- [src\main\resources\1.txt](https://github.com/psbhakuni/myrepo/blob/master/Flights/src/main/resources/1.txt)
- [src\main\resources\2.txt](https://github.com/psbhakuni/myrepo/blob/master/Flights/src/main/resources/2.txt)
- [src\main\resources\3.txt](https://github.com/psbhakuni/myrepo/blob/master/Flights/src/main/resources/3.txt)

## How to use?
- Open this project in Eclipse/STS.
- Go to :

> [src/main/java/com/flightnetworks/Application.java](https://github.com/psbhakuni/myrepo/blob/master/Flights/src/main/java/com/flightnetworks/Application.java)

- Right click on the file and click "Run As" -> "Java Application". This will start the application.
- After this the app can be tested using a client application or directly using browser. I have created a sample client application to test this app which is also included at:

> [src/main/java/com/flightnetworks/client/FlightNetworkClient.java](https://github.com/psbhakuni/myrepo/blob/master/Flights/src/main/java/com/flightnetworks/client/FlightNetworkClient.java)  


## How to test using browser
- Starting the application using Application.java
- Enter the below URL in broweser

> http://localhost:8080/searchFlights/{origin}/{destination}

 Arguments:
 
	1. origin : Origin airport code(mandatory)
	2. destination : Destination airport code(mandatory)
	
## Output 
When run using the browser it shows the output in JSON format and when ran using the demo client it gives a more formatted output:

**On browser** 


For example if we hit below URL on browser

[http://localhost:8080/searchFlights/YYZ/YYC](http://localhost:8080/searchFlights/YYZ/YYC). This will give below output

`[{"origin":"YYZ","destination":"YYC","departureTime":"6/15/2014 6:45:00","destinationTime":"6/15/2014 8:54:00","price":"578.00 "},{"origin":"YYZ","destination":"YYC","departureTime":"6-26-2014 12:00:00","destinationTime":"6-26-2014 14:09:00","price":"630.00 "}]`


**Using FlightNetworkClient**


If ran using FlightNetworkClient output will be:

`YYZ --> YYC (6/15/2014 6:45:00) --> 6/15/2014 8:54:00) - $578.00`

`YYZ --> YYC (6-26-2014 12:00:00) --> 6-26-2014 14:09:00) - $630.00`



## Tech/framework used
- Spring Boot, REST, Java

## Tests
This also includes 5 tests to verify the correct functioning of FlightService which is the workhorse behind the controller.


*Author : Prakash Bhakuni*