CREATE TABLE book(
    book_no     NUMBER          PRIMARY KEY,
    book_name   VARCHAR2(40)    NOT NULL,
    book_writer VARCHAR2(20)    NOT NULL,
    book_pub    VARCHAR2(30)    NOT NULL
);

CREATE TABLE lib_mem(
    mem_no      NUMBER          PRIMARY KEY,
    mem_name    VARCHAR2(10)    NOT NULL,
    mem_phone   VARCHAR2(20)    NOT NULL,
    mem_addr    VARCHAR2(30)
);

CREATE TABLE manage(
    mng_no      NUMBER  PRIMARY KEY,
    book_no     NUMBER  REFERENCES book(book_no),
    mem_no      NUMBER  REFERENCES lib_mem(mem_no),
    borrow_date DATE    DEFAULT sysdate
);

CREATE SEQUENCE lib_mem_seq;

INSERT INTO lib_mem(mem_no, mem_name, mem_phone, mem_addr)
VALUES(lib_mem_seq.nextval, '성춘향', '010-9999-8888', '서울특별시 영등포구 여의도동');

ALTER TABLE lib_mem
MODIFY mem_addr VARCHAR2(50);

SELECT * FROM lib_mem ORDER BY mem_no;

COMMIT;

CREATE SEQUENCE book_no_seq;

INSERT INTO book(book_no, book_name, book_writer, book_pub)
VALUES(book_no_seq.nextval, '혼자 공부하는 자바', '신용권', '한빛미디어');

INSERT INTO book(book_no, book_name, book_writer, book_pub)
VALUES(book_no_seq.nextval, '이것이 리눅스다', '우재남', '한빛미디어');

SELECT * FROM book ORDER BY book_no;

COMMIT;

CREATE SEQUENCE mng_no_seq;

INSERT INTO manage(mng_no, book_no, mem_no)
VALUES(mng_no_seq.nextval, 3, 2);

SELECT * FROM manage;

COMMIT;

SELECT m.book_no, b.book_name, l.mem_name, m.borrow_date
FROM manage m JOIN book b ON m.book_no = b.book_no
JOIN lib_mem l ON m.mem_no = l.mem_no;

ALTER SESSION SET NLS_DATE_FORMAT = 'YYYY-MM-DD';

SELECT book_no FROM manage
WHERE book_no = 5;

SELECT * FROM manage;

DELETE FROM manage WHERE mng_no = 11;

