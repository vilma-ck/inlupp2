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
        // ge namn på kund, returnera kunds status:
       // nuvarande medlem, före detta medlem, obehörig
       // metoden måste ha åtkomst till lista med medlemmar, listan måste startas
       //  customersFromFile("src/customers.txt");

       // find customers with names
       assertTrue(bge.findCustomer("okänd") == "obehörig");
       assertEquals(bge.findCustomer("okänd"), "obehörig");
       assertFalse(bge.findCustomer("Alhambra Aromes") == "nuvarande medlem");
       assertEquals(bge.findCustomer("Alhambra Aromes"), "före detta medlem");
       assertEquals(bge.findCustomer("Diamanda Djedi"), "nuvarande medlem");

       // find customers with number
       assertTrue(bge.findCustomer("0000000000") == "obehörig");
       assertEquals(bge.findCustomer("okänd"), "obehörig");
       assertFalse(bge.findCustomer("Alhambra Aromes") == "nuvarande medlem");

   }

   @Test
    public final void logVisitsTest(){
        // ge kund, lägg till kundvisit i lista
       // listan e protected för att nås här

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
        // jaha vad ska vi göra här då?
       // kolla att en användare anger giltigt namn eller personnummer
       // jobba mer med dessa texter

       bge.test = true;
       String testString = "t";

       assertEquals(bge.userInput("prompt", testString), "t");
       assertFalse(bge.userInput("prompt", testString) == "f");


   }



}
