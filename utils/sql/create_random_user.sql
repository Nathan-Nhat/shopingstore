set @username = concat("user", floor(RAND()*1000));
insert into `users`(
username,
password,
roles,
status
) value (
@username,
"123",
"CUSTOMER",
"ACTIVE"
)