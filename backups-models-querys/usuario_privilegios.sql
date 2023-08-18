create user dba@'localhost' identified by '****';
grant all privileges on *.* to dba@'localhost' with grant option;

create user bdd_gym_application@'localhost' identified by '****';
grant select, insert, update, delete on bdd_gym.* to bdd_gym_application@'localhost';

create user developer@'localhost' identified by '****';
grant create, alter, insert, update, delete, backup_admin, reload on *.*
 to developer@'localhost';

select * from mysql.user;

show grants for bdd_gym_application@'localhost';