insert into role(role, description) VALUES ("ROLE_USER", "default role for user"),("ROLE_ADMIN","default role for admin"),("ROLE_WORKER","default role for worker");
insert into adress(city,country,postal_code,street) values("cos","sss",1223,"sadasd");
insert into adress(city,country,postal_code,street) values("Adminowo","Adminland",1234,"Adminowa 12");
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
insert into `order`(user_id,price,order_date,order_status) values(1,20,"2000-12-12","Nie potwierdzono");
insert into order_item(item_quantity,id_order,id_item) values (2,1,1);
insert into order_item(item_quantity,id_order,id_item) values (1,1,2);
insert into order_item(item_quantity,id_order,id_item) values (3,1,3);
# insert into vote(score,comment,date,user_id,item_id) values (3,"Moze byc","2001-12-14",1,1);
# insert into vote(score,comment,date,user_id,item_id) values (2,"Moze dsdf byc","2001-11-14",1,1);
# insert into vote(score,comment,date,user_id,item_id) values (4,"Moze asd3eqws byc","2004-12-14",1,1);
# insert into vote(score,comment,date,user_id,item_id) values (4,"Moze asdsdsadsaddas byc","2001-12-14",1,1);
insert into image(path)values("/images/samsungS8.jpg"),("/images/samsungS81.jpg"),("/images/samsungS82.jpg"),("/images/samsungS83.jpg");
insert into image_item(image_id,item_id)values(1,1),(2,1),(3,1),(4,1);
