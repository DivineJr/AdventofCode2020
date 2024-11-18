import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.io.File;


public class Day4 {
    
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(new File("input/Day4.txt"));
        ArrayList<Passport> passports = new ArrayList<Passport>();
        
        while(input.hasNext()) {
            String current = "";
            String fromInput = input.nextLine();

            while(!fromInput.equals("")) {
                current += fromInput + " ";
                fromInput = input.nextLine();
            }

            passports.add(new Passport(current));

        }

        // Part1(passports);
        Part2(passports);

    }


    public static void Part1(ArrayList<Passport> passports) {
        int validPassports = 0;
        for(int i = 0; i < passports.size(); i++) {
            if(passports.get(i).validatePart1()) {
                validPassports++;
            }
        }

        System.out.println(validPassports);
    }

    public static void Part2(ArrayList<Passport> passports) {
        int validPassports = 0;
        for(int i = 0; i < passports.size(); i++) {
            if(passports.get(i).validatePart2()) {
                validPassports++;
            }
        }

        System.out.println(validPassports);
    }
}



class Passport {
    final static Map<String,Integer> KEY_VALUE = Map.of("byr", 0, "iyr", 1, "eyr", 2, "hgt", 3, "hcl", 4, "ecl", 5, "pid", 6, "cid", 7);
    final static Map<String,Integer> HCL_CHECK_NUM = Map.of("0",1,"1",1,"2",1,"3",1,"4",1,"5",1,"6",1,"7",1,"8",1,"9",1);
    final static Map<String,Integer> HCL_CHECK_LET = Map.of("a",1,"b",1,"c",1,"d",1,"e",1,"f",1);
    final static Map<String, Integer> ECL_CHECK = Map.of("amb", 1, "blu", 1, "brn", 1, "gry", 1, "grn", 1, "hzl", 1, "oth", 1);
    private String byr = ""; //Birth Year
    private String iyr = ""; //Issue Year
    private String eyr = ""; //Expiration Year
    private String hgt = ""; //Height
    private String hcl = ""; //Hair Color
    private String ecl = ""; //Eye Color
    private String pid = ""; //Passport ID
    private String cid = ""; //Country ID

    public Passport(String text) {
        ArrayList<String> pairs = new ArrayList<String>();

        while(text.indexOf(" ") != -1) {
            pairs.add(text.substring(0, text.indexOf(" ")));
            text = text.substring(text.indexOf(" ") + 1, text.length());
        }

        for(int i = 0; i < pairs.size(); i++) {
            String current = pairs.get(i);
            
            switch(KEY_VALUE.get(current.substring(0, 3))) {
            case 0: //Birth Year
                byr = current.substring(4, current.length());
                break;
            case 1: //Issue Year
                iyr = current.substring(4, current.length());
                break;
            case 2: //Expiration Year
                eyr = current.substring(4, current.length());
                break;
            case 3: //Height
                hgt = current.substring(4, current.length());
                break;
            case 4: //Hair Color
                hcl = current.substring(4, current.length());
                break;
            case 5: //Eye Color
                ecl = current.substring(4, current.length());
                break;
            case 6: //Passport ID
                pid = current.substring(4, current.length());
                break;
            case 7: //Country ID
                cid = current.substring(4, current.length());
                break;
            default:
                System.out.println("Aw shit it fuckin' broke dude");
                break;
            }
        }
    }

    public boolean validatePart1() {
        if(byr.equals("")) {
            return false;
        }
        if(iyr.equals("")) {
            return false;
        }
        if(eyr.equals("")) {
            return false;
        }
        if(hgt.equals("")) {
            return false;
        }
        if(hcl.equals("")) {
            return false;
        }
        if(ecl.equals("")) {
            return false;
        }
        if(pid.equals("")) {
            return false;
        }

        return true;
    }

    public boolean validatePart2() {
        if(!validatePart1()) { //Part 2 adds conditions on top of part 1, so we'll just go ahead and use part 1 instead of copy and pasting the code
            return false;
        }
        try {
        int pbyr = Integer.parseInt(byr); //Convert to number to be interpreted later
        int piyr = Integer.parseInt(iyr); 
        int peyr = Integer.parseInt(eyr);
        
        boolean metOrImp; // Metric = False, Imperial = True

        if(hgt.endsWith("in")) { //Check to see if it's imperial
            metOrImp = true;
        } else if(hgt.endsWith("cm")) { //Check to see if it's Metric
            metOrImp = false;
        } else {
            return false; // t had no unit suffix, invalid data
        }

        //Convert to number to be interpreted later
        int phgt = metOrImp ? Integer.parseInt(hgt.substring(0, hgt.indexOf("in"))) : Integer.parseInt(hgt.substring(0, hgt.indexOf("cm")));

        //Hair color is complicated
        if(hcl.length() != 7) { //Hair color is a hex code in the style #------, meaning it's 7 characters long
            return false;       //If not 7 characters, must be invalid
        } else {
            for(int i = 0; i < 7; i++) { //Go through every character to make sure it makes up a valid hexcode
                String currentChar = hcl.substring(i, i+1);
                if(i == 0) {
                    if(!currentChar.equals("#")) { //If the first character is not a #, it's invalid
                        return false;
                    }
                } else { //Check to see if the other characters make up a valid color
                    if(HCL_CHECK_LET.get(currentChar) == null && HCL_CHECK_NUM.get(currentChar) == null) { //If it's not in either, must be invalid
                        return false;
                    }
                }
            }
        }

        if(ECL_CHECK.get(ecl) == null) { //If it's not in the map, it's invalid
            return false;
        }

        if(pid.length() != 9) { //PID must be 9 characters long
            return false;
        }

        int ppid = Integer.parseInt(pid); //If it's able to be parsed and doesn't crash this section, it must be valid lmao


        


        if(pbyr < 1920 || pbyr > 2002) { //If it's within 1920 - 2002, it's valid
            return false;
        }

        if(piyr < 2010 || piyr > 2020) { //2010-2020
            return false;
        }
        if(peyr < 2020 || peyr > 2030) {
            return false;
        }
        if(metOrImp) { //Imperial
            if(phgt < 59 || phgt > 76) {
                return false;
            }
        } else { //Metric
            if(phgt < 150 || phgt > 193) {
                return false;
            }
        }
    } catch(Exception e) { //If this section crashes for any reason what so ever, likelihood is one of the fields was invalid, so meh
        return false;
    }
        return true;
    }
}