-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Czas generowania: 22 Maj 2018, 09:02
-- Wersja serwera: 5.7.19
-- Wersja PHP: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `regmedtests`
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `addresses`
--

INSERT INTO `addresses` (`id_address`, `city`, `zip_code`, `street`, `number`) VALUES
(1, 'Rzeszów', '36-120', 'Powstańców warszawy', '13'),
(2, 'Wrocław', '65-123', 'Przemysłowa', '56');

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
(1, 'Jan', 'Kówalski', '94568745125', 'jan@kowalski.pl', '568974523', 'cycki123');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `doctors`
--

INSERT INTO `doctors` (`id_doctor`, `first_name`, `last_name`, `PESEL`, `id_address`, `email`, `phone_number`, `password`, `id_specialization`) VALUES
(12, 'Edward', 'Ącki', '94563215864', 2, 'ea@op.pl', '456987452', 'password', 20),
(13, 'Elżbieta', 'Krzywonóg', '87456987531', 1, 'elakrzywenogi@mam.pl', '698486512', 'password', 15),
(14, 'Marian', 'Długonos', '35897456125', 2, 'duzedziury@op.pl', '569874532', 'password', 14),
(15, 'Mirosława', 'Łopata', '68458974532', 1, 'ml@wp.pl', '456878987', 'password', 12),
(16, 'Anna', 'Nowak', '89745896515', 1, 'an@op.pl', '987654321', 'password', NULL);

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
  PRIMARY KEY (`id_doctor_working_day`),
  KEY `id_doctor` (`id_doctor`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `doctorworkingdays`
--

INSERT INTO `doctorworkingdays` (`id_doctor_working_day`, `id_doctor`, `day`, `hour_from`, `hour_to`, `hour_interval`) VALUES
(9, 12, 'tuesday', '10:00:00', '12:00:00', '30'),
(10, 12, 'monday', '10:00:00', '12:00:00', '30'),
(11, 12, 'tuesday', '11:00:00', '12:00:00', '30'),
(12, 12, 'monday', '10:00:00', '12:00:00', '30');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `files`
--

DROP TABLE IF EXISTS `files`;
CREATE TABLE IF NOT EXISTS `files` (
  `id_file` int(11) NOT NULL AUTO_INCREMENT,
  `id_visit` int(11) NOT NULL,
  `date` timestamp NOT NULL,
  `history` mediumtext CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id_file`),
  KEY `id_visit` (`id_visit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `patients`
--

INSERT INTO `patients` (`id_patient`, `first_name`, `last_name`, `PESEL`, `id_address`, `email`, `phone_number`, `id_firstcontact_doctor`, `password`) VALUES
(1, 'Sebastian', 'Krzak', '9123123879', 2, 'bogumila@krzak.com', '92389237', 16, 'password'),
(2, 'Krzysztof', 'Baca', '916273921', 1, 'krzysztof@baca.com', '261738273', 16, 'password');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `specializations`
--

DROP TABLE IF EXISTS `specializations`;
CREATE TABLE IF NOT EXISTS `specializations` (
  `id_specialization` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(300) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_specialization`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Zrzut danych tabeli `specializations`
--

INSERT INTO `specializations` (`id_specialization`, `name`) VALUES
(11, 'Urolog'),
(12, 'Geriatra'),
(13, 'Ginekolog'),
(14, 'Laryngolog'),
(15, 'Podolog'),
(16, 'Chorób Wewnętrznych'),
(17, 'Chirurg'),
(18, 'Immunolog'),
(19, 'Kardiolog'),
(20, 'Nefrolog');

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
-- Ograniczenia dla tabeli `files`
--
ALTER TABLE `files`
  ADD CONSTRAINT `files_ibfk_1` FOREIGN KEY (`id_visit`) REFERENCES `singlevisits` (`id_single_visit`) ON DELETE NO ACTION;

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
