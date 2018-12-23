/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycompany.moviedatabase.dao;

import java.util.List;
import mycompany.moviedatabase.dto.DVD;
import mycompany.moviedatabase.dto.MovieDAOException;

/**
 *
 * @author chaseowens
 */
public interface DAO {

    DVD makeDVD(String title, String releaseDate, String MPAArating, String directorsName, String studio, String userRating);

    public void addMovieToList(DVD newDVD);
    
    public void marshallMovies(List<DVD> dvds) throws MovieDAOException;

    public void removeMovie(String movieToRemove) throws MovieDAOException;

    public void editRating(String title, String newRating) throws MovieDAOException;

    public List<DVD> findMoviesMatching(String query) throws MovieDAOException;

    public void loadMovies() throws MovieDAOException;

    public List<DVD> getMovieList() throws MovieDAOException;
    
    public void deleteList();

    public DVD getMovie(String movie) throws MovieDAOException;
    
}
