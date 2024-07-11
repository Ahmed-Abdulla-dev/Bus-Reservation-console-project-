--------------------------------------------------------
--  File created - Thursday-July-11-2024   
--------------------------------------------------------
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
