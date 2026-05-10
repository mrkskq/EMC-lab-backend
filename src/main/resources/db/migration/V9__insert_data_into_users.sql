insert into users (created_at, updated_at, name, surname, email, username, password, role)
values
    (now(), now(), 'admin', 'admin', 'admin@mail.com', 'admin', '$2a$10$149UXuv.bPCY7rwM0P49R.OCcUZoPd0JtPb7fmSS4a2B6qsw4FclK', 'ROLE_ADMINISTRATOR'),
    (now(), now(), 'user', 'user', 'user@mail.com', 'user', '$2a$10$uTu5R8DBugz68Tq2YQ9L5uEhzelfs7ejNY5UHVpOXX5.n4J7XLJQS', 'ROLE_USER');