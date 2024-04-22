-- 사원 테이블(사원번호, 사원명, 연락처, 이메일, 입사일자, 급여)
DROP TABLE emp PURGE;

CREATE TABLE emp(
    emp_no      NUMBER          PRIMARY KEY, -- emp_seq.nextval
    emp_name    VARCHAR2(40)    NOT NULL,
    emp_phone   VARCHAR2(12)    NOT NULL, -- 02-1234-1234
    email       VARCHAR2(30)    NOT NULL,
    hire_date   date            DEFAULT sysdate,
    salary      NUMBER
);
-- C(reate)R(ead)U(pdate)D(elete)
CREATE SEQUENCE emp_seq;

INSERT INTO emp(emp_no, emp_name, emp_phone, email, salary)
VALUES(emp_seq.nextval, 'kildongHong', '01-1234-5678', 'kildong@email', 2000);
INSERT INTO emp(emp_no, emp_name, emp_phone, email, salary)
VALUES(emp_seq.nextval, 'kildongPark', '01-3333-5678', 'pkildong@email', 2300);

SELECT *
FROM emp
ORDER BY emp_no;

UPDATE emp
SET salary = salary + 500,
    emp_phone = '01-1111-1111'
WHERE emp_name = 'kildongHong';

DELETE FROM emp
WHERE emp_no = 3;

CREATE TABLE member(
    mem_no  NUMBER          PRIMARY KEY,
    name    VARCHAR2(20)    NOT NULL,
    phone   VARCHAR2(15)    NOT NULL,
    birth   DATE            DEFAULT sysdate,
    sex     VARCHAR2(10)    NOT NULL
);

CREATE SEQUENCE mem_seq;

ALTER TABLE member ADD email VARCHAR2(10);
