import java.time.LocalDate;

/**
 * Created by Vilma Couturier Kaijser
 * Date: 2020-10-09
 * Project: inlupp2
 * Copyright: MIT
 */
public class Visit {

    private IforPTInfo customer;
    private LocalDate date;

    public Visit(IforPTInfo customer){
        this.customer = customer;
        this.date = LocalDate.now();
    }

    public IforPTInfo getCustomer(){
        return customer;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "customer=" + customer.getName() + ", person number=" + customer.getPersonNumber()+ ", date of visit=" + date +
                '}';
    }
}
