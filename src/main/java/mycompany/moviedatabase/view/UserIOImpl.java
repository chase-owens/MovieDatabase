/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycompany.moviedatabase.view;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;
import mycompany.moviedatabase.dto.DataPersistenceError;
import mycompany.moviedatabase.dto.DateFormatException;
import org.springframework.stereotype.Component;

/**
 *
 * @author chaseowens
 */
@Component
public class UserIOImpl implements UserIO {

    final Scanner sc = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        double answer = sc.nextInt();
        return answer;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        boolean isIn = false;
        double num = 0;
        while (isIn == false) {
            System.out.println(prompt);
            num = sc.nextInt();
            if (num < min || num > max) {
                System.out.println("Number must be within range.");
            } else {
                isIn = true;
            }
        }
        return num;
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        float answer = sc.nextInt();
        return answer;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float num = 0;
        boolean isIn = false;
        while (!isIn) {
            System.out.println(prompt);
            num = sc.nextInt();
            if (num < min || num > max) {
                System.out.println("Must choose number within range.");
            } else {
                isIn = true;
            }
        }
        return num;
    }

    @Override
    public int readInt(String prompt) throws DataPersistenceError {
        System.out.println(prompt);
        int num;
        try {
            num = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            throw new DataPersistenceError("Just enter a who number without any commas", e);
        }

        return num;
    }

    @Override
    public int readInt(String prompt, int min, int max) throws DataPersistenceError {
        boolean isIn = false;
        int num = 0;
        while (isIn == false) {
            num = readInt(prompt);
            if (num < min || num > max) {
                System.out.println("Must be within range!");
            } else {
                isIn = true;
            }
        }
        return num;
    }

    @Override
    public long readLong(String prompt) {
        return 0;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        return 0;
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        String answer = sc.nextLine();
        return answer;
    }

    @Override
    public LocalDate readLocalDate(String prompt) throws DateFormatException {
        boolean validDate = false;
        LocalDate date = null;

        System.out.println(prompt);
        try {
            String stringDate = sc.nextLine();
            try {
                String[] stringDateArray = stringDate.split("-");
                if (Integer.parseInt(stringDateArray[0]) < 0000 || Integer.parseInt(stringDateArray[0]) > 3000 || Integer.parseInt(stringDateArray[1]) < 0 || Integer.parseInt(stringDateArray[1]) > 12 || Integer.parseInt(stringDateArray[2]) < 00 || Integer.parseInt(stringDateArray[1]) < 0 || Integer.parseInt(stringDateArray[1]) > 12 || Integer.parseInt(stringDateArray[2]) > 31) {
                    throw new DateFormatException("Please enter in yyyy-mm-dd format");
                }
            } catch (PatternSyntaxException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
                throw new DateFormatException("Please enter in yyyy-mm-dd format", e);
            }

            date = LocalDate.parse(stringDate);
            if (date == null) {
                throw new DateFormatException("Please enter in yyyy-mm-dd format");
            }
        } catch (DateTimeParseException | NullPointerException e) {
            throw new DateFormatException("Please enter in yyyy-mm-dd format");
        }

        return date;
    }
}
