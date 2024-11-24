import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.io.File;

public class Day6 {

    public static void main(String[] args) throws Exception {
        // Clearly I didn't think far enough for Part 1, so I moved *all* the code there instead of having basic input stuff up here
        // Didn't work how I wanted it to, sad.
        

        // Part1();
        Part2();
        
    }

    public static void Part1() throws Exception {
        Scanner input = new Scanner(new File("input/Day6.txt"));
        ArrayList<String> inputs = new ArrayList<String>();
        ArrayList<HashSet<String>> list = new ArrayList<HashSet<String>>();

        while(input.hasNextLine()) {
            String fullLine = "";
            String current = input.nextLine();

            while(!current.equals("")) {
                fullLine += current;
                current = input.nextLine();
            }

            inputs.add(fullLine);
        }



        for(int i = 0; i < inputs.size(); i++) {
            String current = inputs.get(i);
            HashSet<String> currentSet = new HashSet<String>();
            for(int j = 0; j < current.length(); j++) {
                currentSet.add(current.substring(j, j+1));
            }

            list.add(currentSet);
        }

        int total = 0;
        for(int i = 0; i < list.size(); i++) {
            total += list.get(i).size();
        }

        System.out.println("Total: " + total);


    }


    public static void Part2() throws Exception {
        Scanner input = new Scanner(new File("input/Day6.txt"));
        ArrayList<ArrayList<String>> inputs = new ArrayList<ArrayList<String>>();

        while(input.hasNextLine()) {
            ArrayList<String> currentGroup = new ArrayList<String>();
            String currentLine = input.nextLine();

            while(!currentLine.equals("")) {
                currentGroup.add(currentLine);
                currentLine = input.nextLine();
            }

            inputs.add(currentGroup);
        }
        
        int total = 0;

        for(int i = 0; i < inputs.size(); i++) {
            ArrayList<String> currentGroup = inputs.get(i);
            if(currentGroup.size() == 1) {
                total += currentGroup.get(0).length();
            } else {
                String checkingString = currentGroup.get(0);
                for(int j = 0; j < checkingString.length(); j++) {
                    boolean flag = false;
                    String currentCharacter = checkingString.substring(j, j+1);
                    for(int k = 0; k < currentGroup.size(); k++) {
                        if(currentGroup.get(k).indexOf(currentCharacter) == -1) {
                            flag = true;
                            k = currentGroup.size();
                        }
                    }

                    if(!flag) {
                        total++;
                    }
                }
            }
        }

        System.out.println(total);
    }
}
