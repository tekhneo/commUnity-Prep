import java.util.ArrayList; // arraylist required for unbounded lists

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

    // displays user's name and table with their resources and designations
    public void displayUserItems() {
        System.out.println(getUser() + "\n");
        System.out.printf("%10s %2s %10s %2s %10s%n", "item","|", "count", "|", "sharing");
        System.out.println("===============================================");
        for(int i = 0; i < getItems().size(); i++) {
            System.out.printf("%10s %2s %10s %2s %10s%n", getItems().get(i), "|", getItemCount().get(i), "|", getShare().get(i));
        }
    }
}
