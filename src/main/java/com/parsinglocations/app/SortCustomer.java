package com.parsinglocations.app;

import java.util.Comparator;

class SortCustomer implements Comparator<Customer>
{
    // Sort based on user ID ascending
    public int compare(Customer a, Customer b) {
        return a.getId() - b.getId();
    }
}