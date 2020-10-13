import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public boolean test = false;
    protected List<Customer> customers = new ArrayList<>();
    protected List<Visit> visits = new ArrayList<>();

    public List<Customer> customersFromFile(String filePath) {
        Path path = Paths.get(filePath);
        String tempLine;
        try(BufferedReader bufferInput = Files.newBufferedReader(path)) {
            while ((tempLine = bufferInput.readLine()) != null) {
                String[] personInfo = tempLine.split(",");
                LocalDate date = LocalDate.parse(bufferInput.readLine());
                customers.add(new Customer(personInfo[0].trim(), personInfo[1].trim(), date));
            }
        }  catch (FileNotFoundException e){
            System.out.println("Filen hittades ej.");
            e.printStackTrace();
        } catch (IOException e){
            System.out.println("Filen kunde ej öppnas.");
            e.printStackTrace();
        } catch (Exception e){
            System.out.println("Något gick fel.");
            e.printStackTrace();
        }
        return customers;
    }


    public Customer findCustomer(String searchTerm){
        Customer foundCustomer = null;
        for(Customer c: customers){
            if(c.getName().equalsIgnoreCase(searchTerm) || c.getPersonNumber().equals(searchTerm)){
                foundCustomer = c;
            }
        }
        return foundCustomer;
    }


    public void logVisit(Customer c){
        visits.add(new Visit(c));
    }

    public String userInput(String userPrompt, String testParameter){
        Scanner sc;
        if(test){
            sc = new Scanner(testParameter);
        } else {
            sc = new Scanner(System.in);
        }

        while(true){
            try {
                System.out.println(userPrompt);
                return sc.nextLine();
            } catch (Exception e){
                System.out.println("Något gick fel.");
                e.printStackTrace();
                sc.next();
            }
        }
    }

    public void printVisitorsToFile(String filePath){
        Path path = Paths.get(filePath);
        try(PrintWriter printer = new PrintWriter(new FileWriter(filePath, true));){
            for(Visit v: visits){
                printer.print(v + "\n");
            }
        } catch (FileNotFoundException e){
            System.out.println("Filen hittades ej.");
            e.printStackTrace();
        } catch (IOException e){
            System.out.println("Filen kunde ej öppnas.");
            e.printStackTrace();
        } catch (Exception e){
            System.out.println("Något gick fel.");
        }
    }


    public void userLoop(){

        while(true){
            String searchTerm = userInput("Skriv namn eller personnummer: ", null);
            if(searchTerm.equalsIgnoreCase("avsluta")){
                break;
            }

            Customer foundCustomer = findCustomer(searchTerm);

            if(foundCustomer!=null && foundCustomer.activeMember()){
                System.out.println("Kunden är nuvarande medlem, besöket loggas.");
                logVisit(foundCustomer);
            } else if(foundCustomer!=null && !foundCustomer.activeMember()){
                System.out.println("Kunden är ej aktiv medlem.");
            } else if (foundCustomer == null) {
                System.out.println("Detta är inte en kund på Best Gymn Ever.");
            }
        }
    }

    public void program(){
        customersFromFile("src/customers.txt");
        userLoop();
        printVisitorsToFile("src/visits.txt");
        System.out.println("Dagens besök sparas till fil.");
    }

    public static void main(String[] args) {
        BestGymEver bge = new BestGymEver();
        bge.program();

    }

}
