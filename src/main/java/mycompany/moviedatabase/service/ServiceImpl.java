/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycompany.moviedatabase.service;

import java.util.List;
import mycompany.moviedatabase.dao.DAO;
import mycompany.moviedatabase.dto.DVD;
import mycompany.moviedatabase.dto.MovieDAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author chaseowens
 */
@Component
public class ServiceImpl implements Service {

    @Autowired
    DAO dao;

    public ServiceImpl(DAO injectedDAO) {
        this.dao = injectedDAO;
    }

    @Override
    public DVD makeDVD(String title, String releaseDate, String MPAArating, String directorsName, String studio, String userRating) {
        return dao.makeDVD(title, releaseDate, MPAArating, directorsName, studio, userRating);
    }

    @Override
    public void addMovieToList(DVD newDVD) {
        if (newDVD != null) {
            dao.addMovieToList(newDVD);
        }
    }

    @Override
    public void marshallMovies(List<DVD> dvds) throws MovieDAOException {
        dao.marshallMovies(dvds);
    }

    @Override
    public void removeMovie(String movieToRemove) throws MovieDAOException {
        if (!checkIfMovieExists(movieToRemove)) {
            throw new MovieDAOException("That doesn't appear to be the name of the movie");
        }
        dao.removeMovie(movieToRemove);
    }

    @Override
    public void editRating(String title, String newRating) throws MovieDAOException {
        if (!checkIfMovieExists(title)) {
            dao.editRating(title, newRating);
        }
    }

    @Override
    public List<DVD> findMoviesMatching(String query) throws MovieDAOException {
        return dao.findMoviesMatching(query);
    }

    @Override
    public void loadMovies() throws MovieDAOException {
        dao.loadMovies();
    }

    @Override
    public List<DVD> getMovieList() throws MovieDAOException {
        if (dao.getMovieList().isEmpty()) {
            throw new MovieDAOException("There are no movies in the Library");
        }
        return dao.getMovieList();
    }

    @Override
    public DVD getMovie(String movie) throws MovieDAOException {
        if (!checkIfMovieExists(movie)) {
            throw new MovieDAOException("That movie is not in the library");
        }
        return dao.getMovie(movie);
    }

    @Override
    public boolean checkIfMovieExists(String movie) throws MovieDAOException {
       boolean exists = dao.getMovie(movie) == null ? false : true;
       return exists;
    }

}
