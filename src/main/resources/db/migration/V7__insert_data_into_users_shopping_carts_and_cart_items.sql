insert into users (created_at, updated_at, name, surname, email)
values (now(), now(), 'John', 'Doe', 'john.doe@example.com'),
       (now(), now(), 'Jane', 'Smith', 'jane.smith@example.com'),
       (now(), now(), 'Alice', 'Johnson', 'alice.johnson@example.com'),
       (now(), now(), 'Bob', 'Brown', 'bob.brown@example.com');

insert into shopping_carts (created_at, updated_at, user_id)
values (now(), now(), (select id from users where email = 'john.doe@example.com')),
       (now(), now(), (select id from users where email = 'jane.smith@example.com')),
       (now(), now(), (select id from users where email = 'alice.johnson@example.com')),
       (now(), now(), (select id from users where email = 'bob.brown@example.com'));

insert into cart_items (shopping_cart_id, book_id, quantity)
values ((select sc.id
         from shopping_carts sc
                  join users u on sc.user_id = u.id
         where u.email = 'john.doe@example.com'),
        (select id from books where name = 'The Shining'),
        1),
       ((select sc.id
         from shopping_carts sc
                  join users u on sc.user_id = u.id
         where u.email = 'john.doe@example.com'),
        (select id from books where name = '1984'),
        2);

insert into cart_items (shopping_cart_id, book_id, quantity)
values ((select sc.id
         from shopping_carts sc
                  join users u on sc.user_id = u.id
         where u.email = 'jane.smith@example.com'),
        (select id from books where name = 'Animal Farm'),
        1),
       ((select sc.id
         from shopping_carts sc
                  join users u on sc.user_id = u.id
         where u.email = 'jane.smith@example.com'),
        (select id from books where name = 'Norwegian Wood'),
        1);

insert into cart_items (shopping_cart_id, book_id, quantity)
values ((select sc.id
         from shopping_carts sc
                  join users u on sc.user_id = u.id
         where u.email = 'alice.johnson@example.com'),
        (select id from books where name = 'Kafka on the Shore'),
        1);