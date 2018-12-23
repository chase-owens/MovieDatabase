/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycompany.moviedatabase.dto;

/**
 *
 * @author chaseowens
 */
public class MovieDAOException extends Exception {
    public MovieDAOException(String message, Throwable cause) {
        super(message, cause); 
    }
    public MovieDAOException(String message) {
        super(message);
    }
    
}
