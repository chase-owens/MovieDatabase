/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycompany.moviedatabase.dao;

import java.util.List;
import mycompany.moviedatabase.dto.DVD;
import mycompany.moviedatabase.dto.MovieDAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author chaseowens
 */
@Repository
public class DAOImplSQL implements DAO {

    @Autowired
    private JdbcTemplate jdbc;

    //List<DVD> movieLibrary = new ArrayList<>();
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
        jdbc.update("INSERT INTO dvdLibrary(title, releaseDate, MPAARating, director, studio, userRating) VALUES (?,?,?,?,?,?)",
                newDVD.getTitle(), newDVD.getDate().toString(), newDVD.getMPAArating(), newDVD.getDirectorsName(), newDVD.getStudio(), newDVD.getRating());
    }

    @Override
    public List<DVD> getMovieList() throws MovieDAOException {
        List<DVD> dvds;
        try {
            dvds = jdbc.query("SELECT * FROM dvdLibrary", new DVDMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new MovieDAOException("Could not find that in the database", e);
        }

        return dvds;
    }

    @Override
    public DVD getMovie(String movie) throws MovieDAOException {
        DVD dvd;

        try {
            dvd = jdbc.queryForObject("SELECT * FROM dvdLibrary WHERE title = ?", new DVDMapper(), movie);
        } catch (EmptyResultDataAccessException e) {
            throw new MovieDAOException("Could not find that in the database", e);
        }

        return dvd;
    }

    @Override
    public List<DVD> findMoviesMatching(String query) throws MovieDAOException {
        List<DVD> matches;
        
        try {
            matches = jdbc.query("SELECT * FROM dvdLibrary WHERE title LIKE ?", new DVDMapper(), query + "%");
        } catch (EmptyResultDataAccessException e) {
            throw new MovieDAOException("Could not find that in the database", e);
        }
        return matches;
    }

    @Override
    public void editRating(String title, String newRating) throws MovieDAOException {
        
        try {
            jdbc.update("UPDATE dvdLibrary SET userRating = ? WHERE title = ?", newRating, title);
        } catch (EmptyResultDataAccessException e) {
            throw new MovieDAOException("Could not find that in the database", e);
        }
        
        
    }

    @Override
    public void removeMovie(String movieToRemove) throws MovieDAOException {
        
        try {
            jdbc.update("DELETE FROM dvdLibrary WHERE title = ?", movieToRemove);
        } catch (EmptyResultDataAccessException e) {
            throw new MovieDAOException("Could not find that in the database", e);
        }
        
        
    }

    @Override
    public void deleteList() {
        //movieLibrary.clear();
    }

    @Override
    public void marshallMovies(List<DVD> dvds) throws MovieDAOException {
        // Not using that anymore
    }

    @Override
    public void loadMovies() throws MovieDAOException {
        //movieLibrary = jdbc.query("SELECT * FROM dvdLibrary", new DVDMapper());

    }
}
