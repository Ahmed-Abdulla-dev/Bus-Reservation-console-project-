--------------------------------------------------------
--  File created - Thursday-July-11-2024   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table DATE_Y
--------------------------------------------------------

  CREATE TABLE "CORE"."DATE_Y" 
   (	"Y_DATE" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into CORE.DATE_Y
SET DEFINE OFF;
Insert into CORE.DATE_Y (Y_DATE) values (to_date('08-07-24','DD-MM-RR'));
