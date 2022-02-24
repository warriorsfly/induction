CREATE TABLE IF NOT EXISTS messages(
    id serial primary key,
    sender text,
    body text,
    message_to int,
    receiver text,
    created_time timestamp
)