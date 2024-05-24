package Utility;
import Members.CompetitionMember;
import Members.Member;
import Members.SwimmingDiscipline;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
public class memberHandler{
    public static ArrayList<Member> memberList = Filehandler.readMembersFromFile();
    public static ArrayList<Member> juniorTeam = Filehandler.readMembersFromFile();
    public static ArrayList<Member> seniorTeam = Filehandler.readMembersFromFile();

    public double calculateMinimumKontingent() {
        return 100.0;
    }
    public static void addMember(){
        Member tempMember = null;
        do {
            int tempID = generateUniqueId();
            System.out.println("Enter the member's full name");
            String tempName = ScannerHandler.scanString();
            System.out.println("Enter the members birthday  in the format YYYY-MM-DD");
            LocalDate tempBirthdate = ScannerHandler.scanDate();
            String gender;
            boolean tempGender = true;
            do {
                System.out.println("Is the member male or female?");
                gender = ScannerHandler.scanString();
                if(gender.equalsIgnoreCase("male")) {
                    tempGender = true;
                } else if (gender.equalsIgnoreCase("female")) {
                    tempGender = false;
                } else {
                    gender = null;
                    System.out.println("Invalid input. Please enter 'male' or 'female'");
                }
            } while (gender == null);
            System.out.println("Is the member in debt? \nPress 1 for yes\nPress 0 for no");
            boolean inDebt = true;
            int answer = ScannerHandler.scanInt(0,1);
            if(answer == 1) {
                inDebt = true;
            } else if (answer == 0) {
                inDebt = false;
            }
            System.out.println("is the member part of a swimming team? \nPress 1 for yes\nPress 0 for no");
            int isSwimmer = ScannerHandler.scanInt(0,1);
            if(isSwimmer == 0){
                tempMember = new Member(tempID,tempName, tempBirthdate, tempGender, inDebt);
                System.out.println("The following member has been added to the club: " + tempMember);
            } else{
                ArrayList<SwimmingDiscipline> tempDisciplines = swimmingHandler.swimmingDisciplines();
                tempMember = new CompetitionMember(tempID,tempName, tempBirthdate, tempGender, inDebt, null, tempDisciplines);
                if(tempMember.isJunior){
                    juniorTeam.add(tempMember);
                } else{
                    seniorTeam.add(tempMember);
                }
                System.out.println("The following member has been added to the club: " + tempMember);
            }
            memberList.add(tempMember);
            Filehandler.writeMembersToFile(memberList);
        }while (tempMember == null);
    }

    public static void editMember(){
        Member tempMember = null;
        int answer = ScannerHandler.scanInt(0,2);
        do{
          searchMember();
        }while (tempMember == null);
        while (true) {
            System.out.println("Which part of the member do you want to edit?");
            System.out.println("1. Name");
            System.out.println("2. Birthdate");
            System.out.println("3. Sex");
            System.out.println("4. Debt Status");
            System.out.println("0. Exit");
            int choice = ScannerHandler.scanInt(0, 4);
            switch (choice) {
                case 1:
                    System.out.println("Enter new name:");
                    String newName = ScannerHandler.scanString();
                    tempMember.setName(newName);
                    break;
                case 2:
                    System.out.println("Enter new birthdate (YYYY-MM-DD):");
                    LocalDate newBirthdate = ScannerHandler.scanDate();
                    tempMember.setBirthdate(newBirthdate);
                    break;
                case 3:
                    String gender;
                    boolean tempGender = true;
                    do {
                        System.out.println("Enter sex (male/female):");
                        gender = ScannerHandler.scanString();
                        if(gender.equalsIgnoreCase("male")) {
                            tempGender = true;
                        } else if (gender.equalsIgnoreCase("female")) {
                            tempGender = false;
                        } else {
                            gender = null;
                            System.out.println("Invalid input. Please enter 'male' or 'female'");
                        }
                    } while (gender == null);
                    tempMember.setMale(tempGender);
                    break;
                case 4:
                    boolean inDebt = true;
                        System.out.println("Is the member in debt? \nPress 1 for yes\nPress 0 for no");
                        int debt = ScannerHandler.scanInt(0,1);
                        if(debt == 1) {
                            inDebt = true;
                        } else if (debt == 0) {
                            inDebt = false;
                        }
                    tempMember.setInDebt(inDebt);
                    break;
                case 0:
                    return;
            }
            System.out.println("Member updated successfully!");
            Filehandler.writeMembersToFile(memberList);
        }
    }
    public static void deleteMember(){
        boolean memberDeleted = false;
        int choice = ScannerHandler.scanInt(0,2);
        do {
            Member tempMember = null;
            do{
               tempMember = searchMember();
            } while (tempMember == null);
            System.out.println("The following member has been removed from the club: "+tempMember);
            memberList.remove(tempMember);
            Filehandler.writeMembersToFile(memberList);
            memberDeleted = true;
        }while (!memberDeleted);
    }

    public static void allMembers() {
        for(Member member : memberList){
            System.out.println(member);
        }
    }

    private static int generateUniqueId() {
        int newId;
        do {
            newId = (int) (Math.random() * Integer.MAX_VALUE);
        } while (!isUniqueId(newId));
        return newId;
    }

    private static boolean isUniqueId(int id) {
        for (Member member : memberList) {
            if (member.getMemberID() == id) {
                return false;
            }
        }
        return true;
    }
    public static Member searchMember(){
            System.out.println("Which member from the club?\nPress 1 to search by name\nPress 2 to search by id\nPress 0 to go back");
            int answer = ScannerHandler.scanInt(0,2);
            switch (answer) {
                case 1:
                    System.out.println("What is the name of the member?");
                    String tempName = ScannerHandler.scanString();
                    return searchName(tempName);
                case 2:
                    System.out.println("What is the ID of the member?");
                    int tempID = ScannerHandler.scanInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
                    return searchID(tempID);
                case 0:
                    return null;
                default:
                    System.out.println("Invalid input.");
                    return null;
            }
    }
    public static Member searchName(String memberName){
        for (Member member : memberList) {
            if (member.getName().equalsIgnoreCase(memberName)) {
                return member;
            }
        }
        System.out.println("Sorry no member with that name was found");
        return null;
    }

    public static Member searchID(int memberID){
        for (Member member : memberList) {
            if (member.getMemberID() == memberID) {
                return member;
            }
        }
        System.out.println("Sorry no member with that ID was found");
        return null;
    }
    public static int specificPrice(){
        Member tempMember = searchMember();
        return tempMember.calculatePrice();
    }

    public static int revenue(){
        int revenue = 0;
        for (Member member : memberList){
            revenue += member.calculatePrice();
        }
        return revenue;
    }
    public static void allDebt(){
        int totalDebt = 0;
        for(Member member : memberList){
            if(member.isInDebt()){
                System.out.println(member);
                totalDebt += member.calculatePrice();
            }
        }
        System.out.println("The total of all debt is: "+totalDebt);
    }

    public static void printTeam(ArrayList<Member> team){
        for(Member member : team){
            System.out.println(member);
        }
    }
}
