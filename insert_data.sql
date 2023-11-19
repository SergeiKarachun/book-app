INSERT INTO users (id, username, email, password, role)
VALUES (1, 'admin', 'admin@gmail.com', '{noop}admin', 'ADMIN'),
       (2, 'client', 'client@gmail.com', '{noop}client', 'CLIENT');
SELECT SETVAL('users_id_seq', (SELECT MAX(id) FROM users));

INSERT INTO user_details (id, user_id, name, surname, address, phone)
VALUES (1, (SELECT u.id FROM users u WHERE email = 'admin@gmail.com'), 'Sergey', 'Ivanov', 'Minsk',
        '+37533 1234567'),
       (2, (SELECT u.id FROM users u WHERE email = 'client@gmail.com'), 'Petr', 'Petrov', 'Minsk',
        '+37533 2345678');
SELECT SETVAL('user_details_id_seq', (SELECT MAX(id) FROM user_details));



INSERT INTO genre (id, name)
VALUES (1, 'comedy'),
       (2, 'tragedy'),
       (3, 'drama'),
       (4, 'horror'),
       (5, 'novel');
SELECT SETVAL('genre_id_seq', (SELECT MAX(id) FROM genre));


INSERT INTO author (id, name, surname)
VALUES (1, 'William', 'Shakespeare'),
       (2, 'Mark', 'Twain'),
       (3, 'Leo', 'Tolstoy'),
       (4, 'Charles', 'Dickens'),
       (5, 'George', 'Eliot');
SELECT SETVAL('author_id_seq', (SELECT MAX(id) FROM author));



INSERT INTO book (id, isbn, name, genre_id, author_id, description)
VALUES (1, 873008518, 'Hamlet', 5, 1, 'The Tragedy of Hamlet, Prince of Denmark, or more simply Hamlet, is a tragedy by William Shakespeare, believed to have been written between 1599 and 1601. The play, set in Denmark, recounts how Prince Hamlet exacts revenge on his uncle Claudius, who has murdered Hamlet''s father, the King, and then taken the throne and married Gertrude, Hamlet''s mother. The play vividly charts the course of real and feigned madness—from overwhelming grief to seething rage—and explores themes of treachery, revenge, incest, and moral corruption.'),
(2, 823677683, 'King Lear', 5, 1, 'King Lear is a tragedy by William Shakespeare, believed to have been written between 1603 and 1606. It is considered one of his greatest works. The play is based on the legend of Leir of Britain, a mythological pre-Roman Celtic king. It has been widely adapted for stage and screen, with the part of Lear played by many of the world''s most accomplished actors.'),
(3, 846609406, 'The Adventures of Huckleberry Finn ', 1, 2, 'Revered by all of the town''s children and dreaded by all of its mothers, Huckleberry Finn is indisputably the most appealing child-hero in American literature. Unlike the tall-tale, idyllic world of Tom Sawyer, The Adventures of Huckleberry Finn is firmly grounded in early reality. From the abusive drunkard who serves as Huckleberry''s father, to Huck''s first tentative grappling with issues of personal liberty and the unknown, Huckleberry Finn endeavors to delve quite a bit deeper into the complexities — both joyful and tragic of life'),
(4, 822357682, 'The Adventures of Tom Sawyer', 5, 2, 'The Adventures of Tom Sawyer by Mark Twain is an 1876 novel about a young boy growing up along the Mississippi River. The story is set in the fictional town of St. Petersburg, inspired by Hannibal, Missouri, where Twain lived.'),
(5, 871609196, 'Anna Karenina', 5, 3, 'Anna Karenina tells of the doomed love affair between the sensuous and rebellious Anna and the dashing officer, Count Vronsky. Tragedy unfolds as Anna rejects her passionless marriage and must endure the hypocrisies of society. Set against a vast and richly textured canvas of nineteenth-century Russia, the novel''s seven major characters create a dynamic imbalance, playing out the contrasts of city and country life and all the variations on love and family happiness.'),
(6, 809782618, 'War and Peace', 5, 3, 'Epic in scale, War and Peace delineates in graphic detail events leading up to Napoleon''s invasion of Russia, and the impact of the Napoleonic era on Tsarist society, as seen through the eyes of five Russian aristocratic families.'),
(7, 872264519, 'The Adventures of Oliver Twist', 5, 4, 'At the heart of Charles Dickens''s second novel, first published in 1838, is a story as much about crime and poverty as it is about justice and charity. Orphaned at birth, Oliver Twist grows up under the loveless, relentless watch of a workhouse. He runs away with hopes for a better life in London, only to become--at the hands of the unforgettable Artful Dodger--a guileless pawn i'),
(8, 869812933, 'David Copperfield', 5, 4, 'The story of the abandoned waif who learns to survive through challenging encounters with distress and misfortune.'),
(9, 811618464, 'Adam Bede', 5, 5, 'Adam Bede, the first novel written by George Eliot (the pen name of Mary Ann Evans), was published in 1859. It was published pseudonymously, even though Evans was a well-published and highly respected scholar of her time. The novel has remained in print ever since and is regularly used in university studies of 19th-century English literature.'),
(10, 852176046, 'Middlemarch', 5, 5, 'Middlemarch: A Study of Provincial Life is a novel by George Eliot, the pen name of Mary Anne Evans, later Marian Evans. It is her seventh novel, begun in 1869 and then put aside during the final illness of Thornton Lewes, the son of her companion George Henry Lewes. ');
SELECT SETVAL('book_id_seq', (SELECT MAX(id) FROM book));



INSERT INTO orders (id, book_id, user_id, start_rental_date, end_rental_date)
VALUES (1, 1, 1, to_date('2023-11-23', 'YYYY-MM-DD'),
        to_date('2023-12-31', 'YYYY-MM-DD')),
       (2, 2, 2, to_date('2023-11-10', 'YYYY-MM-DD'),
        to_date('2023-11-29', 'YYYY-MM-DD')),
       (3, 3, 1, to_date('2023-11-10', 'YYYY-MM-DD'),
        to_date('2023-11-28', 'YYYY-MM-DD'));
SELECT SETVAL('orders_id_seq', (SELECT MAX(id) FROM orders));


INSERT INTO idbook (id, bookid)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5),
       (6, 6),
       (7, 7),
       (8, 8),
       (9, 9),
       (10, 10);
SELECT SETVAL('idbook_id_seq', (SELECT MAX(id) FROM idbook));

INSERT INTO rental_date (id, start_rental_date, end_rental_date, idbook_id, order_id)
VALUES (1, to_date('2023-11-23', 'YYYY-MM-DD'),
        to_date('2023-12-31', 'YYYY-MM-DD'), 1, 1),
       (2,to_date('2023-11-10', 'YYYY-MM-DD'),
        to_date('2023-11-29', 'YYYY-MM-DD'), 2, 2),
       (3, to_date('2023-11-10', 'YYYY-MM-DD'),
        to_date('2023-11-28', 'YYYY-MM-DD'), 3, 3);
SELECT SETVAL('rental_date_id_seq', (SELECT MAX(id) FROM rental_date));
