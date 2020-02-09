create table `shoppingstore`.`users`
(
	`id` bigint not null primary key,
    `username` varchar(45) unique not null,
    `password` varchar(100) not null, 
    `roles` varchar(30) not null
)
