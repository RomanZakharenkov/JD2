CREATE SCHEMA shop;

CREATE TABLE shop.category (
  id   SERIAL PRIMARY KEY,
  name CHARACTER VARYING(100) NOT NULL UNIQUE
);

CREATE TABLE shop.product (
  id          BIGSERIAL PRIMARY KEY,
  brand       CHARACTER VARYING(50) NOT NULL,
  com.zakharenkov.shop.model       CHARACTER VARYING(50) NOT NULL,
  price       INTEGER               NOT NULL,
  category_id INTEGER               NOT NULL REFERENCES shop.category (id),
  description TEXT,
  image       TEXT
);

CREATE TABLE shop.storage (
  id         BIGSERIAL PRIMARY KEY,
  product_id BIGINT REFERENCES shop.product (id),
  count      INTEGER
);

CREATE TABLE shop.user (
  id           BIGSERIAL PRIMARY KEY,
  role         CHARACTER VARYING(10) NOT NULL,
  first_name   CHARACTER VARYING(30) NOT NULL,
  last_name    CHARACTER VARYING(30) NOT NULL,
  email        CHARACTER VARYING(50) NOT NULL UNIQUE,
  password     CHARACTER VARYING(30) NOT NULL,
  phone_number CHARACTER VARYING(20)
);

CREATE TABLE shop.user_order (
  id      BIGSERIAL PRIMARY KEY,
  user_id BIGINT                NOT NULL REFERENCES shop.user (id),
  status  CHARACTER VARYING(20) NOT NULL,
  date    DATE                  NOT NULL
);

CREATE TABLE shop.review (
  id         BIGSERIAL PRIMARY KEY,
  product_id BIGINT NOT NULL REFERENCES shop.product (id),
  user_id    BIGINT NOT NULL REFERENCES shop."user" (id),
  date       DATE   NOT NULL,
  text       TEXT   NOT NULL
);

CREATE TABLE shop.line_item (
  id         BIGSERIAL PRIMARY KEY,
  order_id   BIGINT  NOT NULL REFERENCES shop.user_order (id),
  product_id BIGINT  NOT NULL REFERENCES shop.product (id),
  count      INTEGER NOT NULL
);

CREATE TABLE shop.favorite (
  product_id BIGINT NOT NULL REFERENCES shop.product (id),
  user_id    BIGINT NOT NULL REFERENCES shop."user" (id)
);

CREATE TABLE shop.sale (
  id          BIGSERIAL PRIMARY KEY,
  user_id     BIGINT                 NOT NULL REFERENCES shop."user" (id),
  start_date  DATE                   NOT NULL,
  end_date    DATE,
  type        CHARACTER VARYING(128) NOT NULL,
  status      CHARACTER VARYING(128) NOT NULL,
  description TEXT
);

CREATE TABLE shop.discount (
  sale_id BIGINT  NOT NULL REFERENCES shop.sale (id),
  precent INTEGER NOT NULL
);

CREATE TABLE shop.coupon (
  sale_id BIGINT  NOT NULL REFERENCES shop.sale (id),
  count   INTEGER NOT NULL
);

CREATE TABLE shop.user_sale (
  user_id BIGINT NOT NULL REFERENCES shop."user" (id),
  sale_id BIGINT NOT NULL REFERENCES shop.sale (id)
);



