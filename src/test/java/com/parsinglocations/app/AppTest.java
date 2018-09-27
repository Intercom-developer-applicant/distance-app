package com.parsinglocations.app;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import org.junit.Assert;

public class AppTest
{
    private String testCust = "{\"latitude\": \"52.986375\",\"user_id\": {id},\"name\": \"{name}\",\"longitude\": \"-6.043701\"}";

    @Test
    public void testFileDownloads()
    {
        String loc = "https://www.w3.org/TR/PNG/iso_8859-1.txt";
        FileDownloader fileContents = new FileDownloader(loc);
        String contentStr = fileContents.toString();
        
        Assert.assertTrue(contentStr.startsWith("The following are the graphical"));
        Assert.assertTrue(contentStr.contains("DIAERESIS"));
    }

    @Test
    public void testCalcs()
    {
        Calculations calc = new Calculations();

        Coordinates achillIsland = new Coordinates("53.963909,-10.003031");
        Coordinates crokePark = new Coordinates("53.3564485742,-6.2507989968");
        Coordinates clareIsland = new Coordinates("53.802163458,-9.986162722");
        Coordinates fakeIsland = new Coordinates("");
        
        // Expected to be within 100km
        Assert.assertTrue(calc.within100km(achillIsland, clareIsland));

        // Expected to be more than 100km apart
        Assert.assertFalse(calc.within100km(achillIsland, crokePark));
        
        // Bad data should be converted to zeroes and computation continue
        Assert.assertFalse(calc.within100km(achillIsland, fakeIsland));
    }

    @Test
    public void testCoords()
    {
        Coordinates control = new Coordinates("53.963909,-10.003031");
        Coordinates badData = new Coordinates("three,five");
        Coordinates noData = new Coordinates("");
        Coordinates badFormatting = new Coordinates("bad formatting");

        // Should return a double for correctly formatted data
        Assert.assertEquals(control.getLat(), 0.941847, 0.00001);
        
        // Should return zero for bad data
        Assert.assertEquals(badData.getLat(), 0.000000, 0.00001);
        Assert.assertEquals(noData.getLong(), 0.000000, 0.00001);
        Assert.assertEquals(badFormatting.getLat(), 0.000000, 0.00001);
    }

    @Test
    public void testCustomer()
    {
        Customer control = new Customer(testCust.replace("{id}", "4").replace("{name}", "Clare Byrne"));
        Customer badId = new Customer(testCust.replace("{id}", "\"id\"").replace("{name}", "Clare Byrne"));
        Customer missingData = new Customer("{\"latitude\": \"52.986375\",\"longitude\": \"-6.043701\"}");
        Customer noData = new Customer("{}");
        Customer notJSON = new Customer("plain string, no JSON");

        // Returns information for correctly formatted customer
        Assert.assertEquals(control.getName(), "Clare Byrne");

        // Returns zeroes and nulls for badly formatted customer
        Assert.assertEquals(badId.getId(), 0);
        Assert.assertEquals(missingData.toString(), "0: null");
        Assert.assertEquals(noData.getLocation().getLong(), 0.000000, 0.00001);
        Assert.assertNull(notJSON.getName());
    }

    @Test
    public void testSorting()
    {
        Customer a = new Customer(testCust.replace("{id}", "4").replace("{name}", "Clare Byrne"));
        Customer b = new Customer(testCust.replace("{id}", "1").replace("{name}", "Sandra Kade"));
        Customer c = new Customer(testCust.replace("{id}", "21").replace("{name}", "John Moore"));

        List<Customer> custList = new ArrayList<Customer>();
        custList.add(a);
        custList.add(b);
        custList.add(c);
        
        Collections.sort(custList, new SortCustomer());

        List<Customer> cntrl = new ArrayList<Customer>();
        cntrl.add(a);
        cntrl.add(b);
        cntrl.add(c);

        List<Customer> sorted = new ArrayList<Customer>();
        sorted.add(b);
        sorted.add(a);
        sorted.add(c);

        Assert.assertNotEquals(custList, cntrl);
        Assert.assertEquals(custList, sorted);
    }

    @Test
    public void testSavingData()
    {
        List<String> data = new ArrayList<String>();
        data.add("Cat");
        data.add("Dog");
        data.add("Horse");
        data.add("Monkey");
  
        String fileLoc = "target\\test-output\\saving.txt";

        SendData.print(data, fileLoc);

        // Confirm that the file exists
        // Then clear the file for subsequent runs
        File file = new File(fileLoc);
        Assert.assertTrue(file.exists());
        file.delete();
    }
}
