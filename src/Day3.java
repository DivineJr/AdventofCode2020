import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Day3 {
    
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(new File("input/Day3.txt"));

        ArrayList<Boolean[]> grid = new ArrayList<Boolean[]>();

        while(input.hasNextLine()) {
            String current = input.nextLine();
            Boolean[] line = new Boolean[31];
            for(int i = 0; i < current.length(); i++) {
                String character = current.substring(i, i+1);
                if(character.equals(".")) {
                    line[i] = false;
                } else {
                    line[i] = true;
                }
            }

            grid.add(line);
        }

        // Part1(grid);
        Part2(grid);

    }


    public static void Part1(ArrayList<Boolean[]> grid) {
        final int SLOPEX = 3;
        final int SLOPEY = 1;
        int posX = 0;   

        int treesHit = 0;
        for(int y = 0; y < grid.size(); y+= SLOPEY) {
            if(grid.get(y)[posX]) {
                treesHit++;
            }

            posX += SLOPEX;
            if(posX >= grid.get(y).length) {
                posX = posX % (grid.get(y).length);
            }
        }

        System.out.println(treesHit);

    }

    public static void Part2(ArrayList<Boolean[]> grid) {
        final int[] SLOPE_X = {1, 3, 5, 7, 1};
        final int[] SLOPE_Y = {1, 1, 1, 1, 2};
        int posX;

        int currentTreesHit;
        long totalTreesHit = 1;

        for(int run = 0; run < 5; run++) {
            posX = 0;
            currentTreesHit = 0;
            for(int y = 0; y < grid.size(); y+= SLOPE_Y[run]) {

                if(grid.get(y)[posX]) {
                    currentTreesHit++;
                }

                    posX += SLOPE_X[run];

                if(posX >= grid.get(y).length) {
                    posX = posX % (grid.get(y).length);
                }
            }
            System.out.println(run + " | " + currentTreesHit);
            totalTreesHit *= currentTreesHit;

        }


        System.out.println(totalTreesHit);
    }
}
