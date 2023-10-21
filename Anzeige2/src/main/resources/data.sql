INSERT INTO person(id, name) VALUES(1, 'Bartek');
INSERT INTO person(id, name) VALUES(2, 'Grzegorz');
INSERT INTO person(id, name) VALUES(3, 'Maja');
INSERT INTO person(id, name) VALUES(4, 'Michal');
INSERT INTO person(id, name) VALUES(5, 'Adrian');
INSERT INTO person(id, name) VALUES(6, 'Karol Wojtyla');

INSERT INTO topic(id, name) VALUES(1, 'Language');
INSERT INTO topic(id, name) VALUES(2, 'Travel');
INSERT INTO topic(id, name) VALUES(3, 'Project');
INSERT INTO topic(id, name) VALUES(4, 'Sell');
INSERT INTO topic(id, name) VALUES(5, 'Buy');
INSERT INTO topic(id, name) VALUES(6, 'JP2');

INSERT INTO subtopic(id, topic_id, name) VALUES(1, 1, 'English');
INSERT INTO subtopic(id, topic_id, name) VALUES(2, 1, 'Deutsch');
INSERT INTO subtopic(id, topic_id, name) VALUES(3, 1, 'English C1');

INSERT INTO subtopic(id, topic_id, name) VALUES(4, 2, 'Polish');
INSERT INTO subtopic(id, topic_id, name) VALUES(5, 2, 'City Break');

INSERT INTO subtopic(id, topic_id, name) VALUES(6, 6, 'Barka');
INSERT INTO subtopic(id, topic_id, name) VALUES(7, 4, 'Kapcie');
INSERT INTO subtopic(id, topic_id, name) VALUES(8, 4, 'Samochód');


--
--INSERT INTO post(create_date, id, person_id, subtopic_id, topic_id, content, title) VALUES(DATE())