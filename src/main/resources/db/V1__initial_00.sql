create table if not exists ${flyway:defaultSchema}.erental_users
(
    id uuid primary key default gen_random_uuid(),
    username varchar(128) not null,
    email varchar(256) not null,
    password varchar(256) not null,
    unique(username),unique(email)
)