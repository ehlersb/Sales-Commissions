/**
 * Created by Benjamin Ehlers on 12/22/2018.
 */
public class SalesItem {
    private String name;
    private int revenue;
    private int expenses;
    public SalesItem(String name, int revenue, int expenses) {
        this.name = name;
        this.revenue = revenue;
        this.expenses = expenses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getExpenses() {
        return expenses;
    }

    public void setExpenses(int expenses) {
        this.expenses = expenses;
    }
}
