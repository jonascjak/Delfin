package Utility;
import Members.CompetitionMember;
import Members.Member;
import Members.SwimmingDiscipline;

import java.time.LocalDate;
import java.util.ArrayList;

public class swimmingHandler {

    public static ArrayList<SwimmingDiscipline> swimmingDisciplines() {
        ArrayList<SwimmingDiscipline> tempDisciplines = new ArrayList<>();
        boolean done = false;
        while(!done) {
            System.out.println("Which disciplines does the member partake in?");
            System.out.println("Press 1 for butterfly");
            System.out.println("Press 2 for crawl");
            System.out.println("Press 3 for back crawl");
            System.out.println("Press 4 for breaststroke");
            System.out.println("Press 0 if you are done registering disciplines");
            int answer = ScannerHandler.scanInt(0, 4);
            switch (answer) {
                case 1:
                    addDisciplineIfNotExists(tempDisciplines, "butterfly");
                    break;
                case 2:
                    addDisciplineIfNotExists(tempDisciplines,"crawl");
                    break;
                case 3:
                    addDisciplineIfNotExists(tempDisciplines, "back crawl");
                    break;
                case 4:
                    addDisciplineIfNotExists(tempDisciplines,"breaststroke");
                    break;
                case 0:
                    done = true;
                    break;
            }
        }
        if(tempDisciplines.isEmpty()){
            System.out.println("No disciplines were registered. Please register at least one discipline");
            return swimmingDisciplines();
        }
        return tempDisciplines;
    }

    public static void newTime(){
        Member tempMember = memberHandler.searchMember();
        if(tempMember instanceof CompetitionMember){
            boolean done = false;
            do {
                System.out.println("Which discipline do you wish to enter a new time for?");
                String disciplineName = ScannerHandler.scanString();
                for(SwimmingDiscipline discipline : ((CompetitionMember) tempMember).getSwimmingDisciplines()){
                    if(discipline.getDiscipleName().equalsIgnoreCase(disciplineName)) {
                        System.out.println("Enter the new time:");
                        double newTime = ScannerHandler.scanDouble(Double.MIN_VALUE,Double.MAX_VALUE);
                        if(!discipline.isBestTime(discipline, newTime)){
                            System.out.println("The new time isn't the best");
                            done = true;
                            break;
                        } else{
                            ScannerHandler.clearScanner();
                            discipline.setBestTime(newTime);
                            System.out.println("Which date was the time sat? (YYYY-MM-DD)");
                            LocalDate bestDate = ScannerHandler.scanDate();
                            discipline.setBestDate(bestDate);
                            System.out.println("The new best time of "+newTime+" has been registered for the "+bestDate);
                            done = true;
                            break;
                        }
                    } else{
                        System.out.println("The member isn't part of that discipline");
                    }
                }
            }while (!done);
        } else {
            System.out.println("That member isn't part of any swimming team");
        }
    }
    private static void addDisciplineIfNotExists(ArrayList<SwimmingDiscipline> disciplines, String disciplineName) {
        if (disciplineName == null) {
            return;
        }
        for (SwimmingDiscipline discipline : disciplines) {
            if (discipline.getDiscipleName().equalsIgnoreCase(disciplineName)) {
                return;
            }
        }

        disciplines.add(new SwimmingDiscipline(disciplineName, 0, null));
    }
}
