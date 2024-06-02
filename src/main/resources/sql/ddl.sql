-- docker run --name product-management-postgres -e POSTGRES_PASSWORD=test -p 5432:5432 -d postgres

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
    , product_code varchar(20) unique not null
    , category_id int not null
    , name varchar(255) not null
    , price int
    , image_path varchar(255)
    , description varchar(2000)
    , created_at timestamp
    , updated_at timestamp
);

-- session関連
CREATE TABLE SPRING_SESSION (
    PRIMARY_ID CHAR(36) NOT NULL,
    SESSION_ID CHAR(36) NOT NULL,
    CREATION_TIME BIGINT NOT NULL,
    LAST_ACCESS_TIME BIGINT NOT NULL,
    MAX_INACTIVE_INTERVAL INT NOT NULL,
    EXPIRY_TIME BIGINT NOT NULL,
    PRINCIPAL_NAME VARCHAR(100),
    CONSTRAINT SPRING_SESSION_PK PRIMARY KEY (PRIMARY_ID)
);

CREATE UNIQUE INDEX SPRING_SESSION_IX1 ON SPRING_SESSION (SESSION_ID);
CREATE INDEX SPRING_SESSION_IX2 ON SPRING_SESSION (EXPIRY_TIME);
CREATE INDEX SPRING_SESSION_IX3 ON SPRING_SESSION (PRINCIPAL_NAME);

CREATE TABLE SPRING_SESSION_ATTRIBUTES (
    SESSION_PRIMARY_ID CHAR(36) NOT NULL,
    ATTRIBUTE_NAME VARCHAR(200) NOT NULL,
    ATTRIBUTE_BYTES BYTEA NOT NULL,
    CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
    CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES SPRING_SESSION(PRIMARY_ID) ON DELETE CASCADE
);