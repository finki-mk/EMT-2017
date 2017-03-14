USE `store`;

CREATE TABLE authors
(
    id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name_and_last_name VARCHAR(255)
);
CREATE TABLE book_details
(
    id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    content_type VARCHAR(255),
    data LONGBLOB,
    description LONGTEXT,
    file_name VARCHAR(255),
    size BIGINT(20),
    book_id BIGINT(20)
);
CREATE TABLE books
(
    id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    isbn VARCHAR(255),
    name VARCHAR(255),
    price DOUBLE,
    quantity_in_stock INT(11),
    category_id BIGINT(20)
);
CREATE TABLE books_authors
(
    book_id BIGINT(20) NOT NULL,
    authors_id BIGINT(20) NOT NULL
);
CREATE TABLE cart_items
(
    id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    quantity INT(11) NOT NULL,
    book_id BIGINT(20),
    cart_id BIGINT(20)
);
CREATE TABLE carts
(
    id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    expiry_date DATETIME,
    total_price DOUBLE
);
CREATE TABLE categories
(
    id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    parent_id BIGINT(20)
);
CREATE TABLE checkouts
(
    id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    cart_id BIGINT(20),
    contact_info_id BIGINT(20),
    delivery_info_id BIGINT(20)
);
CREATE TABLE contact_info
(
    id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    phone VARCHAR(255)
);
CREATE TABLE delivery_info
(
    id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    address VARCHAR(255),
    city VARCHAR(255),
    country VARCHAR(255),
    postal_code VARCHAR(255),
    customer_id BIGINT(20)
);
CREATE TABLE invoices
(
    id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    expiry_date DATETIME,
    gross_price DOUBLE,
    status VARCHAR(255),
    tax_amount DOUBLE,
    checkout_id BIGINT(20)
);
CREATE TABLE users
(
    id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    email VARCHAR(255),
    password VARCHAR(255),
    type VARCHAR(255),
    username VARCHAR(255),
    contact_info_id BIGINT(20)
);

ALTER TABLE book_details ADD FOREIGN KEY (book_id) REFERENCES books (id);
CREATE INDEX FKoa7sqrtgxwg066s9521udtvpv ON book_details (book_id);
ALTER TABLE books ADD FOREIGN KEY (category_id) REFERENCES categories (id);
CREATE INDEX FKleqa3hhc0uhfvurq6mil47xk0 ON books (category_id);
ALTER TABLE books_authors ADD FOREIGN KEY (book_id) REFERENCES books (id);
ALTER TABLE books_authors ADD FOREIGN KEY (authors_id) REFERENCES authors (id);
CREATE INDEX FK1b933slgixbjdslgwu888m34v ON books_authors (book_id);
CREATE INDEX FK20menrngp9wi9at1dsu5cbb8o ON books_authors (authors_id);
ALTER TABLE cart_items ADD FOREIGN KEY (book_id) REFERENCES books (id);
ALTER TABLE cart_items ADD FOREIGN KEY (cart_id) REFERENCES carts (id);
CREATE INDEX FKhiu1jw80o45wfiw5tgok1xpkl ON cart_items (book_id);
CREATE INDEX FKpcttvuq4mxppo8sxggjtn5i2c ON cart_items (cart_id);
ALTER TABLE categories ADD FOREIGN KEY (parent_id) REFERENCES categories (id);
CREATE INDEX FKsaok720gsu4u2wrgbk10b5n8d ON categories (parent_id);
ALTER TABLE checkouts ADD FOREIGN KEY (cart_id) REFERENCES carts (id);
ALTER TABLE checkouts ADD FOREIGN KEY (contact_info_id) REFERENCES contact_info (id);
ALTER TABLE checkouts ADD FOREIGN KEY (delivery_info_id) REFERENCES delivery_info (id);
CREATE INDEX FK86fuskbtwaroxm1cvjosp38rd ON checkouts (cart_id);
CREATE INDEX FKlq4vg49v14lmvg9xaws2unr7o ON checkouts (contact_info_id);
CREATE INDEX FKo8go6r9sylyg9gohwd0vtyg7r ON checkouts (delivery_info_id);
ALTER TABLE delivery_info ADD FOREIGN KEY (customer_id) REFERENCES users (id);
CREATE INDEX FKpu1p3umcrw4iwuamcej3stecm ON delivery_info (customer_id);
ALTER TABLE invoices ADD FOREIGN KEY (checkout_id) REFERENCES checkouts (id);
CREATE INDEX FKth0mk832424ly9uc1ju765e22 ON invoices (checkout_id);
ALTER TABLE users ADD FOREIGN KEY (contact_info_id) REFERENCES contact_info (id);
CREATE INDEX FKhrhbec6xa18rkht81ls2bbgoj ON users (contact_info_id);