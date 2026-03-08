insert into countries (name, continent)
values ('United States', 'North America'),
       ('United Kingdom', 'Europe'),
       ('Russia', 'Europe'),
       ('Colombia', 'South America'),
       ('Japan', 'Asia');

insert into authors (created_at, updated_at, name, surname, country_id)
values (now(), now(), 'Stephen', 'King',
        (select id from countries where name = 'United States')),
       (now(), now(), 'George', 'Orwell',
        (select id from countries where name = 'United Kingdom')),
       (now(), now(), 'Leo', 'Tolstoy',
        (select id from countries where name = 'Russia')),
       (now(), now(), 'Gabriel', 'Garcia Marquez',
        (select id from countries where name = 'Colombia')),
       (now(), now(), 'Haruki', 'Murakami',
        (select id from countries where name = 'Japan'));

insert into books (created_at, updated_at, name, category, state, available_copies, author_id)
values (now(), now(), 'The Shining', 'THRILLER', 'GOOD', 5,
        (select id from authors where surname = 'King')),
       (now(), now(), 'It', 'THRILLER', 'GOOD', 3,
        (select id from authors where surname = 'King')),
       (now(), now(), '1984', 'NOVEL', 'GOOD', 7,
        (select id from authors where surname = 'Orwell')),
       (now(), now(), 'Animal Farm', 'CLASSICS', 'GOOD', 4,
        (select id from authors where surname = 'Orwell')),
       (now(), now(), 'War and Peace', 'HISTORY', 'BAD', 2,
        (select id from authors where surname = 'Tolstoy')),
       (now(), now(), 'Anna Karenina', 'DRAMA', 'GOOD', 6,
        (select id from authors where surname = 'Tolstoy')),
       (now(), now(), 'One Hundred Years of Solitude', 'FANTASY', 'GOOD', 4,
        (select id from authors where surname = 'Garcia Marquez')),
       (now(), now(), 'Norwegian Wood', 'NOVEL', 'GOOD', 5,
        (select id from authors where surname = 'Murakami')),
       (now(), now(), 'Kafka on the Shore', 'FANTASY', 'BAD', 1,
        (select id from authors where surname = 'Murakami'));