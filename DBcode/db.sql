-- I have used MySQL database for this project
-- the code is as follows

create database collegeroommanagementdb; -- This query creates a new database "collegeroommanagementdb"

use collegeroommanagementdb; -- to come to collegeroommanagementdb to create our tabels

CREATE TABLE room (
    room_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    capacity INT NOT NULL,
    has_projector TINYINT(1) NOT NULL,
    PRIMARY KEY (room_id)
);


CREATE TABLE schedule (
    schedule_id INT NOT NULL AUTO_INCREMENT,
    room_id INT,
    semester VARCHAR(20) NOT NULL,
    class VARCHAR(50) NOT NULL,
    occupied TINYINT(1) NOT NULL,
    PRIMARY KEY (schedule_id),
    FOREIGN KEY (room_id) REFERENCES room(room_id)
);


