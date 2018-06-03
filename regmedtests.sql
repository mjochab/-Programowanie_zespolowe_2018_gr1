-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Czas generowania: 01 Cze 2018, 21:12
-- Wersja serwera: 5.7.21
-- Wersja PHP: 5.6.35

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `reg`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `addresses`
--

DROP TABLE IF EXISTS `addresses`;
CREATE TABLE IF NOT EXISTS `addresses` (
  `id_address` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(50) NOT NULL,
  `zip_code` varchar(6) NOT NULL,
  `street` varchar(50) NOT NULL,
  `number` varchar(50) NOT NULL,
  PRIMARY KEY (`id_address`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `addresses`
--

INSERT INTO `addresses` (`id_address`, `city`, `zip_code`, `street`, `number`) VALUES
(1, 'Rzeszow', '36-120', 'Powstancow warszawy', '13'),
(2, 'Wroclaw', '65-123', 'Przemyslowa', '56'),
(3, 'Warszawa', '12-345', 'Smolenska', '12'),
(4, 'Gdynia', '23-123', 'Kozaków', '123'),
(5, 'Krzeszow', '11-234', 'Pomiotow', '23'),
(6, 'Brzeszow', '12-123', 'Kozla', '32'),
(7, 'Kosow', '54-123', 'Kotwic', '7'),
(8, 'Poznan', '35-235', 'Ucisnionych', '45'),
(9, 'Grodzisk Wielkopolski', '23-234', 'Krzyzowcow', '5'),
(11, 'Grunwald', '45-234', 'Kaca', '6'),
(12, 'Warszawa', '56-345', 'Brzegi', '2');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `administrators`
--

DROP TABLE IF EXISTS `administrators`;
CREATE TABLE IF NOT EXISTS `administrators` (
  `id_administrator` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `PESEL` varchar(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone_number` varchar(9) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`id_administrator`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `administrators`
--

INSERT INTO `administrators` (`id_administrator`, `first_name`, `last_name`, `PESEL`, `email`, `phone_number`, `password`) VALUES
(1, 'Jan', 'Kowalski', '94568745125', 'jan@kowalski.pl', '568974523', 'cycki123');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `admissiondays`
--

DROP TABLE IF EXISTS `admissiondays`;
CREATE TABLE IF NOT EXISTS `admissiondays` (
  `id_admission_day` int(11) NOT NULL AUTO_INCREMENT,
  `id_doctor_working_day` int(11) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id_admission_day`),
  KEY `id_doctor_working_day` (`id_doctor_working_day`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `admissiondays`
--

INSERT INTO `admissiondays` (`id_admission_day`, `id_doctor_working_day`, `date`) VALUES
(2, 13, '2018-06-06'),
(3, 13, '2018-06-13'),
(4, 13, '2018-06-20'),
(5, 14, '2018-06-10'),
(6, 14, '2018-06-17'),
(7, 14, '2018-06-24'),
(8, 15, '2018-06-17'),
(9, 15, '2018-06-24'),
(10, 16, '2018-06-11'),
(11, 16, '2018-06-25'),
(12, 17, '2018-06-20'),
(13, 17, '2018-06-27'),
(14, 18, '2018-06-15'),
(15, 18, '2018-06-22'),
(16, 18, '2018-06-29'),
(17, 19, '2018-06-19'),
(18, 19, '2018-06-26'),
(19, 20, '2018-06-20'),
(20, 20, '2018-06-06'),
(21, 21, '2018-06-08'),
(22, 21, '2018-06-22'),
(23, 22, '2018-06-29'),
(24, 22, '2018-06-08'),
(25, 23, '2018-06-30'),
(26, 23, '2018-06-16');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `doctors`
--

DROP TABLE IF EXISTS `doctors`;
CREATE TABLE IF NOT EXISTS `doctors` (
  `id_doctor` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `PESEL` varchar(11) NOT NULL,
  `id_address` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone_number` varchar(9) NOT NULL,
  `password` varchar(100) NOT NULL,
  `id_specialization` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_doctor`),
  KEY `id_address` (`id_address`),
  KEY `id_specialization` (`id_specialization`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `doctors`
--

INSERT INTO `doctors` (`id_doctor`, `first_name`, `last_name`, `PESEL`, `id_address`, `email`, `phone_number`, `password`, `id_specialization`) VALUES
(12, 'Edward', 'Ącki', '94563215864', 2, 'ea@op.pl', '456987452', 'password', 20),
(13, 'Elzbieta', 'Krzywonog', '87456987531', 1, 'elakrzywenogi@mam.pl', '698486512', 'password', 15),
(14, 'Marian', 'Dlugonos', '35897456125', 2, 'duzedziury@op.pl', '569874532', 'password', 14),
(15, 'Miros?awa', '?opata', '68458974532', 1, 'ml@wp.pl', '456878987', 'password', 12),
(16, 'Anna', 'Nowak', '89745896515', 1, 'an@op.pl', '987654321', 'password', 21),
(17, 'Krzysztof', 'Bonifacy', '65121233473', 3, 'k@b.com', '566333444', 'Bonifacy', 21);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `doctorworkingdays`
--

DROP TABLE IF EXISTS `doctorworkingdays`;
CREATE TABLE IF NOT EXISTS `doctorworkingdays` (
  `id_doctor_working_day` int(11) NOT NULL AUTO_INCREMENT,
  `id_doctor` int(11) NOT NULL,
  `day` varchar(15) NOT NULL COMMENT 'day of the week',
  `hour_from` time NOT NULL,
  `hour_to` time NOT NULL,
  `hour_interval` varchar(2) NOT NULL,
  `validate_date` date DEFAULT NULL,
  PRIMARY KEY (`id_doctor_working_day`),
  KEY `id_doctor` (`id_doctor`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `doctorworkingdays`
--

INSERT INTO `doctorworkingdays` (`id_doctor_working_day`, `id_doctor`, `day`, `hour_from`, `hour_to`, `hour_interval`, `validate_date`) VALUES
(13, 12, 'Wednesday', '11:00:00', '15:00:00', '30', '2022-06-20'),
(14, 12, 'Monday', '10:00:00', '12:00:00', '30', '2022-06-20'),
(15, 13, 'Monday', '08:00:00', '16:00:00', '60', NULL),
(16, 13, 'Tuesday', '10:00:00', '18:00:00', '60', NULL),
(17, 14, 'Wednesday', '12:00:00', '18:00:00', '30', NULL),
(18, 14, 'Friday', '07:00:00', '13:00:00', '30', NULL),
(19, 16, 'Tuesday', '09:00:00', '16:00:00', '20', NULL),
(20, 16, 'Wednesday', '10:00:00', '20:00:00', '20', NULL),
(21, 16, 'Friday', '08:00:00', '14:00:00', '20', NULL),
(22, 17, 'Friday', '09:00:00', '12:00:00', '15', NULL),
(23, 17, 'Saturday', '10:00:00', '14:00:00', '15', NULL);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `files`
--

DROP TABLE IF EXISTS `files`;
CREATE TABLE IF NOT EXISTS `files` (
  `id_file` int(11) NOT NULL AUTO_INCREMENT,
  `id_patient` int(11) NOT NULL,
  `id_doctor` int(11) NOT NULL,
  `date` timestamp NOT NULL,
  `history` mediumtext CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id_file`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `files`
--

INSERT INTO `files` (`id_file`, `id_patient`, `id_doctor`, `date`, `history`) VALUES
(1, 1, 12, '2018-05-21 22:00:00', 'Patient complains of a headache.');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `patients`
--

DROP TABLE IF EXISTS `patients`;
CREATE TABLE IF NOT EXISTS `patients` (
  `id_patient` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `PESEL` varchar(11) NOT NULL,
  `id_address` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone_number` varchar(9) NOT NULL,
  `id_firstcontact_doctor` int(11) DEFAULT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`id_patient`),
  KEY `id_firstcontact_doctor` (`id_firstcontact_doctor`),
  KEY `id_address` (`id_address`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `patients`
--

INSERT INTO `patients` (`id_patient`, `first_name`, `last_name`, `PESEL`, `id_address`, `email`, `phone_number`, `id_firstcontact_doctor`, `password`) VALUES
(1, 'Sebastian', 'Krzak', '9123123879', 2, 'bogumila@krzak.com', '92389237', 16, 'password'),
(2, 'Krzysztof', 'Baca', '916273921', 1, 'krzysztof@baca.com', '261738273', 16, 'password'),
(3, 'Alojzy', 'Pietka', '95672611392', 4, 'a@p.com', '222555444', 17, 'Pi?tka'),
(4, 'Eugeniusz', 'Prawda', '76787655456', 5, 'e@p.com', '666555777', 16, 'Prawda'),
(5, 'Anastazja', 'Kanonowicz', '54122188782', 6, 'a@k.com', '666555444', 17, 'Kanonowicz'),
(6, 'Maciej', 'Kolczuk', '78786522612', 7, 'm@k.com', '777666555', 17, 'Kolczuk'),
(7, 'Eleonora', 'Kicak', '67071222915', 8, 'e@k.com', '777888565', 17, 'Kicak'),
(8, 'Bartosz', 'Ignaczyk', '12387656547', 9, 'b@i.com', '666555777', 17, 'Ignaczyk'),
(9, 'Krzysztof', 'Karyski', '67123987864', 11, 'k@k.com', '666555777', 17, 'Karyski'),
(10, 'Kasandra', 'Ra', '67865654710', 12, 'k@r.com', '222888555', 16, 'Ra');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `prescriptions`
--

DROP TABLE IF EXISTS `prescriptions`;
CREATE TABLE IF NOT EXISTS `prescriptions` (
  `id_prescription` int(11) NOT NULL AUTO_INCREMENT,
  `id_patient` int(11) NOT NULL,
  `id_doctor` int(11) NOT NULL,
  `date` timestamp NOT NULL,
  `content` varchar(65000) CHARACTER SET latin1 NOT NULL,
  `html_content` mediumtext COLLATE utf8_polish_ci NOT NULL,
  PRIMARY KEY (`id_prescription`),
  KEY `id_doctor` (`id_doctor`),
  KEY `id_patient` (`id_patient`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `refferals`
--

DROP TABLE IF EXISTS `refferals`;
CREATE TABLE IF NOT EXISTS `refferals` (
  `id_refferal` int(11) NOT NULL AUTO_INCREMENT,
  `id_patient` int(11) NOT NULL,
  `id_doctor` int(11) NOT NULL,
  `id_specialist` int(11) NOT NULL,
  `date` timestamp NOT NULL,
  `content` varchar(65000) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id_refferal`),
  KEY `id_doctor` (`id_doctor`),
  KEY `id_patient` (`id_patient`),
  KEY `id_specialist` (`id_specialist`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `singlevisits`
--

DROP TABLE IF EXISTS `singlevisits`;
CREATE TABLE IF NOT EXISTS `singlevisits` (
  `id_single_visit` int(11) NOT NULL AUTO_INCREMENT,
  `id_admission_day` int(11) NOT NULL,
  `visit_hour` time NOT NULL,
  `id_patient` int(11) NOT NULL,
  PRIMARY KEY (`id_single_visit`),
  KEY `id_patient` (`id_patient`),
  KEY `id_admission_day` (`id_admission_day`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `singlevisits`
--

INSERT INTO `singlevisits` (`id_single_visit`, `id_admission_day`, `visit_hour`, `id_patient`) VALUES
(1, 16, '07:00:00', 2),
(2, 14, '07:00:00', 9),
(3, 14, '11:00:00', 7),
(4, 20, '10:00:00', 7),
(5, 11, '14:00:00', 9),
(6, 3, '12:30:00', 1),
(7, 2, '14:00:00', 8),
(8, 2, '11:00:00', 6),
(9, 4, '14:30:00', 3),
(10, 24, '09:00:00', 7),
(11, 23, '10:00:00', 5),
(12, 26, '11:15:00', 6),
(13, 10, '13:00:00', 1),
(14, 20, '11:40:00', 10),
(15, 20, '10:40:00', 5),
(16, 20, '12:40:00', 10),
(17, 20, '14:00:00', 4),
(18, 20, '10:20:00', 6),
(19, 26, '10:45:00', 4),
(20, 26, '12:30:00', 1),
(21, 26, '13:15:00', 7),
(22, 26, '10:15:00', 1),
(23, 26, '13:45:00', 10),
(24, 21, '09:40:00', 2),
(25, 21, '11:00:00', 5),
(26, 21, '08:20:00', 1),
(27, 6, '11:30:00', 10),
(28, 7, '10:30:00', 5),
(29, 5, '10:00:00', 7),
(30, 5, '10:30:00', 5),
(31, 5, '11:00:00', 1),
(32, 5, '11:30:00', 6),
(33, 16, '11:00:00', 9),
(34, 25, '13:00:00', 10),
(35, 25, '13:15:00', 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `specializations`
--

DROP TABLE IF EXISTS `specializations`;
CREATE TABLE IF NOT EXISTS `specializations` (
  `id_specialization` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(300) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_specialization`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Zrzut danych tabeli `specializations`
--

INSERT INTO `specializations` (`id_specialization`, `name`) VALUES
(11, 'Urolog'),
(12, 'Geriatra'),
(13, 'Ginekolog'),
(14, 'Laryngolog'),
(15, 'Podolog'),
(16, 'Chorob Wewnętrznych'),
(17, 'Chirurg'),
(18, 'Immunolog'),
(19, 'Kardiolog'),
(20, 'Nefrolog'),
(21, 'First contact');

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `admissiondays`
--
ALTER TABLE `admissiondays`
  ADD CONSTRAINT `AdmissionDays_ibfk_1` FOREIGN KEY (`id_doctor_working_day`) REFERENCES `doctorworkingdays` (`id_doctor_working_day`);

--
-- Ograniczenia dla tabeli `doctors`
--
ALTER TABLE `doctors`
  ADD CONSTRAINT `Doctors_ibfk_2` FOREIGN KEY (`id_address`) REFERENCES `addresses` (`id_address`) ON DELETE CASCADE,
  ADD CONSTRAINT `doctors_ibfk_1` FOREIGN KEY (`id_specialization`) REFERENCES `specializations` (`id_specialization`) ON DELETE SET NULL;

--
-- Ograniczenia dla tabeli `doctorworkingdays`
--
ALTER TABLE `doctorworkingdays`
  ADD CONSTRAINT `DoctorWorkingDays_ibfk_1` FOREIGN KEY (`id_doctor`) REFERENCES `doctors` (`id_doctor`) ON DELETE CASCADE;

--
-- Ograniczenia dla tabeli `patients`
--
ALTER TABLE `patients`
  ADD CONSTRAINT `Patients_ibfk_3` FOREIGN KEY (`id_address`) REFERENCES `addresses` (`id_address`) ON DELETE CASCADE,
  ADD CONSTRAINT `patients_ibfk_1` FOREIGN KEY (`id_firstcontact_doctor`) REFERENCES `doctors` (`id_doctor`) ON DELETE SET NULL;

--
-- Ograniczenia dla tabeli `prescriptions`
--
ALTER TABLE `prescriptions`
  ADD CONSTRAINT `prescriptions_ibfk_1` FOREIGN KEY (`id_doctor`) REFERENCES `doctors` (`id_doctor`),
  ADD CONSTRAINT `prescriptions_ibfk_2` FOREIGN KEY (`id_patient`) REFERENCES `patients` (`id_patient`);

--
-- Ograniczenia dla tabeli `refferals`
--
ALTER TABLE `refferals`
  ADD CONSTRAINT `refferals_ibfk_1` FOREIGN KEY (`id_doctor`) REFERENCES `doctors` (`id_doctor`) ON DELETE NO ACTION,
  ADD CONSTRAINT `refferals_ibfk_2` FOREIGN KEY (`id_patient`) REFERENCES `patients` (`id_patient`),
  ADD CONSTRAINT `refferals_ibfk_3` FOREIGN KEY (`id_specialist`) REFERENCES `doctors` (`id_doctor`);

--
-- Ograniczenia dla tabeli `singlevisits`
--
ALTER TABLE `singlevisits`
  ADD CONSTRAINT `SingleVisits_ibfk_1` FOREIGN KEY (`id_patient`) REFERENCES `patients` (`id_patient`),
  ADD CONSTRAINT `SingleVisits_ibfk_2` FOREIGN KEY (`id_admission_day`) REFERENCES `admissiondays` (`id_admission_day`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
