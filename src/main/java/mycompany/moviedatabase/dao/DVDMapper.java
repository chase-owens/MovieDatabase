/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycompany.moviedatabase.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import mycompany.moviedatabase.dto.DVD;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author chaseowens
 */
public class DVDMapper implements RowMapper<DVD> {

    @Override
    public DVD mapRow(ResultSet rs, int index) throws SQLException {
        
        
        
        //Build and Return DVD
        DVD dvd = new DVD();
        dvd.setTitle(rs.getString("title"));
        dvd.setDate(rs.getDate("releaseDate").toLocalDate());
        dvd.setMPAArating(rs.getString("MPAARating"));
        dvd.setDirectorsName(rs.getString("director"));
        dvd.setStudio(rs.getString("studio"));
        dvd.setRating(rs.getString("userRating"));
        
        return dvd;
    }
    
}
