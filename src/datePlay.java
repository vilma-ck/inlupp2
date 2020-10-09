import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Created by Vilma Couturier Kaijser
 * Date: 2020-10-08
 * Project: inlupp2
 * Copyright: MIT
 */
public class datePlay {

    public static void main(String[] args) throws FileNotFoundException {



        Customer c = new Customer("7603021234", "Alhambra Aromes",  LocalDate.parse("2019-07-01"));
        Customer c2 = new Customer("8104021234", "Bear Belle",  LocalDate.parse("2019-12-08"));

        // testa ett visit-objekt
        Visit v = new Visit(c);

        System.out.println(v.toString());



    }/*
    public static void file(){
        Scanner file = new Scanner(new File("src/customers.txt"));

        while(file.hasNext()){
            String number = file.next();
            String name = file.nextLine();
            LocalDate date = LocalDate.parse(file.nextLine());

            System.out.println("number " + number);
            System.out.println("name " +  name);
            System.out.println("date " + date);
        }

    } */

    public static void torsdag(){
        LocalDate localDate = LocalDate.now();

        System.out.println(localDate);

        String date = "2019-07-01";

        LocalDate dateFromFile = LocalDate.parse(date);

        System.out.println(dateFromFile);


        Customer c = new Customer("7603021234", "Alhambra Aromes",  LocalDate.parse("2019-07-01"));
        Customer c2 = new Customer("8104021234", "Bear Belle",  LocalDate.parse("2019-12-01"));

        c.activeMember();
        c2.activeMember();

    }
}
