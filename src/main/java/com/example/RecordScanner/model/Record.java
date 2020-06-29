package com.example.RecordScanner.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;


@Data
@NoArgsConstructor
@Configuration
@EnableSwagger2

@Repository

public class Record {

    //Sets the latitude and longitude boundaries for the bounding box
    private static Double LONG_MIN = 0.12803 ;
    private static Double LONG_MAX = 1.2410;
    private static Double NEG_LONG = -LONG_MIN;
    private static Double LAT_MIN = 50.66801;
    private static Double LAT_MAX = 52.34801;

    //Sets the radians and earths radius to be used later in calculations
    private static Double LonRadLat = Math.toRadians(51.5074);
    private static Double LonRadLon = Math.toRadians(0.1278);
    private static Double EarthRadius = 3956.077;


    //Creates the array list that stores the values read from the urls
    public static ArrayList<JSONObject> longFiltered = new ArrayList();




//Creates string builder to read all the records
private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
        sb.append((char) cp);
    }
    return sb.toString();
}
//Reads the Url for data and returns it as a JSON Array
    public static JSONArray readJsonFromUrl(String GET_URL) throws IOException, JSONException {
        InputStream is = new URL(GET_URL).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String jsonText = readAll(rd);
            JSONArray json = new JSONArray(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

@Autowired
//Reads the Json arrays and filters to find the specified people
    public static ArrayList<JSONObject>  findUsers(String url, String cityUrl) throws IOException, JSONException {
    //clears the array list each time so that duplicates do not appear with outputing the array
    longFiltered.clear();

    //ensures that no NullPointerException occurs by making the array empty
    longFiltered.isEmpty();

//Creates the two arrays from the URLs specified
        JSONArray users = readJsonFromUrl(url);
        JSONArray cityUsers = readJsonFromUrl(cityUrl);

        //For loop to iterate through each object and determine if it sits within the boundry box
        for (int i =0; i < users.length(); i++){
            JSONObject data = users.getJSONObject(i);
            double LAT = data.getDouble("latitude");
            double LONG = data.getDouble("longitude");

//First takes latitude
            if (LAT_MIN < LAT && LAT < LAT_MAX){

//then checks longitude to create a square area that the user could be in. Known as a bounding box
                if ( LONG > NEG_LONG && LONG < LONG_MAX) {
                    JSONObject another = users.getJSONObject(i);

                    //This will check if the users is within a 50 mile radius from the geographical centre of London
                    //Gets the longitude and latitude of the refined user array
                    double lat = another.getDouble("latitude");
                    double lon = another.getDouble("longitude");

                    //Converts the latitude and longitude to radians
                    Double latRad = Math.toRadians(lat);
                    Double lonRad =Math.toRadians(lon);
                    Double raddifLat = latRad - LonRadLat;
                    Double raddifLon = lonRad - LonRadLon;

                    //Finds the radian calculation ratio distance between the two points
                    double radDistance = Math.pow(Math.sin(raddifLat / 2), 2)
                            + Math.cos(LonRadLat) * Math.cos(lonRad)
                            * Math.pow(Math.sin(raddifLon / 2),2);
                    double conv = 2 * Math.asin(Math.sqrt(radDistance));

                    //Multiplies the ratio to the earth radius to get an answer in miles
                    Double distanceFromLondon = conv * EarthRadius;

                    //If the distance is less that 50 miles they will be added to the array
                    if (distanceFromLondon<50) {
                        //provided they match the statement the users will be added to the array list
                        longFiltered.add(another);
                    }
                }


            }

        }
        //does the same for city users
    //does not require if statement as the url will specify london only
       for (int i = 0; i < cityUsers.length(); i++){
            JSONObject cityData = cityUsers.getJSONObject(i);

               longFiltered.add(cityData);
        }
       //adds the people to the array
        JSONObject[] json = new JSONObject[longFiltered.size()];
        longFiltered.toArray(json);
        //USED FOR TESTING PURPOSES TO CHECK IF THE DATA HAS BEEN RECEIVED CORRECTLY
    //    System.out.println("FILTERED JSON LONG + LAT " + longFiltered + "\n" + NEG_LONG);

    //returns array
        return longFiltered;
    }

}
