
CREATE TABLE friend (
	id INTEGER NOT NULL AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL,
	age INTEGER,
	PRIMARY KEY(id)
);

create table address(
	id integer not null auto_increment, 
	location varchar(20), 
	street_name varchar(100) not null, 
	city varchar(50) not null, 
	friend_id integer, primary key(id),
    FOREIGN KEY (friend_id) REFERENCES friend(id)
);
