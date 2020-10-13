import java.time.LocalDate;

/**
 * Created by Vilma Couturier Kaijser
 * Date: 2020-10-08
 * Project: inlupp2
 * Copyright: MIT
 */
public class Customer implements IforPTInfo{

    private String personNumber;
    private String name;
    private LocalDate payedMembership;

    public Customer(String personNumber, String name, LocalDate payedMembership){
        this.personNumber = personNumber;
        this.name = name;
        this.payedMembership = payedMembership;
    }

    public String getPersonNumber() {
        return personNumber;
    }

    public String getName() {
        return name;
    }

    public LocalDate getPayedMembership() {
        return payedMembership;
    }

    public boolean activeMember(){
        LocalDate tempDate = payedMembership.plusYears(1);
        if(tempDate.compareTo(LocalDate.now()) >= 0){
            return true;
        } else
            return false;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "personNumber='" + personNumber + '\'' +
                ", name='" + name + '\'' +
                ", payedMembership=" + payedMembership +
                ", active member? " + activeMember() +
                '}';
    }
}
