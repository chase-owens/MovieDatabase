/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycompany.moviedatabase.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import mycompany.moviedatabase.dto.DVD;
import mycompany.moviedatabase.dto.MovieDAOException;

/**
 *
 * @author chaseowens
 */
public class DAOImpl implements DAO {

    List<DVD> movieLibrary = new ArrayList<>();
    public static final String MOVIE_DATABASE = "movieLibrary.txt";
    public static final String DELIMETER = "::";
    PrintWriter write;

    /**
     *
     * @param title
     * @param releaseDate
     * @param MPAArating
     * @param directorsName
     * @param studio
     * @param userRating
     * @return DVD
     */
    @Override
    public DVD makeDVD(String title, String releaseDate, String MPAArating, String directorsName, String studio, String userRating) {
        DVD dvd = new DVD(title, releaseDate, MPAArating, directorsName, studio, userRating);
        return dvd;
    }
    
    @Override
    public void addMovieToList(DVD newDVD) {
        movieLibrary.add(newDVD);
    }

    @Override
    public List<DVD> getMovieList() {
        return movieLibrary;
    }

    @Override
    public DVD getMovie(String movieTitle) {
        int movieIndex = getMovieIndex(movieTitle);
        DVD dvd = movieLibrary.get(movieIndex);
        return dvd;
    }
    
    @Override
    public List<DVD> findMoviesMatching(String query) {
        List<DVD> matches = null;
        for (DVD movie : movieLibrary) {
            String title = movie.getTitle();
            if (title.contains(query)) {
                matches.add(movie);
            }
        }
        return matches;
    }


    @Override
    public void editRating(String title, String newRating) {
        for (DVD movie : movieLibrary) {
            String movieName = movie.getTitle();
            if (movieName.equals(title)) {
                movie.setRating(newRating);
                break;
            }
        }
    }
    
    @Override
    public void removeMovie(String movieToRemove) {
        int i = getMovieIndex(movieToRemove);
        movieLibrary.remove(i);
        if (movieLibrary.isEmpty()) {
            movieLibrary.clear();
        }
    }

    @Override
    public void deleteList() {
        movieLibrary.clear();
    }

    public int getMovieIndex(String movieTitle) {
        int index = 0;
        for (int i = 0; i < movieLibrary.size(); i++) {
            DVD dvd = movieLibrary.get(i);
            String movieName = dvd.getTitle();
            if (movieName.equals(movieTitle)) {
                index = i;
            }
        }
        
        return index;
        
        
    }
    
    @Override
    public void loadMovies() throws MovieDAOException {

        // get fileReader to read file
        Scanner read;

        try {
            read = new Scanner(
                    new BufferedReader(
                            new FileReader(MOVIE_DATABASE)));
        } catch (FileNotFoundException e) {
            throw new MovieDAOException("Couldn't access file", e);
        }

        // get lines from MOVIE_DATABASE and parse it
        while (read.hasNextLine()) {
            String currentLine = read.nextLine();
            String[] currentTokens;

            currentTokens = currentLine.split(DELIMETER);

            // create DVD with parsed line
            DVD dvd = makeDVD(currentTokens[0], currentTokens[1], currentTokens[2], currentTokens[3], currentTokens[4], currentTokens[5]);

            // add DVD to movieLibrary
            movieLibrary.add(dvd);
        }
        read.close();

    }
    
    @Override
    public void marshallMovies(List<DVD> dvds) throws MovieDAOException {
        try {
            write = new PrintWriter(new FileWriter(MOVIE_DATABASE));
        } catch (IOException e) {
            throw new MovieDAOException("Could not add movie to database..", e);
        }
        for (DVD dvd : dvds) {

            write.println(
                    dvd.getTitle() + DELIMETER
                    + dvd.getDate() + DELIMETER
                    + dvd.getMPAArating() + DELIMETER
                    + dvd.getDirectorsName() + DELIMETER
                    + dvd.getStudio() + DELIMETER
                    + dvd.getRating()
            );
            write.flush();

        }
        write.close();
    }

}
