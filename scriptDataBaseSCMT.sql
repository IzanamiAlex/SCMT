CREATE TABLE product
(
  barcode bigint NOT NULL,
  description character varying(80) NOT NULL,
  sales_unit character varying(10) NOT NULL,
  price double precision NOT NULL,
  departament character varying,
  CONSTRAINT "pk-product" PRIMARY KEY (barcode )
)
WITH (
  OIDS=FALSE
);
ALTER TABLE product
  OWNER TO postgres;

CREATE TABLE provider
(
  identifier integer NOT NULL,
  name character varying(100) NOT NULL,
  phone character varying(14),
  address character varying(50),
  CONSTRAINT pk_provider PRIMARY KEY (identifier )
)
WITH (
  OIDS=FALSE
);
ALTER TABLE provider
  OWNER TO postgres;
CREATE TABLE product_list_sold
(
  barcode_product bigint NOT NULL,
  sale_number bigint NOT NULL,
  product_quantity integer,
  CONSTRAINT pk_product_sale PRIMARY KEY (barcode_product , sale_number ),
  CONSTRAINT fk_barcode FOREIGN KEY (barcode_product)
      REFERENCES product (barcode) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_sale_number FOREIGN KEY (sale_number)
      REFERENCES sale_register (sale_number) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE product_list_sold
  OWNER TO postgres;

CREATE TABLE sale_register
(
  sale_number bigint NOT NULL,
  date_sale date,
  total_cost double precision,
  CONSTRAINT pk_sale_register PRIMARY KEY (sale_number )
)
WITH (
  OIDS=FALSE
);
ALTER TABLE sale_register
  OWNER TO postgres;

CREATE TABLE product_list_sold
(
  barcode_product bigint NOT NULL,
  sale_number bigint NOT NULL,
  product_quantity integer,
  CONSTRAINT pk_product_sale PRIMARY KEY (barcode_product , sale_number ),
  CONSTRAINT fk_barcode FOREIGN KEY (barcode_product)
      REFERENCES product (barcode) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_sale_number FOREIGN KEY (sale_number)
      REFERENCES sale_register (sale_number) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE product_list_sold
  OWNER TO postgres;

CREATE SEQUENCE serial_id_provider
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 15
  CACHE 1;
ALTER TABLE serial_id_provider
  OWNER TO postgres;

CREATE SEQUENCE serial_sale_number
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE serial_sale_number
  OWNER TO postgres;
