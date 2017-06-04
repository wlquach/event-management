/* DATABASE */
CREATE DATABASE event_management;

/* APP USER */
CREATE USER 'event_management_app'@'localhost' IDENTIFIED BY 'changeme!';
GRANT DELETE,INSERT,SELECT,UPDATE ON event_management.* TO 'event_management_app'@'localhost';

/* TABLES */
CREATE TABLE event (
  id INT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(64),
  location VARCHAR(128),
  startTime DATETIME,
  endTime DATETIME
);