import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Benjamin Ehlers on 12/22/2018.
 */
public class SalesCommissions {

    public static void main(String[] args) {
        try {
            File input = new File("challenge.txt");
            Scanner fileScanner = new Scanner(input);
            ArrayList<Salesperson> salespersons = evaluateInput(fileScanner);
            fileScanner.close();
            generateOutput(salespersons);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static ArrayList<Salesperson> evaluateInput(Scanner fileScanner) {
        ArrayList<Salesperson> salespersons = new ArrayList<>();
        String title = fileScanner.nextLine();
        // REVENUE

        fileScanner.nextLine(); // skip blank space after Revenue
        String line = fileScanner.nextLine();
        //create salespersons from names
        Scanner lineScanner = new Scanner(line);
        while (lineScanner.hasNext()) {
            salespersons.add(new Salesperson(lineScanner.next(), new ArrayList<>()));
        }
        lineScanner.close();
        //go line by line, getting items and their revenue. we associate them with the salespersons
        //we do this until we hit the blank space before Expenses
        line = fileScanner.nextLine(); // first line with salesitems
        while (!line.equals("")) {
            lineScanner = new Scanner(line);
            //associate item revenue with salespersons
            String itemName = lineScanner.next();
            int i = 0; // column number or saleperson number
            while (lineScanner.hasNext()) {
                int itemRevenue = Integer.parseInt(lineScanner.next());
                SalesItem item = new SalesItem(itemName, itemRevenue, 0);
                salespersons.get(i++).getSalesItems().add(item);
            }
            lineScanner.close();
            line = fileScanner.nextLine();
        }
        // EXPENSES

        fileScanner.nextLine(); // skip Expenses
        fileScanner.nextLine(); // skip blank space after Expenses
        fileScanner.nextLine(); // skip line with names
        //we get the Expenses and associate them with the correct salespersons
        line = fileScanner.nextLine(); // first line with salesitems
        int j = 0; // line number or item number
        while (fileScanner.hasNext()) {
            lineScanner = new Scanner(line);
            //associate item expenses with salespersons
            String itemName = lineScanner.next();
            int i = 0; // column number or saleperson number
            while (lineScanner.hasNext()) {
                int itemExpenses = Integer.parseInt(lineScanner.next());
                salespersons.get(i++).getSalesItems().get(j).setExpenses(itemExpenses);
            }
            lineScanner.close();
            j++;
            line = fileScanner.nextLine();
        }
        return salespersons;
    }

    private static void generateOutput(ArrayList<Salesperson> salespersons) {
        // max name length to help with formatting
        int maxNameLength = 5;
        for(Salesperson salesperson : salespersons) {
            int currentNameLength = salesperson.getName().length();
            if(currentNameLength > maxNameLength)
                maxNameLength = currentNameLength;
        }

        //calculate commissions
        ArrayList<Double> commissions = new ArrayList<>();
        for(int i = 0; i < salespersons.size(); i++) {
            int currentRevenue = 0;
            int currentExpenses = 0;
            int numSalesItems = salespersons.get(i).getSalesItems().size();
            for(int j = 0; j < numSalesItems; j++) {
                currentRevenue+= salespersons.get(i).getSalesItems().get(j).getRevenue();
                currentExpenses+= salespersons.get(i).getSalesItems().get(j).getExpenses();
            }
            commissions.add(Math.max(0.00, 0.062 * (currentRevenue - currentExpenses)));
        }

        //and finally, the output
        //line one: names
        System.out.format("%13s", "");
        for(int i = 0; i < salespersons.size(); i++) {
            System.out.format("%" + (maxNameLength + 2) + "s", salespersons.get(i).getName());
        }
        System.out.println();
        //line two: commissions
        System.out.print("Commission   ");
//        System.out.format("%16s","");
        for(int i = 0; i < salespersons.size(); i++) {
            int relativeSpace = salespersons.get(i).getName().length() - commissions.get(i).toString().length();
            int lengthOfDouble = (Double.toString(Math.round(commissions.get(i) * 100.0) / 100.0)).length();
            int doubleDiff = 3;
            if(lengthOfDouble == 3) doubleDiff = 2;
            relativeSpace = maxNameLength - lengthOfDouble + (lengthOfDouble - doubleDiff);
            System.out.format("%" + relativeSpace + "s%.2f","",commissions.get(i));
        }
    }
}


