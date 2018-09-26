package com.parsinglocations.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SendData 
{
    public static void print(List<?> data, String destination) {
        // Print out invitation list to file and terminal
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(destination, "UTF-8");
            String value;
            for(int i = 0; i < data.size(); i++) {
                value = data.get(i).toString();
                writer.println(value);
                System.out.println(value);
            }
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if(writer != null) writer.close();
        }
    }
}