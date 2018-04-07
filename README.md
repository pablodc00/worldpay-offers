# worldpay-offers


## To run project type:
mvn spring-boot:run
or open the project with any IDE and just Run As Java Appliction class OffersApplication.java

To run testcases:
mvn test



## Project specs
Java8
Spring boot
H2 Database embedded



## Notes
I scheduled a taks that run every day at 1:15 AM to check if it must expire any offer.
A improvement would be parameterize the frequency thought JMX 
or any attribute in memory or database that could be updated through a REST controller,
thus we don't need to restart the application in order to changes take effect.


## Examples endpoints

### 1) Get an offer by ID
GET
http://localhost:8080/api/v1/offers/10


### 1) Get offers by description
GET
http://localhost:8080/api/v1/offers/byDescription?description='an offer'


### 3) Get all offers
GET
http://localhost:8080/api/v1/offers


### 4) Create an offer
POST
http://localhost:8080/api/v1/offers
Payload:
{
	"description": "an offer",
	"price": "120.15",
	"status": "ACTIVE",
	"currency": "USD",
	"issued": "2018-04-20",
	"expiration": "2018-04-30"
}



## API documetation (with Swagger)
You can check API Documentation at this URL:
http://localhost:8080/swagger-ui.html

## Database console
I'm using H2 Database embedded
You can explore and perform queries at this URL:
http://localhost:8080/console