import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;


public class Day5 {
    
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(new File("input/Day5.txt"));
        ArrayList<ArrayList<Boolean>> passes = new ArrayList<ArrayList<Boolean>>();

        while(input.hasNextLine()) {
            //F = False, B = True
            //R = True, L = False
            ArrayList<Boolean> toAdd = new ArrayList<Boolean>();


            String currentLine = input.nextLine();
            String updown = currentLine.substring(0, 7);
            String leftright = currentLine.substring(7, currentLine.length());

            for(int i = 0; i < updown.length(); i++) {
                if(updown.substring(i, i+1).equals("F")) {
                    toAdd.add(false);
                } else {
                    toAdd.add(true);
                }
            }

            for(int i = 0; i < leftright.length(); i++) {
                if(leftright.substring(i, i+1).equals("R")) {
                    toAdd.add(true);
                } else {
                    toAdd.add(false);
                }
            }

            passes.add(toAdd);


        }

        
        // Part1(passes);
        Part2(passes);
    }

    public static void Part1(ArrayList<ArrayList<Boolean>> passes) {
        int highestID = -1;
        for(int i = 0; i < passes.size(); i++) {
            ArrayList<Boolean> current = passes.get(i);
            int rowMin = 0;
            int rowMax = 127;
            int columnMin = 0;
            int columnMax = 7;
            for(int j = 0; j < 7; j++) { //0-6 is front to back
                if(current.get(j)) {
                    rowMin += (rowMax - rowMin) / 2 + 1;
                } else {
                    if(rowMax-rowMin == 1) {
                        rowMax = rowMin;
                    } else {
                        rowMax -= (rowMax - rowMin) / 2 + 1;
                    }
                }
            }

            for(int j = 7; j < 10; j++) {
                if(current.get(j)) {
                    columnMin += (columnMax - columnMin) / 2 + 1;
                } else {
                    if(columnMax-columnMin == 1) {
                        columnMax = columnMin;
                    } else {
                        columnMax -= (columnMax - columnMin) / 2 + 1;
                    }
                }
            }

            if(idCalculator(rowMax, columnMax) > highestID) {
                highestID = idCalculator(rowMax, columnMax);
            }
        }

        System.out.println(highestID);
    }

    public static void Part2(ArrayList<ArrayList<Boolean>> passes) {
        ArrayList<Integer> ids = new ArrayList<Integer>();

        for(int i = 0; i < passes.size(); i++) {
            ArrayList<Boolean> current = passes.get(i);
            int rowMin = 0;
            int rowMax = 127;
            int columnMin = 0;
            int columnMax = 7;
            for(int j = 0; j < 7; j++) { //0-6 is front to back
                if(current.get(j)) {
                    rowMin += (rowMax - rowMin) / 2 + 1;
                } else {
                    if(rowMax-rowMin == 1) {
                        rowMax = rowMin;
                    } else {
                        rowMax -= (rowMax - rowMin) / 2 + 1;
                    }
                }
            }

            for(int j = 7; j < 10; j++) {
                if(current.get(j)) {
                    columnMin += (columnMax - columnMin) / 2 + 1;
                } else {
                    if(columnMax-columnMin == 1) {
                        columnMax = columnMin;
                    } else {
                        columnMax -= (columnMax - columnMin) / 2 + 1;
                    }
                }
            }

            ids.add(idCalculator(rowMax, columnMax));
        }

        for(int i = ids.size(); i >= 0; i--) {
            for(int j = 0; j < i-1; j++) {
                if(ids.get(j) > ids.get(j+1)) {
                    int placeholder = ids.get(j);
                    ids.set(j, ids.get(j+1));
                    ids.set(j+1, placeholder);
                }
            }
        }

        for(int i = 0; i < ids.size() - 1; i++) {
            if(ids.get(i+1) - ids.get(i) == 2) {
                System.out.println(ids.get(i) + 1);
            }
        }
    }

    public static int idCalculator(int row, int column) {
        return (row * 8) + column;
    }
}
