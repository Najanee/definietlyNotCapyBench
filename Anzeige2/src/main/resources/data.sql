INSERT INTO topic(id, name) VALUES(1, 'Sprzedam');
INSERT INTO topic(id, name) VALUES(2, 'Nauka niemieckiego');
INSERT INTO topic(id, name) VALUES(3, 'Zainteresowania i projekty');
INSERT INTO topic(id, name) VALUES(4, 'Po godzinach');

INSERT INTO subtopic(id, topic_id, name) VALUES(1, 1, 'Motoryzacja');
INSERT INTO subtopic(id, topic_id, name) VALUES(2, 1, 'Drobne');

INSERT INTO subtopic(id, topic_id, name) VALUES(3, 2, 'Konwersacje poziom podstawowy');
INSERT INTO subtopic(id, topic_id, name) VALUES(4, 2, 'Oglądanie filmów po niemiecku');

INSERT INTO subtopic(id, topic_id, name) VALUES(5, 3, 'City Break');

INSERT INTO person(id, image_url, name) VALUES(1, 'url1', 'Grzegorz Kapibarowski');
INSERT INTO person(id, image_url, name) VALUES(2, 'url2', 'Franek Brzęczyszczykiewicz');
INSERT INTO person(id, image_url, name) VALUES(3, 'url3', 'Maja Frontowa');

INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title) VALUES
(-1, 1, CURRENT_TIMESTAMP, 1, 1, 'topic 1, subtopic 1', 'Opel rocznik 93'),
(-2, 2, CURRENT_TIMESTAMP, 1, 2, 'topic 1, subtopic 2', 'Kilo gwoździ'),
(-3, 3, CURRENT_TIMESTAMP, 2, 3, 'topic 2, subtopic 3', 'Dobre podręczniki dla początkujących'),
(-4, 1, CURRENT_TIMESTAMP, 2, 4, 'topic 2, subtopic 4', 'Krzyżacy z niemieckim dubbingiem i polskimi napisami? Już w ten piątek w sali Warsaw!'),
(-5, 1, CURRENT_TIMESTAMP, 3, 5, 'topic 3, subtopic 5', 'Wypad do Sosnowca - weekend pełen wrażeń'),
(-6, 2, CURRENT_TIMESTAMP, 3, null, 'topic 3, nie ma subtopic', 'W jakiej języku piszecie po godzinach? Ankieta'),
(-7, 2, CURRENT_TIMESTAMP, 3, null, 'topic 3, nie ma subtopic', 'Interfejsy i kiedy warto ich używać? Fajny wykład online.');

INSERT INTO person_to_post(person_id, post_id) VALUES
(1, -1),
(1, -4),
(1, -5),
(2, -2),
(2, -6),
(2, -7),
(3, -3);

INSERT INTO person_to_subtopic(person_id, subtopic_id) VALUES
(1, 1),
(2, 4),
(2, 5),
(3, 2);

INSERT INTO person_to_topic(person_id, topic_id) VALUES
(1, 1),
(2, 1),
(2, 2),
(3, 3);

-- person id 2 should see posts: 1, 2, 3, 4 (once), 5, 7