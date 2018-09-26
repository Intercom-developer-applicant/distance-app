package com.parsinglocations.app;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class App 
{
    public static void main( String[] args )
    {
        String fileLocation = args[0];
        String defCoordStr = args[1];
        Coordinates defaultCoordinates = new Coordinates(defCoordStr);

        // Read data from URL in runtime arguments
        FileDownloader customerData = new FileDownloader(fileLocation);
        String[] data = customerData.toString().split(System.lineSeparator());

        // Set up invitation list
        List<Customer> invited = new ArrayList<Customer>();
        Calculations calc = new Calculations();
        
        // Check distances to each customer, add to invitation list
        for(String line: data) {
            Customer cust = new Customer(line);
            if(calc.within100km(defaultCoordinates, cust.getLocation()))
                invited.add(cust);
        }
        
        // Sort invitation list
        Collections.sort(invited, new SortCustomer());
        
        // Send invitation list
        SendData.print(invited, "target\\Results.txt");
    }
}
