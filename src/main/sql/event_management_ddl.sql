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

CREATE TABLE user (
  id INT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(64),
  firstName VARCHAR(32),
  lastName VARCHAR(32)
);

CREATE TABLE invitation (
  id INT PRIMARY KEY AUTO_INCREMENT,
  eventId INT,
  userId INT,
  accepted BOOLEAN,
  FOREIGN KEY (eventId)
    REFERENCES event(id)
    ON DELETE CASCADE,
  FOREIGN KEY (userId)
    REFERENCES user(id)
    ON DELETE CASCADE
);