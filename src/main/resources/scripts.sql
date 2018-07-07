
create table friend (
	id integer NOT NULL AUTO_INCREMENT,
	name varchar(100) NOT NULL,
	age integer,
	primary key(id)
);

create table address(
	id integer not null auto_increment, 
	location varchar(20), 
	street_name varchar(100) not null, 
	city varchar(50) not null, 
	friend_id integer, 
	primary key(id),
    foreign key (friend_id) references friend(id)
);
