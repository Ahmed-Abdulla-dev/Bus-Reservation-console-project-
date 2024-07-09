CREATE TABLE places(cityname VARCHAR(30),pincode NUMBER);

INSERT ALL 
    INTO PLACES VALUES(01,'Mayiladuthurai','Chennai','09:00 AM')
    INTO PLACES VALUES(02,'Chennai','Salem','09:45 AM')
    INTO PLACES VALUES(03,'Trichy','Kanya Kumari','10:15 AM')
    INTO PLACES VALUES(04,'Chennai','Mayiladuthurai','08:00 PM')
    INTO PLACES VALUES(05,'Salem','Chennai','09:30 PM')
    INTO PLACES VALUES(06,'Kanya Kumari','Trichy','10:00 PM')
select * from dual;
COMMIT;

SELECT * FROM buses;

DROP TABLE availability;

CREATE TABLE places(route_id NUMBER,from_city VARCHAR(30),to_city VARCHAR(30),timing VARCHAR(10));

CREATE TABLE buses(route_id NUMBER,bus_id NUMBER,bus_name VARCHAR(30),reg_no VARCHAR(30),capacity NUMBER,AC VARCHAR(6));

INSERT ALL 
    INTO buses VALUES(04,101,'vision','TN 82 7015',50,'Non-AC')
    INTO buses VALUES(04,102,'Vision Deluxe','TN 82 3541',40,'AC')
    INTO buses VALUES(05,103,'SpeedPad','TN 82 9876',50,'Non-AC')
    INTO buses VALUES(05,104,'SpeedPad+','TN 82 8547',40,'AC')
    INTO buses VALUES(06,105,'Imaxure','TN 82 2549',40,'Non-AC')
    INTO buses VALUES(06,106,'Imaxure pro','TN 82 7451',35,'AC')
select * from dual;

CREATE TABLE date_y(y_date DATE);

SELECT y_date from DATE_Y;

INSERT into DATE_Y VALUES (To_Date(SYSDATE,'dd-MM-yyyy'));
COMMIT;

SELECT * FROM DATE_Y;

CREATE SEQUENCE random_number_seq
    START WITH 10000
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE OR REPLACE FUNCTION generate_random_5_digit
RETURN NUMBER
IS
    v_random_number NUMBER;
BEGIN
    SELECT random_number_seq.NEXTVAL INTO v_random_number FROM dual;
    RETURN v_random_number;
END;
/

CREATE TABLE booking(booking_id NUMBER,book_date DATE,route_id NUMBER,bus_id NUMBER,capacity NUMBER,cus_name VARCHAR(35),cus_number NUMBER(10),email VARCHAR(50));

CREATE OR REPLACE TRIGGER insert_random_number
BEFORE INSERT ON booking
FOR EACH ROW
BEGIN
    :NEW.booking_id := generate_random_5_digit();
END;
/

SELECT * from BUSES order by ROUTE_ID;

alter table "CORE"."BUSES" add (cost number);

UPDATE BUSES set cost=900 WHERE BUS_ID=106;

COMMIT;

CREATE VIEW receipt AS SELECT A.ROUTE_ID,A.BUS_ID, b.from_city,b.to_city,b.timing,A.bus_name,A.ac,A.cost from buses A inner join places B on A.route_id = B.route_id;

SELECT * from DATE_Y;

DROP TABLE date_y;

delete from date_y;

ROLLBACK;

INSERT into DATE_Y VALUES (To_Date('06-07-2024','dd-MM-yyyy'));

select y_date from date_y;

SELECT * from buses ;

DELETE FROM BOOKING WHERE booking_id=10047;

select booking_id from booking where route_id=3 and bus_id=101 and book_date='06-07-2024' and cus_number=4545454545;

select From_city,to_city,timing,bus_name,ac,cost from receipt ;

select * from RECEIPT;

SELECT * from booking;

SELECT * from BUSES;

COMMIT;

delete from booking where BOOKING_ID in (10051,10052);

select booking_id,cus_name from BOOKING where cus_number='8578127845';

SELECT BOOKING_ID, CUS_NAME
FROM BOOKING
WHERE BOOK_DATE = TO_DATE('10/07/24', 'DD/MM/YY')
  AND CUS_NUMBER = '8575127845';

DELETE FROM booking WHERE book_date < TO_DATE('07-07-2024', 'DD-MM-YYYY');

select route_id,bus_id,cus_name,book_date from BOOKING where booking_id=10033;

UPDATE BUSES SET BUSES.CAPACITY=40 where BUSES.ROUTE_ID=02 AND BUSES.BUS_ID=104;