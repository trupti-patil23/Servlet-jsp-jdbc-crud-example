use java_training;
create table users (
 id  int(3) NOT NULL AUTO_INCREMENT,
 name varchar(120) NOT NULL,
 email varchar(220) NOT NULL,
 country varchar(120),
 PRIMARY KEY (id)
);

create table login(
username varchar(10) NOT NULL,
password varchar(10) NOT NULL
);

INSERT INTO login (username, password) VALUES ('admin', 'admin');