import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Vilma Couturier Kaijser
 * Date: 2020-10-08
 * Project: inlupp2
 * Copyright: MIT
 */
public class BestGymEverTest {

    BestGymEver bge = new BestGymEver();

    @Test
    public final void newCustomerTest(){
        String personNumber = "7603021234";
        String name = "Alhambra Aromes";
        LocalDate payedMembership = LocalDate.parse("2019-07-01");
        Customer c = new Customer(personNumber, name, payedMembership);
    }

    @Test
    public final void activeMemberTest(){
        Customer c = new Customer("7603021234", "Alhambra Aromes",  LocalDate.parse("2019-07-01"));
        Customer c2 = new Customer("8104021234", "Bear Belle",  LocalDate.parse("2019-12-08"));

        assertFalse(c.activeMember());
        assertTrue(c2.activeMember());
    }

   @Test
   public final void customersFromFileTest(){
        // skicka länk till fil
       // skapa Customer-objekt från fil
       // returnera lista med Customer-objekt
       //List<Customer> customers = bge.customersFromFile("test/customerstest.txt");

       bge.customersFromFile("test/customerstest.txt");

       assertEquals(bge.customers.get(0).getName(), "Alhambra Aromes");
       assertNotEquals(bge.customers.get(0).getName(), "Diamanda Djedi");
       assertEquals(bge.customers.get(1).getName(),"Diamanda Djedi");

   }

   @Test
    public final void findCustomerTest(){
        // ge namn på kund eller personnummer
       // returnera kundobjekt

       bge.customersFromFile("src/customers.txt");

       assertEquals(bge.findCustomer("Alhambra Aromes"), bge.customers.get(0));
       assertNotEquals(bge.findCustomer("Alhambra Aromes"), bge.customers.get(1));
       assertEquals(bge.findCustomer("Diamanda Djedi"), bge.customers.get(3));
       assertEquals(bge.findCustomer("0000000000"), null);
       assertEquals(bge.findCustomer("7603021234"), bge.customers.get(0));

   }


   @Test
    public final void logVisitsTest(){

       Customer c = new Customer("7603021234", "Alhambra Aromes",  LocalDate.parse("2019-07-01"));
       Customer c2 = new Customer("8104021234", "Bear Belle",  LocalDate.parse("2019-12-08"));

        bge.logVisit(c);
        bge.logVisit(c2);

        assertTrue(bge.visits.get(0).getCustomer().getName() == "Alhambra Aromes");
        assertFalse(bge.visits.get(1).getCustomer().getName() == "Alhambra Aromes");
        assertTrue(bge.visits.get(1).getCustomer().getName() == "Bear Belle");

   }

   @Test
   public final void userInputTest(){

       bge.test = true;
       String testString = "testar";

       assertEquals(bge.userInput("prompt", testString), "testar");
       assertNotEquals(bge.userInput("prompt", testString), "testar igen");
   }

   public final int countLines(String filePath){
        int lines = 0;
        try(BufferedReader r = new BufferedReader(new FileReader(filePath));){
            while(r.readLine() != null) {
                lines++;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return lines;
   }

   public final void removeFile(String filePath){
        try{
            Files.deleteIfExists(Paths.get(filePath));
        } catch (IOException e){
            e.printStackTrace();
        }
   }

   @Test
    public final void printVisitsToFileTest(){
       Customer c = new Customer("7603021234", "Alhambra Aromes",  LocalDate.parse("2019-07-01"));
       Customer c2 = new Customer("8104021234", "Bear Belle",  LocalDate.parse("2019-12-08"));
       bge.visits.add(new Visit(c));
       bge.visits.add(new Visit(c));

       String filePath = "test/visitsTest.txt";

       // ta bort förra filen iom append
       removeFile(filePath);

       // skapa filen
       bge.printVisitorsToFile(filePath);

       // räkna
       assertEquals(countLines(filePath), 2);
       assertNotEquals(countLines(filePath), 4);


   }



}
