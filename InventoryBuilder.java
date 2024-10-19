import java.util.ArrayList;// arraylist required for unbounded lists

import java.io.*;
import java.util.Arrays;
import java.io.FileWriter;
import java.io.IOException;

import java.io.BufferedReader;
import java.io.FileReader;

public class InventoryBuilder {
    /* declare private objects
            - user holds new user's name
            - unbounded lists for materials uploaded
     */
    private String user;
    private ArrayList<String> items;
    private ArrayList<Integer> itemCount;
    private ArrayList<Boolean> share;

    // class constructor for objects
    InventoryBuilder(String user, ArrayList <String> items, ArrayList<Integer> itemCount, ArrayList<Boolean> share) {
        this.user = user;
        this.items = items;
        this.itemCount = itemCount;
        this.share = share;
    }

    // setters and getters to retrieve and modify private objects
    public void setUser(String user){
        this.user = user;
    }

    public void setItems(ArrayList<String> items) {
        this.items = items;
    }

    public void setItemCount(ArrayList<Integer> itemCount){
        this.itemCount = itemCount;
    }

    public void setShare(ArrayList<Boolean> share) {
        this.share = share;
    }

    public String getUser(){
        return this.user;
    }

    public ArrayList<String> getItems() {
        return this.items;
    }

    public ArrayList<Integer> getItemCount(){
        return this.itemCount;
    }

    public ArrayList<Boolean> getShare() {
        return this.share;
    }

    // pull user data from database and display a table with their resources and measured amount
    public void displayUserItems() {
        // call write to act similarly to setter
        writeUserList();

        // hold read output in method to modify for formatting
        String full = readUserList();
        // corrects discrepancy in array to text data storage to allow proper formatting
        full = full.replace("true" + this.user.charAt(0), "true][" + this.user.charAt(0));
        full = full.replace("false" + this.user.charAt(0), "false][" + this.user.charAt(0));

        // determine number of iterations through list
        int count;
        count = ((readUserList().length() - readUserList().replace("true", "").length())/4) + ((readUserList().length() - readUserList().replace("false", "").length())/5);

        // declare variables to utilize private objects
        String name;
        String items;
        String iCount;
        String shared;

        // declare functional strings for data modification
        String collapseString;
        String collapseExtra;
        String s1 = "";
        String s2 = "";

        // initial print formatting
        System.out.println(getUser() + "\n");
        System.out.printf("%10s %2s %10s %2s %10s %2s %10s%n", "name", "|", "item","|", "count", "|", "sharing");
        System.out.println("\t===============================================");

        // restrict to one set of data at a time
        collapseString = full.substring((full.indexOf("[")+1), (full.indexOf("]")));
        // hold additional data aside for next iterations
        collapseExtra = full.substring(full.indexOf("]")+1);

        /*
            use series of string assignments and reassignments to
            pull and hold data in different locations.

            1. find first data point (name),
            2. hold in s1 with the rest of the data in s2
            3. assign s1 as s2
            4. repeat down the line until s1 and s2 are the final data point (to share)

         */
        s1 = collapseString.replace(" ", "");
        s2 = s1;
        s1 = s1.substring(s1.indexOf("[") + 1,s1.indexOf(","));
        s2 = s2.substring(s2.indexOf(",") + 1);
        name = s1;

        s1 = s2;

        s1 = s1.substring(0,s1.indexOf(",") );
        s2 = s2.substring(s2.indexOf(",") + 1);
        items = s1;

        s1 =s2;

        s1 = s1.substring(0,s1.indexOf(",") );
        s2 = s2.substring(s2.indexOf(",") + 1);
        iCount = s1;

        s1 =s2;

        shared = s1;

        System.out.printf("%10s %2s %10s %2s %10s %2s %10s%n", name, "|", items, "|", iCount, "|", shared);


        /*
            once first iteration is complete, manually increase stepper variable
            continue through the same process until each entry is accounted for
         */
        for(int i = 1; i < count; i++) {
            collapseString = collapseExtra.substring(collapseExtra.indexOf("["), collapseExtra.indexOf("]")+1);
            collapseExtra = collapseExtra.substring(collapseExtra.indexOf("]")+1);

            s1 = collapseString.replace(" ", "");
            s2 = s1;
            s1 = s1.substring(s1.indexOf("[") + 1,s1.indexOf(","));
            s2 = s2.substring(s2.indexOf(",") + 1);
            name = s1;

            s1 = s2;

            s1 = s1.substring(0,s1.indexOf(",") );
            s2 = s2.substring(s2.indexOf(",") + 1);
            items = s1;

            s1 =s2;

            s1 = s1.substring(0,s1.indexOf(",") );
            s2 = s2.substring(s2.indexOf(",") + 1);
            iCount = s1;

            s1 = s2;

            shared = s1.substring(0,s1.indexOf("]"));

            System.out.printf("%10s %2s %10s %2s %10s %2s %10s%n", name, "|", items, "|", iCount, "|", shared);
        }


    }

    // pull data from database -- acts as a getter method
    public static String readUserList() {
        String csvFile = "commUnity_Plan_data.csv"; // for local, use .csv path provided
        String line;
        String items = "";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                // Split the line by comma
                String[] data = line.split(",");

                // Process the data
                items = Arrays.toString(data).toString(); // convert array to string for return
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return items;
    }

    // enters data into database -- acts as setter method
    public void writeUserList() {
        String csvFile = "commUnity_Plan_data.csv"; // for local, use .csv file included
        String current = readUserList();
        String update = "";
        StringBuilder list = new StringBuilder();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            for (int i = 0; i < getItems().size(); i++) {
                String user = getUser();
                String items = getItems().get(i).toString();
                String iCount = getItemCount().get(i).toString();
                String shared = getShare().get(i).toString();

                String [] newline = {user, items, iCount, shared};
                update.concat(String.join(",", newline).toString());
                writer.write(String.join(",", newline));

            }
            current.concat(update);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
