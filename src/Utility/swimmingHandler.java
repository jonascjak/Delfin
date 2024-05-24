package Utility;
import Members.Competition;
import Members.CompetitionMember;
import Members.Member;
import Members.SwimmingDiscipline;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
                            Filehandler.writeMembersToFile(memberHandler.memberList);
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

    public static void addCompetition(){
        Member tempMember = memberHandler.searchMember();
        Competition tempCompetition = null;
            if(tempMember instanceof CompetitionMember){
                boolean done = false;
                while (!done) {
                    System.out.println("What was the name of the competition?");
                    String tempName = ScannerHandler.scanString();
                    ScannerHandler.clearScanner();
                    System.out.println("When was the competition? (YYYY-MM-DD)");
                    LocalDate tempDate = ScannerHandler.scanDate();
                    System.out.println("What was the placement of" + tempMember.getName());
                    int tempPlacement = ScannerHandler.scanInt(0, Integer.MAX_VALUE);
                    System.out.println("What was the lap time?");
                    double tempLap = ScannerHandler.scanDouble(Double.MIN_VALUE, Double.MAX_VALUE);
                    tempCompetition = new Competition(tempName, tempDate, tempPlacement, tempLap);
                    ((CompetitionMember) tempMember).setCompetitions(tempCompetition);
                    done = true;
                }
            }else {
                System.out.println();
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

    public static void showTop5BestSwimmers(){
        ArrayList<CompetitionMember> tempTeam = new ArrayList<>();
        do {
            System.out.println("Select a team to see the top 5 best swimmers for: ");
            System.out.println("Press 1 for the Junior Team");
            System.out.println("Press 2 for the Senior Team");
            System.out.println("Press 0 to go back");
            int answer = ScannerHandler.scanInt(0, 2);
            switch (answer) {
                case 1:
                    for (Member member : memberHandler.juniorTeam) {
                        if (member instanceof CompetitionMember) {
                            tempTeam.add((CompetitionMember) member);
                        }
                    }
                    break;
                case 2:
                    for (Member member : memberHandler.seniorTeam) {
                        if (member instanceof CompetitionMember) {
                            tempTeam.add((CompetitionMember) member);
                        }
                    }
                    break;
                case 0:
                    return;
            }
        }while (tempTeam.isEmpty());
        String disciplineName = null;
        do {
            System.out.println("Select a discipline to see the top 5 best swimmers for: ");
            System.out.println("Press 1 for butterfly");
            System.out.println("Press 2 for crawl");
            System.out.println("Press 3 for back crawl");
            System.out.println("Press 4 for breaststroke");
            System.out.println("Press 0 to go back");
            int choice = ScannerHandler.scanInt(0,4);
            switch (choice) {
                case 1:
                    disciplineName = "butterfly";
                    break;
                case 2:
                    disciplineName = "crawl";
                    break;
                case 3:
                    disciplineName = "back crawl";
                    break;
                case 4:
                    disciplineName = "breaststroke";
                    break;
                case 0:
                    return;
            }
        } while(disciplineName == null);
        printTop5MembersByDiscipline(tempTeam, disciplineName);
    }

    public static void printTop5MembersByDiscipline(ArrayList<CompetitionMember> team, String disciplineName) {
        ArrayList<CompetitionMember> membersForDiscipline = new ArrayList<>();
        for (CompetitionMember member : team) {
            for (SwimmingDiscipline discipline : member.getSwimmingDisciplines()) {
                if (discipline.getDiscipleName().equalsIgnoreCase(disciplineName)) {
                    membersForDiscipline.add(member);
                    break;
                }
            }
        }
        Collections.sort(membersForDiscipline, new Comparator<CompetitionMember>() {
            public int compare(CompetitionMember member1, CompetitionMember member2) {
                double bestTime1 = getBestTimeForDiscipline(member1, disciplineName);
                double bestTime2 = getBestTimeForDiscipline(member2, disciplineName);
                return Double.compare(bestTime1, bestTime2);
            }
        });

        System.out.println("Top 5 members for discipline: " + disciplineName);
        for (int i = 0; i < Math.min(5, membersForDiscipline.size()); i++) {
            CompetitionMember member = membersForDiscipline.get(i);
            double bestTime = getBestTimeForDiscipline(member, disciplineName);
            System.out.println((i + 1) + ". " + member.getName() + ", Best Time: " + bestTime);
        }
        System.out.println();
    }

    private static double getBestTimeForDiscipline(CompetitionMember member, String disciplineName) {
        for (SwimmingDiscipline discipline : member.getSwimmingDisciplines()) {
            if (discipline.getDiscipleName().equalsIgnoreCase(disciplineName)) {
                return discipline.getBestTime();
            }
        }
        return Double.MAX_VALUE;
    }
}
