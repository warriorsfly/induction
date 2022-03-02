CREATE TABLE IF NOT EXISTS users(
    id serial primary key,
    type varchar(64),
    name varchar(64),
    body text,
    private_key text,
    public_key text NOT NULL,
    message_to varchar(64),
    receiver varchar(64),
    created_time timestamp
);

CREATE TABLE IF NOT EXISTS rooms(
    id serial primary key,
    name varchar(64),
    description varchar(255),
    memo text,
    creator varchar(64),
    created_time timestamp
);

CREATE TABLE IF NOT EXISTS room_users(
    id serial primary key,
    room_id bigint,
    user_id bigint,
    join_time timestamp
);

CREATE TABLE IF NOT EXISTS messages(
    id serial primary key,
    sender varchar(64),
    body text,
    message_to varchar(64),
    receiver text,
    created_time timestamp
);