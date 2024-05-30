-- drop table users;
create table users(
    id serial primary key
    , login_id varchar(255) not null unique
    , password varchar(255) not null
    , name varchar(255) not null
    , role int not null
    , created_at timestamp
    , updated_at timestamp
);

-- drop table categories;
create table categories(
    id serial primary key
    , name varchar(255) not null
    , created_at timestamp
    , updated_at timestamp
);

-- drop table products;
create table products(
    id serial primary key
    , product_id varchar(20) unique not null
    , category_id int not null
    , name varchar(255) not null
    , price int
    , image_path varchar(255)
    , description varchar(2000)
    , created_at timestamp
    , updated_at timestamp
);