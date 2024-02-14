
# Simple Case CSV to DB2
Project simple untuk mencoba convert data dari CSV to Database (Cassandra & DB2)


## Requirement

Ada beberapa hal yang harus terinstall dulu sebelum memulai menjalankan program ini
1. Download DB2
Bisa manual atau by docker

2. Download Cassandra NoSQL
Bisa manual atau by docker

3. Download JDK 17
Komputer harus tersedia JDK 17, karena ini menggunakan spring boot 17

## Installation
Untuk menginstall program ini, ikuti beberapa perintah dibawah:
1. Pastikan Requirement telah terlengkapi
2. Buat Database pada pada Cassandra & DB2 sesuai dengan DDL di bawah
3. Clone atau download Repository
4. Setup _application.properties_ pada service __cassandra_version__ dan __db2_version__ 
- _application.properties_ DB2
```env
server.port=<YOUR_APP_PORT>

spring.datasource.url=jdbc:db2://localhost:<YOUR_DB2_PORT>/<YOUR_DATABASE>
spring.datasource.username=<YOUR_USERNAME>
spring.datasource.password=<YOUR_PASSWORD>
```
- _application.properties_ Cassandra
```env
server.port=<YOUR_APP_PORT>

spring.cassandra.contact-points=<YOUR_CASSANDRA_HOST>
spring.cassandra.port=<YOUR_CASSANDRA_PORT>
spring.cassandra.keyspace-name=<YOUR_KEYSPACE_NAME>
spring.cassandra.schema-action=CREATE_IF_NOT_EXISTS
spring.cassandra.request.timeout=10s
spring.cassandra.connection.connect-timeout=10s
spring.cassandra.connection.init-query-timeout=10s

spring.cassandra.local-datacenter=datacenter1

```
5. import [postman_api_docs.json](https://github.com/AgungPN/spring-csv-to-db/blob/v1/db2/simple/postman_api_docs.json) pada postman untuk testing
6. jalankan project ...


## DDL Table
### DB2
Pada DB2 kita harus menyediakan 2 table (_transaction_ dan _inventory_)

#### Transaction Table
```sql
CREATE TABLE TRANSACTION
(
    ID                             INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    Completed                      VARCHAR(3),
    CMPNYCD                        VARCHAR(6),
    STOCK_HANDLING_CUSTOMER_NUMBER VARCHAR(100),
    STOCK_POINT                    VARCHAR(100),
    SLIP_NUMBER                    VARCHAR(100),
    TRANSACTION_CODE               VARCHAR(100),
    SUB_TRANSACTION_CODE           VARCHAR(100),
    TRANSACTION_DATE               DATE,
    TRANSACTION_TIME               INT,
    PURCHASE_ORDER_NUMBER          VARCHAR(100),
    SHIPMENT_NUMBER                VARCHAR(100),
    SUPPLIER_COMPANY_CODE          VARCHAR(100),
    SUPPLIER_NUMBER                VARCHAR(100),
    TOTDETLINE                     INT,
    INTRANSIT_STOCK_POINT          VARCHAR(100),
    RECEIVE_NUMBER                 INT,
    RX_ARRANGEMENT_NUMBER          VARCHAR(30),
    ORIGINAL_STOCK_POINT           VARCHAR(100)
);
```

#### Inventory Table

```sql
CREATE TABLE LENS_FRAME_INVENTORY
(
    ID                             INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    CMPNYCD                        VARCHAR(100),
    STOCK_HANDLING_CUSTOMER_NUMBER VARCHAR(100),
    STOCK_POINT                    VARCHAR(100),
    SLIP_NUMBER                    VARCHAR(100),
    LINE_NUMBER                    VARCHAR(100),
    ITEM_TYPE                      VARCHAR(100),
    F_LENS_RL_TYPE                 VARCHAR(100),
    F_LENS_LENS_CODE               VARCHAR(100),
    F_LENS_COLOR_COAT_CODE         VARCHAR(100),
    F_LENS_NAME                    VARCHAR(100),
    F_LENS_COLOR                   VARCHAR(100),
    F_LENS_COAT                    VARCHAR(100),
    F_LENS_CYLINDER_TYPE           VARCHAR(100),
    F_LENS_SPHERE                  VARCHAR(100),
    F_LENS_CYLINDER                VARCHAR(100),
    F_LENS_AXIS                    VARCHAR(100),
    F_LENS_ADDITION                VARCHAR(100),
    F_LENS_DIAMETER                VARCHAR(100),
    F_LENS_UNIVERSAL_PRODUCT_NAME  VARCHAR(100),
    S_LENS_RL_TYPE                 VARCHAR(100),
    S_LENS_CODE                    VARCHAR(100),
    S_LENS_COLOR_COAT_CODE         VARCHAR(100),
    S_LENS_NAME                    VARCHAR(100),
    S_LENS_COLOR                   VARCHAR(100),
    S_LENS_MAKER                   VARCHAR(100),
    S_LENS_NOMINAL_BASE_CURVE      VARCHAR(100),
    S_LENS_DIAMETER                VARCHAR(100),
    S_LENS_THICKNESS_TYPE          VARCHAR(100),
    S_LENS_ADDITION                VARCHAR(100),
    S_LENS_UNIVERSAL_PRODUCT_NAME  VARCHAR(100),
    FRAME_CODE                     VARCHAR(100),
    FRAME_MAKER                    VARCHAR(100),
    FRAME_NAME                     VARCHAR(100),
    FRAME_EYE_SIZE                 VARCHAR(100),
    FRAME_DBL                      VARCHAR(100),
    FRAME_COLOR                    VARCHAR(100),
    FRAME_PARTS_TYPE               VARCHAR(100),
    INSTRUMENT_CODE                VARCHAR(100),
    INSTRUMENT_NAME                VARCHAR(100),
    INSTRUMENT_PARTS_NUMBER        VARCHAR(100),
    STOCK_IO_QUANTITY              INT,
    RECEIVE_NUMBER                 INT,
    TRANSACTION_CODE               VARCHAR(100),
    SUB_TRANSACTION_CODE           VARCHAR(100),
    TRANSACTION_DATE               DATE,
    TRANSACTION_TIME               INT
);
```

### Cassandra
#### Transaction Table
```CQL
CREATE TABLE transactions
(
    id                             UUID PRIMARY KEY,
    completed                      TEXT,
    cmpnycd                        TEXT,
    stock_handling_customer_number TEXT,
    stock_point                    TEXT,
    slip_number                    TEXT,
    transaction_code               TEXT,
    sub_transaction_code           TEXT,
    transaction_date               DATE,
    transaction_time               INT,
    purchase_order_number          TEXT,
    shipment_number                TEXT,
    supplier_company_code          TEXT,
    supplier_number                TEXT,
    totdetline                     TEXT,
    intransit_stock_point          TEXT,
    receive_number                 TEXT,
    rx_arrangement_number          TEXT,
    original_stock_point           TEXT
);

```
#### Inventory Table
```cql
CREATE TABLE lens_frame_inventory
(
    id                             UUID PRIMARY KEY,
    cmpnycd                        TEXT,
    stock_handling_customer_number TEXT,
    stock_point                    TEXT,
    slip_number                    TEXT,
    line_number                    TEXT,
    item_type                      TEXT,
    f_lens_rl_type                 TEXT,
    f_lens_lens_code               TEXT,
    f_lens_color_coat_code         TEXT,
    f_lens_name                    TEXT,
    f_lens_color                   TEXT,
    f_lens_coat                    TEXT,
    f_lens_cylinder_type           TEXT,
    f_lens_sphere                  TEXT,
    f_lens_cylinder                TEXT,
    f_lens_axis                    TEXT,
    f_lens_addition                TEXT,
    f_lens_diameter                TEXT,
    f_lens_universal_product_name  TEXT,
    s_lens_rl_type                 TEXT,
    s_lens_code                    TEXT,
    s_lens_color_coat_code         TEXT,
    s_lens_name                    TEXT,
    s_lens_color                   TEXT,
    s_lens_maker                   TEXT,
    s_lens_nominal_base_curve      TEXT,
    s_lens_diameter                TEXT,
    s_lens_thickness_type          TEXT,
    s_lens_addition                TEXT,
    s_lens_universal_product_name  TEXT,
    frame_code                     TEXT,
    frame_maker                    TEXT,
    frame_name                     TEXT,
    frame_eye_size                 TEXT,
    frame_dbl                      TEXT,
    frame_color                    TEXT,
    frame_parts_type               TEXT,
    instrument_code                TEXT,
    instrument_name                TEXT,
    instrument_parts_number        TEXT,
    stock_io_quantity              TEXT,
    receive_number                 INT,
    transaction_code               INT,
    sub_transaction_code           TEXT,
    transaction_date               DATE,
    transaction_time               INT
);
```

## Authors

- [@agungpn33@gmail.com](https://www.github.com/octokatherine](https://www.linkedin.com/in/agung-prasetyo-nugroho-0a35a6236/)https://www.linkedin.com/in/agung-prasetyo-nugroho-0a35a6236)

