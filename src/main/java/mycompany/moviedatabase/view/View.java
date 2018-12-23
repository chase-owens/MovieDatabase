/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycompany.moviedatabase.view;

import java.util.List;
import mycompany.moviedatabase.dto.DVD;
import mycompany.moviedatabase.dto.DataPersistenceError;
import mycompany.moviedatabase.dto.DateFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author chaseowens
 */
@Component
public class View {

    @Autowired
    UserIO io;

    public View(UserIO io) {
        this.io = io;
    }

    public int displayMenu() throws DataPersistenceError {
        io.print("Select an option");
        String displayMessage = "1) Add Movie \t2) Remove Movie \t3)Edit Movie Rating \n4) List Movies \t5) Get movie info \t6) Movie Search \n7) Quit";
        return io.readInt(displayMessage, 1, 7);
    }

    public String setTitle() {
        return io.readString("Movie title: ");
    }

    public String getReleaseDate() throws DateFormatException {
        String date = null;
        boolean validDate = false;
        while (!validDate) {
            try {
                date = io.readLocalDate("Release date: ").toString();
                validDate = true;
            } catch (DateFormatException e) {
                displayErrorMessage(e.getMessage());
            }
        }

        return date;
    }

    public String getMPAArating() {
        return io.readString("MPAA rating: ");
    }

    public String getDirector() {
        return io.readString("Director's name:");
    }

    public String getStudio() {
        return io.readString("What studio was it filmed in? ");
    }

    public String getUserRating() {
        return io.readString("Custom Rating");
    }

    public void confirmMovieAdded() {
        io.print("The movie was added to your movie library");
    }

    public void exit() {
        io.print("Have fun not watching TV");
    }

    public void confirmMovieDeleted(String movieToRemove) {
        io.print(movieToRemove + " was remvoed from your movie Library");
    }

    public String getNewRating() {
        return io.readString("Enter the new rating: ");
    }

    public void displayTitles(List<DVD> movies) {
        movies.stream().forEach(dvd -> io.print(dvd.getTitle()));
    }

    public void displayInfo(DVD dvd) {
        io.print("Movie title: " + dvd.getTitle());
        io.print("Release date: " + dvd.getDate().toString());
        io.print("MPAA rating: " + dvd.getMPAArating());
        io.print("Director's name: " + dvd.getDirectorsName());
        io.print("Studio: " + dvd.getStudio());
        io.print("Your rating: " + dvd.getRating());
    }

    public String getQuery() {
        return io.readString("Enter search criteria");
    }

    public void printMoviesFound(String[] moviesFound) {
        for (String movie : moviesFound) {
            io.print(movie);
        }
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("===ERROR===");
        io.print(errorMsg);
    }

    public void noMoviesMessage() {
        io.print("There are no movies matching your query");
    }

}
