-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Czas generowania: 17 Kwi 2018, 20:13
-- Wersja serwera: 10.1.24-MariaDB-cll-lve
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
-- Baza danych: `hubert78_regmed`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Addresses`
--

CREATE TABLE `Addresses` (
  `id_address` int(11) NOT NULL,
  `city` varchar(50) NOT NULL,
  `zip_code` varchar(6) NOT NULL,
  `street` varchar(50) NOT NULL,
  `number` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Administrators`
--

CREATE TABLE `Administrators` (
  `id_administrator` int(11) NOT NULL,
  `id_global` int(11) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `PESEL` varchar(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone_number` varchar(9) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `Administrators`
--

INSERT INTO `Administrators` (`id_administrator`, `id_global`, `first_name`, `last_name`, `PESEL`, `email`, `phone_number`, `password`) VALUES
(1, 1, 'Jan', 'Kowalski', '94568745125', 'jan@kowalski.pl', '568974523', 'cycki123');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `AdmissionDays`
--

CREATE TABLE `AdmissionDays` (
  `id_admission_day` int(11) NOT NULL,
  `id_doctor_working_day` int(11) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Doctors`
--

CREATE TABLE `Doctors` (
  `id_doctor` int(11) NOT NULL,
  `id_global` int(11) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `PESEL` varchar(11) NOT NULL,
  `id_address` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone_number` varchar(9) NOT NULL,
  `password` varchar(100) NOT NULL,
  `id_specialization` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `DoctorWorkingDays`
--

CREATE TABLE `DoctorWorkingDays` (
  `id_doctor_working_day` int(11) NOT NULL,
  `id_doctor` int(11) NOT NULL,
  `day` varchar(15) NOT NULL COMMENT 'day of the week',
  `hour_from` time NOT NULL,
  `hour_to` time NOT NULL,
  `hour_interval` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `IdFactory`
--

CREATE TABLE `IdFactory` (
  `id_global` int(11) NOT NULL,
  `id_entity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `IdFactory`
--

INSERT INTO `IdFactory` (`id_global`, `id_entity`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Patients`
--

CREATE TABLE `Patients` (
  `id_patient` int(11) NOT NULL,
  `id_global` int(11) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `PESEL` varchar(11) NOT NULL,
  `id_address` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone_number` varchar(9) NOT NULL,
  `id_firstcontact_doctor` int(11) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `SingleVisits`
--

CREATE TABLE `SingleVisits` (
  `id_single_visit` int(11) NOT NULL,
  `id_admission_day` int(11) NOT NULL,
  `visit_hour` time NOT NULL,
  `id_patient` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `Addresses`
--
ALTER TABLE `Addresses`
  ADD PRIMARY KEY (`id_address`);

--
-- Indeksy dla tabeli `Administrators`
--
ALTER TABLE `Administrators`
  ADD PRIMARY KEY (`id_administrator`),
  ADD UNIQUE KEY `id_global` (`id_global`);

--
-- Indeksy dla tabeli `AdmissionDays`
--
ALTER TABLE `AdmissionDays`
  ADD PRIMARY KEY (`id_admission_day`),
  ADD KEY `id_doctor_working_day` (`id_doctor_working_day`);

--
-- Indeksy dla tabeli `Doctors`
--
ALTER TABLE `Doctors`
  ADD PRIMARY KEY (`id_doctor`),
  ADD UNIQUE KEY `id_global` (`id_global`),
  ADD KEY `id_address` (`id_address`);

--
-- Indeksy dla tabeli `DoctorWorkingDays`
--
ALTER TABLE `DoctorWorkingDays`
  ADD PRIMARY KEY (`id_doctor_working_day`),
  ADD KEY `id_doctor` (`id_doctor`);

--
-- Indeksy dla tabeli `IdFactory`
--
ALTER TABLE `IdFactory`
  ADD PRIMARY KEY (`id_global`),
  ADD UNIQUE KEY `id_global` (`id_global`);

--
-- Indeksy dla tabeli `Patients`
--
ALTER TABLE `Patients`
  ADD PRIMARY KEY (`id_patient`),
  ADD UNIQUE KEY `id_global` (`id_global`),
  ADD KEY `id_firstcontact_doctor` (`id_firstcontact_doctor`),
  ADD KEY `id_address` (`id_address`);

--
-- Indeksy dla tabeli `SingleVisits`
--
ALTER TABLE `SingleVisits`
  ADD PRIMARY KEY (`id_single_visit`),
  ADD KEY `id_patient` (`id_patient`),
  ADD KEY `id_admission_day` (`id_admission_day`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `Addresses`
--
ALTER TABLE `Addresses`
  MODIFY `id_address` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `Administrators`
--
ALTER TABLE `Administrators`
  MODIFY `id_administrator` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT dla tabeli `AdmissionDays`
--
ALTER TABLE `AdmissionDays`
  MODIFY `id_admission_day` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `Doctors`
--
ALTER TABLE `Doctors`
  MODIFY `id_doctor` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `DoctorWorkingDays`
--
ALTER TABLE `DoctorWorkingDays`
  MODIFY `id_doctor_working_day` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `IdFactory`
--
ALTER TABLE `IdFactory`
  MODIFY `id_global` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT dla tabeli `Patients`
--
ALTER TABLE `Patients`
  MODIFY `id_patient` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `SingleVisits`
--
ALTER TABLE `SingleVisits`
  MODIFY `id_single_visit` int(11) NOT NULL AUTO_INCREMENT;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `Administrators`
--
ALTER TABLE `Administrators`
  ADD CONSTRAINT `Administrators_ibfk_1` FOREIGN KEY (`id_global`) REFERENCES `IdFactory` (`id_global`);

--
-- Ograniczenia dla tabeli `AdmissionDays`
--
ALTER TABLE `AdmissionDays`
  ADD CONSTRAINT `AdmissionDays_ibfk_1` FOREIGN KEY (`id_doctor_working_day`) REFERENCES `DoctorWorkingDays` (`id_doctor_working_day`);

--
-- Ograniczenia dla tabeli `Doctors`
--
ALTER TABLE `Doctors`
  ADD CONSTRAINT `Doctors_ibfk_1` FOREIGN KEY (`id_global`) REFERENCES `IdFactory` (`id_global`),
  ADD CONSTRAINT `Doctors_ibfk_2` FOREIGN KEY (`id_address`) REFERENCES `Addresses` (`id_address`);

--
-- Ograniczenia dla tabeli `DoctorWorkingDays`
--
ALTER TABLE `DoctorWorkingDays`
  ADD CONSTRAINT `DoctorWorkingDays_ibfk_1` FOREIGN KEY (`id_doctor`) REFERENCES `Doctors` (`id_doctor`);

--
-- Ograniczenia dla tabeli `Patients`
--
ALTER TABLE `Patients`
  ADD CONSTRAINT `Patients_ibfk_1` FOREIGN KEY (`id_global`) REFERENCES `IdFactory` (`id_global`),
  ADD CONSTRAINT `Patients_ibfk_2` FOREIGN KEY (`id_firstcontact_doctor`) REFERENCES `Doctors` (`id_doctor`),
  ADD CONSTRAINT `Patients_ibfk_3` FOREIGN KEY (`id_address`) REFERENCES `Addresses` (`id_address`);

--
-- Ograniczenia dla tabeli `SingleVisits`
--
ALTER TABLE `SingleVisits`
  ADD CONSTRAINT `SingleVisits_ibfk_1` FOREIGN KEY (`id_patient`) REFERENCES `Patients` (`id_patient`),
  ADD CONSTRAINT `SingleVisits_ibfk_2` FOREIGN KEY (`id_admission_day`) REFERENCES `AdmissionDays` (`id_admission_day`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
