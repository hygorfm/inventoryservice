
Ticketing Microservices Project from LeetJourney

# TechStack Used

Java
Spring (Boot, Web, Data JPA, Actuator, Cloud Gateway)
MySQL DB
Flyway
Zookeeper
Kafka
Keycloak Auth
Swagger
Lombok
Slf4J
Docker / Docker Compose


# Improvement Ideas:
- Third party API call to a payment service (before placing an order)
- Unit Tests and Integration Tests
- Observability
- Metrics
- Logs


# ticketing_db Script

select * from tb_venue;

select * from tb_event;

select * from tb_customer;

select * from tb_order;


INSERT INTO tb_venue(name, address, total_capacity)
VALUES("Old Trafford", "Manchestering,UK", 80000),
	  ("Etihad Stadium", "Manchestering,UK", 70000);


INSERT INTO tb_event(name, total_capacity, left_capacity, venue_id)
VALUES("Coldplay Concert", 40000, 40000, 1),
	  ("Bruno Mars Concert", 30000, 30000, 2);


INSERT INTO tb_customer(name, email, address)
VALUES("Leet Journey", "info@video.com", "Austin, TX, USA");


