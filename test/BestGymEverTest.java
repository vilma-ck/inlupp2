import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
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
       List<Customer> customers = bge.customersFromFile("test/customerstest.txt");

       assertEquals(customers.get(0).getName(), "Alhambra Aromes");
       assertFalse(customers.get(0).getName() == "Diamanda Djedi");
       assertEquals(customers.get(1).getName(),"Diamanda Djedi");
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
   public final void checkCustomerStatusTest(){
       // ge namn på kund, returnera kunds status:
       // nuvarande medlem, före detta medlem
       // "Alhambra Aromes", bge.customers.get(0)
       // "Diamanda Djedi", bge.customers.get(3)

       bge.customersFromFile("src/customers.txt");

       assertTrue(bge.checkCustomerStatus(bge.customers.get(0)) == "före detta medlem");
       assertTrue(bge.checkCustomerStatus(bge.customers.get(3)) == "nuvarande medlem");
       assertFalse(bge.checkCustomerStatus(bge.customers.get(0)) == "nuvarande medlem");

   }

   @Test
    public final void logVisitsTest(){
        // ge kund, lägg till kundvisit i lista

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
       // kolla att en användare anger giltigt namn eller personnummer
       // jobba mer med denna

       bge.test = true;
       String testString = "testar";

       assertEquals(bge.userInput("prompt", testString), "testar");
       assertNotEquals(bge.userInput("prompt", testString), "destar");
   }

   @Test
    public final void printVisitsToFile(){
        // listan med visits ska skrivas till fil
       // appenda till fil, inte skriva över
       // det testas genom att räkna antalet rader i filen


   }



}
