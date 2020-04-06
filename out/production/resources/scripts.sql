
create table friend (
	id bigint NOT NULL AUTO_INCREMENT,
	name varchar(100) NOT NULL,
	age integer,
	image blob,
	primary key(id)
);

create table address(
	id bigint not null auto_increment, 
	location varchar(20), 
	street_name varchar(100) not null, 
	city varchar(50) not null, 
	friend_id bigint, 
	primary key(id),
    foreign key (friend_id) references friend(id)
);
