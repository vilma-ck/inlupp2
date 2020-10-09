import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Vilma Couturier Kaijser
 * Date: 2020-10-09
 * Project: inlupp2
 * Copyright: MIT
 */
public class BestGymEver {

    protected List<Customer> customers = new ArrayList<>();
    protected List<Visit> visits = new ArrayList<>();

    public List<Customer> customersFromFile(String filePath) {

        try {
            Scanner file = new Scanner(new File(filePath));
            while (file.hasNext()) {
                String number = file.next();
                String name = file.nextLine();
                LocalDate date = LocalDate.parse(file.nextLine());
                customers.add(new Customer(number.substring(0, number.length()-1).trim(), name.trim(), date));

            }
        } catch (Exception FileNotFoundException) {
            System.out.println("Filen hittades inte.");
        }
        return customers;
    }

    public String findCustomerWithName(String name){
        boolean member = false;
        String customerStatus = null;
        for(Customer c: customers){
            if(c.getName().equals(name)){
                member = true;
                customerStatus = c.getMemberStatus();
            }
        }

        if(member){
            return customerStatus;
        } else
            return "obehörig";
    }

    public String findCustomer(String searchWord){
        customersFromFile("src/customers.txt");

        boolean member = false;
        String customerStatus = null;
        for(Customer c: customers){
            if(c.getName().equals(searchWord) || c.getPersonNumber().equals(searchWord)){
                member = true;
                customerStatus = c.getMemberStatus();
            }
        }

        if(member){
            return customerStatus;
        } else
            return "obehörig";
    }

    public void logVisit(Customer c){
        visits.add(new Visit(c));
    }

    public static void main(String[] args) {
        BestGymEver bge = new BestGymEver();
        bge.customersFromFile("src/customers.txt");

        System.out.println(bge.customers.get(2));

        System.out.println(bge.findCustomerWithName("okänd"));
        System.out.println(bge.findCustomerWithName("Alhambra Aromes"));

        System.out.println(bge.findCustomer("Alhambra Aromes"));
        System.out.println(bge.findCustomer("7608021234"));





    }

}
