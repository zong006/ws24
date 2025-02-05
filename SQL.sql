use ws24; -- change this name to the database name on deployment


drop table if exists order_details;
drop table if exists orders;

create table orders (
    order_id int not null auto_increment,
    order_date date,
    customer_name varchar(128) not null,
    ship_address varchar(128) not null,
    notes text,
    tax decimal(2,2),
    constraint pk_orders_id primary key (order_id)
);

create table order_details (
    id int not null auto_increment,
    order_id int not null,
    product varchar(64),
    unit_price decimal (3,2),
    discount decimal (2,2),
    quantity int,
    constraint pk_order_details primary key (id),
    constraint fk_orders_id foreign key (order_id) references orders(order_id) ON DELETE CASCADE
);