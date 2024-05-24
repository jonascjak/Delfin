import Utility.ScannerHandler;
import Utility.memberHandler;
import Utility.swimmingHandler;

public class UI {
    public static void loginMenu(){
        System.out.println();
        System.out.println("Welcome to swimming club delfin");
        System.out.println("press 1 for Chairperson");
        System.out.println("press 2 for Cashier");
        System.out.println("press 3 for Coach");
        System.out.println("press 0 to Exit");
        int choice = ScannerHandler.scanInt(0,3);
        do {
            switch (choice) {
                case 1:
                    chairpersonMenu();
                    break;
                case 2:
                    cashierMenu();
                    break;
                case 3:
                    coachMenu();
                    break;
            }
        }while (choice != 0);
        System.out.println("Exiting the program.");
        System.exit(0);
    }

    public static void chairpersonMenu(){
        System.out.println();
        System.out.println("Welcome chairperson, what would you like to do?");
        System.out.println("press 1 to add new member");
        System.out.println("press 2 to edit a member");
        System.out.println("press 3 to delete a member");
        System.out.println("Press 4 to see all members");
        System.out.println("press 0 to go back");
        int choice = ScannerHandler.scanInt(0,4);
        switch (choice){
            case 1:
                memberHandler.addMember();
                break;
            case 2:
                memberHandler.editMember();
                break;
            case 3:
                memberHandler.deleteMember();
                break;
            case 4:
                memberHandler.allMembers();
                break;
            case 0:
                loginMenu();
                break;
        }
    }

    public static void cashierMenu(){
        System.out.println("Welcome cashier, what would you like to do?");
        System.out.println("press 1 to see price for a specific member");
        System.out.println("press 2 to see expected revenue");
        System.out.println("press 3 to see members in debt");
        System.out.println("press 0 to go back");
        int choice = ScannerHandler.scanInt(0,4);
        switch (choice){
            case 1:
                System.out.println("The specific price for the member is: " + memberHandler.specificPrice()+"kr");
                break;
            case 2:
                System.out.println("The expected revenue for this year is: " + memberHandler.revenue()+"kr");
                break;
            case 3:
                System.out.println("The members in debt are: ");
                memberHandler.allDebt();
                break;
            case 0:
                loginMenu();
                break;
        }
    }

    public static void coachMenu(){
        System.out.println("Welcome coach, what would you like to do?");
        System.out.println("press 1 to see all Members on a team ");
        System.out.println("press 2 to register a new time for a member");
        System.out.println("press 3 to register a new competition for a member");
        System.out.println("Press 4 to ");
        System.out.println("press 0 to go back");
        int choice = ScannerHandler.scanInt(0,4);
        switch (choice){
            case 1:
                System.out.println("Press 1 to see all members on the junior team\nPress 2 to see all members on the senior team");
                int answer = ScannerHandler.scanInt(1,2);
                if (answer == 1){
                    memberHandler.printTeam(memberHandler.juniorTeam);
                } else if (answer == 2) {
                    memberHandler.printTeam(memberHandler.seniorTeam);
                }
                break;
            case 2:
                swimmingHandler.newTime();
                break;
            case 3:
                swimmingHandler.addCompetition();
                break;
            case 4:
                break;
            case 0:
                loginMenu();
                break;
        }
    }
}

