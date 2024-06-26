CREATE TABLE IF NOT EXISTS TEST_USER
(
    ID INTEGER NOT NULL AUTO_INCREMENT,
    NICK_NAME VARCHAR(255) NOT NULL,
    PASSWORD VARCHAR(255) NOT NULL,
    STATUS VARCHAR(50) NOT NULL,
    CREATED_DATETIME DATETIME,
    UPDATED_DATETIME DATETIME,
    PRIMARY KEY (ID)
);

CREATE TABLE IF NOT EXISTS TEST_TASK
(
    ID INTEGER NOT NULL AUTO_INCREMENT,
    USER_ID INTEGER NOT NULL,
    CONTENTS VARCHAR(500) NOT NULL,
    STATUS VARCHAR(50) NOT NULL,
    CREATED_DATETIME DATETIME,
    UPDATED_DATETIME DATETIME,
    FOREIGN KEY (USER_ID) REFERENCES TEST_USER(ID),
    PRIMARY KEY (ID)
);

CREATE TABLE IF NOT EXISTS TEST_ROLE
(
    ID INTEGER NOT NULL AUTO_INCREMENT,
    TYPE VARCHAR(50) NOT NULL,
    PRIMARY KEY (ID)
);


CREATE TABLE IF NOT EXISTS TEST_USER_ROLE
(
    USER_ID INTEGER NOT NULL,
    ROLE_ID INTEGER NOT NULL,
    PRIMARY KEY (USER_ID, ROLE_ID),
    FOREIGN KEY (USER_ID) REFERENCES TEST_USER(ID),
    FOREIGN KEY (ROLE_ID) REFERENCES TEST_ROLE(ID)
);
