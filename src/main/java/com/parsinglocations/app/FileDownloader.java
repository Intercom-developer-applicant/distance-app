package com.parsinglocations.app;

import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileDownloader 
{
    private StringBuilder fileContents;

    FileDownloader(String loc) {
        BufferedReader bf = null;
        fileContents = new StringBuilder();
        try {
            // Parse String to URL
            URL u = new URL(loc);
            bf = new BufferedReader(new InputStreamReader(u.openStream()));

            // Read contents line by line
            for( String line = bf.readLine(); line != null; line = bf.readLine() )
                fileContents.append(line).append(System.lineSeparator());
        
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (bf != null) bf.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public String toString() {
        return fileContents.toString();
    }
}