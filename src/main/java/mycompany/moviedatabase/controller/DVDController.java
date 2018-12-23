/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycompany.moviedatabase.controller;

import java.util.List;
import mycompany.moviedatabase.dto.DVD;
import mycompany.moviedatabase.dto.DataPersistenceError;
import mycompany.moviedatabase.dto.DateFormatException;
import mycompany.moviedatabase.dto.MovieDAOException;
import mycompany.moviedatabase.view.View;
import mycompany.moviedatabase.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author chaseowens
 */
@Controller
public class DVDController {

    @Autowired
    View view;
    @Autowired
    Service service;

    public DVDController(View injectedView, Service injectedService) {
        this.view = injectedView;
        this.service = injectedService;
    }

    public void run() throws DateFormatException {
        boolean keepGoing = true;
        while (keepGoing) {
            try {
                loadMovies();
                while (keepGoing) {
                    int selection = displayMenu();
                    switch (selection) {
                        case 1:
                            addMovie();
                            break;
                        case 2:
                            removeMovie();
                            break;
                        case 3:
                            editRating();
                            break;
                        case 4:
                            listAllMovies();
                            break;
                        case 5:
                            getMovieInfo();
                            break;
                        case 6:
                            movieSearch();
                            break;
                        case 7:
                            marshallMovies();
                            exitGracefully();
                            keepGoing = false;
                            break;
                        default:
                            break;
                    }
                }
            } catch (MovieDAOException | DataPersistenceError e) {
                displayErrorMessage(e);
            }
        }
    }

    private void loadMovies() throws MovieDAOException {
        service.loadMovies();
    }

    private int displayMenu() throws DataPersistenceError {
        return view.displayMenu();
    }

    private void addMovie() throws DateFormatException {
        // Get Movie Info
        String title = "", releaseDate = "", MPAArating = "", directorsName = "", studio = "", userRating = "";

        while (title.equals("")) {
            title = view.setTitle();
        }

        while (releaseDate.equals("")) {
            releaseDate = view.getReleaseDate();
        }

        while (MPAArating.equals("")) {
            MPAArating = view.getMPAArating();
        }

        while (directorsName.equals("")) {
            directorsName = view.getDirector();
        }

        while (studio.equals("")) {
            studio = view.getStudio();
        }

        while (userRating.equals("")) {
            userRating = view.getUserRating();
        }

        // Create movie
        DVD newDVD = service.makeDVD(title, releaseDate, MPAArating, directorsName, studio, userRating);

        // Add movie to file
        service.addMovieToList(newDVD);

        // Print confirmation method
        view.confirmMovieAdded();
    }

    private void removeMovie() throws MovieDAOException {

        //display movie titlesand get title of ovie to remove
        String movieToRemove = null;

        // Find remove movie
        try {
            movieToRemove = displayNameAndGetTitle();
            service.removeMovie(movieToRemove);
            // Print confirmation message
            view.confirmMovieDeleted(movieToRemove);
        } catch (MovieDAOException e) {
            displayErrorMessage(e);
        }

    }

    private void editRating() throws MovieDAOException {

        //display movie titles and get movie title of movie to edit
        String title = displayNameAndGetTitle();

        // get rating
        String newRating = view.getNewRating();

        // edit rating
        service.editRating(title, newRating);
    }

    private void listAllMovies() throws MovieDAOException {

        // get movie titles
        List<DVD> movies = service.getMovieList();

        // display movie titles
        view.displayTitles(movies);
    }

    private void getMovieInfo() throws MovieDAOException {
        // get movie title
        String movie = displayNameAndGetTitle();

        // retrieve information
        DVD dvd = service.getMovie(movie);

        // display info
        view.displayInfo(dvd);
    }

    private void movieSearch() throws MovieDAOException {
        // get query
        String query = view.getQuery();

        // find movies
        List<DVD> moviesFound = service.findMoviesMatching(query);

        // display movie
        if (moviesFound.isEmpty()) {
            view.noMoviesMessage();
        } else {
            view.displayTitles(moviesFound);
        }

    }

    private void exitGracefully() {
        view.exit();
    }

    private void displayErrorMessage(Exception e) {
        view.displayErrorMessage(e.getMessage());
    }

    private void marshallMovies() throws MovieDAOException {
        // get list of movies
        List<DVD> dvds = service.getMovieList();

        // marshall list of movies
        service.marshallMovies(dvds);
    }

    public String displayNameAndGetTitle() throws MovieDAOException {
        //display movie titles
        List<DVD> movies = service.getMovieList();
        view.displayTitles(movies);

        // Get movie Title
        String movie = view.setTitle();
        service.checkIfMovieExists(movie);

        return movie;
    }

}
