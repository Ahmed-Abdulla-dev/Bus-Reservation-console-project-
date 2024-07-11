--------------------------------------------------------
--  File created - Thursday-July-11-2024   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for View RECEIPT
--------------------------------------------------------

  CREATE OR REPLACE FORCE EDITIONABLE VIEW "CORE"."RECEIPT" ("ROUTE_ID", "BUS_ID", "FROM_CITY", "TO_CITY", "TIMING", "BUS_NAME", "AC", "COST") AS 
  SELECT A.ROUTE_ID,A.BUS_ID, b.from_city,b.to_city,b.timing,A.bus_name,A.ac,A.cost from buses A inner join places B on A.route_id = B.route_id
;