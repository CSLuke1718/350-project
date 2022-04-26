package ambientclock;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CSLuk
 */
public class GetData {
    
    
    public static ArrayList UserInfo(String userName, String authToken) throws IOException {
        String fileName;
        fileName = GetGoals(userName, authToken);
        ArrayList goalList = getGoalDetails(fileName);
        return goalList;
    }
    
    /*
    
    */
    private static String GetGoals(String user, String auth) throws IOException{
        
        //create the url with the user name and authentication token
        String URL_STRING = "https://www.beeminder.com/api/v1/users/" + user + "/goals.json?auth_token=" + auth + "&filter=frontburner";
        
        //get the data from the website
        URL url = new URL(URL_STRING);
        URLConnection connection = url.openConnection();
        //System.out.println(connection);
        BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String data = rd.readLine();
        rd.close();
            
            
        //writing the data from the json to a text file
        File file = new File("userGoals.txt");
        FileWriter fileOut = new FileWriter(file);
        fileOut.write(data);
        fileOut.flush();
        fileOut.close();
            
        return file.getName();
    }
    
    
    private static ArrayList getGoalDetails(String fileName) throws FileNotFoundException, IOException {
        //open the file and create arraylist
        File file = new File(fileName);
        String line;
        ArrayList<GoalData> list = new ArrayList();
        BufferedReader br = new BufferedReader(new FileReader(file));
        int goalNum = -1;
        // go through the file, and if it is a goal name, date until due, and units due, add info to the arraylist
        while((line = br.readLine()) != null) {
            String arr[] = line.split("\"");
            for (int i = 0; i < arr.length; i++) {
                if(arr[i].contains("slug")) {
                    list.add(new GoalData());
                    goalNum++;
                    list.get(goalNum).setSlug(arr[i + 2]);
                }
                if(arr[i].contains("losedate")) {
                    long l = Long.parseLong(arr[i + 1].substring(1, arr[i + 1].length() - 2));
                    list.get(goalNum).setDueDate(l);
                }
                    
                if(arr[i].contains("safesum"))
                    list.get(goalNum).setSafetyText(arr[i + 2]);
                    
            }
        }
        /*
        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getSlug());
            System.out.println(list.get(i).getDueDate());
            System.out.println(list.get(i).getSafetyText());
        }
*/
        return list;
    }
    
            
            
}
