import Members.Member;
import Utility.ScannerHandler;
import Utility.memberHandler;

import java.time.LocalDate;

public class UI {
    public static void loginMenu(){
        System.out.println("Welcome to swimming club delfin");
        System.out.println("press 1 for Chairperson");
        System.out.println("press 2 for Cashier");
        System.out.println("press 3 for Coach");
        System.out.println("press 0 to Exit");
        System.out.print("Enter your choice: ");
        int choice = ScannerHandler.scanInt(0,3);
        switch (choice) {
            case 1:
                chairpersonMenu();
                break;
            case 2:
                System.out.println("2");
                break;
            case 3:
                System.out.println(3);
                break;
            case 0:
                System.out.println("Exiting the program.");
                System.exit(0);
                break;
        }
    }

    public static void chairpersonMenu(){
        System.out.println("Welcome chairperson, what would you like to do?");
        System.out.println("press 1 to add new member");
        System.out.println("press 2 to edit a member");
        System.out.println("press 3 to delete a member");
        System.out.println("press 0 to go back");
        int choice = ScannerHandler.scanInt(0,3);
        switch (choice){
            case 1:
                memberHandler.addMember();
                break;
            case 2:
                memberHandler.editMember();
            case 3:
                memberHandler.deleteMember();
            case 0:
                loginMenu();
                break;
        }
    }
}

