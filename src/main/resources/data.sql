-- DEPARTMENTS
INSERT INTO DEPARTMENTS VALUES(nextval('hibernate_sequence'), 'Rzeszow', 5000, 2000, 'Development');
INSERT INTO DEPARTMENTS VALUES(nextval('hibernate_sequence'), 'Rzeszow', 3000, 1000, 'Human Resource Management');
INSERT INTO DEPARTMENTS VALUES(nextval('hibernate_sequence'), 'Wroclaw', 2500, 1000, 'Finance');
INSERT INTO DEPARTMENTS VALUES(nextval('hibernate_sequence'), 'Warsaw', 10000, 5000, 'Management');

-- ROLES
INSERT INTO ROLES VALUES(nextval('hibernate_sequence'), 'ROLE_EMPLOYEE');
INSERT INTO ROLES VALUES(nextval('hibernate_sequence'), 'ROLE_MANAGER');
INSERT INTO ROLES VALUES(nextval('hibernate_sequence'), 'ROLE_CEO');

-- USERS
-- - CEO
INSERT INTO USERS VALUES(nextval('hibernate_sequence'), true, 664372194, '2012-12-12 12:12:43', 'ceo@company.com', 'John','12-12-2012 12:12', 'Snow', '$2a$10$DS.O7qJ78qa/Jpsw3Us69ukgSpRIOHVrSo.XjUJHJYWW/727mEdkC', 664372193, 10000, 'ceo', (SELECT DEPARTMENTS.ID FROM DEPARTMENTS WHERE DEPARTMENTS.NAME='Development'));
-- - MANAGERS
INSERT INTO USERS VALUES(nextval('hibernate_sequence'), true, 664372194, '2012-12-12 12:12:43', 'manager1@company.com', 'Petyr','12-12-2012 12:12', 'Baelish', '$2a$10$DS.O7qJ78qa/Jpsw3Us69ukgSpRIOHVrSo.XjUJHJYWW/727mEdkC', 664372198, 3500, 'manager1', (SELECT DEPARTMENTS.ID FROM DEPARTMENTS WHERE DEPARTMENTS.NAME='Development'));
INSERT INTO USERS VALUES(nextval('hibernate_sequence'), true, 664372194, '2012-12-12 12:12:43', 'manager2@company.com', 'Cersei','12-12-2012 12:12', 'Lannister', '$2a$10$DS.O7qJ78qa/Jpsw3Us69ukgSpRIOHVrSo.XjUJHJYWW/727mEdkC', 664372198, 3500, 'manager2', (SELECT DEPARTMENTS.ID FROM DEPARTMENTS WHERE DEPARTMENTS.NAME='Development'));
-- - EMPLOYEES
INSERT INTO USERS VALUES(nextval('hibernate_sequence'), true, 664372194, '2012-12-12 12:12:43', 'employee1@company.com', 'Sandor','12-12-2012 12:12', 'Clegane', '$2a$10$DS.O7qJ78qa/Jpsw3Us69ukgSpRIOHVrSo.XjUJHJYWW/727mEdkC', 664372193, 2000, 'employee1', (SELECT DEPARTMENTS.ID FROM DEPARTMENTS WHERE DEPARTMENTS.NAME='Development'));
INSERT INTO USERS VALUES(nextval('hibernate_sequence'), true, 664372194, '2012-12-12 12:12:43', 'employee11@company.com', 'Daenerys','12-12-2012 12:12', 'Targaryen', '$2a$10$DS.O7qJ78qa/Jpsw3Us69ukgSpRIOHVrSo.XjUJHJYWW/727mEdkC', 664372198, 2100, 'employee11', (SELECT DEPARTMENTS.ID FROM DEPARTMENTS WHERE DEPARTMENTS.NAME='Development'));
INSERT INTO USERS VALUES(nextval('hibernate_sequence'), true, 664372194, '2012-12-12 12:12:43', 'employee2@company.com', 'Tyrion','12-12-2012 12:12', 'Lannister', '$2a$10$DS.O7qJ78qa/Jpsw3Us69ukgSpRIOHVrSo.XjUJHJYWW/727mEdkC', 664372193, 2000, 'employee2', (SELECT DEPARTMENTS.ID FROM DEPARTMENTS WHERE DEPARTMENTS.NAME='Development'));
INSERT INTO USERS VALUES(nextval('hibernate_sequence'), true, 664372194, '2012-12-12 12:12:43', 'employee22@company.com', 'Joffrey','12-12-2012 12:12', 'Baratheon', '$2a$10$DS.O7qJ78qa/Jpsw3Us69ukgSpRIOHVrSo.XjUJHJYWW/727mEdkC', 664372198, 2100, 'employee22', (SELECT DEPARTMENTS.ID FROM DEPARTMENTS WHERE DEPARTMENTS.NAME='Development'));


INSERT INTO USERS_ROLES VALUES((SELECT USERS.ID FROM USERS WHERE USERS.USERNAME='ceo'), (SELECT ROLES.ID FROM ROLES WHERE ROLES.NAME='ROLE_CEO'));
-- - MANAGERS
INSERT INTO USERS_ROLES VALUES((SELECT USERS.ID FROM USERS WHERE USERS.USERNAME='manager1'), (SELECT ROLES.ID FROM ROLES WHERE ROLES.NAME='ROLE_MANAGER'));
INSERT INTO USERS_ROLES VALUES((SELECT USERS.ID FROM USERS WHERE USERS.USERNAME='manager2'), (SELECT ROLES.ID FROM ROLES WHERE ROLES.NAME='ROLE_MANAGER'));
-- - EMPLOYEES
INSERT INTO USERS_ROLES VALUES((SELECT USERS.ID FROM USERS WHERE USERS.USERNAME='employee1'), (SELECT ROLES.ID FROM ROLES WHERE ROLES.NAME='ROLE_EMPLOYEE'));
INSERT INTO USERS_ROLES VALUES((SELECT USERS.ID FROM USERS WHERE USERS.USERNAME='employee11'), (SELECT ROLES.ID FROM ROLES WHERE ROLES.NAME='ROLE_EMPLOYEE'));
INSERT INTO USERS_ROLES VALUES((SELECT USERS.ID FROM USERS WHERE USERS.USERNAME='employee2'), (SELECT ROLES.ID FROM ROLES WHERE ROLES.NAME='ROLE_EMPLOYEE'));
INSERT INTO USERS_ROLES VALUES((SELECT USERS.ID FROM USERS WHERE USERS.USERNAME='employee22'), (SELECT ROLES.ID FROM ROLES WHERE ROLES.NAME='ROLE_EMPLOYEE'));


-- MANAGERS SELECT ROLES.ID FORM ROLES WHERE ROLES.NAME='ROLE_MANAGER'
INSERT INTO MANAGERS VALUES(nextval('hibernate_sequence'), '12-12-2012 12:12', (SELECT DEPARTMENTS.ID FROM DEPARTMENTS WHERE DEPARTMENTS.NAME='Development'), (SELECT USERS.ID FROM USERS WHERE USERS.USERNAME='manager1'));
INSERT INTO MANAGERS VALUES(nextval('hibernate_sequence'), '12-12-2012 12:12', (SELECT DEPARTMENTS.ID FROM DEPARTMENTS WHERE DEPARTMENTS.NAME='Human Resource Management'), (SELECT USERS.ID FROM USERS WHERE USERS.USERNAME='manager2'));
