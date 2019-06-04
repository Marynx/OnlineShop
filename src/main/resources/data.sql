# DROP TABLE IF EXISTS order_item;
# DROP TABLE IF EXISTS `order`;
# DROP TABLE IF EXISTS user_role;
# DROP TABLE IF EXISTS vote;
# DROP TABLE IF EXISTS `user`;
# DROP TABLE IF EXISTS adress;
# DROP TABLE IF EXISTS item;
# DROP TABLE IF EXISTS product;
# DROP TABLE IF EXISTS category;
# DROP TABLE IF EXISTS image;
# DROP TABLE IF EXISTS producer;
# DROP TABLE IF EXISTS role;
# DROP TABLE IF EXISTS image_item;
#
# create table adress(
#                        id_adress int auto_increment,
#                        country varchar(40) not null,
#                        city varchar(40)not null,
#                        street varchar(40) not null,
#                        postal_code char(6) not null,
#                        primary key (id_adress)
# );
#
# create table category(
#                          id_category int auto_increment,
#                          type varchar(40) not null,
#                          description varchar(400) not null,
#                          primary key (id_category)
# );
#
# create table producer(
#                          id_producer int auto_increment,
#                          name varchar(200) not null,
#                          rating decimal not null,
#                          product_count int not null,
#                          primary key (id_producer)
# );
#
# create table image(
#                       id_image int auto_increment,
#                       path varchar(400) not null,
#                       primary key (id_image)
# );
#
# create table product(
#                         id_product int auto_increment,
#                         name varchar(100) not null,
#                         description varchar(400) not null,
#                         quantity int not null,
#                         category_id int,
#                         producer_id int,
#                         primary key(id_product),
#                         foreign key(category_id) references category(id_category),
#                         foreign key(producer_id) references producer(id_producer)
# );
#
# create table item(
#                      id_item int auto_increment,
#                      price decimal not null,
#                      rating decimal not null,
#                      version varchar(200) not null,
#                      description varchar(400) not null,
#                      quantity int not null,
#                      product_id int,
#                      primary key(id_item),
#                      foreign key (product_id) references product(id_product)
# );
#
# create table image_item(
#                            image_id int,
#                            item_id int,
#                            primary key(image_id,item_id),
#                            foreign key(image_id) references image(id_image),
#                            foreign key(item_id) references item(id_item)
# );
#
# create table `user`(
#                        id_user int auto_increment,
#                        login varchar(40) not null,
#                        password binary(100) not null,
#                        first_name varchar(45) not null,
#                        lastname varchar(45) not null,
#                        email varchar(45) not null,
#                        adress_id int,
#                        primary key(id_user),
#                        foreign key(adress_id) references adress(id_adress)
# );
#
# create table `order`(
#                         id_order int auto_increment,
#                         price decimal not null,
#                         order_date date not null,
#                         order_status varchar(100) not null,
#                         user_id int,
#                         primary key (id_order),
#                         foreign key (user_id) references `user`(id_user)
# );
#
# create table order_item(
#                            id_order_item int auto_increment,
#                            item_quantity int not null,
#                            id_order int,
#                            id_item int,
#                            primary key(id_order_item),
#                            foreign key (id_order) references `order`(id_order) on delete cascade,
#                            foreign key (id_item) references item(id_item) on delete cascade
# );
#
#
# create table role(
#                      id_role int auto_increment,
#                      role varchar(60) not null,
#                      description varchar(400) not null,
#                      primary key(id_role)
# );
#
#
#
# create table user_role(
#                           user_id int not null,
#                           role_id int not null,
#                           primary key(user_id,role_id),
#                           foreign key(user_id) references `user`(id_user),
#                           foreign key(role_id) references role(id_role)
# );
#
# create table vote(
#                      id_vote int auto_increment,
#                      score int not null,
#                      comment varchar(400) not null,
#                      `date` date not null,
#                      item_id int,
#                      user_id int,
#                      primary key(id_vote),
#                      foreign key(item_id) references item(id_item),
#                      foreign key(user_id) references `user`(id_user)
# );
#

insert into role(role, description) VALUES ("ROLE_USER", "default role for user"),("ROLE_ADMIN","default role for admin"),("ROLE_WORKER","default role for worker");
insert into adress(city,country,postal_code,street) values("cos","sss",122345,"sadasd");
insert into adress(city,country,postal_code,street) values("Adminowo","Adminland",123434,"Adminowa 12");
insert into user(email,first_name,lastname,login,password,adress_id) values ("ads@as.pl","Sdasd","Sdas","a","{bcrypt}$2a$10$YmOEfShQ49ODm4eCfVmmKeJ2CtIapMeN08ZiC/k80MddgMx5aHu3W",1);
insert into user(email,first_name,lastname,login,password,adress_id) values ("admin@admin.pl","Admin","Adminski","admin","{bcrypt}$2a$10$YmOEfShQ49ODm4eCfVmmKeJ2CtIapMeN08ZiC/k80MddgMx5aHu3W",2);
insert into user(email,first_name,lastname,login,password,adress_id) values ("worker@worker.pl","Worker","Workowski","worker","{bcrypt}$2a$10$YmOEfShQ49ODm4eCfVmmKeJ2CtIapMeN08ZiC/k80MddgMx5aHu3W",1);
insert into user_role(user_id,role_id) values(1,1);
insert into user_role(user_id,role_id) values(2,2);
insert into user_role(user_id,role_id) values(3,3);
insert into category(type,description)values("Elektronika","Rzecz elektroniczna");
insert into category(type,description)values("Komputer","Rzecz komputerowa");
insert into category(type,description)values("AGD","Rzecz agdowa");
insert into category(type,description)values("RTV","Rzecz rtvowa");
insert into producer(name,rating,product_count)values("Samsung",0,0);
insert into producer(name,rating,product_count)values("Sony",0,0);
insert into producer(name,rating,product_count)values("Xiaomi",0,0);
insert into product(name,description,quantity,category_id,producer_id) values ("Samsung s8","Szybki telefon",4,1,1);
insert into product(name,description,quantity,category_id,producer_id) values ("Sony 4","Sredni sony",4,1,2);
insert into product(name,description,quantity,category_id,producer_id) values ("Xiaomi S3","Szybki telefon",4,1,3);
insert into item(price,rating,version,description,quantity,product_id) values(12.20,0,"6GB Ram","Szybki telefon z wiekszym Ram",4,1);
insert into item(price,rating,version,description,quantity,product_id) values(20,0,"4GB Ram","Telefon z Srednim Ram",8,2);
insert into item(price,rating,version,description,quantity,product_id) values(30,0,"2GB Ram","Po prostu xiaomi",5,3);
insert into `order`(user_id,price,order_date,order_status) values(1,20,"2000-12-12","W trakcie realizacji");
insert into `order`(user_id,price,order_date,order_status) values(1,20,"2034-12-12","W trakcie realizacji");
insert into order_item(item_quantity,id_order,id_item) values (2,2,1);
insert into order_item(item_quantity,id_order,id_item) values (2,1,1);
insert into order_item(item_quantity,id_order,id_item) values (1,1,2);
insert into order_item(item_quantity,id_order,id_item) values (3,1,3);
# insert into vote(score,comment,date,user_id,item_id) values (3,"Moze byc","2001-12-14",1,1);
# insert into vote(score,comment,date,user_id,item_id) values (2,"Moze dsdf byc","2001-11-14",1,1);
# insert into vote(score,comment,date,user_id,item_id) values (4,"Moze asd3eqws byc","2004-12-14",1,1);
# insert into vote(score,comment,date,user_id,item_id) values (4,"Moze asdsdsadsaddas byc","2001-12-14",1,1);
insert into image(path)values("/images/samsungS8.jpg"),("/images/samsungS81.jpg"),("/images/samsungS82.jpg"),("/images/samsungS83.jpg");
insert into image_item(image_id,item_id)values(1,1),(2,1),(3,1),(4,1);
