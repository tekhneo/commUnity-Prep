import java.util.ArrayList; // arraylist required for unbounded lists
import java.util.Scanner;

public class commUnityPrep {
    // use method to build new table of user resources multiple times 
    // will be used to update database of resources
    public static void pullUserResources() {
        // initialize new Scanner
        Scanner input = new Scanner(System.in);

        // declare variables to communicate with InventoryBuilder class
        String user = "";
        ArrayList<String> items = new ArrayList<>();
        ArrayList<Integer> itemCount = new ArrayList<>();
        ArrayList<Boolean> share = new ArrayList<>();

        // create new object
        InventoryBuilder build1 = new InventoryBuilder(user, items, itemCount, share);

        // declare variables to retrieve user input (minimizes clutter within ArrayList add calls)
        String entered;
        int enteredCount;

        // retrieve user name from Scanner
        System.out.print("Enter your name: ");
        user = input.nextLine();

        // open conditions for loop to build ArrayList
        boolean cont = true;

        while(cont) {
            // enter name of item to add item, enter cancel to terminate loop
            System.out.print("Scan Item (type in name) or Press Cancel (type cancel): ");
            entered = input.nextLine().toLowerCase().trim();
            if (entered.equals("cancel")) {
                System.out.println("Process terminated.");
                cont = false;
                break;
            }

            // if loop is not terminated, update items and continue to retrieve additional information about supplies
            else {
                items.add(entered);

                // retrieve and store available number of entered items
                System.out.print("How many " + items.getLast() + " do you have?: ");
                enteredCount = input.nextInt();
                itemCount.add(enteredCount);

                entered = "";

                // use do-while statement to run request for answer at least once
                do {
                    // retrieve and store whether user wants to keep this item private or add to group resources
                    System.out.print("Do you want to designate " + items.getLast() + " as a shareable item? [yes or no]: ");
                    entered = input.next();

                    /* if user enters no, store false
                        if user enters yes, store true
                        if user enters invalid option, prompt again
                     */

                    if(entered.equals("no")){
                        share.add(false);
                    }

                    else if(entered.equals("yes")){
                        share.add(true);
                    }

                    else {
                        System.out.println("Please enter a valid response");
                    }
                } while (!(entered.equals("no")) && !(entered.equals("yes")));
            }
            input.nextLine();
            System.out.println();
        }

        // update created object with entered information
        build1.setUser(user);
        build1.setItems(items);
        build1.setItemCount(itemCount);
        build1.setShare(share);

        // call on method from Inventor Builder class to print user's resources in a chart
        build1.displayUserItems();
    }
    
    public static void main(String [] args){
        pullUserResources();
    }
}


