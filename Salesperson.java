import java.util.ArrayList;

/**
 * Created by Benjamin Ehlers on 12/22/2018.
 */
public class Salesperson {
    private String name;
    private ArrayList<SalesItem> salesItems;
    public Salesperson(String name, ArrayList<SalesItem> salesItems) {
        this.name = name;
        this.salesItems = salesItems;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<SalesItem> getSalesItems() {
        return salesItems;
    }

    public void setSalesItems(ArrayList<SalesItem> salesItems) {
        this.salesItems = salesItems;
    }
}
