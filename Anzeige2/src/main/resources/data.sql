INSERT INTO topic(id, name) VALUES(1, 'Trade');
INSERT INTO topic(id, name) VALUES(2, 'Learn a language');
INSERT INTO topic(id, name) VALUES(3, 'After hours');
INSERT INTO topic(id, name) VALUES(4, 'Hobbies and projects');
INSERT INTO topic(id, name) VALUES(5, 'Exchange');

INSERT INTO subtopic(id, topic_id, name) VALUES(1, 1, 'Motors');
INSERT INTO subtopic(id, topic_id, name) VALUES(2, 1, 'Second hand');
INSERT INTO subtopic(id, topic_id, name) VALUES(3, 2, 'Entry level german course');
INSERT INTO subtopic(id, topic_id, name) VALUES(4, 2, 'Watch american blockbusters with german dubbing');
INSERT INTO subtopic(id, topic_id, name) VALUES(5, 3, 'City Break');
INSERT INTO subtopic(id, topic_id, name) VALUES(6, 3, 'Learning');
INSERT INTO subtopic(id, topic_id, name) VALUES(7, 3, 'Travel');

-- subtopiki Wymiana
INSERT INTO subtopic(id, topic_id, name) VALUES(8, 5, 'Electronics');
INSERT INTO subtopic(id, topic_id, name) VALUES(9, 5, 'Software');
INSERT INTO subtopic(id, topic_id, name) VALUES(10, 5, 'Books');
INSERT INTO subtopic(id, topic_id, name) VALUES(11, 5, 'Art');
INSERT INTO subtopic(id, topic_id, name) VALUES(12, 5, 'Other');

-- subtopiki Sprzedaż
INSERT INTO subtopic(id, topic_id, name) VALUES(13, 1, 'Elektronika');
INSERT INTO subtopic(id, topic_id, name) VALUES(14, 1, 'Książki');
INSERT INTO subtopic(id, topic_id, name) VALUES(15, 1, 'Sztuka');
INSERT INTO subtopic(id, topic_id, name) VALUES(16, 1, 'AGD & RTV');
INSERT INTO subtopic(id, topic_id, name) VALUES(17, 1, 'Inne');
INSERT INTO subtopic(id, topic_id, name) VALUES(18, 1, 'Gry');
INSERT INTO subtopic(id, topic_id, name) VALUES(19, 1, 'Odzież');

-- subtopiki Jezyki
INSERT INTO subtopic(id, topic_id, name) VALUES(20, 2, 'Polski');
INSERT INTO subtopic(id, topic_id, name) VALUES(21, 2, 'Niemiecki');
INSERT INTO subtopic(id, topic_id, name) VALUES(22, 2, 'Angielski');
INSERT INTO subtopic(id, topic_id, name) VALUES(23, 2, 'Hiszpański');
INSERT INTO subtopic(id, topic_id, name) VALUES(24, 2, 'Rosyjski');
INSERT INTO subtopic(id, topic_id, name) VALUES(25, 2, 'Francuski');
INSERT INTO subtopic(id, topic_id, name) VALUES(26, 2, 'Inne');

-- subtopiki po godzinach
INSERT INTO subtopic(id, topic_id, name) VALUES(27, 3, 'Planszówki');
INSERT INTO subtopic(id, topic_id, name) VALUES(28, 3, 'Luźne');
INSERT INTO subtopic(id, topic_id, name) VALUES(29, 3, 'Film');
INSERT INTO subtopic(id, topic_id, name) VALUES(30, 3, 'Wieczór');
INSERT INTO subtopic(id, topic_id, name) VALUES(31, 3, 'Muzyka');
INSERT INTO subtopic(id, topic_id, name) VALUES(32, 3, 'Inne');

-- subtopiki projekty
INSERT INTO subtopic(id, topic_id, name) VALUES(33, 3, 'Java');
INSERT INTO subtopic(id, topic_id, name) VALUES(34, 3, 'Java Script');
INSERT INTO subtopic(id, topic_id, name) VALUES(35, 3, 'Python');
INSERT INTO subtopic(id, topic_id, name) VALUES(36, 3, 'C#');
INSERT INTO subtopic(id, topic_id, name) VALUES(37, 3, 'Tech-Talk');
INSERT INTO subtopic(id, topic_id, name) VALUES(38, 3, 'Algorytmy');
INSERT INTO subtopic(id, topic_id, name) VALUES(39, 3, 'Inne');

INSERT INTO person(id, image_url, name) VALUES(1, 'https://github.com/MayPaw/definietlyNotCapyBench/blob/frontend-development/Anzeige%202.0/images/kapibara1.png?raw=true', 'Grzegorz Kapibarowski');
INSERT INTO person(id, image_url, name) VALUES(2, 'https://github.com/MayPaw/definietlyNotCapyBench/blob/frontend-development/Anzeige%202.0/images/MicrosoftTeams-image%20(2).png?raw=true', 'Franek Brzęczyszczykiewicz');
INSERT INTO person(id, image_url, name) VALUES(3, 'https://github.com/MayPaw/definietlyNotCapyBench/blob/frontend-development/Anzeige%202.0/images/MicrosoftTeams-image%20(2).png?raw=true', 'Maja Frontowa');
INSERT INTO person(id, image_url, name) VALUES(4, 'https://github.com/MayPaw/definietlyNotCapyBench/blob/frontend-development/Anzeige%202.0/images/MicrosoftTeams-image%20(4).png?raw=true', 'Piotr Nowak');
INSERT INTO person(id, image_url, name) VALUES(5, 'https://github.com/MayPaw/definietlyNotCapyBench/blob/frontend-development/Anzeige%202.0/images/MicrosoftTeams-image%20(7).png?raw=true', 'Magdalena Wojciechowska');
INSERT INTO person(id, image_url, name) VALUES(6, 'https://github.com/MayPaw/definietlyNotCapyBench/blob/frontend-development/Anzeige%202.0/images/MicrosoftTeams-image%20(2).png?raw=true', 'Michał Jankowski');
INSERT INTO person(id, image_url, name) VALUES(7, 'https://github.com/MayPaw/definietlyNotCapyBench/blob/frontend-development/Anzeige%202.0/images/MicrosoftTeams-image%20(5).png?raw=true', 'Karolina Kaczmarek');
INSERT INTO person(id, image_url, name) VALUES(8, 'https://github.com/MayPaw/definietlyNotCapyBench/blob/frontend-development/Anzeige%202.0/images/MicrosoftTeams-image%20(7).png?raw=true', 'Adam Szymański');
INSERT INTO person(id, image_url, name) VALUES(9, 'https://github.com/MayPaw/definietlyNotCapyBench/blob/frontend-development/Anzeige%202.0/images/MicrosoftTeams-image%20(6).png?raw=true', 'Ewa Lewandowska');

INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title) VALUES
(-1, 1, CURRENT_TIMESTAMP, 1, 1, 'topic 1, subtopic 1', 'Opel rocznik 93'),
(-2, 2, CURRENT_TIMESTAMP, 1, 2, 'topic 1, subtopic 2', 'Kilo gwoździ'),
(-3, 3, CURRENT_TIMESTAMP, 2, 3, 'topic 2, subtopic 3', 'Dobre podręczniki dla początkujących'),
(-4, 1, CURRENT_TIMESTAMP, 2, 4, 'topic 2, subtopic 4', 'Krzyżacy z niemieckim dubbingiem i polskimi napisami? Już w ten piątek w sali Warsaw!'),
(-5, 1, CURRENT_TIMESTAMP, 3, 5, 'topic 3, subtopic 5', 'Wypad do Sosnowca - weekend pełen wrażeń'),
(-6, 2, CURRENT_TIMESTAMP, 3, null, 'topic 3, nie ma subtopic', 'W jakim języku piszecie po godzinach? Ankieta'),
(-7, 2, CURRENT_TIMESTAMP, 3, null, 'topic 3, nie ma subtopic', 'Interfejsy i kiedy warto ich używać? Fajny wykład online.');


-- Wymiana Sprzętu
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-8, 3, CURRENT_TIMESTAMP, 5, 8, 'Chcę wymienić mój stary aparat na porządny laptop. Napisz do mnie po szczegóły.', 'Wymiana Aparatu na Laptopa');

-- Wymiana Usług
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-9, 2, CURRENT_TIMESTAMP, 5, 9, 'Oferuję usługi graficznego projektowania w zamian za lekcje programowania. Zaczynamy handel!', 'Projektowanie za Programowanie');

-- Handel Technologią
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-10, 1, CURRENT_TIMESTAMP, 5, 8, 'Mam do wymiany konsolę do gier. Zainteresowany/a smartfonem lub innymi gadżetami technologicznymi.', 'Handel Konsolą do Gier');

-- Wymiana Książek
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-11, 2, CURRENT_TIMESTAMP, 5, 10, 'Chcę wymieniać książki. Mam powieści science fiction, chętnie przyjmę inne gatunki.', 'Wymiana Książek');

-- Sztuka za Sztukę
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-12, 3, CURRENT_TIMESTAMP, 5, 11, 'Tworzę sztukę cyfrową i chciałbym wymieniać się dziełami z innymi artystami. Handlujmy kreatywnością!', 'Wymiana Sztuki');

-- Wymiana Nauki Języków
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-13, 2, CURRENT_TIMESTAMP, 5, 12, 'Oferuję lekcje hiszpańskiego w zamian za lekcje francuskiego. Poprawmy razem nasze umiejętności językowe!', 'Wymiana Językowa');

-- Elektronika za Meble
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-14, 2, CURRENT_TIMESTAMP, 5, 8, 'Chcę wymienić mojego starego laptopa na eleganckie meble do salonu. Propozycje mile widziane!', 'Laptop za Meble');

-- Usługi Ogrodnicze za Malowanie
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-15, 2, CURRENT_TIMESTAMP, 5, 12, 'Oferuję usługi ogrodnicze w zamian za pomalowanie mojego płotu. Daj znać, jeśli jesteś zainteresowany/a!', 'Ogrodnictwo za Malowanie');

-- Kolekcja Kafelków do Wymiany
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-16, 3, CURRENT_TIMESTAMP, 5, 12, 'Posiadam kolekcję kafelków do łazienki do wymiany. Interesuje mnie coś związane z dekoracją wnętrz.', 'Wymiana Kafelków do Łazienki');

-- Sprzątanie za Gotowanie
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-17, 2, CURRENT_TIMESTAMP, 5, 12, 'Chcę wymieniać usługi sprzątania na gotowanie obiadów. Znajdziemy coś dla siebie!', 'Sprzątanie za Gotowanie');

-- Kursy Programowania za Opiekę nad Psem
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-18, 1, CURRENT_TIMESTAMP, 5, 9, 'Oferuję kursy programowania w zamian za opiekę nad moim psem podczas weekendów. Ktoś chętny?', 'Kursy za Opiekę nad Psem');

-- Zdjęcia Krajobrazowe za Rękodzieło
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-19, 1, CURRENT_TIMESTAMP, 5, 11, 'Wykonuję piękne zdjęcia krajobrazowe i chciałbym wymieniać je za rękodzielnicze dzieła. Ktoś zainteresowany?', 'Wymiana Zdjęć za Rękodzieło');

-- Sprzedaż Laptopa
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-20, 2, CURRENT_TIMESTAMP, 1, 13, 'Na sprzedaż: używany laptop HP, świetny do pracy biurowej. Cena do negocjacji. Więcej informacji prywatnie.', 'Sprzedaż Laptopa HP');

-- Oferuję Meble do Salonu
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-21, 1, CURRENT_TIMESTAMP, 1, 16, 'Oferuję eleganckie meble do salonu w bardzo dobrym stanie. Cena do uzgodnienia. Zainteresowanych proszę o kontakt.', 'Meble do Salonu na Sprzedaż');

-- Konsola do Gier na Sprzedaż
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-22, 2, CURRENT_TIMESTAMP, 1, 18, 'Do sprzedania: konsola do gier PlayStation 5, używana, w idealnym stanie. Cena do negocjacji. Kontakt telefoniczny.', 'Sprzedaż PlayStation 5');

-- Ubrania Damskie na Wyprzedaż
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-23, 3, CURRENT_TIMESTAMP, 1, 19, 'Wyprzedaż ubrań damskich! Różne rozmiary i style. Bardzo atrakcyjne ceny. Zapytaj o listę dostępnych rzeczy.', 'Ubrania Damskie na Wyprzedaż');

-- Książki Naukowe do Oddania
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-24, 5, CURRENT_TIMESTAMP, 1, 14, 'Oddam za darmo książki naukowe z dziedziny biologii. Idealne dla studentów. Odbiór osobisty w dowolnym terminie.', 'Oddam Książki Naukowe');

-- Aparat Fotograficzny na Sprzedaż
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-25, 4, CURRENT_TIMESTAMP, 1, 13, 'Na sprzedaż profesjonalny aparat fotograficzny Nikon. W komplecie torba i dodatkowe akcesoria. Cena do negocjacji.', 'Sprzedaż Aparatu Nikon');

-- Samochód na Sprzedaż
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-26, 4, CURRENT_TIMESTAMP, 1, 17, 'Sprzedam samochód marki Toyota Corolla. Rok produkcji: 2020, przebieg: 30 000 km. Cena: 50 000 PLN. Więcej informacji na priv.', 'Toyota Corolla na Sprzedaż');

-- Elektronarzędzia do Odsprzedaży
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-27, 6, CURRENT_TIMESTAMP, 1, 17, 'Mam do odsprzedania zestaw elektronarzędzi: wiertarka, szlifierka, wkrętarka. Stan idealny. Cena do uzgodnienia. Kontakt mailowy.', 'Elektronarzędzia na Sprzedaż');

-- Nauka Angielskiego
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-28, 7, CURRENT_TIMESTAMP, 2, 22, 'Rozpoczynam kurs nauki angielskiego dla początkujących. Zajęcia online przez Zoom. Zainteresowanych zapraszam do kontaktu!', 'Kurs Angielskiego Online');

-- Wymiana Językowa: Polski - Hiszpański
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-29, 6, CURRENT_TIMESTAMP, 2, 23, 'Szukam partnera do wymiany językowej: uczę polskiego, a chciałbym doskonalić hiszpański. Spotkania raz w tygodniu.', 'Wymiana Polski - Hiszpańskiego');

-- Kurs Niemieckiego dla Zaawansowanych
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-30, 5, CURRENT_TIMESTAMP, 2, 21, 'Zapraszam do udziału w kursie niemieckiego dla zaawansowanych. Zajęcia w grupie, terminy elastyczne. Kontakt telefoniczny.', 'Kurs Niemieckiego dla Zaawansowanych');

-- Nauka Francuskiego - Indywidualne Lekcje
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-31, 9, CURRENT_TIMESTAMP, 2, 25, 'Oferuję indywidualne lekcje z języka francuskiego. Dostosowane do poziomu i potrzeb ucznia. Kontakt mailowy dla zainteresowanych.', 'Nauka Francuskiego Indywidualnie');

-- Kurs Chińskiego dla Dzieci
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-32, 8, CURRENT_TIMESTAMP, 2, 26, 'Rozpoczynam kurs języka chińskiego dla dzieci. Zabawy, piosenki i nauka znaków. Zapisy otwarte! Więcej informacji na stronie internetowej.', 'Kurs Chińskiego dla Dzieci');

-- Wymiana Językowa: Włoski - Rosyjski
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-33, 4, CURRENT_TIMESTAMP, 2, 24, 'Szukam partnera do wymiany językowej: uczę włoskiego, a chciałbym doskonalić rosyjski. Spotkania raz w tygodniu.', 'Wymiana Włoski - Rosyjskiego');

-- Kurs Portugalskiego Online
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-34, 2, CURRENT_TIMESTAMP, 2, 26, 'Zapisz się na kurs nauki portugalskiego online. Lekcje dostępne przez platformę edukacyjną. Sprawdź harmonogram na stronie internetowej.', 'Kurs Portugalskiego Online');

-- Spotkanie Kawiarniane
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-35, 1, CURRENT_TIMESTAMP, 3, 28, 'Zapraszam na niezobowiązujące spotkanie w kawiarni w centrum miasta. Kawa, rozmowy i dobra atmosfera. Wszystkich chętnych serdecznie zapraszam!', 'Spotkanie Kawiarniane');

-- Spacer po Parku
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-36, 2, CURRENT_TIMESTAMP, 3, 28, 'Planuję spacer po parku w nadchodzący weekend. Jeśli ktoś chce się dołączyć, dajcie znać. Spotykamy się o godzinie 10 przed wejściem głównym.', 'Spacer po Parku');

-- Wieczór Gry Planszowej
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-37, 3, CURRENT_TIMESTAMP, 3, 27, 'Organizuję wieczór gry planszowej w moim mieszkaniu. Zainteresowani niech piszą, a podam adres. Przynieście swoje ulubione gry!', 'Gra Planszowa u Mnie');

-- Impreza Filmowa
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-38, 4, CURRENT_TIMESTAMP, 3, 29, 'Planuję małą imprezę filmową w piątek wieczorem. Głosujemy na film! Miejsce: mój salon. Do zobaczenia!', 'Impreza Filmowa');

-- Piknik nad Jeziorem
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-39, 5, CURRENT_TIMESTAMP, 3, 28, 'Zapraszam na piknik nad jeziorem w niedzielę. Każdy przynosi swoje jedzenie i napoje. Będzie grill i dobra zabawa!', 'Piknik nad Jeziorem');

-- Wieczór Karaoke
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-40, 6, CURRENT_TIMESTAMP, 3, 30, 'Organizuję wieczór karaoke w klubie muzycznym. Zapisy do udziału w karaoke przy wejściu. Startujemy o 20:00.', 'Wieczór Karaoke');

-- Spotkanie dla Miłośników Książek
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-41, 7, CURRENT_TIMESTAMP, 3, 32, 'Dla wszystkich miłośników książek: spotkanie dyskusyjne w bibliotece miejskiej. Przynieście swoje ulubione tytuły do omówienia.', 'Spotkanie Książkowe');

-- Wieczór Stand-up Comedy
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-42, 8, CURRENT_TIMESTAMP, 3, 30, 'Zapraszam na wieczór stand-up comedy. Lokal "Śmiechoholik". Rezerwacje miejsc pod numerem telefonu: XXX-XXX-XXX.', 'Stand-up Comedy Night');

-- Turniej Gier Planszowych
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-43, 9, CURRENT_TIMESTAMP, 3, 27, 'Organizujemy turniej gier planszowych w klubie gier "Rozgrywka". Zapisy otwarte do piątku. Liczba miejsc ograniczona!', 'Turniej Gier Planszowych');

-- Wieczór Muzyczny w Ogrodzie
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-44, 9, CURRENT_TIMESTAMP, 3, 31, 'Zapraszam na wieczór muzyczny w moim ogrodzie. Akustyka, grill, i dobra muzyka. Początek o 18:00. Przynosicie swoje leżaki!', 'Muzyczny Wieczór w Ogrodzie');

-- Hackathon Weekend
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-45, 1, '2023-12-04', 4, 38, 'Zapraszam na weekendowy hackathon! Temat: rozwijanie aplikacji mobilnej. Spotykamy się w biurze coworkingowym. Zapewniamy przekąski!', 'Hackathon Weekend');

-- Pair Programming Session
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-46, 2, '2023-12-05', 4, 35, 'Szukam partnera do sesji pair programmingu. Pracujemy nad projektem w języku Python. Doświadczenie niekonieczne, chęci mile widziane!', 'Pair Programming Python Project');

-- Code Review Evening
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-47, 3, '2023-12-06', 4, 37, 'Organizuję wieczór code review. Przynosimy swoje projekty, omawiamy kod, dzielimy się wiedzą. Lokalizacja: kawiarnia "Kodownik".', 'Code Review Evening');

-- Learn JavaScript Together
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-48, 4, '2023-12-07', 4, 34, 'Chętnie uczyłbym się JavaScript razem z kimś. Spotkania online, 2 razy w tygodniu. Zaczynamy od podstaw. Zgłoszenia mailowe.', 'Nauka JavaScript Razem');

-- Algorithm Study Group
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-49, 5, '2023-12-08', 4, 38, 'Tworzymy grupę do nauki algorytmów. Co tydzień rozwiązujemy wyzwania i omawiamy najlepsze podejścia. Dołącz do nas!', 'Grupa Nauki Algorytmów');

-- Workshop: Introduction to React
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-50, 6, '2023-12-09', 4, 34, 'Prowadzę warsztat wprowadzający do React. Dla osób z podstawową wiedzą z JavaScript. Miejsca ograniczone, zapisy mailowe.', 'Warsztat: Wprowadzenie do React');

-- Tech Talk: Microservices Architecture
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-51, 7, '2023-12-10', 4, 37, 'Zapraszam na prezentację na temat architektury mikroserwisów. Lokalizacja: sala konferencyjna w biurowcu "Kodopolis".', 'Tech Talk: Architektura Mikroserwisów');

-- C++ Coding Night
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-52, 8, '2023-12-11', 4, 39, 'Organizuję noc kodowania w C++. Przynosimy swoje projekty lub pracujemy nad wyzwaniem. Pizza i napoje zapewnione!', 'Noc Kodowania w C++');

-- Agile Development Workshop
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-53, 9, '2023-12-12', 4, 39, 'Warsztat na temat rozwoju projektów w metodyce Agile. Praktyczne ćwiczenia i case study. Miejsca limitowane, zapisy mailowe.', 'Warsztat: Rozwój w Metodyce Agile');

-- Open Source Contribution Day
INSERT INTO post(id, author_id, created_date, topic_id, subtopic_id, content, title)
VALUES (-54, 8, '2023-12-13', 4, 39, 'Dzień kontrybucji do projektów open source. Wybieramy projekty, rozwiązujemy issues, uczymy się razem. Spotykamy się w coworkingu.', 'Dzień Kontrybucji do Open Source');

INSERT INTO person_to_post(person_id, post_id) VALUES
(1, -1),
(1, -4),
(1, -5),
(2, -2),
(2, -6),
(2, -7),
(4, -13),
(5, -30),
(5, -31),
(8, -10),
(9, -25);

INSERT INTO person_to_subtopic(person_id, subtopic_id) VALUES
(1, 1),
(2, 4),
(2, 5),
(5, 12),
(6, 20),
(3, 3),
(7, 7),
(7, 14),
(9, 9);

INSERT INTO person_to_topic(person_id, topic_id) VALUES
(1, 1),
(2, 1),
(2, 2),
(3, 3),
(5, 4),
(6, 5),
(8, 4);
