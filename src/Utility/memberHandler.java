package Utility;
import Members.CompetitionMember;
import Members.Member;

import java.time.LocalDate;
import java.util.ArrayList;
public class memberHandler{
    public static ArrayList<Member> memberList = Filehandler.readMembersFromFile();
    public static ArrayList<Member> juniorTeam = Filehandler.readMembersFromFile();
    public static ArrayList<Member> seniorTeam = Filehandler.readMembersFromFile();

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
            System.out.println("Is the member active? \nPress 1 for yes\nPress 0 for no");
            boolean isActive = true;
            int answer = ScannerHandler.scanInt(0,1);
            if(answer == 1) {
                isActive = true;
            } else if (answer == 0) {
                isActive = false;
            }
            System.out.println("is the member part of swimming a swimming team? \nPress 1 for yes\nPress 0 for no");
            int isSwimmer = ScannerHandler.scanInt(0,1);
            if(isSwimmer == 0){
                tempMember = new Member(tempID,tempName, tempBirthdate, tempGender, isActive);
                System.out.println("The following member has been added to the club: " + tempMember);
            } else{
                tempMember = new CompetitionMember(tempID,tempName, tempBirthdate, tempGender, isActive, null, null);
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
            System.out.println("Which member do you wanna edit from the club?\nPress 1 to search by name\nPress 2 to search by id\nPress 0 to go back");
            switch (answer) {
                case 1:
                    System.out.println("What is the name of the member?");
                    String tempName = ScannerHandler.scanString();
                    tempMember = searchName(tempName);
                    break;
                case 2:
                    System.out.println("What is the ID of the member?");
                    int tempID = ScannerHandler.scanInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
                    tempMember = searchID(tempID);
                    break;
                case 0:
                    return;
            }
        }while (tempMember == null);
        while (true) {
            System.out.println("Which part of the member do you want to edit?");
            System.out.println("1. Name");
            System.out.println("2. Birthdate");
            System.out.println("3. Sex");
            System.out.println("4. Activity Status");
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
                    String active;
                    boolean isActive = true;
                    do {
                        System.out.println("Enter active status (active/inactive):");
                        active = ScannerHandler.scanString();
                        if(active.equalsIgnoreCase("active")) {
                            isActive = true;
                        } else if (active.equalsIgnoreCase("inactive")) {
                            isActive = false;
                        } else {
                            active = null;
                            System.out.println("Invalid input. Please enter 'active' or 'inactive'");
                        }
                    } while (active == null);
                    tempMember.setActive(isActive);
                    break;
                case 0:
                    return;
            }
            System.out.println("Member updated successfully!");
        }
    }
    public static void deleteMember(){
        boolean memberDeleted = false;
        int choice = ScannerHandler.scanInt(0,2);
        do {
            Member tempMember = null;
            do{
                System.out.println("Which member do you wanna remove from the club?\nPress 1 to search by name\nPress 2 to search by id\nPress 0 to go back");
                switch (choice) {
                    case 1:
                        System.out.println("What is the name of the member?");
                        String tempName = ScannerHandler.scanString();
                        tempMember = searchName(tempName);
                        break;
                    case 2:
                        System.out.println("What is the ID of the member?");
                        int tempID = ScannerHandler.scanInt(Integer.MIN_VALUE,Integer.MAX_VALUE);
                        tempMember = searchID(tempID);
                        break;
                    case 0:
                        return;
                }
            } while (tempMember == null);
            System.out.println("The following member has been removed from the club: "+tempMember);
            memberList.remove(tempMember);
            Filehandler.writeMembersToFile(memberList);
            memberDeleted = true;
        }while (!memberDeleted);
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
        System.out.println("Sorry no member with that name was found");
        return null;
    }
}
