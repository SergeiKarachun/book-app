--User
CREATE TABLE IF NOT EXISTS users
(
    id       BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    email    VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role     VARCHAR(32)  NOT NULL DEFAULT 'CLIENT'
);

--UserDetails
CREATE TABLE IF NOT EXISTS user_details
(
    id      BIGSERIAL PRIMARY KEY,
    user_id BIGINT       NOT NULL,
    name    VARCHAR(128) NOT NULL,
    surname VARCHAR(128) NOT NULL,
    address VARCHAR(128) NOT NULL,
    phone   VARCHAR(32)  NOT NULL,
    CONSTRAINT userdetails_user_fk FOREIGN KEY (user_id) REFERENCES users (id)
        ON UPDATE CASCADE
        ON DELETE SET NULL
);


--Genre
CREATE TABLE IF NOT EXISTS genre
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(128) NOT NULL
);

--Author
CREATE TABLE IF NOT EXISTS author
(
    id      BIGSERIAL PRIMARY KEY,
    name    VARCHAR(128) NOT NULL,
    surname VARCHAR(128) NOT NULL,
    CONSTRAINT uq_author UNIQUE (name, surname)
);

--Book
CREATE TABLE IF NOT EXISTS book
(
    id          BIGSERIAL PRIMARY KEY,
    isbn        BIGINT       NOT NULL UNIQUE,
    name        varchar(255) NOT NULL,
    genre_id    BIGINT       NOT NULL,
    author_id   BIGINT       NOT NULL,
    description VARCHAR(255),
    CONSTRAINT book_genre_fk FOREIGN KEY (genre_id) REFERENCES author (id)
        ON UPDATE CASCADE
        ON DELETE SET NULL
);

--Order
CREATE TABLE IF NOT EXISTS orders
(
    id                BIGSERIAL PRIMARY KEY,
    date              DATE   NOT NULL DEFAULT now(),
    book_id           BIGINT NOT NULL UNIQUE,
    user_id           BIGINT NOT NULL UNIQUE,
    start_rental_date DATE   NOT NULL,
    end_rental_date   DATE   NOT NULL,
    CONSTRAINT order_book_fk FOREIGN KEY (book_id) REFERENCES book (id),
    CONSTRAINT order_user_fk FOREIGN KEY (user_id) REFERENCES users (id)
        ON UPDATE CASCADE
        ON DELETE SET NULL
);

--Library
CREATE TABLE IF NOT EXISTS library
(
    id                BIGSERIAL PRIMARY KEY,
    book_id           BIGINT       NOT NULL,
    isbn              BIGINT       NOT NULL,
    name              VARCHAR(255) NOT NULL,
    genre             VARCHAR(128) NOT NULL,
    author            VARCHAR(255) NOT NULL,
    description       VARCHAR(255),
    start_rental_time date,
    end_rental_time   date
);