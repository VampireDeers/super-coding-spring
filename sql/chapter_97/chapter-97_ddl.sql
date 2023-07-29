CREATE DATABASE chapter_97;
USE chapter_97;

CREATE TABLE users(
	user_id INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(20),
    like_travel_place VARCHAR(30),
    phone_num VARCHAR(30)
);

CREATE TABLE passenger(
	passenger_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    passport_num VARCHAR(50),
    FOREIGN KEY(user_id) REFERENCES users(user_id)
);

CREATE TABLE airline_ticket(
	ticket_id INT AUTO_INCREMENT PRIMARY KEY,
	ticket_type char(5) CHECK (ticket_type in ('편도', '왕복') ) ,
    departure_loc VARCHAR(20),
    arrival_loc VARCHAR(20),
    departure_at DATETIME,
    return_at DATETIME,
    tax DOUBLE, 
    total_price DOUBLE
);

CREATE TABLE reservation(
	reservation_id INT AUTO_INCREMENT PRIMARY KEY,
	passenger_id INT,
    airline_ticket_id INT,
    reservation_status VARCHAR(10),
    reserve_at DATETIME,
	FOREIGN KEY(passenger_id) REFERENCES passenger(passenger_id),
	FOREIGN KEY(airline_ticket_id) REFERENCES airline_ticket(ticket_id)
);

CREATE TABLE payment(
	payment_id INT AUTO_INCREMENT PRIMARY KEY,
    passenger_id INT,
    reservation_id INT,
    FOREIGN KEY(passenger_id) REFERENCES passenger(passenger_id),
    FOREIGN KEY(reservation_id) REFERENCES reservation(reservation_id)
);

CREATE TABLE flight(
	flight_id INT AUTO_INCREMENT PRIMARY KEY,
    ticket_id INT,
    departure_at DATETIME,
    arrival_at DATETIME,
    departure_loc VARCHAR(50),
    arrival_loc VARCHAR(50),
    flight_price DOUBLE,
    charge DOUBLE,
	FOREIGN KEY(ticket_id) REFERENCES airline_ticket(ticket_id)
);