import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(new File("input/Day1.txt"));

        ArrayList<Integer> expenses = new ArrayList<Integer>();
        
        while(input.hasNext()) {
            expenses.add(Integer.parseInt(input.nextLine()));
        }

        // partOne(expenses);
        partTwo(expenses);
    }
        
    private static void partOne(ArrayList<Integer> expenses) {
        for(int i = 0; i < expenses.size() - 2; i++) {
            for(int j = i + 1; j < expenses.size() - 1; j++) {
                if(expenses.get(i) + expenses.get(j) == 2020) {
                    System.out.println(expenses.get(i) + " + " + expenses.get(j) + " | " + expenses.get(i) * expenses.get(j));
                    break;
                }
            }
        }
    }
    
    private static void partTwo(ArrayList<Integer> expenses) {
        for(int i = 0; i < expenses.size() - 3; i++) {
            for(int j = i + 1; j < expenses.size() - 2; j++) {
                for(int k = j + 1; k < expenses.size() - 1; k++) {
                    if(expenses.get(i) + expenses.get(j)  + expenses.get(k) == 2020) {
                        System.out.println(expenses.get(i) + " + " + expenses.get(j) + " + " +  expenses.get(k) + " | " + expenses.get(i) * expenses.get(j) * expenses.get(k));
                        break;
                    }
                }
            }
        }
    }
}
