create table categories
(
    id    TINYINT auto_increment primary key,
    name  varchar(255) not null
);

create table products
(
    id      bigint auto_increment primary key,
    name    varchar(255) not null,
    price   decimal(10, 2) not null,
    category_id TINYINT,
    CONSTRAINT fk_category
        foreign key (category_id) references categories (id)
        ON DELETE RESTRICT
);