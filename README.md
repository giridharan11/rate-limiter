# Rate Limiter

A Spring Boot project implementing a rate-limiting mechanism to control the number of requests a client can make to your API.

## Features

- Configurable request limits per client
- In-memory or persistent storage options
- Easy integration with existing Spring Boot applications

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- (Optional) Docker

## Getting Started

1. Clone the repository

```sh
git clone https://github.com/your-username/rate-limiter.git
cd rate-limiter

2. Configure the application
Edit src/main/resources/application.properties to set your desired server port and rate-limiting properties.
server.port=8080
rate.limiter.requests=100
rate.limiter.timeWindow=60	

3. Build the project
mvn clean install

4. Run the application
mvn spring-boot:run

5. Test the API
Use tools like curl or Postman to send requests and observe rate-limiting behavior.
curl http://localhost:8080/api/default

curl http://localhost:8080/api/custom
