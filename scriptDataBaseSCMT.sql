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

CREATE TABLE price_provider
(
  barcode_product bigint NOT NULL,
  indentifier_provider integer NOT NULL,
  price_product_for_provider double precision NOT NULL,
  CONSTRAINT pk_price_provide PRIMARY KEY (barcode_product , indentifier_provider ),
  CONSTRAINT fk_product FOREIGN KEY (barcode_product)
      REFERENCES product (barcode) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_provider FOREIGN KEY (indentifier_provider)
      REFERENCES provider (identifier) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE price_provider
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

CREATE TABLE service
(
  identifier bigint NOT NULL,
  description character varying(50) NOT NULL,
  characteristics character varying(250) NOT NULL,
  estimate_price double precision NOT NULL,
  date_receipt date NOT NULL,
  date_delivery date NOT NULL,
  is_did boolean NOT NULL,
  is_delivered boolean NOT NULL,
  CONSTRAINT "pk-service" PRIMARY KEY (identifier )
)
WITH (
  OIDS=FALSE
);
ALTER TABLE service
  OWNER TO postgres;

CREATE SEQUENCE serial_id_service
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 15
  CACHE 1;
ALTER TABLE serial_id_service
  OWNER TO postgres;