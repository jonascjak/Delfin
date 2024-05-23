package Utility;

import Members.Member;

import java.time.LocalDate;

public class memberHandler {
    public static void addMember(){
        Member tempMember = null;
        do {
            System.out.println("Enter the member's full name");
            String tempName = ScannerHandler.scanString();
            System.out.println("Enter the members birthday  in the format YYYY-MM-DD");
            LocalDate tempBirthdate = ScannerHandler.scanDate();
            String gender;
            boolean isMale = true;
            do {
                System.out.println("Is the member male or female?");
                gender = ScannerHandler.scanString();
                if(gender.equalsIgnoreCase("male")) {
                    isMale = true;
                } else if (gender.equalsIgnoreCase("female")) {
                    isMale = false;
                } else {
                    gender = null;
                    System.out.println("Invalid input. Please enter 'male' or 'female'");
                }
            } while (gender == null);
            boolean isActive = true;
            int answer;
            System.out.println("Is the member active? \nPress 1 for yes\npress 0 for no");
            answer = ScannerHandler.scanInt(0,1);
            if(answer == 1) {
                isActive = true;
            } else if (answer == 0) {
                isActive = false;
            }
            tempMember = new Member(0,tempName, tempBirthdate, isMale, isActive);
        }while (tempMember == null);
    }

    public static void editMember(){

    }
    public static void deleteMember(){

    }
}
