
import mycompany.moviedatabase.controller.DVDController;
import mycompany.moviedatabase.dto.DateFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author chaseowens
 */
@ComponentScan("mycompany.moviedatabase")
@SpringBootApplication
public class App implements CommandLineRunner{
    @Autowired
    private DVDController controller;
    
    public App() {
        
    }
    
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    /**
     *
     * @param args
     * @throws mycompany.moviedatabase.dto.DateFormatException
     */
    @Override
    public void run(String... args) throws DateFormatException {
    // Call run
    controller.run ();  
    }

    
}


