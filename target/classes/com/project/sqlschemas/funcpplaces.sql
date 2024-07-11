--------------------------------------------------------
--  File created - Thursday-July-11-2024   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Function GENERATE_RANDOM_5_DIGIT
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "CORE"."GENERATE_RANDOM_5_DIGIT" 
RETURN NUMBER
IS
    v_random_number NUMBER;
BEGIN
    SELECT random_number_seq.NEXTVAL INTO v_random_number FROM dual;
    RETURN v_random_number;
END;

/RANDOM_NUMBER_SEQ

