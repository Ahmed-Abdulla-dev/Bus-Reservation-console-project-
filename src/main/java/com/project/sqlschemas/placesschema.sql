--------------------------------------------------------
--  File created - Thursday-July-11-2024   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table BOOKING
--------------------------------------------------------

  CREATE TABLE "CORE"."BOOKING" 
   (	"BOOKING_ID" NUMBER, 
	"BOOK_DATE" DATE, 
	"ROUTE_ID" NUMBER, 
	"BUS_ID" NUMBER, 
	"CUS_NAME" VARCHAR2(35 BYTE), 
	"CUS_NUMBER" NUMBER(10,0), 
	"EMAIL" VARCHAR2(50 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Trigger INSERT_RANDOM_NUMBER
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "CORE"."INSERT_RANDOM_NUMBER" 
BEFORE INSERT ON booking
FOR EACH ROW
BEGIN
    :NEW.booking_id := generate_random_5_digit();
END;

/
ALTER TRIGGER "CORE"."INSERT_RANDOM_NUMBER" ENABLE;
