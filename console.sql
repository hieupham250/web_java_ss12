create database ss12;

use ss12;

# drop database ss12;

create table Students
(
    id    int primary key auto_increment,
    name  varchar(255) not null,
    email varchar(255) not null unique,
    dob   date
);

create table Products
(
    id       int primary key auto_increment,
    name     varchar(255)   not null,
    price    decimal(10, 2) not null,
    quantity int            not null,
    image    text
);

create table bus
(
    id            int primary key auto_increment,
    license_plate varchar(20)                      not null,
    bus_type      enum ('NORMAL', 'VIP', 'LUXURY') not null,
    row_seat      int                              not null,
    col_seat      int                              not null,
    total_seat    int                              not null,
    image         text
);

create table seat
(
    id        int primary key auto_increment,
    name_seat varchar(10),
    price     decimal(10, 2),
    bus_id    int,
    status    enum ('Trống', 'Đã đặt') default 'Trống',
    foreign key (bus_id) references bus (id) on delete cascade
);

delimiter //
create procedure find_all_student()
begin
    select * from Students;
end;

create procedure find_student_by_id(
    id_in int
)
begin
    select * from Students where id = id_in;
end;

create procedure is_exist_email(
    email_in varchar(255)
)
begin
    select count(*) as is_exist from Students where email = email_in;
end;

create procedure create_student(
    name_in varchar(255),
    email_in varchar(255),
    dob_in date
)
begin
    insert into Students(name, email, dob)
    values (name_in, email_in, dob_in);
end;

create procedure update_student(
    id_in int,
    name_in varchar(255),
    email_in varchar(255),
    dob_in date
)
begin
    update Students
    set name  = name_in,
        email = email_in,
        dob   = dob_in
    where id = id_in;
end;

create procedure delete_student(
    id_in int
)
begin
    delete from Students where id = id_in;
end;

create procedure find_all_product()
begin
    select * from Products;
end;

create procedure find_product_by_id(
    id_in int
)
begin
    select * from Products where id = id_in;
end;

create procedure create_product(
    name_in varchar(255),
    price_in decimal(10, 2),
    quantity_in int,
    image_in text
)
begin
    insert into Products(name, price, quantity, image)
    values (name_in, price_in, quantity_in, image_in);
end;

create procedure update_product(
    id_in int,
    name_in varchar(255),
    price_in decimal(10, 2),
    quantity_in int,
    image_in text
)
begin
    update Products
    set name     = name_in,
        price    = price_in,
        quantity = quantity_in,
        image    = image_in
    where id = id_in;
end;

create procedure delete_product(
    id_in int
)
begin
    delete from Products where id = id_in;
end;

create procedure find_all_bus()
begin
    select * from bus;
end;

create procedure find_bus_by_id(
    id_in int
)
begin
    select * from bus where id = id_in;
end;

create procedure create_bus(
    licensePlate_in varchar(50),
    busType_in varchar(100),
    rowSeat_in int,
    colSeat_in int,
    totalSeat_in int,
    image_in text
)
begin
    insert into bus(license_plate, bus_type, row_seat, col_seat, total_seat, image)
    values (licensePlate_in, busType_in, rowSeat_in, colSeat_in, totalSeat_in, image_in);
end;

create procedure update_bus(
    id_in int,
    licensePlate_in varchar(50),
    busType_in varchar(100),
    rowSeat_in int,
    colSeat_in int,
    totalSeat_in int,
    image_in text
)
begin
    update bus
    set license_plate = licensePlate_in,
        bus_type      = busType_in,
        row_seat      = rowSeat_in,
        col_seat      = colSeat_in,
        total_seat    = totalSeat_in,
        image         = image_in
    WHERE id = id_in;
end;

create procedure delete_bus(
    id_in int
)
begin
    delete from bus where id = id_in;
end;

create procedure find_seat_by_bus_id(
    bus_id_in int
)
begin
    select * from seat where bus_id = bus_id_in;
end;

create procedure save_seat(
    name_in varchar(20),
    price_in decimal(10, 2),
    bus_id_in int,
    status_in varchar(20)
)
begin
    insert into seat(name_seat, price, bus_id, status)
        values (name_in, price_in, bus_id_in, status_in);
end;

create procedure update_seat(
    id_in int,
    name_in varchar(20),
    price_in decimal(10, 2),
    bus_id_in int,
    status_in varchar(20)
)
begin
    update seat
        set name_seat = name_in,
            price = price_in,
            bus_id = bus_id_in,
            status = status_in
    WHERE id = id_in;
end;

create procedure delete_seat_by_bus_id(
    id_in int
)
begin
    delete from seat where bus_id = id_in;
end;
delimiter //