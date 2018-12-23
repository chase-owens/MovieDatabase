/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycompany.moviedatabase.dao;

import java.util.List;
import mycompany.moviedatabase.dto.DVD;
import mycompany.moviedatabase.dto.MovieDAOException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author chaseowens
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class DAOTest {
    
    @Autowired
    DAO dao;
    
    DVD tester;
    

    public DAOTest() {
        this.tester = new DVD("a", "1999-01-01", "a", "a", "a", "a");
    }

    @BeforeClass
    public static void setUpClass() {
        
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws MovieDAOException{
        List<DVD> movieLibrary = dao.getMovieList();
        for (DVD dvd : movieLibrary) {
            dao.removeMovie(dvd.getTitle());
        }
    }

    @After
    public void tearDown() throws MovieDAOException {
        
    }

    /**
     * Test of makeDVD method, of class DAO.
     */
    @Test
    public void testMakeDVD() {
        DVD newMovie = dao.makeDVD("a", "1999-01-01", "a", "a", "a", "a");
        assertEquals(newMovie, tester);
    }

    /**
     * Test of addMovieToList method, of class DAO.
     * @throws mycompany.moviedatabase.dto.MovieDAOException
     */
    @Test
    public void testAddMovieToList() throws MovieDAOException{
        assertEquals(0, dao.getMovieList().size());
        dao.addMovieToList(tester);
        assertEquals(1, dao.getMovieList().size());
    }

    /**
     * Test of removeMovie method, of class DAO.
     * @throws mycompany.moviedatabase.dto.MovieDAOException
     */
    @Test
    public void testRemoveMovie() throws MovieDAOException{
        dao.addMovieToList(tester);
        assertEquals(1, dao.getMovieList().size());
        dao.removeMovie(tester.getTitle());
        assertEquals(0, dao.getMovieList().size());
    }

    /**
     * Test of editRating method, of class DAO.
     * @throws mycompany.moviedatabase.dto.MovieDAOException
     */
    @Test
    public void testEditRating() throws MovieDAOException{
        String ratingBefore = tester.getRating();
        dao.addMovieToList(tester);
        dao.editRating(tester.getTitle(), ratingBefore + ratingBefore);
        DVD dvd = dao.getMovie(tester.getTitle());
        assertNotEquals(ratingBefore, dvd.getRating());
    }

    /**
     * Test of findMoviesMatching method, of class DAO.
     * @throws mycompany.moviedatabase.dto.MovieDAOException
     */
    @Test
    public void testFindMoviesMatching() throws MovieDAOException{
        assertEquals(0, dao.findMoviesMatching("").size());
        dao.addMovieToList(tester);
        assertEquals(1, dao.findMoviesMatching(tester.getTitle()).size());

        DVD tester2 = dao.makeDVD(tester.getTitle() + tester.getTitle(), tester.getDate().toString(), tester.getMPAArating(), tester.getDirectorsName(), tester.getStudio(), tester.getRating());
        dao.addMovieToList(tester2);
        assertEquals(2, dao.findMoviesMatching(tester.getTitle()).size());
    }
}
