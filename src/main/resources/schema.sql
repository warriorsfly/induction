CREATE TABLE IF NOT EXISTS messages(
    id serial primary key,
    sender text,
    body text,
    message_to text,
    receiver text,
    created_time timestamp
);