import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Day2 {
    //Minimum Appearances - Maximum Appearances (Letter to Appear): Password tobe checked

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(new File("input/Day2.txt"));

        ArrayList<Inputs> list = new ArrayList<Inputs>();
        
        while(input.hasNextLine()) {
            list.add(new Inputs(input.nextLine()));
        }

        // Part1(list);
        Part2(list);

    }

    public static void Part1(ArrayList<Inputs> list) {

        int totalValid = 0;
        for(int i = 0; i < list.size(); i++) {
            Inputs current = list.get(i);
            String pass = current.getPassword();
            String character = current.getChar();
            int max = current.getMax();
            int min = current.getMin();
            int numberOfOccurances = 0;
            while(pass.indexOf(character) != -1) {
                pass = pass.substring(pass.indexOf(character)+1, pass.length());
                numberOfOccurances++;
            }

            if(numberOfOccurances <= max && numberOfOccurances >= min) {
                totalValid++;
            }

        }

        System.out.println(totalValid);
    }

    public static void Part2(ArrayList<Inputs> list) {
        int totalValid = 0;
        for(int i = 0; i < list.size(); i++) {
            Inputs current = list.get(i);
            String pass = current.getPassword();
            String character = current.getChar();
            int firstPlace = current.getMax();
            int secondPlace = current.getMin();
            //The character must show up at exactly 1 of the places
            //It's in valid if it shows up in none or in both.

            if((pass.substring(firstPlace - 1, firstPlace).equals(character) || pass.substring(secondPlace - 1, secondPlace).equals(character)) && !(pass.substring(firstPlace - 1, firstPlace).equals(character) && pass.substring(secondPlace - 1, secondPlace).equals(character))) {
                totalValid++; 
            }


        }

        System.out.println(totalValid);

    }
    
}

class Inputs {
    private int maxAppearances;
    private int minAppearances;
    private String characterToBeCheckedFor;
    private String passwordToBeChecked;

    public Inputs(String lineFromFile) {
        int dash = lineFromFile.indexOf("-");
        int firstSpace = lineFromFile.indexOf(" ");
        int colon = lineFromFile.indexOf(":");

        minAppearances = Integer.parseInt(lineFromFile.substring(0, dash));
        maxAppearances = Integer.parseInt(lineFromFile.substring(dash+1, firstSpace));
        characterToBeCheckedFor = lineFromFile.substring(firstSpace + 1, firstSpace + 2);
        passwordToBeChecked = lineFromFile.substring(colon + 2, lineFromFile.length());
    }

    public int getMin() {
        return minAppearances;
    }

    public int getMax() {
        return maxAppearances;
    }

    public String getChar() {
        return characterToBeCheckedFor;
    }

    public String getPassword() {
        return passwordToBeChecked;
    }
}
