DROP DATABASE IF EXISTS TestMovieLibraryDB;

CREATE DATABASE IF NOT EXISTS TestMovieLibraryDB;

USE TestMovieLibraryDB;

CREATE TABLE dvdLibrary (
	title VARCHAR(50) NOT NULL PRIMARY KEY,
    releaseDate DATE,
    MPAArating VARCHAR(20),
    director VARCHAR(20),
    studio VARCHAR(50),
    userRating TEXT
);