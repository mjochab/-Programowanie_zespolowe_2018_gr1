-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Czas generowania: 01 Maj 2018, 08:57
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
  `id_specialization` int(11) NOT NULL,
  PRIMARY KEY (`id_doctor`),
  KEY `id_address` (`id_address`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `doctors`
--

INSERT INTO `doctors` (`id_doctor`, `first_name`, `last_name`, `PESEL`, `id_address`, `email`, `phone_number`, `password`, `id_specialization`) VALUES
(1, 'Eugeniusz', 'Zaborski', '92384547281', 1, 'eugeniusz@zaborski.pl', '665445645', 'password', 1),
(2, 'Joanna', 'Mana', '78281732398', 1, 'joanna@mana.pl', '662224813', 'password', 2);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `id_firstcontact_doctor` int(11) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`id_patient`),
  KEY `id_firstcontact_doctor` (`id_firstcontact_doctor`),
  KEY `id_address` (`id_address`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `patients`
--

INSERT INTO `patients` (`id_patient`, `first_name`, `last_name`, `PESEL`, `id_address`, `email`, `phone_number`, `id_firstcontact_doctor`, `password`) VALUES
(1, 'Sebastian', 'Krzak', '9123123879', 2, 'bogumila@krzak.com', '92389237', 1, 'password'),
(2, 'Krzysztof', 'Baca', '916273921', 1, 'krzysztof@baca.com', '261738273', 1, 'password');

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
  ADD CONSTRAINT `Doctors_ibfk_2` FOREIGN KEY (`id_address`) REFERENCES `addresses` (`id_address`);

--
-- Ograniczenia dla tabeli `doctorworkingdays`
--
ALTER TABLE `doctorworkingdays`
  ADD CONSTRAINT `DoctorWorkingDays_ibfk_1` FOREIGN KEY (`id_doctor`) REFERENCES `doctors` (`id_doctor`);

--
-- Ograniczenia dla tabeli `patients`
--
ALTER TABLE `patients`
  ADD CONSTRAINT `Patients_ibfk_2` FOREIGN KEY (`id_firstcontact_doctor`) REFERENCES `doctors` (`id_doctor`),
  ADD CONSTRAINT `Patients_ibfk_3` FOREIGN KEY (`id_address`) REFERENCES `addresses` (`id_address`);

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
