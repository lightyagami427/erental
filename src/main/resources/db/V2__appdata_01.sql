create table if not exists ${flyway:defaultSchema}.rental_properties
(
    id uuid primary key default gen_random_uuid(),
    rentaltitle varchar(256) not null,
    description varchar(256) not null,
    monthlyprice int not null,
    hno varchar(32) not null,
    street varchar(128) not null,
    area varchar(128) not null,
    landmark varchar(256),
    city varchar(128) not null,
    state varchar(64) not null,
    pincode int not null,
    unique(hno,street,area,city,state,pincode)
);
create table if not exists ${flyway:defaultSchema}.properties_image
(
    id uuid primary key default gen_random_uuid(),
    propertyid uuid references ${flyway:defaultSchema}.rental_properties(id),
    imgname text not null, 
    img bytea not null
);


create table if not exists ${flyway:defaultSchema}.user_property
(
   id uuid primary key default gen_random_uuid(),
   propertyid uuid references ${flyway:defaultSchema}.rental_properties(id),
   userid uuid references ${flyway:defaultSchema}.erental_users(id),
   status varchar(128) not null
);
