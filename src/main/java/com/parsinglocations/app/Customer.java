package com.parsinglocations.app;

import org.json.JSONObject;
import org.json.JSONException;

public class Customer 
{
    private String name;
    private int id;
    private String latitude;
    private String longitude;

    Customer(String str) {
        try {
            JSONObject obj = new JSONObject(str);

            if(obj.has("name")) name = obj.getString("name");
            if(obj.has("user_id")) id = obj.getInt("user_id");
            if(obj.has("latitude")) latitude = obj.getString("latitude");
            if(obj.has("longitude")) longitude = obj.getString("longitude");

        } catch(JSONException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Coordinates getLocation() {
        String location = latitude + "," + longitude;
        return new Coordinates(location);
    }

    public String toString() {
        return id + ": " + name;
    }
}